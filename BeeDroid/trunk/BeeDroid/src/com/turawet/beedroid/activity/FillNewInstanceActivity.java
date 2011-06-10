/**
 * @title FillNewInstanceActivity
 * @authors Francisco Jose Cabrera Hernandez,
 *          Nicolas Pernas Maradei,
 *          Romen Rodriguez Gil
 * 
 */
package com.turawet.beedroid.activity;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.Writer;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.turawet.beedroid.R;
import com.turawet.beedroid.beans.InstanceBean;
import com.turawet.beedroid.constants.Cte;
import com.turawet.beedroid.database.DataBaseManager;
import com.turawet.beedroid.parser.XmlToBeansParser;
import com.turawet.beedroid.view.EfficientViewFlipper;
import com.turawet.beedroid.wsclient.beans.FormIdentificationBean;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FillNewInstanceActivity extends Activity // implements
// OnGestureListener
{
	/**
	 *
	 */
	private final String				TAG	= getClass().getName();
	private float						oldTouchValue;
	private static int				WIDTH;
	private static int				HEIGHT;
	
	private EfficientViewFlipper	flipper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		
		super.onCreate(savedInstanceState);
		
		try
		{
			WIDTH = this.getWindowManager().getDefaultDisplay().getWidth();
			HEIGHT = this.getWindowManager().getDefaultDisplay().getHeight();
			Bundle parameters = getIntent().getExtras();
			String name = parameters.getString(Cte.FormIdentificationBean.name);
			String version = parameters.getString(Cte.FormIdentificationBean.version);
			FormIdentificationBean form = new FormIdentificationBean(name, version);
			DataBaseManager db = DataBaseManager.getInstance(this);
			String xml = db.getFormInfo(form).getXml();
			XmlToBeansParser parser;
			parser = new XmlToBeansParser(xml);
			InstanceBean instance = parser.getInstance();
			
			flipper = new EfficientViewFlipper(this, instance);
			flipper.setWidth(WIDTH);
			flipper.setHeight(HEIGHT);
			setContentView(flipper);
		}
		catch (SAXException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		catch (ParserConfigurationException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		catch (IOException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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
				// Queremos ver la vista previa
				if (oldTouchValue < WIDTH / 2 && currentX > WIDTH / 2 && Math.abs(currentX - oldTouchValue) > WIDTH / 2)
				{
					// flipper.setInAnimation(inAnimationLeft);
					// flipper.setOutAnimation(outAnimationLeft);
					flipper.showPrevious();
				}
				// Queremos ver la vista siguiente
				else if (oldTouchValue > WIDTH / 2 && currentX < WIDTH / 2 && Math.abs(currentX - oldTouchValue) > WIDTH / 2)
				{
					// flipper.setInAnimation(inAnimationRight);
					// flipper.setOutAnimation(outAnimationRight);
					flipper.showNext();
				}
				else
				{
					flipper.backToCurrent();
				}
				break;
			}
			case MotionEvent.ACTION_MOVE:
			{
				flipper.move((int) oldTouchValue, (int) touchEvent.getX());
				break;
			}
		}
		
		return true;
	}
}
