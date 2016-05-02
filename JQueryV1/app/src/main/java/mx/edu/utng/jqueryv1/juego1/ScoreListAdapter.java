package mx.edu.utng.jqueryv1.juego1;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Vector;
import mx.edu.utng.jqueryv1.R;


public class ScoreListAdapter extends BaseAdapter {
    private final Activity actividad;
    private final Vector<String> lista;

    public ScoreListAdapter(Activity actividad, Vector<String> lista) {
          super();
          this.actividad = actividad;
          this.lista = lista;
    }

    @Override 
    public View getView(int position, View convertView, ViewGroup parent) {
    	
          LayoutInflater inflater = actividad.getLayoutInflater();
          View view = inflater.inflate(R.layout.list_element, null, true);
          View v = view.findViewById(R.id.scorePlayer1);
          TextView textView = (TextView)v; 
          textView.setText(lista.elementAt(position).split(" - ")[0]);
          TextView subtextView =(TextView)view.findViewById(R.id.scorePlayer2);
          subtextView.setText(lista.elementAt(position).split(" - ")[1]);
          ImageView imageView=(ImageView)view.findViewById(R.id.icono);
          switch (Math.round((float)Math.random()*4)){
          case 0:
                 imageView.setImageResource(R.drawable.fichaamarilla);
                 break;
          case 1:
                 imageView.setImageResource(R.drawable.fichaazul);
                 break;
          case 2:
	        	  imageView.setImageResource(R.drawable.fichaverde);
	              break;
          default:
                 imageView.setImageResource(R.drawable.ficharoja);
                 break;
          }
          return view;
    }

    @Override
    public int getCount() {
          return lista.size();
    }

    @Override
    public Object getItem(int arg0) {
          return lista.elementAt(arg0);
    }

    @Override
    public long getItemId(int position) {
          return position;
    }
}
