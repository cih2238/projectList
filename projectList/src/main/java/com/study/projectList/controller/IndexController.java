package com.study.projectList.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

	@GetMapping("/")
	public String goIndex() {
		return "/index_kor";
	}
	@GetMapping("/kor")
	public String goIndexKor() {
		return "/index_kor";
	}
	@GetMapping("/eng")
	public String goIndexEng() {
		return "/index_eng";
	}
}
