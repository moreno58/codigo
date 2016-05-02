package mx.edu.utng.jqueryv1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Euroconvert extends Activity {
	/** Called when the activity is first created. */

	// Button

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.euroconvert);
	}

	public void convert_2_pesetas(View view) {
		EditText convertir = (EditText) findViewById(R.id.euroentrada);
		TextView convertido = (TextView) findViewById(R.id.eurosalidapesetas);
		String texto = this.getString(R.string.converted);
		
		Double resultado = Double.parseDouble(convertir.getText().toString()) * 166.386;
		
		convertido.setText(texto + " " + resultado.toString() + " pesetas");
	}
	
	public void go_out(View view) {
		this.finish();
	}
}