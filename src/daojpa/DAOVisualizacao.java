package daojpa;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import modelo.Visualizacao;
public class DAOVisualizacao extends DAO<Visualizacao> {
	
	public Visualizacao read (Object chave){
		try{
			String nome = (String) chave;
			TypedQuery<Visualizacao> q = manager.createQuery("SELECT * FROM VISUALIZACAO WHERE A.PALAVRA=:n", Visualizacao.class);
			q.setParameter("n", nome);
			return q.getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}
	
	public List<Visualizacao> readAll(){
		TypedQuery<Visualizacao> q = manager.createQuery("SELECT * FROM VISUALIZACAO ORDER BY ID", Visualizacao.class);
		return q.getResultList();
	}

}
