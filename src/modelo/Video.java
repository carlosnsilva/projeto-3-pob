package modelo;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="video")
public class Video {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String link;
	private String nome;
	private double media;
	private String versao;
	
	@ManyToMany(mappedBy="videos", 
				cascade={CascadeType.ALL}) 	
	private List<Assunto> assuntos = new ArrayList<>();
	
	
	@OneToMany(mappedBy="video", 
			cascade={CascadeType.ALL}) 	
	private List<Visualizacao> visualizacoes = new ArrayList<>();

	public Video () {};
	
	public Video(String link, String nome) {
		this.link = link;
		this.nome = nome;
	}
	
	public String getVersao() {
		return versao;
	}

	public void setVersao(String versao) {
		this.versao = versao;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setMedia(double media) {
		this.media = media;
	}

	public void setAssuntos(List<Assunto> assuntos) {
		this.assuntos = assuntos;
	}

	public void setVisualizacoes(List<Visualizacao> visualizacoes) {
		this.visualizacoes = visualizacoes;
	}

	
	public String getNome() {
		return nome;
	}
	
	public String getLink() {
		return link;
	}
	
	public double getMedia() {
		double total = 0;
		for(Visualizacao v : visualizacoes) {
		    total += v.getNota();
		}
		media = total / visualizacoes.size();
		return media;
	}
	
	public List<Assunto> getAssuntos() {
		return assuntos;
	}

	public void adicionar(Assunto a) {
		assuntos.add(a);
	}
	
	public List<Visualizacao> getVisualizacoes() {
		return visualizacoes;
	}

	public void adicionar(Visualizacao vis) {
		visualizacoes.add(vis);
	}
	
	public void remover(Visualizacao v){
		visualizacoes.remove(v);
	}


	@Override
	public String toString() {
		String texto = "\nVideo [" + (link != null ? "link=" + link + ", " : "") + (nome != null ? "nome=" + nome + ", " : "")
				+ "media=" + getMedia() + ", ";
		
		texto+=", assuntos=";
		for(Assunto a : assuntos) {
			texto += a.getPalavra() + ",";
		}
		texto+="\n visualizacoes=";
		for(Visualizacao vis : visualizacoes) {
			//texto += vis;
			texto += (vis != null ? vis + ", " : "");
		}
		return texto;
	}
}
