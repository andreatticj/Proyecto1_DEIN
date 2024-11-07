package eu.andreatt.proyecto1_dein.model;

import java.util.Objects;

public class Deporte {

	/** VARIABLES */
	private int id_deporte;
	private String nombre;
	
	/** CONSTRUCTOR */
	public Deporte(int id_deporte, String nombre) {
		this.id_deporte=id_deporte;
		this.nombre=nombre;
	}

	/** GETTERS Y SETTERS */
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getId_deporte() {
		return id_deporte;
	}

	public void setId_deporte(int id_deporte) {
		this.id_deporte = id_deporte;
	}

	/** EQUALS Y HASHCODE */
	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}
	
	/** TO STRING */
	public String toString() {
		return nombre;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Deporte other = (Deporte) obj;
		return Objects.equals(nombre, other.nombre);
	}
}