package com.turawet.beedroid.listener;

import com.google.android.maps.GeoPoint;
import com.turawet.beedroid.R;
import com.turawet.beedroid.exception.NotInitializatedPositionException;
import com.turawet.beedroid.util.AlertMaker;

import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.location.LocationManager;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class ObtainPositionListener implements OnClickListener
{
	private Context							context;
	private OnPositionUpdatedListener	positionUpdatedListener;
	
	public ObtainPositionListener(Context context)
	{
		this.context = context;
	}
	
	public void setOnPositionUpdatedListener(OnPositionUpdatedListener positionUpdatedListener)
	{
		this.positionUpdatedListener = positionUpdatedListener;
	}
	
	@Override
	public void onClick(View v)
	{
		final LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
		final MyLocationListener locationListener = new MyLocationListener();
		
		locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
		
		/*
		 * Progress dialog that appears while the systems response with the
		 * location
		 */
		final ProgressDialog progressDialog = ProgressDialog.show(context, "", context.getString(R.string.geo_obtaining), true, false);
		
		/*
		 * When the location its obtained, we have to update the textviews
		 * with the longitud and latitude values. It must be done in a
		 * diferent thread that listen to the location system.
		 * We have a ProgressThread class that use a handler to make the
		 * comunication between threads. When our listener already have the
		 * current location or the timeout has passed, stop the listener and
		 * show the results
		 */
		final ProgressThread progressThread = new ProgressThread();
		
		final Handler handler = new Handler()
		{
			private static final int	LOCATION_TIMEOUT	= 30 * 1000;
			private boolean				done					= false;
			
			/*
			 * The ProgressThread object call this method every second with a
			 * new message. This contains the time elapsed
			 */
			public void handleMessage(Message msg)
			{
				int timeElapsed = msg.arg1;
				if (!done && (locationListener.maxTriesReached() || timeElapsed > LOCATION_TIMEOUT))
				{
					/*
					 * We have the location or times out!
					 */
					done = true;
					/*
					 * Stop listening
					 */
					locationManager.removeUpdates(locationListener);
					/*
					 * Stop the thread
					 */
					progressThread.setState(ProgressThread.STATE_DONE);
					/*
					 * Remove the dialog
					 */
					progressDialog.dismiss();
					/*
					 * Update the longitud and latitude values if we have it
					 */
					if (locationListener.gotLocation())
					{
						try
						{
							positionUpdatedListener.onPositionUpdated(locationListener.getLatitud(), locationListener.getLongitud());
						}
						catch (NotInitializatedPositionException e)
						{
							showErrorOnGettingPosition();
						}
					}
					else
					{
						showErrorOnGettingPosition();
					}
					
				}
			}
		};
		progressThread.setHandler(handler);
		progressThread.start();
	}
	
	private void showErrorOnGettingPosition()
	{
		AlertMaker.showErrorMessage(new Builder(context), R.string.geo_obtain_error).show();
	}
	
	private class ProgressThread extends Thread
	{
		Handler				mHandler;
		final static int	STATE_DONE		= 0;
		final static int	STATE_RUNNING	= 1;
		final static int	ONE_SECOND		= 1000;
		int					mState;
		int					timeElapsed;
		
		/**
		 * 
		 */
		public ProgressThread()
		{
			super();
		}
		
		void setHandler(Handler h)
		{
			mHandler = h;
		}
		
		public void run()
		{
			mState = STATE_RUNNING;
			timeElapsed = 0;
			while (mState == STATE_RUNNING)
			{
				try
				{
					Thread.sleep(ONE_SECOND);
				}
				catch (InterruptedException e)
				{
					Log.e("ERROR", "Thread Interrupted");
				}
				Message msg = mHandler.obtainMessage();
				msg.arg1 = timeElapsed;
				mHandler.sendMessage(msg);
				timeElapsed += ONE_SECOND;
			}
		}
		
		/*
		 * sets the current state for the thread,
		 * used to stop the thread
		 */
		public void setState(int state)
		{
			mState = state;
		}
	}
}
