package daojpa;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import modelo.Assunto;

public class DAOAssunto  extends DAO<Assunto> {
	
	public Assunto read (Object chave){
		try{
			String nome = (String) chave;
			TypedQuery<Assunto> q = manager.createQuery("SELECT * FROM ASSUNTO WHERE A.PALAVRA=:n", Assunto.class);
			q.setParameter("n", nome);
			return q.getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}
	
	public List<Assunto> readAll(){
		TypedQuery<Assunto> q = manager.createQuery("SELECT * FROM ASSUNTO ORDER BY ID", Assunto.class);
		return q.getResultList();
	}
	
}
