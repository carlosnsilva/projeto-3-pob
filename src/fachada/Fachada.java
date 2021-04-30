package fachada;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import javax.imageio.ImageIO;

import daojpa.DAO;
import daojpa.DAOPessoa;
import daojpa.DAOReuniao;
import daojpa.DAOTelefone;
import daojpa.DAOViagem;
import modelo.Pessoa;
import modelo.Reuniao;
import modelo.Sexo;
import modelo.Telefone;
import modelo.Viagem;

public class Fachada {
	private static DAOPessoa daopessoa = new DAOPessoa();  
	private static DAOTelefone daotelefone = new DAOTelefone();  
	private static DAOReuniao daoreuniao = new DAOReuniao();  
	private static DAOViagem daoviagem = new DAOViagem();  

	private static DateTimeFormatter formatadorDT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private static DateTimeFormatter formatadorDTH = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

	public static void inicializar(){
		DAO.open();
	}
	public static void finalizar(){
		DAO.close();
	}

	public static Pessoa localizarPessoa(String nome) throws  Exception{
		Pessoa p = daopessoa.read(nome);	
		if (p==null) {
			throw new Exception("nome inexistente:" + nome);
		}
		return p;
	}


	public static Pessoa criarTelefone(String nome, String numero) throws  Exception{
		DAO.begin();	
		Pessoa p = daopessoa.read(nome);
		if(p == null) {
			p = new Pessoa(nome);
			daopessoa.create(p);		// criar pessoa
		}

		Telefone t = daotelefone.read(numero);
		if (t!=null){
			DAO.rollback();
			throw new Exception("criar telefone - numero ja cadastrado:" + numero);
		}
		t = new Telefone(numero);
		p.adicionar(t);
		daotelefone.create(t);
		daopessoa.update(p);
		DAO.commit();
		return p;
	}	

	public static void excluirTelefone(String numero) throws Exception {
		DAO.begin();
		Telefone t = daotelefone.read(numero);
		if (t==null) {
			DAO.rollback();
			throw new Exception("excluir telefone - numero inexistente:" + numero);
		}
		Pessoa p = t.getPessoa();
		p.remover(t);
		t.setPessoa(null);
		daopessoa.update(p);
		//daotelefone.delete(t);	//orphanRemoval=true
		DAO.commit();
	}

	public static void excluirTelefonesFixos(String nome) throws Exception {
		DAO.begin();
		Pessoa p = daopessoa.read(nome);
		if (p==null) {
			DAO.rollback();	
			throw new Exception("nome inexistente:" + nome);
		}

		if(!p.removerFixos()) {
			DAO.rollback();	
			throw new Exception("nao encontrou telefone fixo:" + nome);
		}
		daopessoa.update(p);
		DAO.commit();
	}

	public static Reuniao criarReuniao(String datahora, String assunto, String... nomes) 
			throws Exception {
		LocalDateTime dt;
		try {
			dt = LocalDateTime.parse(datahora, formatadorDTH);
		}
		catch(DateTimeParseException e) {
			throw new Exception("formato datahora invalido:"+ datahora);
		}

		DAO.begin();
		Reuniao r = daoreuniao.read(dt);
		if(r!=null) {
			DAO.rollback();	
			throw new Exception("criar reuniao - reuniao ja existe:" + datahora);
		}

		r = new Reuniao(dt, assunto);
		for(String nome : nomes) {
			Pessoa p = daopessoa.read(nome);
			if (p==null) {
				DAO.rollback();	
				throw new Exception("criar reuniao - participante inexistente:" + nome);
			}
			r.adicionar(p);
			p.adicionar(r);
		}
		daoreuniao.create(r);
		DAO.commit();
		return r;
	}

	public static void excluirReuniao(String datahora) throws Exception {
		LocalDateTime dt;
		try {
			dt = LocalDateTime.parse(datahora, formatadorDTH);
		}
		catch(DateTimeParseException e) {
			throw new Exception("formato datahora invalido:"+ datahora);
		}

		DAO.begin();
		Reuniao r = daoreuniao.read(dt);
		if (r==null) {
			DAO.rollback();
			throw new Exception("excluir reuniao - reuniao inexistente:" + datahora);
		}

		for(Pessoa p : r.getParticipantes()) {
			p.remover(r);
			daopessoa.update(p);
		}
		daoreuniao.delete(r);
		DAO.commit();
	}

	public static Viagem criarViagem(String nome, String data, String destino) 
			throws Exception {
		LocalDate dt;
		try {
			dt = LocalDate.parse(data, formatadorDT);
		}
		catch(DateTimeParseException e) {
			throw new Exception("formato data invalido:"+ data);
		}

		DAO.begin();
		Pessoa p = daopessoa.read(nome);
		if(p == null) {
			DAO.rollback();
			throw new Exception("criar viagem - pessoa inexistente:" + nome);
		}
		Viagem v = daoviagem.readNomeData(nome,dt);
		if(v != null) {
			DAO.rollback();
			throw new Exception("criar viagem - viagem ja existe:"+nome+dt);
		}

		v = new Viagem(dt,destino);
		p.adicionar(v);
		p=daopessoa.update(p);
		DAO.commit();
		return v;
	}

	public static Viagem excluirViagem(String nome, String data) 
			throws Exception {
		LocalDate dt;
		try {
			dt = LocalDate.parse(data, formatadorDT);
		}
		catch(DateTimeParseException e) {
			throw new Exception("formato data invalido:"+ data);
		}

		DAO.begin();
		Pessoa p = daopessoa.read(nome);
		if(p == null) {
			DAO.rollback();
			throw new Exception("excluir viagem - pessoa inexistente:" + nome);
		}

		Viagem v = daoviagem.readNomeData(nome,dt);
		if(v == null) {
			DAO.rollback();
			throw new Exception("excluir viagem - viagem inexistente:"+nome+dt);
		}
		p.remover(v);
		p=daopessoa.update(p);
		DAO.commit();
		return v;
	}

	public static void excluirPessoa(String nome) throws Exception {
		DAO.begin();
		Pessoa p = daopessoa.read(nome);
		if (p==null) {
			DAO.rollback();	
			throw new Exception("excluir pessoa - nome inexistente:" + nome);
		}
		for(Reuniao r : p.getReunioes()) {
			r.remover(p);
			daoreuniao.update(r);
		}
		daopessoa.delete(p);  //remove telefones e viagens em cascata
		DAO.commit();
	}

	public static Pessoa alterarNome(String nome, String novonome) throws Exception{
		DAO.begin();		
		Pessoa p = daopessoa.read(nome);	//usando  chave primaria
		if (p==null) {
			DAO.rollback();
			throw new Exception("alterar nome - nome inexistente:" + nome);
		}
		p.setNome(novonome); 			
		p=daopessoa.update(p);     	
		DAO.commit();
		return p;
	}

	public static Pessoa alterarSexo(String nome, Sexo s) throws Exception{
		DAO.begin();		
		Pessoa p = daopessoa.read(nome);	//usando  chave primaria
		if (p==null) {
			DAO.rollback();
			throw new Exception("alterar sexo - nome inexistente:" + nome);
		}
		p.setSexo(s); 			
		p=daopessoa.update(p);     	
		DAO.commit();
		return p;
	}

	public static Pessoa alterarNascimento(String nome, String data) throws Exception{
		LocalDate dt;
		try {
			dt = LocalDate.parse(data, formatadorDT);
		}
		catch(DateTimeParseException e) {
			throw new Exception("formato data invalido:"+ data);
		}

		DAO.begin();		
		Pessoa p = daopessoa.read(nome);	//usando  chave primaria
		if (p==null) {
			DAO.rollback();
			throw new Exception("alterar nascimento - nome inexistente:" + nome);
		}

		p.setDtnascimento(dt); 			
		p=daopessoa.update(p);     	
		DAO.commit();
		return p;
	}



	public static Pessoa alterarFoto(String nome, byte[] bytesfoto) throws Exception{
		DAO.begin();		
		Pessoa p = daopessoa.read(nome);	//usando  chave primaria
		if (p==null) {
			DAO.rollback();
			throw new Exception("FOTO - nome inexistente:" + nome);
		}
		p.setFoto(bytesfoto); 			
		p=daopessoa.update(p);     	
		DAO.commit();
		return p;
	}

	public static Pessoa alterarApelidos(String nome, String[] apelidos) throws  Exception{
		DAO.begin();	
		Pessoa p = daopessoa.read(nome);	
		if(p == null) {
			DAO.rollback();	
			throw new Exception("alterar apelido - pessoa inexistente:" + nome);
		}

		p.criarApelidos(apelidos);
		p=daopessoa.update(p);		
		DAO.commit();
		return p;
	}

	public static Telefone alterarTelefone(String numero, String novonumero) throws Exception{
		DAO.begin();		
		Telefone t = daotelefone.read(numero);	
		if (t==null) {
			DAO.rollback();	
			throw new Exception("alterar telefone - numero inexistente:" + numero);
		}
		Telefone t2 = daotelefone.read(novonumero);	
		if (t2!=null) {
			DAO.rollback();	
			throw new Exception("alterar telefone - novo numero ja existe:" + novonumero);
		}
		t.setNumero(novonumero); 	//trocar		
		t=daotelefone.update(t);     	
		DAO.commit();	
		return t;
	}

	public static List<Pessoa> listarPessoas(){
		return daopessoa.readAll();
	}
	public static List<Telefone> listarTelefones(){
		return daotelefone.readAll();
	}
	public static List<Reuniao> listarReunioes(){
		return daoreuniao.readAll();
	}


	public static void esvaziar() throws  Exception{
		DAO.clear();	//apaga todos objetos do banco
	}

	/**********************************************************
	 * 
	 * CONSULTAS IMPLEMENTADAS NOS DAO
	 * 
	 **********************************************************/

	public static List<Pessoa> consultarPessoas(String caracteres) {
		if(caracteres.isEmpty())
			return daopessoa.readAll();
		else
			return daopessoa.readLike(caracteres);
	}

	public static List<Telefone> consultarTelefones(String digitos) {
		if(digitos.isEmpty())
			return daotelefone.readAll();
		else
			return daotelefone.readLike(digitos);
	}


	public static List<Reuniao> consultarReunioes(String assunto) {
		if(assunto.isEmpty())
			return daoreuniao.readAll();
		else
			return daoreuniao.readAssunto(assunto);
	}

	public static List<Viagem> consultarViagens(String destino) {
		if(destino.isEmpty())
			return daoviagem.readAll();
		else
			return daoviagem.readDestino(destino);
	}

	public static List<Pessoa> consultarPessoasNTelefones(int n) {
		return daopessoa.readNtelefones(n);
	}

	public static boolean temTelefoneFixo(String nome) {
		return daopessoa.temTelefoneFixo(nome);
	}

	public static boolean temTelefoneCelular(String nome) {
		return daopessoa.temTelefoneCelular(nome);
	}



}
