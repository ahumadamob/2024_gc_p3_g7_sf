package imb.gc4.turnero.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

@Entity
public class Turno extends BaseEntity {
	

    private Integer numero;

    @NotNull(message = "La fecha y hora no pueden estar vacías.")
    @Future(message = "La fecha y hora ingresadas ya sucedieron.")
    private LocalDateTime fechaYHora;

    @NotNull(message = "Debe ingresar un paciente.")
    @ManyToOne
    @JoinColumn(name = "pacienteId")
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "salaId")
    private Sala sala;

    @NotNull(message = "Debe ingresar un profesional.")
    @ManyToOne
    @JoinColumn(name = "profesionalId")
    private Profesional profesional;

    // Getters and setters
    // Constructor with necessary fields

    // Constructor vacío
    public Turno() {}

    // Constructor con parámetros
    public Turno(Integer numero, LocalDateTime fechaYHora, Paciente paciente, Sala sala, Profesional profesional) {
        this.numero = numero;
        this.fechaYHora = fechaYHora;
        this.paciente = paciente;
        this.sala = sala;
        this.profesional = profesional;
    }


    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public LocalDateTime getFechaYHora() {
        return fechaYHora;
    }

    public void setFechaYHora(LocalDateTime fechaYHora) {
        this.fechaYHora = fechaYHora;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Profesional getProfesional() {
        return profesional;
    }

    public void setProfesional(Profesional profesional) {
        this.profesional = profesional;
    }
}