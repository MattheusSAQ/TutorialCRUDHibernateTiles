<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title><tiles:getAsString name="title" /></title>
	<link href="<c:url value='/static/css/bootstrap.css' />"  rel="stylesheet"></link>
	<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script type="text/javascript">
	$(function() {
	    /*  Submit form using Ajax */
	    $('input[type=submit]').click(function(e) {
	        e.preventDefault();
	        
	        $('span').remove();
	        $.post({
	            url : 'addFuncionario',
	            data : $('form[name=funcionario]').serialize(),
	            success : function(res) {
	                
	                if(res.validated){         
	                    location.reload(true);                     
	                }else{
	                    $.each(res.errorMessages,function(key,value){
	                        $('input[name='+key+']').after('<span class="error"><br/>'+value+'</span>');
	                        });                           
	                }
	            }
	        })
	    });
	});
	</script>
</head>
 
<body>
		<header id="header">
			<tiles:insertAttribute name="header" />
		</header>
	
		<section id="sidemenu">
			<tiles:insertAttribute name="menu" />
		</section>
			
		<section id="site-content">
			<tiles:insertAttribute name="body" />
		</section>
		
		<footer id="footer">
			<tiles:insertAttribute name="footer" />
		</footer>
</body>
</html>