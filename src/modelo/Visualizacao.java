package modelo;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="visualizacao")
public class Visualizacao {
	@Id		
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String dataHora = String.valueOf((LocalDateTime.now()));
	private int nota;
	private String versao;
	
	@ManyToOne(cascade={CascadeType.ALL})
	private Usuario usuario;
	
	@ManyToOne(cascade={CascadeType.ALL})
	private Video video;

	public Visualizacao() {

	}

	public Visualizacao( int nota, Usuario usuario, Video video) throws Exception {
		this.nota = nota;
		this.usuario = usuario;
		this.video = video;
	}
	
	public String getDatahora() {
		return dataHora;
	}

	public void setDatahora(String datahora) {
		this.dataHora = datahora;
	}

	public String getVersao() {
		return versao;
	}

	public void setVersao(String versao) {
		this.versao = versao;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}

	public void setVideo(Video video) {
		this.video = video;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getDataHora() {
		return this.dataHora;
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
				", datahora=" + dataHora + 
				", nota=" + nota +
				"\n usuario=" + usuario.getEmail() + ", video=" + video.getNome() + "]"; 
	}

	
}
