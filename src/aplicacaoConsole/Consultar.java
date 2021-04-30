package aplicacaoConsole;
/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 *
 */

import fachada.Fachada;


public class Consultar {

	public Consultar(){
		try {
			Fachada.inicializar();
			System.out.println("1.\n"+Fachada.consultarPessoas("jo") );
			System.out.println("2.\n"+Fachada.consultarPessoas("maria") );
			System.out.println("3.\n"+Fachada.consultarTelefones("98") );
			System.out.println("4.\n"+Fachada.consultarTelefones("988883333") );
			System.out.println("5.\n"+Fachada.consultarPessoasNTelefones(2) );
			System.out.println("---------------------------------");
			System.out.println("6.\n"+Fachada.temTelefoneCelular("joao") );
			System.out.println("7.\n"+Fachada.temTelefoneFixo("joao") );
			System.out.println("8.\n"+Fachada.temTelefoneFixo("maria") );
			System.out.println("---------------------------------");
			System.out.println("9.\n"+Fachada.consultarReunioes("economia") );	//mes
			System.out.println("10.\n"+Fachada.consultarViagens("EUA") );	//mes
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			Fachada.finalizar();
		}
		System.out.println("\nfim do programa");
	}


	//=================================================
	public static void main(String[] args) {
		new Consultar();
	}
}

