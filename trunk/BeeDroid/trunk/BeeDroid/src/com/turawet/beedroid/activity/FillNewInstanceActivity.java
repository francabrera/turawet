/**
 * 
 */
package com.turawet.beedroid.activity;

import java.io.InputStream;

import com.turawet.beedroid.R;
import com.turawet.beedroid.constants.Cte;
import com.turawet.beedroid.database.DataBaseManager;
import com.turawet.beedroid.parser.XmlToViewsParser;
import com.turawet.beedroid.view.EfficientViewFlipper;
import com.turawet.beedroid.wsclient.beans.FormIdentificationBean;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.ViewFlipper;

/**
 * @author nicopernas
 * 
 */
public class FillNewInstanceActivity extends Activity // implements
// OnGestureListener
{
	/**
	 *
	 */
	private ViewFlipper		flipper;
	private final String		TAG	= getClass().getName();
	
	private Animation			inAnimationLeft;
	private Animation			outAnimationLeft;
	private Animation			inAnimationRight;
	private Animation			outAnimationRight;
	
	private GestureDetector	detector;
	private float				oldTouchValue;
	
	static int					WIDTH;
	
	/*
	 * (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	static String[]			items	=
											{ "11111", "222222", "333333", "444444", "555555", "6666666" };
	
	private View getView(int position)
	{
		TextView text = new TextView(this);
		text.setText(items[position]);
		text.setTextColor(Color.BLACK);
		text.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
		text.setBackgroundColor(position % 2 == 0 ? Color.WHITE : Color.GRAY);
		return text;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		inAnimationLeft = AnimationUtils.loadAnimation(this, R.anim.slide_in_left);
		outAnimationLeft = AnimationUtils.loadAnimation(this, R.anim.slide_out_left);
		inAnimationRight = AnimationUtils.loadAnimation(this, R.anim.slide_in_right);
		outAnimationRight = AnimationUtils.loadAnimation(this, R.anim.slide_out_right);
		
		// detector = new GestureDetector(this, this);
		
		super.onCreate(savedInstanceState);
		Bundle parameters = getIntent().getExtras();
		String name = parameters.getString(Cte.FormIdentificationBean.name);
		String version = parameters.getString(Cte.FormIdentificationBean.version);
		FormIdentificationBean form = new FormIdentificationBean(name, version);
		DataBaseManager db = DataBaseManager.getInstance(this);
		String xml = db.getFormInfo(form).getXml();
		
		Log.d(TAG, "Name = " + name);
		Log.d(TAG, "Version = " + version);
		Log.d(TAG, "XML = " + xml);
		setContentView(R.layout.instance_fields_flipper);
		
		flipper = (ViewFlipper) findViewById(R.id.flipper);
		flipper.addView(getView(0), new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));
		flipper.addView(getView(1), new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));
		flipper.addView(getView(2), new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));
		flipper.addView(getView(3), new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));
		try
		{
			// TODO obtener el xml de la BBDD y no de los assets
			//InputStream in = getAssets().open("formulario.xml");
			//XmlToViewsParser xmlParser = new XmlToViewsParser(in);
			// flipper.setFields(xmlParser.getFields());
		}
		catch (Exception e)
		{
			// TODO Mostrar mensajito de error a la vista
			e.printStackTrace();
		}
		
		WIDTH = this.getWindowManager().getDefaultDisplay().getWidth() / 2;
	}
	
	/**
	 */
	@Override
	public void onBackPressed()
	{
		// TODO Esto se ejecutará cuando se presione volver. Habría que preguntar
		// que se va a hacer con la nueva instancia (guardar, descartar) si es que
		// no esta guardada
		
		Log.d(TAG, "BACK presionada...");
		super.onBackPressed();
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent touchEvent)
	{
		int action = touchEvent.getAction();
		switch (action)
		{
			case MotionEvent.ACTION_DOWN:
			{
				oldTouchValue = touchEvent.getX();
				break;
			}
			case MotionEvent.ACTION_UP:
			{
				float currentX = touchEvent.getX();
				if (oldTouchValue > currentX)
				{
					flipper.setInAnimation(inAnimationLeft);
					flipper.setOutAnimation(outAnimationLeft);
					
					flipper.showNext();
				}
				if (oldTouchValue < currentX)
				{
					flipper.setInAnimation(inAnimationRight);
					flipper.setOutAnimation(outAnimationRight);
					
					flipper.showPrevious();
				}
				break;
			}
			case MotionEvent.ACTION_MOVE:
			{
				final View currentView = flipper.getCurrentView();
				currentView.layout((int) (touchEvent.getX() - oldTouchValue), currentView.getTop(), currentView.getRight(), currentView.getBottom());
				//flipper.getChildAt(flipper.getDisplayedChild() - 1); // previous
				//flipper.getChildAt(flipper.getDisplayedChild() + 1); // next
				break;
			}
		}
		
		// detector.onTouchEvent(me);
		return true;
	}
	/*
	 * @Override
	 * public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
	 * float velocityY)
	 * {
	 * Log.d("Motion Event", e1.toString() + " - " + e2.toString());
	 * if (e1.getRawX() > e2.getRawX())
	 * {
	 * flipper.setInAnimation(inAnimationLeft);
	 * flipper.setOutAnimation(outAnimationLeft);
	 * flipper.showNext();
	 * }
	 * else if (e1.getRawX() < e2.getRawX())
	 * {
	 * flipper.setInAnimation(inAnimationRight);
	 * flipper.setOutAnimation(outAnimationRight);
	 * flipper.showPrevious();
	 * }
	 * return true;
	 * }
	 * @Override
	 * public boolean onDown(MotionEvent e)
	 * {
	 * return false;
	 * }
	 * @Override
	 * public void onLongPress(MotionEvent e)
	 * {
	 * }
	 * @Override
	 * public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
	 * float distanceY)
	 * {
	 * /*
	 * Log.d("Scroll", "X1 = " + e1.getX() + " X2 = " + e2.getX());
	 * int x1 = (int) e1.getX();
	 * int x2 = (int) e2.getX();
	 * if (x1 < x2) // Previous
	 * {
	 * int fade = x2 - x1;
	 * flipper.setPadding(fade, 0, -fade, 0);
	 * if (x2 > WIDTH)
	 * {
	 * flipper.showPrevious();
	 * flipper.setPadding(0, 0, 0, 0);
	 * }
	 * }
	 * else if (x1 > x2) // Next
	 * {
	 * int fade = x1 - x2;
	 * flipper.setPadding(-fade, 0, fade, 0);
	 * if (x1 < WIDTH)
	 * {
	 * flipper.showNext();
	 * flipper.setPadding(0, 0, 0, 0);
	 * }
	 * }
	 * //flipper.setPadding((int) e2.getX(), 0, 0, 0);
	 */
	/*
	 * return false;
	 * }
	 * @Override
	 * public void onShowPress(MotionEvent e)
	 * {
	 * }
	 * @Override
	 * public boolean onSingleTapUp(MotionEvent e)
	 * {
	 * return false;
	 * }
	 */
}
