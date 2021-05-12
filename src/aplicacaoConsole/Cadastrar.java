package aplicacaoConsole;

import fachada.Fachada;

public class Cadastrar {

	public Cadastrar(){
		try {
			Fachada.iniciar();
			
			System.out.println("cadastrando...");
			
			Fachada.cadastrarVideo("https://www.youtube.com/watch?v=1u2qu-EmIRc", "Git - Lecture 0 - CS50's Web Programming with Python and JavaScript 2018", "Git");
			Fachada.cadastrarVideo("https://www.youtube.com/watch?v=6i-_R5cAcEc", "Curso POO Java #04b​ - Métodos Getter, Setter e Construtor", "Poo");
			Fachada.cadastrarVideo("https://www.youtube.com/watch?v=XLr-igSIUU0&list=PLbaXFvl9CSyUfTbVhyHItpiuONlZCW9ZU&index=3", "Programação para Web I - Inserindo Angular Material - Angular aula 2", "Angular");
			
			
			Fachada.cadastrarUsuario("guilherme@email.com");
			Fachada.cadastrarUsuario("carlos@email.com");
			Fachada.cadastrarUsuario("jane@email.com");
			Fachada.cadastrarUsuario("david@email.com");
			
			
			Fachada.registrarVisualizacao("https://www.youtube.com/watch?v=1u2qu-EmIRc", "carlos@email.com", 5); 
			Fachada.registrarVisualizacao("https://www.youtube.com/watch?v=1u2qu-EmIRc", "guilherme@email.com", 5);
			Fachada.registrarVisualizacao("https://www.youtube.com/watch?v=6i-_R5cAcEc", "jane@email.com", 4);
			Fachada.registrarVisualizacao("https://www.youtube.com/watch?v=6i-_R5cAcEc", "david@email.com", 4);
			Fachada.registrarVisualizacao("https://www.youtube.com/watch?v=XLr-igSIUU0&list=PLbaXFvl9CSyUfTbVhyHItpiuONlZCW9ZU&index=3", "carlos@email.com", 5);
			Fachada.registrarVisualizacao("https://www.youtube.com/watch?v=XLr-igSIUU0&list=PLbaXFvl9CSyUfTbVhyHItpiuONlZCW9ZU&index=3", "guilherme@email.com", 5);
			Fachada.registrarVisualizacao("https://www.youtube.com/watch?v=XLr-igSIUU0&list=PLbaXFvl9CSyUfTbVhyHItpiuONlZCW9ZU&index=3", "jane@email.com", 3);
			Fachada.registrarVisualizacao("https://www.youtube.com/watch?v=XLr-igSIUU0&list=PLbaXFvl9CSyUfTbVhyHItpiuONlZCW9ZU&index=3", "david@email.com", 3);
			
			
		} catch (Exception e) 	{
			System.out.println(e.getMessage());
		}
		finally {
			Fachada.finalizar();
		}
		System.out.println("fim do programa");
	}


	public void cadastrar(){

	}	


	//=================================================
	public static void main(String[] args) {
		new Cadastrar();
	}
}