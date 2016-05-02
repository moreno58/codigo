package mx.edu.utng.jqueryv1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import mx.edu.utng.jqueryv1.grafica.SQLControlador;

public class GraficaActivity extends AppCompatActivity {
SQLControlador db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            db = new SQLControlador(getApplication());
db.abrirBaseDeDatos();
        //Valores a mostrar en la gráfica

        ArrayList<BarEntry> entradas = new ArrayList<>();
        entradas.add(new BarEntry(Integer.parseInt(db.resultado("1")), 0));
        entradas.add(new BarEntry(Integer.parseInt(db.resultado("2")), 1));
        entradas.add(new BarEntry(Integer.parseInt(db.resultado("3")), 2));
        entradas.add(new BarEntry(Integer.parseInt(db.resultado("4")), 3));
        entradas.add(new BarEntry(Integer.parseInt(db.resultado("5")), 4));
        //entradas.add(new BarEntry(Integer.parseInt(db.resultado("1")), 5));

        //Creamos el conjunto de datos a partir de las entradas

        BarDataSet dataset = new BarDataSet(entradas, "# de Temas");

        //Etiquetas para el eje X

        ArrayList<String> etiquetas = new ArrayList<String>();
        etiquetas.add("Tema 1");
        etiquetas.add("Tema 2");
        etiquetas.add("Tema 3");
        etiquetas.add("Tema 4");
        etiquetas.add("Tema 5");
       // etiquetas.add("Junio");

        //Aplicamos una plantillas de colores al conjunto de datos
        dataset.setColors(ColorTemplate.PASTEL_COLORS);

        //Definimos la gráfica

        BarChart grafica = new BarChart(getApplicationContext());
        setContentView(grafica);

        //Incluimos los datos y etiquetas en la gráfica

        BarData datos = new BarData(etiquetas, dataset);
        grafica.setData(datos);

        //Añadimos una descripción a la gráfica
        grafica.setDescription("# Modulos que has terminado");

        //Aplicamos una animación al eje Y
        grafica.animateY(5000);

        //Incluímos una línea límite
        LimitLine linea = new LimitLine(22f);
        YAxis ejeY = grafica.getAxisLeft();
        ejeY.addLimitLine(linea);
    }
}
