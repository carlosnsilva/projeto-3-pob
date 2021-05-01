package daojpa;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import modelo.Video;

public class DAOVideo extends DAO<Video> {
	
	public Video read (Object chave){
		try{
			String nome = (String) chave;
			TypedQuery<Video> q = manager.createQuery("SELECT * FROM VISUALIZACAO WHERE A.PALAVRA=:n", Video.class);
			q.setParameter("n", nome);
			return q.getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}
	
	public List<Video> readAll(){
		TypedQuery<Video> q = manager.createQuery("SELECT * FROM VISUALIZACAO ORDER BY ID", Video.class);
		return q.getResultList();
	}
	

}
