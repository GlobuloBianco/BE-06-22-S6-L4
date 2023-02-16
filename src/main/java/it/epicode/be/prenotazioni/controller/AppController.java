package it.epicode.be.prenotazioni.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AppController {
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@PostMapping("login_success")
	@ResponseBody
	public String login_success() {
		return "Login effettuato correttamente";
	}
	
	@PostMapping("pag1")
	@ResponseBody
	public String pag1() {
		return "pagina 1 lorem ipsum";
	}
	
	@PostMapping("pag2")
	@ResponseBody
	public String pag2() {
		return "pagina 2 lorem ipsum2";
	}
	
	@GetMapping("dashboard")
	@ResponseBody
	@PreAuthorize("hasRole('ADMIN')")
	public String info() {
		return "Vedi la dashboard perchè dovresti avere il permesso ADMIN";
	}
}
