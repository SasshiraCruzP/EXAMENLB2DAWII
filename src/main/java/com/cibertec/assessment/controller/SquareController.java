package com.cibertec.assessment.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.assessment.model.Square;
import com.cibertec.assessment.service.SquareService;

@RestController
@RequestMapping("/cuadrado")
public class SquareController {
	
	private SquareService squareServ;
	
	public SquareController(SquareService squareServ) {
		super();
		this.squareServ = squareServ;
	}
	
	@PostMapping
	public ResponseEntity<Square> crearProducto(@RequestBody Square cuadrado) {
			
		
		squareServ.create(cuadrado);
		System.out.println("controller: "+cuadrado);
		
		if(cuadrado.getId()!=null)
			return ResponseEntity.status(HttpStatus.CREATED).body(cuadrado);
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(cuadrado);
			
	}
	
	@GetMapping
	public ResponseEntity<List<Square>> list(){
		
		
		return  new ResponseEntity<>(squareServ.list(), HttpStatus.OK);
	}
	
	

	
 

}
