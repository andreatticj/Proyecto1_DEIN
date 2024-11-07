package eu.andreatt.proyecto1_dein.model;

import java.util.Objects;

public class Equipo {

	/** VARIABLES */
	private int id_equipo;
	private String nombre, iniciales;
	
	/** CONSTRUCTOR */
	public Equipo(int id_equipo, String nombre, String iniciales) {
		this.id_equipo=id_equipo;
		this.nombre=nombre;
		this.iniciales=iniciales;
	}

	/** GETTERS Y SETTERS */
	public int getId_equipo() {
		return id_equipo;
	}

	public String getNombre() {
		return nombre;
	}

	public String getIniciales() {
		return iniciales;
	}

	public void setId_equipo(int id_equipo) {
		this.id_equipo = id_equipo;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setIniciales(String iniciales) {
		this.iniciales = iniciales;
	}
	
	/** TO STRING */
	public String toString() {
		return nombre;
	}

	/** EQUALS Y HASHCODE */
	@Override
	public int hashCode() {
		return Objects.hash(id_equipo, iniciales, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Equipo other = (Equipo) obj;
		return id_equipo == other.id_equipo && Objects.equals(iniciales, other.iniciales)
				&& Objects.equals(nombre, other.nombre);
	}
}