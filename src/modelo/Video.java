package modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import modelo.*;

@Entity
public class Video {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String link;
	
	private String nome;
	
	private double media;

	@Column(columnDefinition = "DATE")
	private LocalDateTime dataHora = LocalDateTime.now();
	
	@ManyToMany(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	List<Assunto> assuntos = new ArrayList<>();
	
	@OneToMany (cascade=CascadeType.ALL, orphanRemoval = true) 
	@JoinColumn(name="dataHora") 
	private List<Visualizacao> visualizacoes = new ArrayList<>();

	public Video() {}
	
	public Video(String link, String nome, String palavra, LocalDateTime data) {
		this.link = link;
		this.nome = nome;
		this.assuntos.add(new Assunto(palavra));
		this.dataHora = data;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getMedia() {
		return media;
	}

	public void setClassificacao(double media) {
		this.media = media;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}
	
	public List<Assunto> getAssuntos() {
		return assuntos;
	}

	public void setAssuntos(List<Assunto> assuntos) {
		this.assuntos = assuntos;
	}

	public List<Visualizacao> getVisualizacoes() {
		return visualizacoes;
	}

	public void setVisualizacoes(List<Visualizacao> visualizacoes) {
		this.visualizacoes = visualizacoes;
	}
	
}
