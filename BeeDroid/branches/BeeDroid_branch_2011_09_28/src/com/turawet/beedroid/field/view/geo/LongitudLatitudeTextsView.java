package com.turawet.beedroid.field.view.geo;

import com.turawet.beedroid.R;
import com.turawet.beedroid.exception.NotInitializatedPositionException;
import com.turawet.beedroid.field.misc.Position;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class LongitudLatitudeTextsView extends RelativeLayout implements GeoPositionView
{
	
	private TextView	latitudeText;
	private TextView	longitudText;
	
	public LongitudLatitudeTextsView(Context context)
	{
		super(context);
		addView(inflate(context, R.layout.geoposition_lat_lang_view, null));
		longitudText = (TextView) findViewById(R.id.geo_longitude_label);
		latitudeText = (TextView) findViewById(R.id.geo_latitude_label);
	}

	@Override
	public void showPosition(Position position) throws NotInitializatedPositionException
	{
		latitudeText.append(" " + position.getLatitude());
		longitudText.append(" " + position.getLongitud());
	}

	@Override
	public View getView()
	{
		return this;
	}

}
