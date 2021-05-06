package daojpa;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import modelo.Video;
import modelo.Visualizacao;

public class DAOVideo extends DAO<Video> {
	
	private DAOAssunto daoAssunto = new DAOAssunto();
	private DAOUsuario daoUsuario = new DAOUsuario();
	private static DAOVideo daoVideo = new DAOVideo();
	private static DAOVisualizacao daoVisualizacao = new DAOVisualizacao();
	
	public Video read (Object chave){
		try{
			String link = (String) chave;
			TypedQuery<Video> q = manager.createQuery("select v from Video v where v.link=:l", Video.class);
			q.setParameter("l", link);;
			return q.getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}
	
	/*
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
		Visualizacao v = daoVisualizacao.VisualizacaoPorUsuario(email);
		
		try {
			TypedQuery<Video> q = manager.createQuery("SELECT * FROM VIDEO V JOIN "
					+ "VISUALIZACAO VI ON VI.VIDEO=:X = V.LINK"
					+ "JOIN USUARIO u ON U.EMAIL=:Z = VI.USUARIO=:C ", Video.class);
				q.setParameter("Z",daoUsuario.read(email));
				q.setParameter("C", v.getUsuario());				
				q.setParameter("X", v.getVideo());
				return q.getResultList();
		}catch(NoResultException e) {
			return null;
		}
		
	}
	
	public Video readPorLink(String link){
		try{
			TypedQuery<Video> q = manager.createQuery("SELECT * FROM VIDEO V WHERE V.LINK=:n", Video.class);
			q.setParameter("n", link);
			return q.getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}
	*/
}
