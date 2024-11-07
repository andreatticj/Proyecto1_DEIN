package eu.andreatt.proyecto1_dein.model;

import java.util.Objects;

public class Olimpiada {

	/** VARIABLES */
	private int id_olimpiada, anio;
	private String nombre, temporada, ciudad;
	
	/** CONSTRUCTOR */
	public Olimpiada(int id_olimpiada, String nombre, int anio, String temporada, String ciudad) {
		this.id_olimpiada=id_olimpiada;
		this.nombre=nombre;
		this.anio=anio;
		this.temporada=temporada;
		this.ciudad=ciudad;
	}

	/** GETTERS Y SETTERS */
	public int getId_olimpiada() {
		return id_olimpiada;
	}

	public int getAnio() {
		return anio;
	}

	public String getNombre() {
		return nombre;
	}

	public String getTemporada() {
		return temporada;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setId_olimpiada(int id_olimpiada) {
		this.id_olimpiada = id_olimpiada;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setTemporada(String temporada) {
		this.temporada = temporada;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	
	/** TO STRING */
	public String toString() {
		return nombre;
	}

	/** EQUALS Y HASHCODE */
	@Override
	public int hashCode() {
		return Objects.hash(anio, ciudad, id_olimpiada, nombre, temporada);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Olimpiada other = (Olimpiada) obj;
		return anio == other.anio && Objects.equals(ciudad, other.ciudad) && id_olimpiada == other.id_olimpiada
				&& Objects.equals(nombre, other.nombre) && Objects.equals(temporada, other.temporada);
	}
}