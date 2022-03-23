package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio;

import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;

import java.util.ArrayList;
import java.util.Iterator;

public class Aulas {

	private List<Aula> coleccionAulas;
	
	public Aulas(){
		
		coleccionAulas = new ArrayList<>();
	}
	
	
	public Aulas(Aulas aulas) {
		
		setAulas(aulas);
	}

	public void setAulas(Aulas aulas) {
		
		if (aulas == null) {
			throw new NullPointerException("ERROR: No se pueden copiar aulas nulas.");
		}
		
		coleccionAulas = copiaProfundaAulas(aulas.coleccionAulas);
	}
	
	public List<Aula> getAulas() {
		return copiaProfundaAulas(coleccionAulas);
	}
	
	private List<Aula> copiaProfundaAulas(List<Aula> aulas) {
		
		List<Aula> otrasAulas = new ArrayList<>();
		
		
		for(Iterator<Aula> it = aulas.iterator(); it.hasNext();) {
			
			Aula aula = it.next();
			otrasAulas.add(new Aula(aula));
			
		}
		
		return otrasAulas;
				
	}
	
	public int getNumAulas() {
		
		return coleccionAulas.size();
		
	}
	
	
	public void insertar(Aula aula) throws OperationNotSupportedException {

		if (aula == null) {
			throw new NullPointerException("ERROR: No se puede insertar un aula nula.");
		}

		if(!coleccionAulas.contains(aula)) {
			
			coleccionAulas.add(aula);

		} else {
			throw new OperationNotSupportedException("ERROR: Ya existe un aula con ese nombre.");

		}

	}
	
	public Aula buscar(Aula aula) { // Busco indice/posicion donde esta esta aula

		if (aula == null) {
			throw new NullPointerException("ERROR: No se puede buscar un aula nula.");
		}
		
		int indice = coleccionAulas.indexOf(aula);
		
		if(indice == -1) {
			return null;
		}else {
			return new Aula(coleccionAulas.get(indice)); 
		}
		
	}
	
	public void borrar(Aula aula) throws OperationNotSupportedException {

		if (aula == null) {

			throw new NullPointerException("ERROR: No se puede borrar un aula nula.");
		}

        if(!coleccionAulas.contains(aula)) {
			
        	throw new OperationNotSupportedException("ERROR: No existe ningún aula con ese nombre.");
		}else {
			coleccionAulas.remove(aula);
		}
	}	
	
	
	public List<String> representar() {
	
		List<String> reprecentacion = new ArrayList<>();
		
		for (Iterator<Aula> it = getAulas().iterator(); it.hasNext();) {
			
			reprecentacion.add(it.next().toString());
		}
		return reprecentacion;
	}
	
}
