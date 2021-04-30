package aplicacaoConsole;
/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 *
 */


import fachada.Fachada;
import modelo.Pessoa;
import modelo.Reuniao;
import modelo.Telefone;

public class Listar {

	public Listar(){
		try {
			Fachada.inicializar();
			
			System.out.println("Listagem de pessoas:");
			for(Pessoa p : Fachada.listarPessoas())		
				System.out.println(p);
			
			System.out.println("\nListagem de telefones:");
			for(Telefone t : Fachada.listarTelefones())	
				System.out.println(t);
			
			System.out.println("\nListagem de reunioes:");
			for(Reuniao r : Fachada.listarReunioes())	
				System.out.println(r);
		
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			Fachada.finalizar();
		}
	}


	//=================================================
	public static void main(String[] args) {
		new Listar();
	}
}

