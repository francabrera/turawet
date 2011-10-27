package com.turawet.beedroid.listener;

import com.turawet.beedroid.exception.NotInitializatedPositionException;

public interface OnPositionUpdatedListener
{
	public void onPositionUpdated(double latitude, double longitud) throws NotInitializatedPositionException;
}
