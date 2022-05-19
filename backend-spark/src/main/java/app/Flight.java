package app;

import java.util.List;

public class Flight {
	private int flightId;
	public int getFlightId() {
		return flightId;
	}
	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}
	public List<Ticket> getList() {
		return list;
	}
	public void setList(List<Ticket> list) {
		this.list = list;
	}
	public City getDepartureCity() {
		return departureCity;
	}
	public void setDepartureCity(City departureCity) {
		this.departureCity = departureCity;
	}
	public City getDestinationCity() {
		return destinationCity;
	}
	public void setDestinationCity(City destinationCity) {
		this.destinationCity = destinationCity;
	}
	private List<Ticket> list;
	private City departureCity;
	private City destinationCity;
	
}
