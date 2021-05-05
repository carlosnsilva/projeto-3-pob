package modelo;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Assunto {
	@Id
	private String palavra;
	
	private String versao;

	@OneToMany(mappedBy="assunto", 
			cascade=CascadeType.ALL, 	
			orphanRemoval=true,			//default � false
			fetch=FetchType.EAGER) 		//default � LAZY
	private List<Video> videos = new ArrayList<>();
	
	public Assunto () {};
	
	public Assunto(String palavra) {
		this.palavra = palavra;
	}
	
	public String getVersao() {
		return versao;
	}

	public void setVersao(String versao) {
		this.versao = versao;
	}

	public List<Video> getVideos() {
		return videos;
	}

	public void setVideos(List<Video> videos) {
		this.videos = videos;
	}

	public void setPalavra(String palavra) {
		this.palavra = palavra;
	}

	public String getPalavra() {
		return palavra;
	}

	public void adicionar(Video v) {
		videos.add(v);
	}
	
	@Override
	public String toString() {
		String texto = "Assunto [palavra=" + palavra;
		for(Video v : videos) {
			texto += v.getNome();
		}
		return texto;
	}
	
	
	
}
