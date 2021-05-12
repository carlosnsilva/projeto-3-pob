package daojpa;

import java.time.LocalDate;
import java.time.Period;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;

import modelo.*;

public class TriggerListener{

	@PrePersist
	public void exibirmsg1(Object obj) throws Exception {
		System.out.println(" @PrePersist... " + obj.getClass().getSimpleName());
	}

	@PostPersist
	public void exibirmsg2(Object obj) {
		System.out.println(" @PostPersist... " + obj.getClass().getSimpleName());
		if (obj instanceof Visualizacao)  {
			Visualizacao v = (Visualizacao)obj;
			System.out.println("   idade =" + v.getIdade() );
			int idade = calcularIdade( v );
			v.setIdade(idade);
			System.out.println("   idade calculada=" + " "+  idade );
		}

	}

	@PostUpdate
	public void exibirmsg3(Object obj) {
		System.out.println(" @PostUpdate... " + obj.getClass().getSimpleName() );
		if (obj instanceof Visualizacao)  {
			Visualizacao v = (Visualizacao)obj;
			System.out.println("   idade =" + " "+  v.getIdade() );
			int idade = calcularIdade( v );
			v.setIdade(idade);
			System.out.println("   idade calculada=" + " "+  idade );
		}

	}
	
	@PostLoad
	public void exibirmsg4(Object obj) {
		System.out.println(" @PostLoad... " + obj.getClass().getSimpleName());
		if (obj instanceof Visualizacao)  {
			Visualizacao v = (Visualizacao)obj;
			System.out.println("   idade =" + " "+  v.getIdade() );
			int idade = calcularIdade( v );
			v.setIdade(idade);
			System.out.println("   idade calculada=" + " "+ idade );
		}
	}	

	@PostRemove
	public void exibirmsg5(Object obj) {
		System.out.println(" @PostRemove.... " + obj.getClass().getSimpleName());
	}

	//============================================================
	public int calcularIdade(Visualizacao v) {
		LocalDate hoje = LocalDate.now();
		Period per = Period.between(v.getDataHora(), hoje);
		int idade = per.getYears();
		return idade;
	}

}
