/**
 * 
 */
package com.turawet.beedroid.activity;

import com.turawet.beedroid.R;
import com.turawet.beedroid.constantes.Cte;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

/**
 * @author nicopernas
 * 
 */
public class OptionsActivity extends Activity
{

	/**
	 *
	 */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.options);
		
		TextView repoUrlTextView = (TextView) findViewById(R.id.repo_server_url);
		
		// Obtenemos las preferencias
		SharedPreferences settings = getPreferences(MODE_PRIVATE);
		
		// Obtenemos la direción del repositorio
		String repositoryUrl =  settings.getString(Cte.Tags.REPOSITORY_URL, Cte.WSClient.REPOSITORY_URL);
		
		// Se muestra en la caja e texto
		repoUrlTextView.setText(repositoryUrl);
	}
	
	/**
	 * 
	 */
	@Override
	protected void onStop() 
	{
		super.onStop();
		SharedPreferences settings = getPreferences(MODE_PRIVATE);
		TextView repoUrlTextView = (TextView) findViewById(R.id.repo_server_url);
		
		// Obtenemos la dirección del repositorio que intridujo el usuario
		String repositoryUrl = repoUrlTextView.getText().toString(); 
      SharedPreferences.Editor editor = settings.edit();
      
      // La almacenamos en las preferencias
      editor.putString(Cte.Tags.REPOSITORY_URL, repositoryUrl);
      editor.commit();
		
	}
}
