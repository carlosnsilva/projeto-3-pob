package modelo;


/**IFPB - Curso SI - Disciplina de PERSISTENCIA DE OBJETOS
 * @author Prof Fausto Ayres
 */
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity 		 
public class Telefone {
	@Id		
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String numero;	
	
	@ManyToOne		
	private Pessoa pessoa;			//lado inverso do relacionamento
	
	
	public Telefone (){}
	public Telefone(String numero) {
		this.numero = numero;
	}

	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}

	
	//	--------------------RELACIONAMENTO--------------------------------
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	
	@Override
	public String toString() {
		return "id=" + id + ", numero=" + numero +
			 (pessoa != null ? "pessoa=" + pessoa.getNome() : "sem nome") ;
	}

	public boolean ehCelular() {
		return numero.startsWith("9");
	}
	
	public boolean ehFixo() {
		return numero.startsWith("3");
	}
	
	
}
