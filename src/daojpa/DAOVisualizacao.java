package daojpa;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import modelo.*;
public class DAOVisualizacao extends DAO<Visualizacao> {
	
	private static DAOUsuario daoUsuario = new DAOUsuario();
	
	public Visualizacao read (Object chave){
		try{
			int id = (int) chave;
			TypedQuery<Visualizacao> q = manager.createQuery("select vi from Visualizacao vi where vi.id=:i", Visualizacao.class);
			q.setParameter("i", id);
			return q.getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}
	
	public List<Visualizacao> readAll(){
		TypedQuery<Visualizacao> q = manager.createQuery("select vi from Visualizacao vi order by vi.id", Visualizacao.class);
		return q.getResultList();
	}
	/*
	public Visualizacao readPorId(int id){
		try{
			TypedQuery<Visualizacao> q = manager.createQuery("SELECT * FROM VISUALIZACAO VI WHERE VI.id=:n", Visualizacao.class);
			q.setParameter("n", id);
			return q.getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}
	
	public Visualizacao VisualizacaoPorUsuario(String email) {
		TypedQuery<Visualizacao> q = manager.createQuery("SELECT * FROM VISUALIZACAO VI"
				+ "JOIN USUARIO U ON U.EMAIL=:X = VI.USUARIO=:Y", Visualizacao.class);
				q.setParameter("X", email);
				q.setParameter("Y", daoUsuario.read(email));
				return q.getResultList().get(0);
	}
	
	public Visualizacao readPorLink(String link){
		try{
			TypedQuery<Visualizacao> q = manager.createQuery("SELECT * FROM VISUALIZACAO VI WHERE VI.VIDEO=:n", Visualizacao.class);
			q.setParameter("n", link);
			return q.getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}
	
	
	public Visualizacao VisualizacaoPorVideo(String link) {
		Visualizacao v = readPorLink(link);
		
		TypedQuery<Visualizacao> q = manager.createQuery("SELECT * FROM VISUALIZACAO VI"
				+ "JOIN VIDEO V ON V.LINK = VI.VIDEO=:Y", Visualizacao.class);
				q.setParameter("Y", v.getVideo());
				return q.getResultList().get(0);
	}
	*/
}
