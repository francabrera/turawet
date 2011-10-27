package com.turawet.beedroid.field.view;

import com.turawet.beedroid.R;
import com.turawet.beedroid.exception.NotInitializatedPositionException;
import com.turawet.beedroid.exception.NullSectionTitleExcpetion;
import com.turawet.beedroid.field.misc.Position;
import com.turawet.beedroid.field.view.geo.GeoPositionView;
import com.turawet.beedroid.field.view.geo.GoogleMapsView;
import com.turawet.beedroid.field.view.geo.LongitudLatitudeTextsView;
import com.turawet.beedroid.listener.ObtainPositionListener;
import com.turawet.beedroid.listener.OnPositionUpdatedListener;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Button;
import android.widget.RelativeLayout;

public class GeolocalizationFieldView extends FieldView implements OnPositionUpdatedListener
{
	private Button							positionButton;
	private GeoPositionView				positionView;
	private ObtainPositionListener	obtainPositionListener;
	private Position						position;
	private boolean						showMap;
	
	public GeolocalizationFieldView(Context context)
	{
		super(context);
		position = new Position();
		obtainPositionListener = new ObtainPositionListener(context);
		obtainPositionListener.setOnPositionUpdatedListener(this);
		showMap = isConnectionEnabled();
	}
	
	@Override
	public FieldView performView() throws NullSectionTitleExcpetion
	{
		setSectionTitle(getContext().getString(R.string.geo_field_title));
		addSectionTitle();
		determineGeoPositionView();
		addGeoView();
		return this;
	}
	
	private void addGeoView()
	{
		RelativeLayout geoFrame = (RelativeLayout) viewInflater.inflate(R.layout.geoposition_frame, null);
		positionButton = (Button) geoFrame.findViewById(R.id.geo_position_button);
		positionButton.setOnClickListener(obtainPositionListener);
		geoFrame.addView(positionView.getView(), 0);
		addView(geoFrame);
	}
	
	private void determineGeoPositionView()
	{
		if (showMap)
			positionView = new GoogleMapsView(getContext());
		else
			positionView = new LongitudLatitudeTextsView(getContext());
	}
	
	private boolean isConnectionEnabled()
	{
		ConnectivityManager cm = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInformer = cm.getActiveNetworkInfo();
		if (networkInformer == null)
			return false;
		else
			return networkInformer.isConnectedOrConnecting();
	}
	
	@Override
	public Object getValue()
	{
		return position;
	}
	
	@Override
	public void onPositionUpdated(double latitude, double longitud) throws NotInitializatedPositionException
	{
		position.setLatitude(latitude);
		position.setLongitud(longitud);
		positionView.showPosition(position);
	}
}
