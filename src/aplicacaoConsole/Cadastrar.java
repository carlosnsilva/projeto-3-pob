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
import modelo.Viagem;


public class Cadastrar {

	public Cadastrar(){
		Fachada.inicializar();
		System.out.println("cadastrando...");
		try {
			Pessoa p;
			p=Fachada.criarTelefone("joao","988880000");
			p=Fachada.criarTelefone("joao","988881111");	
			p=Fachada.criarTelefone("maria","987882222");
			p=Fachada.criarTelefone("maria","988883333");
			p=Fachada.criarTelefone("maria","32471234");
			p=Fachada.criarTelefone("jose","987884444");
			p=Fachada.criarTelefone("paulo","988885555");
		} catch (Exception e) 	{
			System.out.println(e.getMessage());
		}

		try {
			Viagem v;
			v=Fachada.criarViagem("joao",	"01/05/2021", "CHINA");
			v=Fachada.criarViagem("joao",	"02/05/2021", "EUA");
			v=Fachada.criarViagem("joao",	"03/05/2021", "CANADA");
			v=Fachada.criarViagem("maria",	"01/05/2021", "SAO PAULO");
			v=Fachada.criarViagem("maria", 	"02/05/2021", "BRASILIA");
			v=Fachada.criarViagem("maria",	"03/05/2021", "BRASILIA");
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}

		try {
			Reuniao r;
			r=Fachada.criarReuniao("07/05/2021 08:00","economia", "joao", "maria");
			r=Fachada.criarReuniao("07/05/2021 19:00","economia", "joao", "jose");
			r=Fachada.criarReuniao("10/05/2021 14:00","demissao", "joao", "paulo");
			r=Fachada.criarReuniao("12/05/2021 10:00","eleicao", "joao", "maria", "jose", "paulo");
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}

		Fachada.finalizar();
		System.out.println("fim do programa");
	}


	public void cadastrar(){

	}	


	//=================================================
	public static void main(String[] args) {
		new Cadastrar();
	}
}


