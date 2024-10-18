package imb.gc4.turnero.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@MappedSuperclass
public abstract class BaseEntity {
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id;
	  @Column(name = "fecha_creacion")
	    private LocalDateTime fechaCreacion;
	  @Column(name = "fecha_actualizacion")
	    private LocalDateTime fechaActualizacion;
	  
	  @PrePersist
	    protected void onCreate() {
	        fechaCreacion = LocalDateTime.now();
	    }
	  
	  @PreUpdate
	    protected void onUpdate() {
	        fechaActualizacion = LocalDateTime.now();
	    }
	  

	  
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public LocalDateTime getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}
	    
	  
}
