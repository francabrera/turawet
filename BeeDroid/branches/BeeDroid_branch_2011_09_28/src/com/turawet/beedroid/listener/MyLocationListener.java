/**
 * 
 */
package com.turawet.beedroid.listener;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;

public class MyLocationListener implements LocationListener
{
	private Location				currentBestLocation;
	private int						locationCount				= 0;
	private static final int	NUM_OF_LOCATIONS_SEARCH	= 1;
	
	protected boolean isBetterLocation(Location location)
	{
		Log.d("", "Provider " + location.getProvider());
		if (currentBestLocation == null)
		{
			currentBestLocation = location;
			locationCount++;
			return true;
		}
		
		// Check whether the new location fix is newer or older
		long timeDelta = location.getTime() - currentBestLocation.getTime();
		boolean isNewer = timeDelta > 0;
		
		// Check whether the new location fix is more or less accurate
		int accuracyDelta = (int) (location.getAccuracy() - currentBestLocation.getAccuracy());
		boolean isLessAccurate = accuracyDelta > 0;
		boolean isMoreAccurate = accuracyDelta < 0;
		boolean isSignificantlyLessAccurate = accuracyDelta > 200;
		
		// Check if the old and new location are from the same provider
		boolean isFromSameProvider = isSameProvider(location.getProvider(), currentBestLocation.getProvider());
		
		boolean isBetterLocation = false;
		// Determine location quality using a combination of timeliness and
		// accuracy
		if (isMoreAccurate)
			isBetterLocation = true;
		else if (isNewer && !isLessAccurate)
			isBetterLocation = true;
		else if (isNewer && !isSignificantlyLessAccurate && isFromSameProvider)
			isBetterLocation = true;
		
		if (isBetterLocation)
		{
			currentBestLocation = location;
			locationCount++;
		}
		return isBetterLocation;
	}
	
	/** Checks whether two providers are the same */
	private boolean isSameProvider(String provider1, String provider2)
	{
		if (provider1 == null)
		{
			return provider2 == null;
		}
		return provider1.equals(provider2);
	}
	
	/*
	 * (non-Javadoc)
	 * @see
	 * android.location.LocationListener#onLocationChanged(android.location
	 * .Location)
	 */
	@Override
	public void onLocationChanged(Location location)
	{
		Log.d("", "Capturada nueva localización");
		if (isBetterLocation(location))
		{
			Log.d("", "Es más precisa... van " + locationCount);
			Log.d("", "Es más precisa... van " + currentBestLocation.getLatitude() + " " + currentBestLocation.getLongitude());
		}
	}
	
	public boolean maxTriesReached()
	{
		return locationCount >= NUM_OF_LOCATIONS_SEARCH;
	}
	
	public boolean gotLocation()
	{
		return currentBestLocation != null;
	}
	
	/**
	 * @return
	 */
	public double getLatitud()
	{
		return currentBestLocation.getLatitude();
	}
	
	/**
	 * @return
	 */
	public double getLongitud()
	{
		return currentBestLocation.getLongitude();
	}
	
	/*
	 * (non-Javadoc)
	 * @see
	 * android.location.LocationListener#onProviderDisabled(java.lang.String)
	 */
	@Override
	public void onProviderDisabled(String provider)
	{
	}
	
	/*
	 * (non-Javadoc)
	 * @see
	 * android.location.LocationListener#onProviderEnabled(java.lang.String)
	 */
	@Override
	public void onProviderEnabled(String provider)
	{
	}
	
	/*
	 * (non-Javadoc)
	 * @see
	 * android.location.LocationListener#onStatusChanged(java.lang.String,
	 * int, android.os.Bundle)
	 */
	@Override
	public void onStatusChanged(String provider, int status, Bundle extras)
	{
	}
	
}
