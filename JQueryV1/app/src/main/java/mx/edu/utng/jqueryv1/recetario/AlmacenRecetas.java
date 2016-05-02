package mx.edu.utng.jqueryv1.recetario;

import java.util.Vector;

public interface AlmacenRecetas {
	public void guardarReceta(String nombre);
	public Vector<ObjetoReceta> listaRecetas();
	public ObjetoReceta getReceta(Integer id);
}