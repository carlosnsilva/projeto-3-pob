package aplicacaoConsole;
/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 *
 */

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.imageio.ImageIO;

import fachada.Fachada;
import modelo.Pessoa;
import modelo.Sexo;
import modelo.Telefone;


public class Alterar {

	public Alterar(){
		Fachada.inicializar();
		System.out.println("alterando...");
		try {
			Pessoa p;
			p=Fachada.alterarSexo("joao",Sexo.MASCULINO);
			p=Fachada.alterarSexo("maria",Sexo.FEMININO);
			p=Fachada.alterarSexo("jose",Sexo.MASCULINO);
			p=Fachada.alterarSexo("paulo",Sexo.MASCULINO);

			//usar arquivos da pastas /fotos
			p=Fachada.alterarFoto("joao", arquivoToBytes("m1.jpg"));
			p=Fachada.alterarFoto("maria", arquivoToBytes("f1.jpg"));
			p=Fachada.alterarFoto("jose", arquivoToBytes("m2.jpg"));

			p=Fachada.alterarApelidos("joao", new String[] {"jo", "joaozinho", "jojo"});
			p=Fachada.alterarApelidos("maria", new String[] {"mary", "mar"});
			p=Fachada.alterarApelidos("jose", new String[] {"zezinho", "zezao"});
			p=Fachada.alterarApelidos("paulo", new String[] {"paulao"});

			//p=Fachada.alterarNome("paulo", "paula");
		
			Telefone t;
			t=Fachada.alterarTelefone("988880000", "999999999");
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}

		Fachada.finalizar();
		System.out.println("fim do programa");
	}


	public byte[] arquivoToBytes(String arquivo) {
		try {
			URL url = Fachada.class.getResource("/fotos/"+arquivo);
			File f = new File(url.toURI());				// pasta src/fotos (interna)
			//File f = new File("/imagens/"+arquivo);	//pasta proj/imagens (externa)
			BufferedImage buffer = ImageIO.read(f);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(buffer, "jpg", baos );
			byte[] bytesfoto = baos.toByteArray();
			baos.close();
			return bytesfoto;

			//alternativa
			//			Path path = FileSystems.getDefault().getPath(arquivo);
			//			byte[] bytesfoto = Files.readAllBytes(path);
		} catch (IOException e) {
			throw new RuntimeException("arquivo invalido:"+arquivo);
		} catch (URISyntaxException e) {
			throw new RuntimeException("arquivo invalido:"+arquivo);
		}	
	}

	//=================================================
	public static void main(String[] args) {
		new Alterar();
	}
}

