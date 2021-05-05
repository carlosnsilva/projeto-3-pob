package modelo;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Visualizacao {
	@Id		
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(columnDefinition = "DATETIME")	//columnDefinition="TIMESTAMP"
	private LocalDateTime datahora = LocalDateTime.now();
	
	private int nota;
	
	@ManyToOne		
	private Usuario usuario;			//lado inverso do relacionamento
	
	@ManyToOne
	private Video video;
	
	public Visualizacao () {};
	
	public Visualizacao(int nota, Usuario usuario, Video video) {
		this.nota = nota;
		this.usuario = usuario;
		this.video = video;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getDataHora() {
		return this.datahora.format(DateTimeFormatter.ofPattern("dd/MM/yyy hh:mm:ss"));
	}
	
	public String getUsuario() {
		return this.usuario.getEmail();
	}
	
	public String getVideo() {
		return this.video.getLink();
	}
	
	public int getNota() {
		return this.nota;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
		
	}

	@Override
	public String toString() {
		return "[id=" + id + 
				", datahora=" + datahora.format(DateTimeFormatter.ofPattern("dd/MM/yyy hh:mm:ss")) + 
				", nota=" + nota +
				"\n usuario=" + usuario.getEmail() + ", video=" + video.getNome() + "]"; 
	}

	
}
