package app;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class Ticket {

	private int ticketId;
	private Company company;
	private boolean oneWay;
	private Date departDate;
	private Date returnDate;
	private int flightId; 
	private long count; //number of reamaining tickets
	//private static AtomicInteger idCounter = new AtomicInteger();
	private static AtomicInteger idCounter1 = new AtomicInteger();
	
	////////
	
	
	private String test;
	
	///////

	 public void setTicketId() {
	        this.ticketId = idCounter1.getAndIncrement();
	    }
	
	public String getTest() {
		return test;
	}
	public void setTest(String test) {
		this.test = test;
	}
	public int getTicketId() {
		return ticketId;
	}
	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public boolean isOneWay() {
		return oneWay;
	}
	public void setOneWay(boolean oneWay) {
		this.oneWay = oneWay;
	}
	public Date getDepartDate() {
		return departDate;
	}
	public void setDepartDate(Date departDate) {
		this.departDate = departDate;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	public int getFlightId() {
		return flightId;
	}
	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
}
