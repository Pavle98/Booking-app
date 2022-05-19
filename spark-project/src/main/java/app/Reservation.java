package app;

import java.util.concurrent.atomic.AtomicInteger;

public class Reservation {
	private int id;
	private boolean isAvailable;
	private Flight flight;
	private Ticket ticket;
	private User reservationUser;
	private boolean outDated;
	private boolean expired;
	//private static AtomicInteger idCounter = new AtomicInteger();
	private static AtomicInteger idCounter2 = new AtomicInteger();
	public boolean isExpired() {
		return expired;
	}
	public void setExpired(boolean expired) {
		this.expired = expired;
	}
	public boolean isOutDated() {
		return outDated;
	}
	public void setOutDated(boolean outDated) {
		this.outDated = outDated;
	}
	public void setId() {
		this.id = idCounter2.getAndIncrement();
	}
	public User getReservationUser() {
		return reservationUser;
	}
	public void setReservationUser(User reservationUser) {
		this.reservationUser = reservationUser;
	}
	public int getId() {
		return id;
	}
	public Flight getFlight() {
		return flight;
	}
	public Ticket getTicket() {
		return ticket;
	}
	public boolean isAvailable() {
		return isAvailable;
	}
	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	public void setFlight(Flight reservationFlight) {
		this.flight = reservationFlight;
	}

	public void setTicket(Ticket reservationTicket) {
		this.ticket = reservationTicket;
	}
	
	public void setUser(User reservationUser) {
		this.reservationUser = reservationUser;
	}
}
