package org.iesalandalus.programacion.reservasaulas.mvc.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.*;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.*;

public class Modelo {

	private Aulas aulas;
	private Profesores profesores;
	private Reservas reservas;

	public Modelo() {

		aulas = new Aulas();
		profesores = new Profesores();
		reservas = new Reservas();
	}

	public List<Aula> getAulas() {
		return aulas.getAulas();
	}

	public int getNumAulas() {
		return aulas.getNumAulas();
	}

	public List<String> representarAulas() {
		return aulas.representar();
	}

	public Aula buscar(Aula aula) {

		return aulas.buscar(aula);
	}

	public void insertarAula(Aula aula) throws OperationNotSupportedException {

		aulas.insertar(aula);
	}

	public void borrarAula(Aula aula) throws OperationNotSupportedException {

		aulas.borrar(aula);

	}

	public List<Profesor> getProfesores() {
		return profesores.getProfesores();
	}

	public int getNumProfesores() {
		return profesores.getNumProfesores();
	}

	public List<String> representarProfesores() {
		return profesores.representar();
	}

	public Profesor buscarProfesor(Profesor profesor) {

		return profesores.buscar(profesor);
	}

	public void insertarProfesor(Profesor profesor) throws OperationNotSupportedException {

		profesores.insertar(profesor);
	}

	public void borrarProfesor(Profesor profesor) throws OperationNotSupportedException {

		profesores.borrar(profesor);

	}

	public List<Reserva> getReservas() {
		return reservas.getReservas();
	}

	public int getNumReservas() {
		return reservas.getNumReservas();
	}

	public List<String> representarReservas() {
		return reservas.representar();
	}

	public Reserva buscarReserva(Reserva reserva) {

		return reservas.buscar(reserva);
	}

	public void realizarReserva(Reserva reserva) throws OperationNotSupportedException {

		if (reserva == null) {

			throw new NullPointerException("ERROR: No se puede reservar un aula nula.");
		}
		
		Profesor profeReservaRecibida = reserva.getProfesor();
		
		
		if(profeReservaRecibida == null) {
			
			throw new OperationNotSupportedException("ERROR: Profesor no pude ser nulo.");
			
		}
			
	    if(profesores.buscar(profeReservaRecibida) == null) {
				
		   throw new OperationNotSupportedException("ERROR: Profesor no esta registrado en el sistema");
		}
		
		
		
		/*if( profesores.buscar(reserva.getProfesor()) == null) {
			
			throw new OperationNotSupportedException("ERROR: Profesor no pude ser nulo.");
		}*/
		
		if (aulas.buscar(reserva.getAula()) == null) {

			throw new OperationNotSupportedException("ERROR: Aula no esta registrado en el sistema");
		}
		
		if(reservas.consultarDisponibilidad(reserva.getAula(), reserva.getPermanencia()) == false) {
			
			throw new OperationNotSupportedException("ERROR: Esta aula ya está  reservada para este tramo de  este dia");
		}
		
	
		reservas.insertar(reserva);
	}

	public void anularReserva(Reserva reserva) throws OperationNotSupportedException {

		reservas.borrar(reserva);

	}

	public List<Reserva> getReservasAula(Aula aula) {
		return reservas.getReservasAula(aula);
	}

	public List<Reserva> getReservasProfesor(Profesor profesor) {
		return reservas.getReservasProfesor(profesor);
	}

	public List<Reserva> getReservasPermanencia(Permanencia permanencia) {
		return reservas.getReservasPermanencia(permanencia);
	}

	public boolean consultarDisponibilidad(Aula aula, Permanencia permanencia) {

		if(aulas.buscar(aula) == null) {
			
			throw new NullPointerException("ERROR: No se puede consultar un aula que no este registrada.");
		}
		
		return reservas.consultarDisponibilidad(aula, permanencia);
	}

}
