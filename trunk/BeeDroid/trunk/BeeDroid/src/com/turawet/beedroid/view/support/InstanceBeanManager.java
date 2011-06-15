/**
 * 
 */
package com.turawet.beedroid.view.support;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.turawet.beedroid.beans.DateFieldBean;
import com.turawet.beedroid.beans.FieldOptionBean;
import com.turawet.beedroid.beans.GenericInstanceFieldBean;
import com.turawet.beedroid.beans.InstanceBean;
import com.turawet.beedroid.beans.RadioFieldBean;
import com.turawet.beedroid.beans.TextFieldBean;
import com.turawet.beedroid.constants.Cte.FieldType;
import com.turawet.beedroid.listener.MyLocationListener;
import com.turawet.beedroid.view.FieldView;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
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
	private int			sectionIndex;
	
	public InstanceBeanManager(Context context, InstanceBean instance)
	{
		this.instance = instance;
		this.context = context;
	}
	
	public String instanceToXml() throws IllegalArgumentException, IllegalStateException, IOException
	{
		Writer writer = new StringWriter();
		instance.toXml(writer);
		return writer.toString();
	}
	
	/**
	 * @return
	 */
	public List<FieldView> getAllInstanceViews()
	{
		List<FieldView> views = new ArrayList<FieldView>();
		int numberOfSections = instance.getSections().size();
		for (int section = 0; section < numberOfSections; section++)
		{
			int numberOfFields = instance.getSections().get(section).getSectionChildren().size();
			for (int field = 0; field < numberOfFields; field++)
			{
				views.add(getNewFieldView(section, field));
			}
		}
		
		if (instance.getForm().isGeolocalized())
		{
			views.add(getNewGeolocalizedView());
		}
		return views;
	}
	
	/**
	 * @return
	 */
	private FieldView getNewGeolocalizedView()
	{
		final TextView latitudText = new TextView(context);
		latitudText.setText("Latitud: ");
		latitudText.setTextColor(Color.WHITE);
		latitudText.setGravity(Gravity.TOP);
		latitudText.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 0.0F));
		
		final TextView longitudText = new TextView(context);
		longitudText.setText("Longitud: ");
		longitudText.setTextColor(Color.WHITE);
		longitudText.setGravity(Gravity.TOP);
		longitudText.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 0.0F));
		
		Button geoButton = new Button(context);
		
		geoButton.setText("Obtener posición");
		// text.setText(textField.getValue());
		geoButton.setGravity(Gravity.TOP);
		geoButton.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 0.0F));
		
		geoButton.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				// Acquire a reference to the system Location Manager
				final LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
				
				// Define a listener that responds to location updates
				final LocationListener locationListener = new MyLocationListener();
				
				// Register the listener with the Location Manager to receive
				// location updates
				locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
				locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
				
				final ProgressDialog pd = ProgressDialog.show(context, "", "Conectando...", true, false);
				new Thread(new Runnable()
				{
					public void run()
					{
						while (((MyLocationListener) locationListener).locationIsFounded())
						{
							try
							{
								Thread.sleep(1000);
							}
							catch (InterruptedException e)
							{
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						locationManager.removeUpdates(locationListener);
						longitudText.setText("Longitud: " + ((MyLocationListener) locationListener).getLongitud());
						latitudText.setText("Latitud: " + ((MyLocationListener) locationListener).getLatitud());
						pd.dismiss();
					}
				}).start();
				
			}
		});
		String sectionTitle = "";
		String fieldLabel = "Geolocalización";
		
		FieldView fieldView = new FieldView(context, null);
		fieldView.setGravity(Gravity.CENTER_HORIZONTAL);
		fieldView.setOrientation(LinearLayout.VERTICAL);
		fieldView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT, 0.0F));
		
		fieldView.setSectionTitle(sectionTitle);
		fieldView.setField(geoButton, fieldLabel);
		fieldView.addView(longitudText);
		fieldView.addView(latitudText);
		return fieldView;
	}
	
	/**
	 * @param section
	 * @param field
	 * @return
	 */
	private final FieldView getNewFieldView(int sectionInd, int fieldInd)
	{
		GenericInstanceFieldBean fieldBean = (GenericInstanceFieldBean) instance.getSections().get(sectionInd).getSectionChildren().get(fieldInd);
		
		FieldType fieldType = fieldBean.getFormField().getType();
		FieldView fieldView = null;
		switch (fieldType)
		{
			case TEXT:
				fieldView = getNewTextFieldView((TextFieldBean) fieldBean);
				break;
			case DATE:
				fieldView = getNewDateFieldView((DateFieldBean) fieldBean);
				break;
			case RADIO:
				fieldView = getNewRadioFieldView((RadioFieldBean) fieldBean);
				break;
			default:
				break;
		}
		
		return fieldView;
	}
	
	/**
	 * @param fieldBean
	 * @return
	 */
	private FieldView getNewRadioFieldView(RadioFieldBean radioField)
	{
		RadioGroup radioGroup = new RadioGroup(context);
		
		List<FieldOptionBean> options = radioField.getFormField().getFieldOptions();
		int id = 0;
		for (FieldOptionBean option : options)
		{
			RadioButton radio = new RadioButton(context);
			radio.setText(option.getLabel());
			radio.setId(id++);
			radioGroup.addView(radio);
		}
		
		String sectionTitle = instance.getSections().get(sectionIndex).getName();
		return getNewFieldView(radioField, sectionTitle, radioGroup);
	}
	
	/**
	 * @param fieldBean
	 * @return
	 */
	private FieldView getNewDateFieldView(DateFieldBean dateField)
	{
		DatePicker datePicker = new DatePicker(context);
		
		// text.setText(textField.getValue());
		datePicker.setForegroundGravity(Gravity.TOP);
		datePicker.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 0.0F));
		
		String sectionTitle = instance.getSections().get(sectionIndex).getName();
		return getNewFieldView(dateField, sectionTitle, datePicker);
	}
	
	/**
	 * @param fieldBean
	 * @return
	 */
	private FieldView getNewTextFieldView(TextFieldBean textField)
	{
		
		EditText text = new EditText(context);
		
		text.setText(textField.getText());
		text.setText("Texto por defecto...");
		text.setGravity(Gravity.TOP);
		text.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 0.0F));
		
		String sectionTitle = instance.getSections().get(sectionIndex).getName();
		return getNewFieldView(textField, sectionTitle, text);
	}
	
	/**
	 * @return
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
	 * @param value
	 * @param mCurrentPage
	 * 
	 */
	private void saveDataToPage(int section, int field, Object value)
	{
		GenericInstanceFieldBean instanceBean = (GenericInstanceFieldBean) instance.getSections().get(section).getSectionChildren().get(field);
		FieldType type = instanceBean.getFormField().getType();
		switch (type)
		{
			case TEXT:
				((TextFieldBean) instanceBean).setText((String) value);
				break;
			case DATE:
				((DateFieldBean) instanceBean).setDate((Date) value);
				break;
			case RADIO:
				((RadioFieldBean) instanceBean).setValue(((Integer) value).intValue());
				break;
			default:
				break;
		}
	}
	
	/**
	 * @param childAt
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
				saveDataToPage(section, field, value);
			}
		}
	}
	
}
