package com.turawet.beedroid.field.misc;

import com.google.android.maps.GeoPoint;

public class Position
{
	private final static double	MILLON	= 1E6;
	private GeoPoint					position;
	
	public Position()
	{
		position = new GeoPoint(0, 0);
	}
	
	public GeoPoint getPositionAsGeoPoint()
	{
		return position;
	}
	
	public String getLatitude()
	{
		return String.valueOf((double) position.getLatitudeE6() / MILLON);
	}
	
	public String getLongitud()
	{
		return String.valueOf((double) position.getLongitudeE6() / MILLON);
	}
	
	public void setPosition(double latitude, double longitude)
	{
		position = new GeoPoint((int) (latitude * MILLON), (int) (longitude * MILLON));
	}
	
}
