/**
 * 
 */
package com.turawet.beedroid.adapter;

import com.turawet.beedroid.R;

import android.content.Context;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * @author nicopernas
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
										{ R.drawable.icon_portada1, R.drawable.icon_portada2, R.drawable.icon_portada3 };
	
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
		if (convertView == null)
		{ // if it's not recycled, initialize some attributes
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
		}
		else
		{
			imageView = (ImageView) convertView;
		}
		
		imageView.setImageResource(iconsIds[position]);
		return imageView;
	}
}
