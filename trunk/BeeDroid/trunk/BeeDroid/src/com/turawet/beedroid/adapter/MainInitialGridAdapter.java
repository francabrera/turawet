/**
 * 
 */
package com.turawet.beedroid.adapter;

import com.turawet.beedroid.R;
import com.turawet.beedroid.view.FieldView;

import android.content.Context;
import android.graphics.Color;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * MainInitialGridAdapter
 * 
 * @version 1.0
 * 
 * @author Francisco José Cabrera Hernández
 * @author Nicolás Pernas Maradei
 * @author Romén Rodríguez Gil
 * 
 */
public class MainInitialGridAdapter extends BaseAdapter
{
	/**
	 *
	 */
	private Context	mContext;
	private Display	mDisplay;
	// references to our images
	private int[]		iconsIds	=
										{ R.drawable.icon_portada1, R.drawable.icon_portada2,
										  R.drawable.icon_portada3, R.drawable.icon_portada4 };
	
	public MainInitialGridAdapter(Context c)
	{
		mContext = c;
		mDisplay = ((WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
	}
	
	public int getCount()
	{
		return iconsIds.length;
	}
	
	public Object getItem(int position)
	{
		return null;
	}
	
	public long getItemId(int position)
	{
		return 0;
	}
	
	// create a new ImageView for each item referenced by the Adapter
	public View getView(int position, View convertView, ViewGroup parent)
	{
		
		ImageView imageView;
		LinearLayout fieldView;
		TextView text;

		if (convertView == null)
		{ // if it's not recycled, initialize some attributes
			
			fieldView = new LinearLayout(mContext, null);
			fieldView.setGravity(Gravity.CENTER_HORIZONTAL);
			fieldView.setOrientation(LinearLayout.VERTICAL);
			fieldView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT, 0.0F));
			/*fieldView.addView(longitudText);
			fieldView.addView(latitudText);*/
			
			imageView = new ImageView(mContext);
			
			int width = mDisplay.getWidth() / 2;
			int wPad = width / 10;
			width -= wPad;
			
			int height = mDisplay.getHeight() / 2;
			int hPad = height / 10;
			height -= 3 * hPad;
			
			imageView.setLayoutParams(new GridView.LayoutParams(width, height));
			imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
			imageView.setPadding(wPad, hPad, wPad, hPad);
			imageView.setAdjustViewBounds(true);
			
			text = new TextView(mContext);
			text.setText("Prueba");
			text.setTextColor(Color.RED);
			text.setGravity(Gravity.TOP);
			text.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 0.0F));

			fieldView.addView(text);
			fieldView.addView(imageView);
		}
		else
		{
			fieldView = (LinearLayout) convertView;
		}
		
		((ImageView)fieldView.getChildAt(0)).setImageResource(iconsIds[position]);
		
		return fieldView;
	}
}
