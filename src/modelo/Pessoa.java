package modelo;

/**IFPB - Curso SI - Disciplina de PERSISTENCIA DE OBJETOS
 * @author Prof Fausto Ayres
 */
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity 
public class Pessoa {
	@Id		
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String nome;

	@Column(columnDefinition = "DATE")	//columnDefinition="TIMESTAMP"
	private LocalDate dtnascimento = LocalDate.now();

	@Enumerated(EnumType.STRING)
	private Sexo sexo;

	@Lob
	private byte[] foto;

	@ElementCollection
	private List<String> apelidos = new ArrayList<>();		//lista de não-entidade

	//-----------------------------
	//relacionamento bidirecional um para muitos
	//-----------------------------
	@OneToMany(mappedBy="pessoa", 
			cascade=CascadeType.ALL, 	
			orphanRemoval=true,			//default é false
			fetch=FetchType.EAGER) 		//default é LAZY
	private List<Telefone> telefones = new ArrayList<>();

	//-----------------------------
	//Relacionamento bidirecional muitos para muitos
	//-----------------------------
	@ManyToMany(mappedBy="participantes", 
			cascade={CascadeType.PERSIST,CascadeType.MERGE}) 	
	private List<Reuniao> reunioes = new ArrayList<>();

	//-----------------------------
	//Relacionamento unidirecional 
	//-----------------------------
	@OneToMany (cascade=CascadeType.ALL, orphanRemoval = true) 
	@JoinColumn(name="minhachave") 		//cria chave estrangeira na tabela viagem
	private List<Viagem> viagens = new ArrayList<>();


	public Pessoa (){}

	public Pessoa(String nome) {
		this.nome = nome;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getId() {
		return id;
	}
	public byte[] getFoto() {
		return foto;
	}
	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public LocalDate getDtnascimento() {
		return dtnascimento;
	}

	public void setDtnascimento(LocalDate dtnascimento) {
		this.dtnascimento = dtnascimento;
	}

	public void adicionar(Telefone t){
		telefones.add(t);
		t.setPessoa(this);
	}
	public void remover(Telefone t){
		telefones.remove(t);
		t.setPessoa(null);
	}
	
	
	public boolean removerFixos(){
		boolean ok = telefones.removeIf( new Predicate<Telefone>() {
			@Override
			public boolean test(Telefone t) {
				return  t.ehFixo();
			}
		});
		return ok;
	}

	public void criarApelidos(String[] lista){
		this.apelidos = new ArrayList<String>(Arrays.asList(lista));
	}
	public void adicionar(String ap){
		this.apelidos.add(ap);
	}
	public void adicionar(Reuniao r){
		reunioes.add(r);
	}
	public void remover(Reuniao r){
		reunioes.remove(r);
	}
	public void adicionar(Viagem v){
		viagens.add(v);
	}
	public void remover(Viagem v){
		viagens.remove(v);
	}

	public String toString() {
		String texto = "\nid=" + id + ", nome=" +  nome 
		+ ", dtnascimento=" + dtnascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		//				+ ", sexo=" + sexo

		texto += "\n    apelidos: ";
		if (apelidos !=null)
			for(String a : apelidos)
				texto+= a + ",";
		
		texto += "\n    telefones: ";
		for(Telefone t : telefones)
			texto+= t.getNumero() + ",";

		texto += "\n    reunioes: ";
		for(Reuniao c : reunioes)
			texto+= c.getAssunto() + ",";
		
		texto += "\n    viagens: ";
		for(Viagem v : viagens)
			texto+= v.getLocal() + ",";

		return texto;
	}


	public List<Telefone> getTelefones() {
		return telefones;
	}
	public List<String> getApelidos() {
		return apelidos;
	}
	public List<Reuniao> getReunioes() {
		return reunioes;
	}

	public List<Viagem> getViagens() {
		return viagens;
	}

}
