package aplicacaoConsole;

import fachada.Fachada;

public class Consultar {

	public Consultar(){

		try {
			Fachada.iniciar();
			System.out.println("1.video por assunto 'angular'\n"+Fachada.consultarVideosPorAssunto("angular")+"\n");
			System.out.println("2.video pelo usuario 'guilherme@email.com'\n"+Fachada.consultarVideosPorUsuario("guilherme@email.com")+"\n");
			System.out.println("3.usuario por video \'https://www.youtube.com/watch?v=1u2qu-EmIRc\'\n"+Fachada.consultarUsuarioPorVideo("https://www.youtube.com/watch?v=1u2qu-EmIRc")+"\n");
			
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

