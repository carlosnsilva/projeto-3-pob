package aplicacaoConsole;

import fachada.Fachada;

public class Atualizar {

	public Atualizar(){
		Fachada.iniciar();
		try {
			System.out.println("adicionando assuntos...");
			Fachada.adicionarAssunto("https://www.youtube.com/watch?v=1u2qu-EmIRc", "github");
			Fachada.adicionarAssunto("https://www.youtube.com/watch?v=6i-_R5cAcEc", "java");
			Fachada.adicionarAssunto("https://www.youtube.com/watch?v=XLr-igSIUU0&list=PLbaXFvl9CSyUfTbVhyHItpiuONlZCW9ZU&index=3", "typescript");
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		Fachada.finalizar();
		System.out.println("fim do programa");
	}




	//=================================================
	public static void main(String[] args) {
		new Atualizar();
	}
}

