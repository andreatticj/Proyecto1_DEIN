package eu.andreatt.proyecto1_dein.model;

import java.util.Objects;

public class Evento {

	/** VARIABLES */
	private int id_evento, id_olimpiada, id_deporte;
	private String nombre;
	
	/** CONSTRUCTOR */
	public Evento(int id_evento, String nombre, int id_olimpiada, int id_deporte) {
		this.id_evento=id_evento;
		this.nombre=nombre;
		this.id_olimpiada=id_olimpiada;
		this.id_deporte=id_deporte;
	}

	/** GETTERS Y SETTERS */
	public int getId_evento() {
		return id_evento;
	}

	public int getId_olimpiada() {
		return id_olimpiada;
	}

	public int getId_deporte() {
		return id_deporte;
	}

	public String getNombre() {
		return nombre;
	}

	public void setId_evento(int id_evento) {
		this.id_evento = id_evento;
	}

	public void setId_olimpiada(int id_olimpiada) {
		this.id_olimpiada = id_olimpiada;
	}

	public void setId_deporte(int id_deporte) {
		this.id_deporte = id_deporte;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/** TO STRING */
	public String toString() {
		return nombre;
	}

	/** EQUALS Y HASHCODE */
	@Override
	public int hashCode() {
		return Objects.hash(id_deporte, id_evento, id_olimpiada, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Evento other = (Evento) obj;
		return id_deporte == other.id_deporte && id_evento == other.id_evento && id_olimpiada == other.id_olimpiada
				&& Objects.equals(nombre, other.nombre);
	}
}