package eu.andreatt.proyecto1_dein.model;

import java.io.InputStream;
import java.util.Objects;

public class Deportista {
	
	/** VARIABLES */
	private int id_deportista, peso, altura;
	private String nombre,sexo;
	private InputStream foto;
	
	/** CONSTRUCTOR */
	public Deportista(int id_deportista, String nombre, String sexo, int peso, int altura, InputStream foto) {
		this.id_deportista=id_deportista;
		this.nombre=nombre;
		this.sexo=sexo;
		this.peso=peso;
		this.altura=altura;
		this.foto=foto;
	}
	
	/** CONSTRUCTOR SIN FOTO */
	public Deportista(int id_deportista, String nombre, String sexo, int peso, int altura) {
		this.id_deportista=id_deportista;
		this.nombre=nombre;
		this.sexo=sexo;
		this.peso=peso;
		this.altura=altura;
		this.foto=null;
	}

	/** GETTERS Y SETTERS */
	public int getId_deportista() {
		return id_deportista;
	}

	public int getPeso() {
		return peso;
	}

	public int getAltura() {
		return altura;
	}

	public String getNombre() {
		return nombre;
	}

	public String getSexo() {
		return sexo;
	}

	public InputStream getFoto() {
		return foto;
	}

	public void setId_deportista(int id_deportista) {
		this.id_deportista = id_deportista;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public void setFoto(InputStream foto) {
		this.foto = foto;
	}
	
	/** TO STRING */
	public String toString() {
		return nombre;
	}

	/** EQUALS Y HASHCODE */
	@Override
	public int hashCode() {
		return Objects.hash(altura, id_deportista, nombre, peso, sexo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Deportista other = (Deportista) obj;
		return altura == other.altura && id_deportista == other.id_deportista && Objects.equals(nombre, other.nombre)
				&& peso == other.peso && Objects.equals(sexo, other.sexo);
	}
}