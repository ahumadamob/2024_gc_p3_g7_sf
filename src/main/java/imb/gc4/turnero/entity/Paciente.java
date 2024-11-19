package imb.gc4.turnero.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

@Entity
public class Paciente extends BaseEntity{
	
 
	
	@NotBlank(message = "El nombre no puede estar vacío.")
	@Size(max = 40, message = "El nombre no debe superar los 40 caracteres.")
	private String nombre;
	
	@NotBlank(message = "El apellido no puede estar vacío.")
	@Size(max = 40, message = "El apellido no debe superar los 40 caracteres.")
	private String apellido;
	
	@NotBlank(message = "El dni no puede estar vacío.")
	@Size(max = 12, message = "El dni no debe superar los 12 caracteres.")
	private String dni;
	
	@NotBlank(message = "El domicilio no puede estar vacío.")
	@Size(max = 60, message = "El domicilio no debe superar los 60 caracteres.")
	private String domicilio;
	
	@NotNull(message = "La fecha y hora no pueden estar vacías.")
	@Past(message = "La fecha y hora ingresadas ya sucedieron.")
	private LocalDate fechaNacimiento;
	
	//@Min(value=1, message="El id de la mutual debe ser mayor que 1.")
	@ManyToOne
	@JoinColumn(name = "mutualId")
	private Mutual mutual;
	
	@NotNull(message = "Debe definir el estado del paciente si activo o inactivo")
	@Size(max = 15, message = "Debe ser activo o inactivo el valor asignado")
	private String estado;
	
	@ManyToOne
	@JoinColumn(name = "profesional_id")
	private Profesional profesional;
	
	
	public Profesional getProfesional() {
		return profesional;
	}
	public void setProfesional(Profesional profesional) {
		this.profesional = profesional;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombrePaciente) {
		this.nombre = nombrePaciente;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellidoPaciente) {
		this.apellido = apellidoPaciente;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
}
