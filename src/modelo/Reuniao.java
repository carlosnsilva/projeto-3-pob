package modelo;
/**IFPB - Curso SI - Disciplina de PERSISTENCIA DE OBJETOS
 * @author Prof Fausto Ayres
 */

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Reuniao {
	@Id	
	@Column(columnDefinition = "TIMESTAMP")	
	private LocalDateTime datahora;  		// chave primaria 
	private String assunto;

	//-----------------------------
	//Relacionamento bidirecional muitos para muitos (lado inverso nao tem mappedBy)
	//-----------------------------
	@ManyToMany(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	List<Pessoa> participantes = new ArrayList<>();


	public Reuniao() {}
	public Reuniao(LocalDateTime datahora, String descricao) {
		this.datahora = datahora;
		this.assunto = descricao;
	}
	public void adicionar(Pessoa comp){
		participantes.add(comp);
	}
	public void remover(Pessoa comp){
		participantes.remove(comp);
	}

	public String getAssunto() {
		return assunto;
	}
	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public LocalDateTime getDatahora() {
		return datahora;
	}

	public List<Pessoa> getParticipantes() {
		return participantes;
	}

	@Override
	public String toString() {
		String texto = datahora.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + 
				" -> " + assunto ;		

		texto += ", participantes:";
		for(Pessoa p : participantes)
			texto+= p.getNome() + ",";

		return texto;
	}



}
