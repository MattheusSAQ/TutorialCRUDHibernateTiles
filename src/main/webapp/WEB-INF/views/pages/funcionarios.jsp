<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>  
<html>
<head>
	<style>           
	.blue-button{
		background: #25A6E1;
		filter: progid: DXImageTransform.Microsoft.gradient( startColorstr='#25A6E1',endColorstr='#188BC0',GradientType=0);
		padding:3px 5px;
		color:#fff;
		font-family:'Helvetica Neue',sans-serif;
		font-size:12px;
		border-radius:2px;
		-moz-border-radius:2px;
		-webkit-border-radius:4px;
		border:1px solid #1A87B9
	}     
	table {
	  font-family: "Helvetica Neue", Helvetica, sans-serif;
	   width: 50%;
	}
	th {
	  background: #272727;
	  color: white;
	}
	 td,th{
	                border: 1px solid gray;
	                width: 25%;
	                text-align: left;
	                padding: 5px 10px;
	            }
	</style>
</head>
<body>
	<form:form method="post" name="funcionario" modelAttribute="funcionario" action="/Spring4MVCApacheTiles3Example/addFuncionario">
	<table>
			<tr>
				<th colspan="2">Adicionar funconário</th>
			</tr>
			<tr>
			<form:hidden path="matricula" />
	          <td><form:label path="nome">Nome:</form:label></td>
	          <td><form:input name="nome" path="nome" size="30" maxlength="30"></form:input></td>
	        </tr>
			<tr>
				    <td><form:label path="salario">Salário:</form:label></td>
	          <td><form:input path="salario" size="30" maxlength="30"></form:input></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit"
					class="blue-button" /></td>
			</tr>
		</table> 
	</form:form>
	<br/>
	<h3>Lista de funcionários</h3>
	<c:if test="${!empty listOfFuncionarios}">
		<table class="tg">
		<tr>
			<th width="80">Matricula</th>
			<th width="120">Nome</th>
			<th width="120">Salário</th>
			<th width="60">Editar</th>
			<th width="60">Deletar</th>
		</tr>
		<c:forEach items="${listOfFuncionarios}" var="funcionario">
			<tr>
				<td>${funcionario.matricula}</td>
				<td>${funcionario.nome}</td>
				<td>R$ ${funcionario.salario}0</td>
				<td><a href="<c:url value='/${funcionario.matricula}' />" >Editar</a></td>
				<td><a href="<c:url value='/deleteFuncionario/${funcionario.matricula}' />" >Deletar</a></td>
			</tr>
		</c:forEach>
		</table>
	</c:if>
</body>
</html>