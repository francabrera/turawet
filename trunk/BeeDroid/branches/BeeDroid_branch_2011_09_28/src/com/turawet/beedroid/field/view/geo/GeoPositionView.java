package com.turawet.beedroid.field.view.geo;

import android.view.View;

import com.turawet.beedroid.exception.NotInitializatedPositionException;
import com.turawet.beedroid.field.misc.Position;

public interface GeoPositionView
{
	public void showPosition(Position position) throws NotInitializatedPositionException;
	public View getView();
}
