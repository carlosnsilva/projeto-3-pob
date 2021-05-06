package modelo;
import java.util.ArrayList;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="usuario")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String email;
	private String versao;
	private Date dataNasc;
	
	@OneToMany(mappedBy="usuario")
	private List<Visualizacao> visualizacoes = new ArrayList<>();
	
	public Usuario() {};
	
	public Usuario(String email, String dataNasc) throws Exception {
		this.email = email;
		this.dataNasc = new SimpleDateFormat("dd/MM/yyyy").parse(dataNasc);
	}

	public String getEmail() {
		return email;
	}
	
	public void adicionar(Visualizacao v){
		visualizacoes.add(v);
	}
	
	public List<Visualizacao> getVisualizacoes(){
		return visualizacoes;
	}
	
	public String getVersao() {
		return versao;
	}

	public void setVersao(String versao) {
		this.versao = versao;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setVisualizacoes(List<Visualizacao> visualizacoes) {
		this.visualizacoes = visualizacoes;
	}
	
	
	@Override
	public String toString() {
		String texto =  "\nUsuario [email=" + email + "]";
		
		texto+="\n visualizacoes=";
		for(Visualizacao vis : visualizacoes) {
			//texto += vis;
			texto += (vis != null ? vis + ", " : "");
		}
		return texto;
	}

	
	
	
}
