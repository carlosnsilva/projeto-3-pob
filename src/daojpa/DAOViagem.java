/**IFPB - Curso SI - Disciplina de POB
 * @author Prof Fausto Ayres
 */
package daojpa;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import modelo.Viagem;

public class DAOViagem  extends DAO<Viagem>{

	public Viagem read (Object chave){
		try{
			int id = (Integer) chave;
			TypedQuery<Viagem> q = manager.createQuery("select v from Viagem v where v,id=:i", Viagem.class);
			q.setParameter("i", id);
			return q.getSingleResult();
			
		}catch(NoResultException e){
			return null;
		}
	}
	
	public Viagem readNomeData (String nome, LocalDate data) {
		try{
			TypedQuery<Viagem> q = manager.createQuery("select v from Pessoa p join p.viagens v where p.nome=:n and v.data=:dt", Viagem.class);
			q.setParameter("n", nome);
			q.setParameter("dt", data);
			return q.getSingleResult();
		}
		catch(NoResultException e){
			return null;
		}
	}
	
	public List<Viagem> readDestino (String destino) {
			TypedQuery<Viagem> q = manager.createQuery("select v from Viagem v where v.destino=:d", Viagem.class);
			q.setParameter("d", destino);
			return  q.getResultList();		
	}

}

