package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio;

import java.util.List;

import javax.naming.OperationNotSupportedException;

import java.util.ArrayList;
import java.util.Iterator;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;

public class Profesores {

	private List<Profesor> coleccionProfesores;
	
    public  Profesores(){
		
		coleccionProfesores = new ArrayList<>();
	}
    
    public Profesores(Profesores profesores) {
		
		setProfesores(profesores);
	}

    public void setProfesores(Profesores profesores) {
		
		if (profesores == null) {
			throw new NullPointerException("ERROR: No se pueden copiar profesores nulos.");
		}
		
		coleccionProfesores = copiaProfundaProfesores(profesores.coleccionProfesores);
	}
    
    public List<Profesor> getProfesores() {
    	
		return copiaProfundaProfesores(coleccionProfesores);
	}
    
    private List<Profesor> copiaProfundaProfesores(List<Profesor> profesores) {
		
		List<Profesor> otrosProfesores = new ArrayList<>();
		
		
		for(Iterator<Profesor> it = profesores.iterator(); it.hasNext();) {
			
			Profesor profesor = it.next();
			otrosProfesores.add(new Profesor(profesor));
			
		}
		
		return otrosProfesores;
				
	}
    
    public int getNumProfesores() {
		
		return coleccionProfesores.size();
		
	}
    
    public void insertar(Profesor profesor) throws OperationNotSupportedException {

		if (profesor == null) {
			throw new NullPointerException("ERROR: No se puede insertar un profesor nulo.");
		}

		if(!coleccionProfesores.contains(profesor)) {
			
			coleccionProfesores.add(profesor);

		} else {
			throw new OperationNotSupportedException("ERROR: Ya existe un profesor con ese nombre.");

		}

	}
    
    public Profesor buscar(Profesor profesor) { // Busco indice/posicion donde esta esta aula

		if (profesor == null) {
			throw new NullPointerException("ERROR: No se puede buscar un profesor nulo.");
		}
		
		int indice = coleccionProfesores.indexOf(profesor);
		
		if(indice == -1) {
			return null;
		}else {
			return new Profesor(coleccionProfesores.get(indice)); 
		}
		
	}
    
    public void borrar(Profesor profesor) throws OperationNotSupportedException {

		if (profesor == null) {

			throw new NullPointerException("ERROR: No se puede borrar un profesor nulo.");
		}

        if(!coleccionProfesores.contains(profesor)) {
			
        	throw new OperationNotSupportedException("ERROR: No existe ningún profesor con ese nombre.");
		}else {
			coleccionProfesores.remove(profesor);
		}
	}	
    
    public List<String> representar() {
    	
		List<String> reprecentacion = new ArrayList<>();
		
		for (Iterator<Profesor> it = getProfesores().iterator(); it.hasNext();) {
			
			reprecentacion.add(it.next().toString());
		}
		return reprecentacion;
	}
    
    
}
