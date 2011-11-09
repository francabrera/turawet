package com.turawet.beedroid.field.view.geo;

import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class MapMarker extends ItemizedOverlay<OverlayItem>
{
	private OverlayItem	overlay;
	
	public MapMarker(Drawable defaultMarker)
	{
		super(boundCenterBottom(defaultMarker));
	}
	
	public void setOverlay(OverlayItem overlay)
	{
		this.overlay = overlay;
		populate();
	}
	
	@Override
	protected OverlayItem createItem(int i)
	{
		return overlay;
	}
	
	@Override
	public int size()
	{
		return 1;
	}
	
}
