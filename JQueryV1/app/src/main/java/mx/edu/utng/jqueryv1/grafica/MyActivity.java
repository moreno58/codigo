package mx.edu.utng.jqueryv1.grafica;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import mx.edu.utng.jqueryv1.R;

public class MyActivity extends ActionBarActivity {

    Button btnAgregarMiembro;
    ListView lista;
    SQLControlador dbconeccion;
    TextView tv_miemID, tv_miemNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        dbconeccion = new SQLControlador(this);
        dbconeccion.abrirBaseDeDatos();
        btnAgregarMiembro = (Button) findViewById(R.id.btnAgregarMiembro);
        lista = (ListView) findViewById(R.id.listViewMiembros);

        //acción del boton agregar miembro
        btnAgregarMiembro.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iagregar = new Intent(MyActivity.this, AgregarCalificacion.class);
                startActivity(iagregar);
            }
        });

        // Tomar los datos desde la base de datos para poner en el curso y después en el adapter
        Cursor cursor = dbconeccion.leerDatos();

        String[] from = new String[] {
                DBhelper.CALIFICACION_ID,
                DBhelper.CALIFICACION_NOMBRE
        };
        int[] to = new int[] {
                R.id.miembro_id,
                R.id.miembro_nombre
        };

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                MyActivity.this, R.layout.formato_fila, cursor, from, to);

        adapter.notifyDataSetChanged();
        lista.setAdapter(adapter);

        // acción cuando hacemos click en item para poder modificarlo o eliminarlo
        lista.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {

                tv_miemID = (TextView) view.findViewById(R.id.miembro_id);
                tv_miemNombre = (TextView) view.findViewById(R.id.miembro_nombre);

                String aux_miembroId = tv_miemID.getText().toString();
                String aux_miembroNombre = tv_miemNombre.getText().toString();

                Intent modify_intent = new Intent(getApplicationContext(), ModificarCalificacion.class);
                modify_intent.putExtra("miembroId", aux_miembroId);
                modify_intent.putExtra("miembroNombre", aux_miembroNombre);
                startActivity(modify_intent);
            }
        });
    }  //termina el onCreate
} //termina clase