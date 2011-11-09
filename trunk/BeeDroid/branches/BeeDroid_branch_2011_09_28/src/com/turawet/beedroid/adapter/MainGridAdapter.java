package com.turawet.beedroid.adapter;

import java.util.ArrayList;
import java.util.List;

import com.turawet.beedroid.R;
import com.turawet.beedroid.util.MainIcon;

import android.content.Context;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author nicopernas
 * 
 */
public class MainGridAdapter extends BaseAdapter
{
	Context			context;
	LayoutInflater	inflater;
	List<MainIcon>	icons;
	
	public MainGridAdapter(Context context)
	{
		this.context = context;
		this.inflater = LayoutInflater.from(context);
		this.icons = new ArrayList<MainIcon>();
	}
	
	public boolean addIcon(MainIcon icon)
	{
		return icons.add(icon);
	}
	
	public MainIcon getIcon(int position)
	{
		return icons.get(position);
	}
	
	@Override
	public int getCount()
	{
		return icons.size();
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		View currentIcon;
		if (convertView == null)
		{
			// TODO: pasar la lógica de traducción del ícono a la clase MainIcon
			currentIcon = inflater.inflate(R.layout.main_icon, null);
			
			MainIcon mainIcon = icons.get(position);
			TextView iconText = (TextView) currentIcon.findViewById(R.id.icon_text);
			iconText.setText(mainIcon.getName());
			iconText.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 17);
			iconText.setTypeface(Typeface.DEFAULT_BOLD);
			
			ImageView iconImage = (ImageView) currentIcon.findViewById(R.id.icon_image);
			iconImage.setBackgroundResource(mainIcon.getResourceId());
			iconImage.setAdjustViewBounds(true);
			iconImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
			iconImage.setPadding(8, 8, 8, 8);
		}
		else
		{
			currentIcon = convertView;
		}
		
		return currentIcon;
		
	}
	
	@Override
	public Object getItem(int arg0)
	{
		return null;
	}
	
	@Override
	public long getItemId(int arg0)
	{
		return 0;
	}
}
