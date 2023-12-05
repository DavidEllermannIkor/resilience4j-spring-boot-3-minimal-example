package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.BackendService;

@RestController
@RequestMapping("/test")
public class DemoController {
	
	public DemoController(BackendService backendService) {
		this.backendService = backendService;
	}

	private final BackendService backendService;
	
	@GetMapping(path = "start")
	public HttpStatusCode start() {
		return backendService.send();
	}

  @GetMapping(path = "not-available")
  @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Internal Server Error")
  public void notAvailable() {
  }
  
  @GetMapping(path = "available")
  @ResponseStatus(code = HttpStatus.OK, reason = "OK")
  public void available() {
  }
}