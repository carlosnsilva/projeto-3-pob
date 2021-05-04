package modelo;

import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
public class Visualizacao {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(columnDefinition = "DATE")
	private LocalDateTime dataHora = LocalDateTime.now();
	
	private int nota;
	
	private Video video;
	private Usuario usuario;
	
	public Visualizacao() {}
	
	public Visualizacao(int nota, Video video, Usuario usuario) {
		this.nota = nota;
		this.video = video;
		this.usuario = usuario;
	}
	
	public int getId() {
		return id;
	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}

}
