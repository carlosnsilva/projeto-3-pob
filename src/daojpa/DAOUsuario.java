package daojpa;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import modelo.Usuario;

public class DAOUsuario extends DAO<Usuario>{

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

}
