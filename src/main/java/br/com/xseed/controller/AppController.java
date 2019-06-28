package br.com.xseed.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import br.com.xseed.model.Funcionario;
import br.com.xseed.model.FuncionarioResponse;
import br.com.xseed.service.FuncionarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
@RequestMapping("/")
public class AppController {
	
	@Autowired
	FuncionarioService funcionarioService;

	@RequestMapping(value = { "/"}, method = RequestMethod.GET)
	public String homePage(ModelMap model) {
		return "home";
	}

	@RequestMapping(value = { "/contactus"}, method = RequestMethod.GET)
	public String contactUsPage(ModelMap model) {
		return "contactus";
	}
	
	
	@RequestMapping(value = "/getAllFuncionarios", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getFuncionarios(Model model) {
		
		List<Funcionario> listOfFuncionarios = funcionarioService.getAllFuncionarios();
		model.addAttribute("funcionario", new Funcionario());
		model.addAttribute("listOfFuncionarios", listOfFuncionarios);
		return "funcionarios";
	}

	@RequestMapping(value = "/getFuncionario/{matricula}", method = RequestMethod.GET, headers = "Accept=application/json")
	public Funcionario getFuncionarioById(@PathVariable int id) {
		return funcionarioService.getFuncionario(id);
	}

	@PostMapping(value = "/addFuncionario", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
	public FuncionarioResponse addFuncionario(@ModelAttribute @Valid Funcionario funcionario, BindingResult result) {	
		
		FuncionarioResponse retorno = new FuncionarioResponse();
		if(funcionario.getMatricula()==0)
		{
			if(result.hasErrors()){
                Map<String, String> errors = result.getFieldErrors().stream()
                      .collect(
                            Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)
                        );
         
                retorno.setValidated(false);
                retorno.setErrorMessages(errors);
                return retorno;
             }else{
                retorno.setValidated(true);
                retorno.setFuncionario(funcionario);
                funcionarioService.addFuncionario(funcionario);
                return retorno;
             }   
		}
		else{
			if(result.hasErrors()){
				Map<String, String> errors = result.getFieldErrors().stream()
						.collect(
								Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)
								);

				retorno.setValidated(false);
				retorno.setErrorMessages(errors);
				return retorno;
			}else{
				retorno.setValidated(true);
				retorno.setFuncionario(funcionario);
				funcionarioService.updateFuncionario(funcionario);
				return retorno;
			}

		}        
		
	}

	@RequestMapping(value = "/{matricula}", method = RequestMethod.GET, headers = "Accept=application/json")
	public String updateFuncionario(@PathVariable("matricula") int id,Model model) {
		 model.addAttribute("funcionario", this.funcionarioService.getFuncionario(id));
	        model.addAttribute("listOfFuncionarios", this.funcionarioService.getAllFuncionarios());
	        return "funcionarios";
	}

	@RequestMapping(value = "/deleteFuncionario/{matricula}", method = RequestMethod.GET, headers = "Accept=application/json")
	public String deleteFuncionario(@PathVariable("matricula") int id) {
		funcionarioService.deleteFuncionario(id);
		 return "redirect:/getAllFuncionarios";

	}
	
}