package aplicacaoConsole;
/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 *
 */

import fachada.Fachada;


public class Deletar {

	public Deletar(){
		Fachada.inicializar();
		try {
			Fachada.excluirPessoa("jose");
			System.out.println("deletou jose e seus telefones");
			
			Fachada.excluirTelefone("988881111");
			System.out.println("deletou telefone de joao...988881111");
			
			Fachada.excluirTelefonesFixos("maria");
			System.out.println("deletou os telefones fixo de maria");
			
			Fachada.excluirReuniao("07/05/2021 08:00");
			System.out.println("deletou reuniao");
			
			Fachada.excluirViagem("joao", "03/05/2021"); 
			System.out.println("deletou viagem");
			
			Fachada.excluirViagem("joao", "03/05/2021"); 	//nao consegue excluir
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		Fachada.finalizar();
		System.out.println("fim do programa");
	}



	//=================================================
	public static void main(String[] args) {
		new Deletar();
	}
}

