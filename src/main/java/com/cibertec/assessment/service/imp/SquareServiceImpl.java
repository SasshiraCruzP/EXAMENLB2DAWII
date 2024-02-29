package com.cibertec.assessment.service.imp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.assessment.beans.PolygonBean;
import com.cibertec.assessment.model.Square;
import com.cibertec.assessment.repo.SquareRepo;
import com.cibertec.assessment.service.PolygonService;
import com.cibertec.assessment.service.SquareService;

@Service
public class SquareServiceImpl implements SquareService{

	@Autowired 
	SquareRepo squareRepo;
	
	@Autowired
	PolygonService polygonService;
	
	//Al momento de crear se debe validar si 
	//alguno de parte del cuadrado se encuentra dentro de algun
	//poligono y de ser asi se debe capturar el id de los poligonos y 
	//guardar como un string pero con formato de array
	//Ejemplo polygons = "["1","2"]"
	//Se guardan los ids correspondites
	//usar los metodos ya existentes para listar polygonos
	@Override
	public void create(Square cuadrado) {
		
	
		cuadrado.setPolygons(cuadradoInscritoEnPoligonos(cuadrado));
		
		System.out.println(cuadradoInscritoEnPoligonos(cuadrado));
		System.out.println(cuadrado);
		
		String xpoint=cuadrado.getXPoints().replaceAll("\\s", "");
		String ypoint=cuadrado.getYPoints().replaceAll("\\s", "");
		System.out.println("******"+xpoint);
		System.out.println("-----"+ypoint);
		String[] xpointArray = xpoint.split(",");
		String[] ypointArray = ypoint.split(",");
		
		int numpointX=0 , numpointY=0;
				
		numpointX=xpointArray.length;
		numpointY=ypointArray.length;
		
		if(numpointX==numpointY) {
			System.out.println("******"+"INGRESA METODO");
			if(cuadrado.getNpoints()==4) {
				System.out.println("******"+"CUMPLE CONDICION 4 PUNTOS");
				cuadrado.setXPoints(Arrays.toString(xpointArray));
				cuadrado.setYPoints(Arrays.toString(ypointArray));	
				
				cuadrado = squareRepo.save(cuadrado);
				
				System.out.println("S: "+cuadrado);
			}
		}
		
	}

	@Override
	public List<Square> list() {
		
		List<Square> list = squareRepo.findAll();
		return list;
	}
	
	
	
	private String cuadradoInscritoEnPoligonos(Square cua) {
		
		System.out.println(cua);
		List<PolygonBean> poligonos = polygonService.list();
		System.out.println(poligonos);
		List<PolygonBean> poligonosIncribenCuadrado = new ArrayList<PolygonBean>();
        
        for (PolygonBean poligono : poligonos) {
        	
            // Obtener puntos del poligono
            Integer[] xPoligono = poligono.getXPoints();
            Integer[] yPoligono = poligono.getYPoints();
            
            // Obtener puntos del cuadrado
            boolean todosDentro = true;
            
            String[] xPoints = cua.getXPoints().split(",");
            String[] yPoints = cua.getYPoints().split(",");
            Integer[] xCuadrado = new Integer[xPoints.length];
            Integer[] yCuadrado = new Integer[yPoints.length];
            
            for (int i = 0; i < xPoints.length; i++) {
                xCuadrado[i] = Integer.parseInt(xPoints[i].trim());
                yCuadrado[i] = Integer.parseInt(yPoints[i].trim());
            }
            
           // Verificar si el cuadrado esta inscrito en el poligono
            
            for (int i = 0; i < 4; i++) {
                if (!puntoEnPoligono(xCuadrado[i], yCuadrado[i], xPoligono, yPoligono)) {
                	
                    todosDentro = false;
                    break;
                }
            }
            System.out.println("rr: "+todosDentro);
            // Si todos los vértices están dentro, agregar el polígono a la lista de polígonos inscritos
            if (todosDentro) {
            	poligonosIncribenCuadrado.add(poligono);
            }
        }
		
        // Obtener ids de poligonos
        
        String polygonsIds = "[";
        
        System.out.println(poligonosIncribenCuadrado);
        System.out.println(poligonosIncribenCuadrado.size());
        if(poligonosIncribenCuadrado.size() > 0)
        	polygonsIds += poligonosIncribenCuadrado.get(0).getId();
        
        System.out.println(polygonsIds);
        
        for (int i = 1; i < poligonosIncribenCuadrado.size(); i++) {
        	
        	polygonsIds += ","+poligonosIncribenCuadrado.get(i).getId();
        }

        polygonsIds += "]";
        
		return polygonsIds;
		
	}

	// Método para determinar si un punto (x, y) está dentro de un polígono definido por sus coordenadas x e y
    private static boolean puntoEnPoligono(int x, int y, Integer[] xPoligono, Integer[] yPoligono) {
        int n = xPoligono.length;
        boolean dentro = false;
        
        // Algoritmo de ray casting para determinar si el punto está dentro del polígono
        for (int i = 0, j = n - 1; i < n; j = i++) {
        	System.out.println(x);
            System.out.println(y);
            System.out.println(xPoligono[i]);
            System.out.println(yPoligono[i]);
            System.out.println(xPoligono[j]);
            System.out.println(yPoligono[j]);
            if (((yPoligono[i] > y) != (yPoligono[j] > y)) &&
                (x < (xPoligono[j] - xPoligono[i]) * (y - yPoligono[i]) / (yPoligono[j] - yPoligono[i]) + xPoligono[i])) {
                dentro = !dentro;
            }
            System.out.println(dentro);
        }
        System.out.println("r: "+dentro);
        return dentro;
    }
}
