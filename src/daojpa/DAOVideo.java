package daojpa;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import daojpa.*;

import modelo.Video;

public class DAOVideo extends DAO<Video> {
	
	private DAOAssunto daoAssunto = new DAOAssunto();
	private DAOUsuario daoUsuario = new DAOUsuario();
	private static DAOVideo daoVideo = new DAOVideo();
	private static DAOVisualizacao daoVisualizacao = new DAOVisualizacao();
	
	public Video read (Object chave){
		try{
			String nome = (String) chave;
			TypedQuery<Video> q = manager.createQuery("SELECT * FROM VIDEO WHERE A.PALAVRA=:n", Video.class);
			q.setParameter("n", nome);
			return q.getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}
	
	public List<Video> readAll(){
		TypedQuery<Video> q = manager.createQuery("SELECT * FROM VIDEO ORDER BY ID", Video.class);
		return q.getResultList();
	}
	
	public List<Video> consultarVideosPorAssunto(String assunto){
		try {
			TypedQuery<Video> q = manager.createQuery("SELECT * FROM VIDEO V JOIN "
					+ "ASSUNTO A ON A.PALAVRA=:X = V.ASSUNTOS=:Y"
					+ "V WHERE V.", Video.class);
				q.setParameter("X", assunto);
				q.setParameter("Y", daoAssunto.read(assunto));
				return q.getResultList();
		}catch(NoResultException e) {
			return null;
		}
		
	}
	
	public List<Video> consultarVideosPorUsuario(String email){
		try {
			TypedQuery<Video> q = manager.createQuery("SELECT * FROM VIDEO V JOIN "
					+ "VISUALIZACAO VI ON VI.VIDEO=:X = V.NOME=:Y"
					+ "JOIN USUARIO u ON U.EMAIL=:Z = VI.USUARIO=:C ", Video.class);
				q.setParameter("Z",daoUsuario.read(email));
				q.setParameter("C", daoVisualizacao.VisualizacaoPorUsuario(email));
				
				// Montar a lógica para solucionar
				q.setParameter("Y", daoAssunto.read(assunto));
				q.setParameter("X", daoVideo.);
				
				
				return q.getResultList();
		}catch(NoResultException e) {
			return null;
		}
		
	}

}
