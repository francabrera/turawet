/**
 * 
 */
package com.turawet.beedroid.view.support;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Date;
import java.util.List;

import com.turawet.beedroid.R;
import com.turawet.beedroid.activity.FillNewInstanceActivity;
import com.turawet.beedroid.adapter.ImageGalleryAdapter;
import com.turawet.beedroid.beans.DateFieldBean;
import com.turawet.beedroid.beans.FieldOptionBean;
import com.turawet.beedroid.beans.GenericInstanceFieldBean;
import com.turawet.beedroid.beans.ImageFieldBean;
import com.turawet.beedroid.beans.InstanceBean;
import com.turawet.beedroid.beans.NumericFieldBean;
import com.turawet.beedroid.beans.RadioFieldBean;
import com.turawet.beedroid.beans.TextFieldBean;
import com.turawet.beedroid.constants.Cte.FieldType;
import com.turawet.beedroid.constants.Cte.InstanceBeanCte;
import com.turawet.beedroid.listener.MyLocationListener;
import com.turawet.beedroid.util.AlertMaker;
import com.turawet.beedroid.view.BeanViewFlipper;
import com.turawet.beedroid.view.FieldView;

import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.LocationManager;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.text.InputType;
import android.util.Log;
import android.util.Pair;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * @author nicopernas
 * 
 */
public class InstanceBeanManager
{
	/**
	 *
	 */
	InstanceBean		instance;
	private Context	context;
	
	/**
	 * @param context
	 *           Context where it's instanciated
	 * @param instance
	 *           Instance objet of the form definition
	 */
	public InstanceBeanManager(Context context, InstanceBean instance)
	{
		this.instance = instance;
		this.context = context;
	}
	
	/**
	 * @return
	 *         A string with the traduction of the instance to xml. This xml
	 *         it's ready to be uploaded to the repository
	 * @throws IllegalArgumentException
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public String instanceToXml() throws IllegalArgumentException, IllegalStateException, IOException
	{
		Writer writer = new StringWriter();
		/*
		 * The instance traduce it self to xml
		 */
		instance.toXml(writer);
		return writer.toString();
	}
	
	/**
	 * @param sections
	 * @return
	 *         A list of all FieldViews of the instance
	 */
	public void getAllSectionsAndFieldsViews(List<FieldView> fields, List<Pair<String, Integer>> sectionsList) throws NullPointerException
	{
		/*
		 * Camera
		 */
		// views.add(getNewImageGalleryFieldView());
		
		/*
		 * Go trough all the sections and fields and create the asociated view
		 */
		int numberOfSections = instance.getSections().size();
		int page = 0;
		for (int section = 0; section < numberOfSections; section++)
		{
			sectionsList.add(new Pair<String, Integer>(instance.getSections().get(section).getName(), page));
			int numberOfFields = instance.getSections().get(section).getSectionChildren().size();
			for (int field = 0; field < numberOfFields; field++)
			{
				/*
				 * Add the asociated view to the concrete field and section
				 */
				FieldView fieldView = getNewFieldView(section, field);
				if (fieldView == null)
					throw new NullPointerException("Error la intentar obtener la vista del campo " + field + " de la sección " + section);
				else
				{
					page++;
					fields.add(fieldView);
				}
			}
			
		}
		
		/*
		 * If the form needs to be geolacalized
		 */
		if (instance.getForm().isGeolocalized())
		{
			fields.add(getNewGeolocalizedView());
		}
	}
	
	/**
	 * @param bitmap
	 * @param flipper
	 */
	public boolean addImage(Bitmap bitmap, BeanViewFlipper flipper)
	{
		if (flipper == null)
			return false;
		
		BeanViewFlipper viewFlipper = (BeanViewFlipper) flipper;
		int currentPage = viewFlipper.getCurrentPage();
		
		LinearLayout child = (LinearLayout) viewFlipper.getChildAt(0);
		if (child == null)
			return false;
		
		FieldView fieldView = (FieldView) child.getChildAt(currentPage);
		if (fieldView == null)
			return false;
		
		ImageView image = (ImageView) fieldView.getField();
		if (image == null)
			return false;
		
		image.setImageBitmap(bitmap);
		image.refreshDrawableState();
		return true;
	}
	
	/**
	 * @param bitmap
	 *           Image taken with the camera that gonna be added to the current
	 *           gallery
	 */
	public boolean addImageToGallery(Bitmap bitmap, View flipper)
	{
		if (flipper == null)
			return false;
		
		BeanViewFlipper viewFlipper = (BeanViewFlipper) flipper;
		int currentPage = viewFlipper.getCurrentPage();
		
		LinearLayout child = (LinearLayout) viewFlipper.getChildAt(0);
		if (child == null)
			return false;
		
		FieldView fieldView = (FieldView) child.getChildAt(currentPage);
		if (fieldView == null)
			return false;
		
		Gallery gallery = (Gallery) fieldView.getField();
		if (gallery == null)
			return false;
		
		ImageGalleryAdapter adapter = (ImageGalleryAdapter) gallery.getAdapter();
		if (adapter == null)
			return false;
		
		boolean isAdded = adapter.addImage(bitmap);
		adapter.notifyDataSetChanged();
		return isAdded;
	}
	
	/**
	 * @param imageField
	 * @return
	 */
	private FieldView getNewImageFieldView(ImageFieldBean imageField, int section)
	{
		ImageView image = new ImageView(context);
		
		String sectionTitle = instance.getSections().get(section).getName();
		FieldView fieldView = getNewFieldView(imageField, sectionTitle, image);
		
		Button button = new Button(context);
		button.setText("Hacer nueva fotografía");
		button.setGravity(Gravity.CENTER_HORIZONTAL);
		button.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 0.0F));
		
		button.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				((FillNewInstanceActivity) context).startActivityForResult(intent, InstanceBeanCte.SINGLE_IMAGE);
			}
		});
		
		fieldView.addView(button);
		return fieldView;
	}
	
	/**
	 * @return
	 *         A FieldView with an image gallery. Allows to take pictures
	 */
	private FieldView getNewImageGalleryFieldView(int section)
	{
		/*
		 * Create the gallery and the adapter
		 */
		Gallery gallery = new Gallery(context);
		ImageGalleryAdapter galleryAdapter = new ImageGalleryAdapter(context);
		gallery.setAdapter(galleryAdapter);
		
		/*
		 * The botton who allos to call the camera and then take the picture
		 */
		Button button = new Button(context);
		button.setText("Hacer nueva fotografía");
		button.setGravity(Gravity.CENTER_HORIZONTAL);
		button.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 0.0F));
		
		button.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				((FillNewInstanceActivity) context).startActivityForResult(intent, InstanceBeanCte.GALLERY_IMAGE);
			}
		});
		
		/*
		 * The section title
		 */
		String sectionTitle = instance.getSections().get(section).getName();
		
		/*
		 * This is the fieldview that contains the gallery
		 */
		FieldView fieldView = new FieldView(context);
		fieldView.setGravity(Gravity.TOP);
		fieldView.setOrientation(LinearLayout.VERTICAL);
		fieldView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT, 0.0F));
		
		/*
		 * Set the section title, button and gallery to the fieldview
		 */
		fieldView.setSectionTitle(sectionTitle);
		fieldView.addView(button);
		fieldView.addView(gallery);
		return fieldView;
	}
	
	/**
	 * @return
	 *         A FieldView with a button that allows to take the current location
	 *         of the user
	 */
	private FieldView getNewGeolocalizedView()
	{
		/*
		 * TextView to show the current latitud
		 */
		// final TextView latitudText = new TextView(context);
		// latitudText.setGravity(Gravity.TOP);
		// latitudText.setLayoutParams(new
		// LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
		// ViewGroup.LayoutParams.WRAP_CONTENT, 0.0F));
		//
		/*
		 * TextView to show the current longitud
		 */
		final TextView locationText = new TextView(context);
		locationText.setGravity(Gravity.TOP);
		locationText.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 0.0F));
		
		/*
		 * Button that iniciate the location capture process
		 */
		Button geoButton = new Button(context);
		geoButton.setText(context.getString(R.string.geo_obtain));
		geoButton.setGravity(Gravity.TOP);
		geoButton.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 0.0F));
		
		geoButton.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(final View v)
			{
				/*
				 * Acquire a reference to the system Location Manager
				 */
				final LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
				
				/*
				 * Define a listener that responds to location updates
				 */
				final MyLocationListener locationListener = new MyLocationListener();
				
				/*
				 * Register the listener with the Location Manager to receive
				 * location updates from the network and gps
				 */
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
								// longitudText.setText(context.getString(R.string.geo_longitud)
								// + " " + locationListener.getLongitud());
								// latitudText.setText(context.getString(R.string.geo_latitud)
								// + " " + locationListener.getLatitud());
								locationText.setText(locationListener.getLatitud() + "\n" + locationListener.getLongitud());
							}
							else
							{
								AlertMaker.showErrorMessage(new Builder(context), R.string.geo_obtain_error).show();
							}
							
						}
					}
				};
				progressThread.setHandler(handler);
				progressThread.start();
			}
		});
		
		FieldView fieldView = new FieldView(context, FieldType.GEO);
		fieldView.setGravity(Gravity.CENTER_HORIZONTAL);
		fieldView.setOrientation(LinearLayout.VERTICAL);
		fieldView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT, 0.0F));
		
		fieldView.setSectionTitle("");
		// fieldView.setField(geoButton, context.getString(R.string.geo_tittle));
		fieldView.setField(locationText, context.getString(R.string.geo_tittle));
		fieldView.addView(geoButton);
		return fieldView;
	}
	
	/**
	 * @param section
	 *           Section index in the form description
	 * @param field
	 *           Field index in the current section
	 * @return
	 *         A FieldView with the desire fields
	 */
	private final FieldView getNewFieldView(int sectionInd, int fieldInd)
	{
		/*
		 * Obtain the current field of the instance
		 */
		GenericInstanceFieldBean fieldBean = (GenericInstanceFieldBean) instance.getSections().get(sectionInd).getSectionChildren().get(fieldInd);
		
		FieldType fieldType = fieldBean.getFormField().getType();
		FieldView fieldView = null;
		/*
		 * Depending on the type of the field, creates the needed fields
		 */
		switch (fieldType)
		{
			case TEXT:
				fieldView = getNewTextFieldView((TextFieldBean) fieldBean, sectionInd);
				break;
			case NUMERIC:
				fieldView = getNewNumericFieldView((NumericFieldBean) fieldBean, sectionInd);
				break;
			case DATE:
				fieldView = getNewDateFieldView((DateFieldBean) fieldBean, sectionInd);
				break;
			case RADIO:
				fieldView = getNewRadioFieldView((RadioFieldBean) fieldBean, sectionInd);
				break;
			case IMAGE:
				fieldView = getNewImageFieldView((ImageFieldBean) fieldBean, sectionInd);
				break;
			default:
				break;
		}
		
		return fieldView;
	}
	
	/**
	 * @param radioField
	 *           Description of the radio field
	 * @return
	 *         A field view with a radio group containing the needed radio
	 *         buttons
	 */
	private FieldView getNewRadioFieldView(RadioFieldBean radioField, int section)
	{
		RadioGroup radioGroup = new RadioGroup(context);
		
		/*
		 * For each option it creates and new radiobutton and then its added to
		 * the radio group
		 */
		List<FieldOptionBean> options = radioField.getFormField().getFieldOptions();
		int id = 0;
		for (FieldOptionBean option : options)
		{
			RadioButton radio = new RadioButton(context);
			radio.setText(option.getLabel());
			radio.setId(id++);
			radioGroup.addView(radio);
		}
		
		String sectionTitle = instance.getSections().get(section).getName();
		return getNewFieldView(radioField, sectionTitle, radioGroup);
	}
	
	/**
	 * @param dateField
	 *           Description of the date field
	 * @return
	 *         A Fieldview with a date picker
	 */
	private FieldView getNewDateFieldView(DateFieldBean dateField, int section)
	{
		DatePicker datePicker = new DatePicker(context);
		datePicker.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 0.0F));
		String sectionTitle = instance.getSections().get(section).getName();
		return getNewFieldView(dateField, sectionTitle, datePicker);
	}
	
	/**
	 * @param textField
	 *           Description of the text field
	 * @return
	 *         A fieldView with an editable text field
	 */
	private FieldView getNewNumericFieldView(NumericFieldBean textField, int section)
	{
		EditText text = new EditText(context);
		text.setHint("Número...");
		text.setInputType(InputType.TYPE_CLASS_NUMBER);
		
		text.setGravity(Gravity.TOP);
		
		text.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 0.0F));
		
		String sectionTitle = instance.getSections().get(section).getName();
		return getNewFieldView(textField, sectionTitle, text);
	}
	
	/**
	 * @param textField
	 *           Description of the text field
	 * @return
	 *         A fieldView with an editable text field
	 */
	private FieldView getNewTextFieldView(TextFieldBean textField, int section)
	{
		EditText text = new EditText(context);
		text.setHint("Texto...");
		// text.setInputType( InputType.TYPE_CLASS_NUMBER);
		
		text.setGravity(Gravity.TOP);
		
		text.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 0.0F));
		
		String sectionTitle = instance.getSections().get(section).getName();
		return getNewFieldView(textField, sectionTitle, text);
	}
	
	/**
	 * @param fieldBean
	 *           Description of the field, label and type
	 * @param sectionTitle
	 *           Title of the section
	 * @param field
	 *           Generated view with the field
	 * @return
	 *         A FieldView with a section title, a label that describes the
	 *         semantic of the field and a view that allows to collect the data
	 */
	private final FieldView getNewFieldView(GenericInstanceFieldBean fieldBean, String sectionTitle, View field)
	{
		FieldView fieldView = new FieldView(context, fieldBean.getFormField().getType());
		fieldView.setGravity(Gravity.CENTER_HORIZONTAL);
		fieldView.setOrientation(LinearLayout.VERTICAL);
		fieldView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT, 0.0F));
		
		fieldView.setSectionTitle(sectionTitle);
		fieldView.setField(field, fieldBean.getFormField().getLabel());
		return fieldView;
	}
	
	/**
	 * Save the data obtained from the view into the instance object
	 * 
	 * @param section
	 *           Index of the section in the instance
	 * @param field
	 *           Index of the field in the section
	 * @param value
	 *           Value readed from the view to save on the instance
	 */
	private void copyDataFromViewsToInstance(int section, int field, Object value)
	{
		/*
		 * With both index, obtain the field instance object
		 */
		GenericInstanceFieldBean instanceBean = (GenericInstanceFieldBean) instance.getSections().get(section).getSectionChildren().get(field);
		/*
		 * Save the data depending on the type of the field
		 */
		FieldType type = instanceBean.getFormField().getType();
		switch (type)
		{
			case TEXT:
				((TextFieldBean) instanceBean).setText((String) value);
				break;
			case NUMERIC:
				((NumericFieldBean) instanceBean).setNumber(((Integer) value).intValue());
				break;
			case DATE:
				((DateFieldBean) instanceBean).setDate((Date) value);
				break;
			case RADIO:
				((RadioFieldBean) instanceBean).setValue(((Integer) value).intValue());
				break;
			case IMAGE:
				((ImageFieldBean) instanceBean).setValue((ByteArrayOutputStream) value);
				break;
			default:
				break;
		}
	}
	
	/**
	 * Pass through the list of view obtained from the flipper manager with all
	 * the field views and read all the values. Then save the values on the
	 * instance
	 * 
	 * @param fieldsViews
	 *           View with a children list with all the field views
	 */
	public void readFieldValues(View fieldsViews)
	{
		int numOfSections = instance.getSections().size();
		int i = 0;
		for (int section = 0; section < numOfSections; section++)
		{
			int numOfFields = instance.getSections().get(section).getSectionChildren().size();
			for (int field = 0; field < numOfFields; field++)
			{
				FieldView fieldView = (FieldView) ((LinearLayout) fieldsViews).getChildAt(i++);
				Object value = fieldView.getFieldValue();
				copyDataFromViewsToInstance(section, field, value);
			}
		}
		if (instance.getForm().isGeolocalized())
		{
			FieldView fieldView = (FieldView) ((LinearLayout) fieldsViews).getChildAt(i);
			String geo = (String) fieldView.getFieldValue();
			if (geo != null && !geo.equals(""))
			{
				String[] geolocalization = ((String) geo).split("\n");
				instance.setLatitude(geolocalization[0]);
				instance.setLongitud(geolocalization[1]);
			}
		}
	}
	
	/** Nested class that performs progress calculations (counting) */
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
