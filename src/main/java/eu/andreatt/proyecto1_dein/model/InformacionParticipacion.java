package eu.andreatt.proyecto1_dein.model;

import java.util.Objects;

public class InformacionParticipacion {
	
	/** VARIABLES */
	private String nombreDeportista, nombreEvento, nombreEquipo, medalla;
	private int edad;
	
	/** CONSTRUCTOR */
	public InformacionParticipacion(String nombreDeportista, String nombreEvento, String nombreEquipo, int edad, String medalla) {
		this.nombreDeportista=nombreDeportista;
		this.nombreEvento=nombreEvento;
		this.nombreEquipo=nombreEquipo;
		this.edad=edad;
		this.medalla=medalla;
	}

	/** GETTERS Y SETTERS */
	public String getNombreDeportista() {
		return nombreDeportista;
	}

	public String getNombreEvento() {
		return nombreEvento;
	}

	public String getNombreEquipo() {
		return nombreEquipo;
	}

	public String getMedalla() {
		return medalla;
	}

	public int getEdad() {
		return edad;
	}

	public void setNombreDeportista(String nombreDeportista) {
		this.nombreDeportista = nombreDeportista;
	}

	public void setNombreEvento(String nombreEvento) {
		this.nombreEvento = nombreEvento;
	}

	public void setNombreEquipo(String nombreEquipo) {
		this.nombreEquipo = nombreEquipo;
	}

	public void setMedalla(String medalla) {
		this.medalla = medalla;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	/** EQUALS Y HASHCODE */
	@Override
	public int hashCode() {
		return Objects.hash(edad, medalla, nombreDeportista, nombreEquipo, nombreEvento);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InformacionParticipacion other = (InformacionParticipacion) obj;
		return edad == other.edad && Objects.equals(medalla, other.medalla)
				&& Objects.equals(nombreDeportista, other.nombreDeportista)
				&& Objects.equals(nombreEquipo, other.nombreEquipo) && Objects.equals(nombreEvento, other.nombreEvento);
	}
}