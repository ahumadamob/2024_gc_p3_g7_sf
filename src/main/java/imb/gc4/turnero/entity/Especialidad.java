package imb.gc4.turnero.entity;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Especialidad extends BaseEntity{
	
	
	@NotBlank(message = "El nombre de la especialidad no puede estar vacío")
	@Size(max = 40, message = "El nombre de la especialidad no debe superar los 40 caracteres")
	private String nombre;
	
	@NotNull
	private boolean actividad;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public boolean isActividad() {
		return actividad;
	}
	public void setActividad(boolean actividad) {
		this.actividad = actividad;
	}

}
