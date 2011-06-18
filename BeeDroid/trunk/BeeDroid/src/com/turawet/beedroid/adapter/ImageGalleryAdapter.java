/**
 * 
 */
package com.turawet.beedroid.adapter;

import java.util.Vector;

import com.turawet.beedroid.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

/**
 * @author nicopernas
 * 
 */
public class ImageGalleryAdapter extends BaseAdapter
{
	private Context			mContext;
	private Vector<Bitmap>	images	= new Vector<Bitmap>();
	private int					mGalleryItemBackground;
	
	public ImageGalleryAdapter(Context c)
	{
		mContext = c;
		TypedArray a = mContext.obtainStyledAttributes(R.styleable.Image_Gallery);
		mGalleryItemBackground = a.getResourceId(R.styleable.Image_Gallery_android_galleryItemBackground, 0);
		a.recycle();
	}
	
	public void addImage(Bitmap b)
	{
		images.add(b);
	}
	
	@Override
	public int getCount()
	{
		return images.size();
	}
	
	@Override
	public Object getItem(int position)
	{
		return images.get(position);
	}
	
	@Override
	public long getItemId(int position)
	{
		return position;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		ImageView image;
		if (convertView == null)
		{
			image = new ImageView(mContext);
			image.setScaleType(ImageView.ScaleType.FIT_XY);
			//image.setLayoutParams(new Gallery.LayoutParams(200, 100));
			image.setBackgroundResource(mGalleryItemBackground);
		}
		else
		{
			image = (ImageView) convertView;
		}
		
		image.setImageBitmap(images.get(position));
		return image;
	}
	
}
