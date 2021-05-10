package fachada;

import java.util.List;

import daojpa.*;
import modelo.*;

public class Fachada {

	private static DAOAssunto daoAssunto = new DAOAssunto();
	private static DAOUsuario daoUsuario = new DAOUsuario();
	private static DAOVideo daoVideo = new DAOVideo();
	private static DAOVisualizacao daoVisualizacao = new DAOVisualizacao();
	
	public static void iniciar() {
		DAO.open();
	}
	
	public static void finalizar() {
		DAO.close();
	}
	
	public static Video cadastrarVideo(String link, String nome, String palavra) throws  Exception{
		DAO.begin();
		if (nome.isEmpty()) {
			DAO.rollback();
			throw new Exception("Não foi passado um nome pro video");
		}
		if (link.isEmpty()) {
			DAO.rollback();
			throw new Exception("Não foi passado o link do video");
		}
		Video v = daoVideo.read(link);
		if (v != null) {
			DAO.rollback();
			throw new Exception("Link ja cadastrado: " + link);
		}
		Assunto a = daoAssunto.read(palavra.toLowerCase());
		v = new Video(link, nome);
		if (a != null) {
			v.adicionar(a);
			a.adicionar(v);
			daoVideo.create(v);
			
		} else {
			Assunto asu = new Assunto(palavra.toLowerCase());
			v.adicionar(asu);
			asu.adicionar(v);
			daoVideo.create(v);
		}

		DAO.commit();
		return v;
	}
	
	public static Usuario cadastrarUsuario(String email, String dataNasc) throws  Exception{
		DAO.begin();	
		Usuario u = daoUsuario.read(email);
		if(u != null) {
			DAO.rollback();
			throw new Exception("usuario ja cadastrado:" + email);
		}
		u = new Usuario(email, dataNasc);
		daoUsuario.create(u);	
		DAO.commit();
		return u;
	}
	
	public static void adicionarAssunto(String link, String assunto) throws  Exception{
		DAO.begin();	
		Video v = daoVideo.read(link);	
		if(v == null) {
			DAO.rollback();	
			throw new Exception("video inexistente:" + link);
		}

		v.adicionar(new Assunto(assunto.toLowerCase()));

		daoVideo.update(v);		
		DAO.commit();
	}
	
	public static Integer getMaiorId() throws Exception {
		List<Visualizacao> vis = listarVisualizacao();
		int id = 0;
		for (Visualizacao v : vis) {
			if (v.getId() > id) {
				id = v.getId();
			}
		}
		return id;
	};
	
	public static void registrarVisualizacao(String link, String email, int nota) throws Exception{
		DAO.begin();
		Video video = daoVideo.read(link);
		if(video == null) {
			DAO.rollback();
			throw new Exception("video inexistente:" + link);
		}
		
		if(nota < 1 || nota > 5) {
			throw new Exception("nota deve ser de 1 a 5");
		}
		
		Usuario usuario = daoUsuario.read(email);
		if(usuario == null) {
			usuario = cadastrarUsuario(email, null);
		}
		
		Visualizacao vis = new Visualizacao(nota, usuario, video);
		usuario.adicionar(vis);
		video.adicionar(vis);
		daoVisualizacao.create(vis);
		DAO.commit();
	}
	
	public static Visualizacao localizarVisualizacao(int id) {
		DAO.begin();
		Visualizacao vis = daoVisualizacao.read(id);
		if(vis == null) {
			DAO.rollback();
			return null;
		}else {
			return vis;	
		} 
	}
	
	public static void apagarVisualizacao(int id) throws Exception {
		DAO.begin();
		Visualizacao visual = localizarVisualizacao(id);
		// verifica se a visualizacao existe
		if(visual == null) {
			DAO.rollback();
			throw new Exception("Visualizacao de id " + id + " inexistente");
		}
		daoVisualizacao.delete(visual);
		DAO.commit();
	}
	
	//Métodos de listagem
	public static List<Video> listarVideos(){
		return daoVideo.readAll();
	}
	
	public static List<Usuario> listarUsuarios(){
		return daoUsuario.readAll();
	}
	
	public static List<Assunto> listarAssunto(){
		return daoAssunto.readAll();
	}
	
	public static List<Visualizacao> listarVisualizacao(){
		return daoVisualizacao.readAll();
	}
	
	//Métodos de consulta
	public static List<Video> consultarVideosPorAssunto(String palavra) {
		return daoVideo.consultarVideosPorAssunto(palavra.toLowerCase()); 
	}
	
	public static List<Video> consultarVideosPorUsuario(String email) {
		return daoVideo.consultarVideosPorUsuario(email);
	}
	
	public static List<Usuario> consultarUsuarioPorVideo(String link) {
		return daoUsuario.consultarUsuarioPorVideo(link);
		
	}
	
	
	public static void esvaziar() throws  Exception{
		DAO.clear();	
	}
	
}
