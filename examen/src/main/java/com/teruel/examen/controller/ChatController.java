package com.teruel.examen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping({"/", ""})
public class ChatController {
	@GetMapping("/")
	public String showChat(){
		return "chat";
	}
}
