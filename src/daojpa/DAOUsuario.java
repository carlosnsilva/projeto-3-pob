package daojpa;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import modelo.*;


public class DAOUsuario extends DAO<Usuario>{

	private static DAOAssunto daoAssunto = new DAOAssunto();
	private static DAOUsuario daoUsuario = new DAOUsuario();
	private static DAOVideo daoVideo = new DAOVideo();
	private static DAOVisualizacao daoVisualizacao = new DAOVisualizacao();
	
	public Usuario read (Object chave){
		try{
			String nome = (String) chave;
			TypedQuery<Usuario> q = manager.createQuery("SELECT * FROM USUARIO WHERE A.PALAVRA=:n", Usuario.class);
			q.setParameter("n", nome);
			return q.getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}
	
	public List<Usuario> readAll(){
		TypedQuery<Usuario> q = manager.createQuery("SELECT * FROM USUARIO ORDER BY ID", Usuario.class);
		return q.getResultList();
	}
	
	public List<Usuario> consultarUsuarioPorVideo(String link){
		Visualizacao v = daoVisualizacao.readPorLink(link);
		
		try {
			TypedQuery<Usuario> q = manager.createQuery("SELECT * FROM USUARIO U "
					+ "JOIN VISUALIZACAO VI ON U.EMAIL = VI.USUARIO=:X"
					+ "JOIN VIDEO V = V.LINK = VI.VIDEO=:Y", Usuario.class);
				q.setParameter("X", v.getUsuario());
				q.setParameter("Y", v.getVideo());
				return q.getResultList();
		}catch(NoResultException e) {
			return null;
		}
		
	}

}
