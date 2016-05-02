package mx.edu.utng.jqueryv1.recetario;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Vector;

import mx.edu.utng.jqueryv1.R;

public class RecetasAdaptador extends BaseAdapter {
    private final Activity actividad;
    private final Vector<ObjetoReceta> lista;

    public RecetasAdaptador(Activity actividad, Vector<ObjetoReceta> lista) {
          super();
          this.actividad = actividad;
          this.lista = lista;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
          LayoutInflater inflater = actividad.getLayoutInflater();
          View view = inflater.inflate(R.layout.elemento_lista, null, true);
          TextView textView = (TextView)view.findViewById(R.id.titulo);
          textView.setText(lista.elementAt(position).getTitulo());
          TextView subtextView = (TextView)view.findViewById(R.id.subtitulo);
          subtextView.setText(lista.elementAt(position).getDificultad());
          ImageView imageView = (ImageView)view.findViewById(R.id.icono);
          imageView.setImageResource(R.drawable.ic_launcher);
          return view;
    }

    public int getCount() {
          return lista.size();
    }

    public Object getItem(int arg0) {
          return lista.elementAt(arg0);
    }

    public long getItemId(int position) {
          return position;
    }
}
