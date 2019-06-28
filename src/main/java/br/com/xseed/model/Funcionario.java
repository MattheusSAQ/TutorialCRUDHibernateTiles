package br.com.xseed.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="FUNCIONARIO")
public class Funcionario{
	
	@Id
	@Column(name="matricula")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int matricula;
	
	@NotEmpty(message="O campo nome não pode estar vazio")
	@Column(name="nome")
	String nome;	
	
	@DecimalMin(value="400", message="O campo salário não pode ser menor que {value}")
	@Column(name="salario")
	double salario;
	
	public Funcionario() {
		super();
	}
	public Funcionario(int matricula, @NotEmpty String nome, @NotEmpty double salario) {
		super();
		this.matricula = matricula;
		this.nome = nome;
		this.salario = salario;
	}
	
	public int getMatricula() {
		return matricula;
	}
	
	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public double getSalario() {
		return salario;
	}
	
	public void setSalario(double salario) {
		this.salario = salario;
	}
	
}