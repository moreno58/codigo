package mx.edu.utng.jqueryv1.recetario;

public class ObjetoReceta {
	
	public Integer id;
	public String dificultad, titulo, ingredientes, elaboracion;
	
	public ObjetoReceta(Integer id, String dificultad, String titulo, String ingredientes, String elaboracion) {
		this.id = id;
		this.dificultad = dificultad;
		this.titulo = titulo;
		this.ingredientes = ingredientes;
		this.elaboracion = elaboracion;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDificultad() {
		return dificultad;
	}

	public void setDificultad(String dificultad) {
		this.dificultad = dificultad;
	}

	public String getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(String ingredientes) {
		this.ingredientes = ingredientes;
	}

	public String getElaboracion() {
		return elaboracion;
	}

	public void setElaboracion(String elaboracion) {
		this.elaboracion = elaboracion;
	}
	
	
}
