package modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String email;
	
	@OneToMany (cascade=CascadeType.ALL, orphanRemoval = true) 
	@JoinColumn(name="id")
	private List<Visualizacao> visualizacoes = new ArrayList<>();
	
	
	public Usuario() {}
	
	public Usuario(String email) {
		this.email = email;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public List<Visualizacao> getVisualizacoes() {
		return visualizacoes;
	}
	public void setVisualizacoes(List<Visualizacao> visualizacoes) {
		this.visualizacoes = visualizacoes;
	}
}
