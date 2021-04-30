/**IFPB - Curso SI - Disciplina de POB
 * @author Prof Fausto Ayres
 */
package daojpa;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import modelo.Reuniao;

public class DAOReuniao  extends DAO<Reuniao>{

	public Reuniao read (Object chave) {
		try{
			LocalDateTime datahora = (LocalDateTime) chave;	
			TypedQuery<Reuniao> q = manager.createQuery("select r from Reuniao r where r.datahora=:dt", Reuniao.class);
			q.setParameter("dt", datahora);
			return q.getSingleResult();
		}
		catch(NoResultException e){
			return null;
		}
	}

	//consulta
	public  List<Reuniao> readAssunto(String assunto) {
		TypedQuery<Reuniao> q = manager.createQuery(
		"select r from Reuniao r where r.assunto like :a order by r.datahora", Reuniao.class);
		q.setParameter("a", "%"+assunto+"%");

		return q.getResultList();
	}

}

