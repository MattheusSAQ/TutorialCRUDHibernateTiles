package br.com.xseed.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.xseed.model.Funcionario;

@Repository
public class FuncionarioDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	public List<Funcionario> getAllFuncionarios() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Funcionario> funcionarioList = session.createQuery("from Funcionario").list();
		return funcionarioList;
	}

	public Funcionario getFuncionario(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Funcionario funcionario = (Funcionario) session.get(Funcionario.class, new Integer(id));
		return funcionario;
	}

	public Funcionario addFuncionario(Funcionario funcionario) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(funcionario);
		return funcionario;
	}

	public void updateFuncionario(Funcionario funcionario) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(funcionario);
	}

	public void deleteFuncionario(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Funcionario p = (Funcionario) session.load(Funcionario.class, new Integer(id));
		if (null != p) {
			session.delete(p);
		}
	}	
}
