package mx.edu.utng.jqueryv1.juego1;

import android.app.ListActivity;
import android.os.Bundle;

import mx.edu.utng.jqueryv1.R;


public class Puntuaciones extends ListActivity {
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.puntuaciones);
	        setListAdapter(
	             new ScoreListAdapter(this, PenguinsoniceMainActivity.almacen.listaPuntuaciones(10)));

	    }
}
