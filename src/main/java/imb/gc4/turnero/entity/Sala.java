package imb.gc4.turnero.entity;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Sala extends BaseEntity {
	
	
	@NotBlank(message = "El nombre no puede estar vacío")
	@Size(max = 20, message = "El nombre no debe superar los 20 caracteres")
	private	String nombre;
	private boolean disponibles;
	@NotBlank(message = "La ubicacion no puede estar vacío")
	@Size(max = 30, message = "La ubicacion no debe superar los 30 caracteres")
	private String ubicacion;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public boolean isDisponibles() {
		return disponibles;
	}
	public void setDisponibles(boolean disponibles) {
		this.disponibles = disponibles;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	
	
}
