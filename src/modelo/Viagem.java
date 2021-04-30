package modelo;
/**IFPB - Curso SI - Disciplina de PERSISTENCIA DE OBJETOS
 * @author Prof Fausto Ayres
 */

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Viagem {
	@Id		
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(columnDefinition = "DATE")	
	private LocalDate data;
	private String destino;

	public Viagem() {}
	public Viagem(LocalDate data, String local) throws Exception{
		this.data = data;
		this.destino = local;
	}
	
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public String getLocal() {
		return destino;
	}
	public void setLocal(String local) {
		this.destino = local;
	}
	@Override
	public String toString() {
		String datastr = data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		return "id=" + id+ ", data=" + datastr + ", destino=" + destino ;
	}

	
	
}
