package mx.edu.utng.jqueryv1.recetario;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import mx.edu.utng.jqueryv1.R;

public class Recetas extends ListActivity {

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listado_recetas);
        setListAdapter(new RecetasAdaptador(this, Main.almacen.listaRecetas()));        
	}
	
	@Override
	protected void onListItemClick(ListView listView, View view, int position, long id) {
		super.onListItemClick(listView, view, position, id);
		Intent i = new Intent(this, RecetaDetalle.class);
		i.putExtra("id", position+1 );
		startActivity(i);
	}
	
}
