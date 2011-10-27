package com.turawet.beedroid.field.view.geo;

import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;
import com.turawet.beedroid.R;
import com.turawet.beedroid.constants.Cte;
import com.turawet.beedroid.exception.NotInitializatedPositionException;
import com.turawet.beedroid.field.misc.Position;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

public class GoogleMapsView  implements GeoPositionView
{
	private MapView mapView;
	private Context context;
	public GoogleMapsView(Context context)
	{
		super();
		this.context = context;
		mapView = new MapView(context, Cte.Field.GEO_API_KEY);
		mapView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
	}

	
	@Override
	public void showPosition(Position position) throws NotInitializatedPositionException
	{
		mapView.getOverlays().add(createMarkerForPosition(position));
		zoomIntoPosition(position);
	}
	
	private MapMarker createMarkerForPosition(Position position) throws NotInitializatedPositionException
	{
		MapMarker mapMarker = new MapMarker(context.getResources().getDrawable(R.drawable.marker));
		mapMarker.setOverlay(new OverlayItem(position.getPositionAsGeoPoint(), context.getString(R.string.geo_tittle), ""));
		return mapMarker;
	}
	
	private void zoomIntoPosition(Position position) throws NotInitializatedPositionException
	{
		MapController controller = mapView.getController();
		controller.animateTo(position.getPositionAsGeoPoint());
		controller.setZoom(Cte.Field.GEO_ZOOM);
	}

	@Override
	public View getView()
	{
		return mapView;
	}
	
}
