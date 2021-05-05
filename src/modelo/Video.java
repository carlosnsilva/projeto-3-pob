package modelo;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Video {
	@Id
	private String link;
	
	private String nome;
	private double media;
	private String dataHora;
	
	//Relacionamento bidirecional muitos para muitos
	@ManyToMany(mappedBy="assuntos", 
				cascade={CascadeType.PERSIST,CascadeType.MERGE}) 	
	private List<Assunto> assuntos = new ArrayList<>();
	
	
	@OneToMany(mappedBy="visualizacoes", 
			cascade=CascadeType.ALL, 	
			orphanRemoval=true,			//default � false
			fetch=FetchType.EAGER) 		//default � LAZY
	private List<Visualizacao> visualizacoes = new ArrayList<>();

	public Video () {};
	
	public Video(String link, String nome, String palavra, String dataStr) {
		this.link = link;
		this.nome = nome;
		this.assuntos.add(new Assunto(palavra));
		this.dataHora = dataStr;
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
	
	public String getDataHora() {
		return this.dataHora;
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

	@Override
	public String toString() {
		String texto = "\nVideo [" + (link != null ? "link=" + link + ", " : "") + (nome != null ? "nome=" + nome + ", " : "")
				+ "media=" + getMedia() + ", " + "dataHora=" + dataHora;
		
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
