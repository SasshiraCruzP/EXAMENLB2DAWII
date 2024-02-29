package com.cibertec.assessment.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.assessment.beans.PolygonBean;
import com.cibertec.assessment.model.Polygon;
import com.cibertec.assessment.service.PolygonService;

@RestController
@RequestMapping("/poligono")
public class PolygonController {
	
	private PolygonService polygonServ;
	
	public PolygonController(PolygonService polygonServ) {
        this.polygonServ = polygonServ;
    }
	
	@PostMapping
	public ResponseEntity<Polygon> crearProducto(@RequestBody Polygon poligono) {
			
		
				polygonServ.create(poligono);
				if(poligono.getId()!=null)
					return ResponseEntity.status(HttpStatus.CREATED).body(poligono);
				else
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(poligono);
			
	}
	
	@GetMapping
	public ResponseEntity<List<PolygonBean>> list(){
		
		return  new ResponseEntity<>(polygonServ.list(), HttpStatus.OK)  ;
	}
	
	
}
