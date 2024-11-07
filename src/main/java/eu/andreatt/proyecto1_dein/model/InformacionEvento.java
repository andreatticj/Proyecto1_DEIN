package eu.andreatt.proyecto1_dein.model;

import java.util.Objects;

public class InformacionEvento {

	/** VARIABLES */
	private String nombre, nombreOlimpiada, nombreDeporte;
	
	/** CONSTRUCTOR */
	public InformacionEvento(String nombre, String nombreOlimpiada, String nombreDeporte) {
		this.nombre=nombre;
		this.nombreDeporte=nombreDeporte;
		this.nombreOlimpiada=nombreOlimpiada;
	}

	/** GETTERS Y SETTERS */
	public String getNombre() {
		return nombre;
	}

	public String getNombreOlimpiada() {
		return nombreOlimpiada;
	}

	public String getNombreDeporte() {
		return nombreDeporte;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setNombreOlimpiada(String nombreOlimpiada) {
		this.nombreOlimpiada = nombreOlimpiada;
	}

	public void setNombreDeporte(String nombreDeporte) {
		this.nombreDeporte = nombreDeporte;
	}
	
	/** TO STRING */
	public String toString() {
		return nombre;
	}

	/** EQUALS Y HASHCODE */
	@Override
	public int hashCode() {
		return Objects.hash(nombre, nombreDeporte, nombreOlimpiada);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InformacionEvento other = (InformacionEvento) obj;
		return Objects.equals(nombre, other.nombre) && Objects.equals(nombreDeporte, other.nombreDeporte)
				&& Objects.equals(nombreOlimpiada, other.nombreOlimpiada);
	}
}