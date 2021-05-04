package daojpa;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import modelo.Visualizacao;
public class DAOVisualizacao extends DAO<Visualizacao> {
	
	private static DAOAssunto daoAssunto = new DAOAssunto();
	private static DAOUsuario daoUsuario = new DAOUsuario();
	private static DAOVideo daoVideo = new DAOVideo();
	private static DAOVisualizacao daoVisualizacao = new DAOVisualizacao();
	
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

	public String VisualizacaoPorUsuario(String email) {
		TypedQuery<Visualizacao> q = manager.createQuery("SELECT * FROM VISUALIZACAO VI"
				+ "JOIN USUARIO U ON U.EMAIL=:X = VI.USUARIO=:Y", Visualizacao.class);
				q.setParameter("X", email);
				q.setParameter("Y", daoUsuario.read(email));
		return q.getResultList().get(0).getUsuario().getEmail().toString();
	}
}
