package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import java.util.Iterator;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.*;

public class Reservas {
	
	private List<Reserva> coleccionReservas;
	
	public Reservas() {
		
		coleccionReservas = new ArrayList<>();
	}
	
	public Reservas(Reservas reservas) {
		
		setReservas(reservas);
		
	}
	
	private void setReservas(Reservas reservas) {
		
		if (reservas == null) {
			throw new NullPointerException("ERROR: No se pueden copiar reservas nulas.");
		}
		
		coleccionReservas = copiaProfundaReservas(reservas.coleccionReservas); 
		
	}
	
	private List<Reserva> copiaProfundaReservas(List<Reserva> reservas) {
		
		List<Reserva> otrasReservas = new ArrayList<>(); 
		
		for(Iterator<Reserva> it = reservas.iterator(); it.hasNext();) {
			
			Reserva reserva = it.next();
			otrasReservas.add(new Reserva(reserva));
		}
		
		return otrasReservas;
		
	}
	
	public List<Reserva> getReservas(){
		
		return copiaProfundaReservas(coleccionReservas);		
	}
	
	public int getNumeroReservas() {
		
		return coleccionReservas.size();
	}
	
	public void insertarReserva(Reserva reserva) throws OperationNotSupportedException {
		
		if (reserva == null) {
			throw new NullPointerException("ERROR: No se puede insertar una reserva nula.");
		}
		
		if(!coleccionReservas.contains(reserva)) {
		
			coleccionReservas.add(reserva);
			
		}else {
			throw new OperationNotSupportedException("ERROR: Ya existe una reserva con ese nombre.");
		}
		
	}
	
	private boolean esMesSiguienteOPosterior(Reserva reserva) {
		
		
		
		return false;
		
	}
	
	private List<Reserva> getReservasProfesorMes(Profesor profesor,LocalDate localDate){
		

		List<Reserva> resrvaProfeMes = new ArrayList<>();
		
		Month mesReserva = localDate.getMonth();
		
		for(Iterator<Reserva> it = getReservas().iterator(); it.hasNext();) {
			
			Reserva reserva = it.next();
			
			if(reserva.getProfesor().equals(profesor) && reserva.getPermanencia().getDia().getMonth().equals(mesReserva)) {
				
				resrvaProfeMes.add(new Reserva(reserva));
			}
			
		}
				
		return resrvaProfeMes;
		
	}
	
	private Reserva getReservaAulaDia(Aula aula,LocalDate localDate) {//-----------
		
		List<Reserva> resrvaAulaDia = new ArrayList<>();
		
        int diaReserva = localDate.getDayOfMonth();
        
        for(Iterator<Reserva> it = getReservas().iterator(); it.hasNext();) {
			
			Reserva reserva = it.next();
			
        if(reserva.getAula().equals(aula) && reserva.getPermanencia().getDia().equals(diaReserva)) {
				
        	resrvaAulaDia.add(new Reserva(reserva));
			}
			
        }
		
		return (Reserva) resrvaAulaDia;
		
	}
	
	public Reserva busacar(Reserva reserva) {
		
		if (reserva == null) {
			throw new NullPointerException("ERROR: No se puede buscar una reserva nula.");
		}
		
		int indice = coleccionReservas.indexOf(reserva);
		
		if(indice == -1) {
			
			return null;
		}else {
			
			return new Reserva(coleccionReservas.get(indice));
		}
	
	}
	
	public void borrar(Reserva reserva) throws OperationNotSupportedException {
		
		if (reserva == null) {

			throw new NullPointerException("ERROR: No se puede borrar una reserva nula.");
		}

        if(!coleccionReservas.contains(reserva)) {
			
        	throw new OperationNotSupportedException("ERROR: No existe ningún reserva con ese nombre.");
		}else {
			coleccionReservas.remove(reserva);
		}
		
	}
	
	public List<String> representar() {
		
		List<String> reprecentacion = new ArrayList<>();
		
		for (Iterator<Reserva> it = getReservas().iterator(); it.hasNext();) {
			
			reprecentacion.add(it.next().toString());
		}
		return reprecentacion;
	}
	
	public List<Reserva> getReservasProfesor(Profesor profesor){
		
		List<Reserva> reservasProfe = new ArrayList<>();
		
		
		for(Iterator<Reserva> it = getReservas().iterator(); it.hasNext();) {
			
			Reserva reserva = it.next();
			
			if(reserva.getProfesor().equals(profesor)) {
				
			 reservasProfe.add(new Reserva(reserva));
			}
			
		}
		
		return reservasProfe;	
	}
	
	public List<Reserva> getReservasAulas(Aula aula){
		
		List<Reserva> reservasAula = new ArrayList<>();
		
		for(Iterator<Reserva> it = getReservas().iterator(); it.hasNext();) {
			
			Reserva reserva = it.next();
			
			if(reserva.getAula().equals(aula)) {
				
				reservasAula.add(new Reserva(reserva));
			}
		}
		return reservasAula;
		
	}
	
   public List<Reserva> getReservasPermanencia(Permanencia permanencia){
	   
	   List<Reserva> reservasPermanencia = new ArrayList<>();
	   
	   for(Iterator<Reserva> it = getReservas().iterator(); it.hasNext();) {
		   
		   Reserva reserva = it.next();
		   
		   if(reserva.getPermanencia().equals(permanencia)) {
			   
			   reservasPermanencia.add(new Reserva(reserva));
		   }
		   
	   }
	   
	return reservasPermanencia;
	   
   }
   
   public boolean consultarDisponibilidad(Aula aula, Permanencia permanencia) {
	
	   boolean disponible = true;
	   
	   if (aula == null) {
			throw new NullPointerException("ERROR: No se puede consultar la disponibilidad de un aula nula.");
		}

		if (permanencia == null) {
			throw new NullPointerException("ERROR: No se puede consultar la disponibilidad de una permanencia nula.");
		}
		
		for(Iterator<Reserva> it = getReservas().iterator(); it.hasNext();) {
		
			Reserva reserva = it.next();
			
			if(reserva.getAula().equals(aula) && reserva.getPermanencia().equals(permanencia)) {
				
				disponible = false;
			}
			
		}
	   
	   
	   return disponible;
	   
   }
	
	

}
