package br.com.xseed.service;

import java.util.List;

import br.com.xseed.dao.FuncionarioDAO;
import br.com.xseed.model.Funcionario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("funcionarioService")
public class FuncionarioService {

	@Autowired
	FuncionarioDAO funcionarioDao;
	
	@Transactional
	public List<Funcionario> getAllFuncionarios() {
		return funcionarioDao.getAllFuncionarios();
	}

	@Transactional
	public Funcionario getFuncionario(int id) {
		return funcionarioDao.getFuncionario(id);
	}

	@Transactional
	public void addFuncionario(Funcionario funcionario) {
		funcionarioDao.addFuncionario(funcionario);
	}

	@Transactional
	public void updateFuncionario(Funcionario funcionario) {
		funcionarioDao.updateFuncionario(funcionario);

	}

	@Transactional
	public void deleteFuncionario(int id) {
		funcionarioDao.deleteFuncionario(id);
	}
}
