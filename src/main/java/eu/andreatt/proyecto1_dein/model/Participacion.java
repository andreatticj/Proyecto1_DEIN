package eu.andreatt.proyecto1_dein.model;

import java.util.Objects;

public class Participacion {
	
	/** VARIABLES */
	private int id_deportista, id_evento, id_equipo, edad;
	private String medalla;
	
	/** CONSTRUCTOR */
	public Participacion(int id_deportista, int id_evento, int id_equipo, int edad, String medalla) {
		this.id_deportista=id_deportista;
		this.id_evento=id_evento;
		this.id_equipo=id_equipo;
		this.edad=edad;
		this.medalla=medalla;
	}

	/** GETTERS Y SETTERS */
	public int getId_deportista() {
		return id_deportista;
	}

	public int getId_evento() {
		return id_evento;
	}

	public int getId_equipo() {
		return id_equipo;
	}

	public int getEdad() {
		return edad;
	}

	public String getMedalla() {
		return medalla;
	}

	public void setId_deportista(int id_deportista) {
		this.id_deportista = id_deportista;
	}

	public void setId_evento(int id_evento) {
		this.id_evento = id_evento;
	}

	public void setId_equipo(int id_equipo) {
		this.id_equipo = id_equipo;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public void setMedalla(String medalla) {
		this.medalla = medalla;
	}

	/** EQUALS Y HASHCODE */
	@Override
	public int hashCode() {
		return Objects.hash(edad, id_deportista, id_equipo, id_evento, medalla);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Participacion other = (Participacion) obj;
		return edad == other.edad && id_deportista == other.id_deportista && id_equipo == other.id_equipo
				&& id_evento == other.id_evento && Objects.equals(medalla, other.medalla);
	}
}