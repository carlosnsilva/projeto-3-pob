package modelo;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Usuario {
	@Id
	private String email;
	
	//relacionamento bidirecional um para muitos
	@OneToMany(mappedBy="usuario", 
			cascade=CascadeType.ALL, 	
			orphanRemoval=true,			//default � false
			fetch=FetchType.EAGER) 		//default � LAZY
	private List<Visualizacao> visualizacoes = new ArrayList<>();
	
	public Usuario() {};
	
	public Usuario(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}
	
	public void adicionar(Visualizacao v){
		visualizacoes.add(v);
		v.setUsuario(this);
	}
	
	public List<Visualizacao> getVisualizacoes(){
		return visualizacoes;
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
