package com.cibertec.assessment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cibertec.assessment.model.Polygon;
import com.cibertec.assessment.service.PolygonService;

import jakarta.annotation.PostConstruct;

@Component
public class DataLoader {

	@Autowired
	PolygonService polygonService;

	@PostConstruct
	public void initializeData() {
		List<Polygon> list = new ArrayList<>();

		String[] xpoint1 = { "0", "205", "305", "405", "500" };
		String[] ypoint1 = { "0", "501", "506", "107", "30" };

		String[] xpoint2 = { "10", "605", "305", "405", "500" };
		String[] ypoint2 = { "10", "601", "506", "337", "300" };
		
		int n =xpoint1.length;
		int m =xpoint2.length;
		System.out.println("NUMERO DE PUNTOS ****" + n);
		
		Polygon polygon1 = new Polygon().builder().name("Poligon 01").npoints(n).xPoints(Arrays.toString(xpoint1)).yPoints(Arrays.toString(ypoint1)).build();
		Polygon polygon2 = new Polygon().builder().name("Poligon 01").xPoints(Arrays.toString(xpoint2)).yPoints(Arrays.toString(ypoint2)).build();
		list.add(polygon1);
		list.add(polygon2);
		System.out.println(Arrays.toString(xpoint2));
		//polygonService.create(list);
		
		//polygonService.list();
	}
}
