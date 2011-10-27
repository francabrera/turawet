package com.turawet.beedroid.field.misc;

import com.google.android.maps.GeoPoint;
import com.turawet.beedroid.exception.NotInitializatedPositionException;

public class Position
{
	private final static double	MILLON	= 1E6;
	private GeoPoint					position;
	private double						latitude;
	private double						longitud;
	
	public Position()
	{
		latitude = 0;
		longitud = 0;
	}
	
	private void createGeoPointFromPosition() throws NotInitializatedPositionException
	{
		if (position == null)
		{
			if (longitud == 0 || latitude == 0)
				throw new NotInitializatedPositionException();
			position = new GeoPoint((int) (latitude * MILLON), (int) (longitud * MILLON));
		}
	}
	
	public GeoPoint getPositionAsGeoPoint() throws NotInitializatedPositionException
	{
		createGeoPointFromPosition();
		return position;
	}
	
	public String getLatitude() throws NotInitializatedPositionException
	{
		createGeoPointFromPosition();
		return String.valueOf((double) position.getLatitudeE6() / MILLON);
	}
	
	public String getLongitud() throws NotInitializatedPositionException
	{
		createGeoPointFromPosition();
		return String.valueOf((double) position.getLongitudeE6() / MILLON);
	}
	
	public void setLatitude(double latitude)
	{
		this.latitude = latitude;
	}
	
	public void setLongitud(double longitud)
	{
		this.longitud = longitud;
	}
	
}
