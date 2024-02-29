package com.cibertec.assessment.service;

import java.util.List;

import com.cibertec.assessment.model.Square;

public interface SquareService {

	public void create(Square s);
	
	public List<Square> list();
}
