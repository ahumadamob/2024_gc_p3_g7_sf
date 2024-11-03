package imb.gc4.turnero.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Profesional extends BaseEntity {
   

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 60, message = "El nombre no debe superar los 60 caracteres")
    private String nombreProfesional;

    @NotBlank(message = "El apellido no puede estar vacío")
    @Size(max = 60, message = "El apellido no debe superar los 60 caracteres")
    private String apellidoProfesional;

    @OneToMany
    private Turno turno;

    @NotNull(message = "La especialidad no puede ser nula")
    @OneToOne
    @JoinColumn(name="especialidadId")
    private Especialidad especialidad;

	public String getNombreProfesional() {
		return nombreProfesional;
	}
	public void setNombreProfesional(String nombreProfesional) {
		this.nombreProfesional = nombreProfesional;
	}
	public String getApellidoProfesional() {
		return apellidoProfesional;
	}
	public void setApellidoProfesional(String apellidoProfesional) {
		this.apellidoProfesional = apellidoProfesional;
	}
	public Especialidad getEspecialidad() {
		return especialidad;
	}
	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}
	

	
}
