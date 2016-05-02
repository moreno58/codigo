package mx.edu.utng.jqueryv1.juego1;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import mx.edu.utng.jqueryv1.R;


public class Preferencias extends PreferenceActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferencias);
	}
}
