package app.user;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import app.City;
import app.Company;
import app.Flight;
import app.Reservation;
import app.Ticket;
import app.User;

/**
 * Repository se ovde bavi imitacijom komunikacije sa bazom
 */
public class UserRepository {

	private static List<User> USER_LIST;
	private static List<City> CITY_LIST;
	private static List<Company> COMPANY_LIST;

	private static List<Ticket> TICKET_LIST;
	private static List<Ticket>	SEARCHED_TICKET_LIST;
	private static List<Reservation> USER_RESERVATIONS;
	private static List<Flight> FLIGHT_LIST;
	private static List<Reservation> RESERVATION_LIST;
	private static List <Ticket> COMPANY_TICKETS;
	private static List <Ticket> PAGINATION_TICKETS;
	private static List <Ticket> LAST_USED_TICKETS;
	/**
	 * Java identifikuje objekte po njihovom hashCode-u. Izmenom vrednosti atributa
	 * objekta, i hashCode objekta se menja. Zato je najbolje kao monitor koristiti
	 * final Object().
	 */
	private static final Object LOCK = new Object();
	static {
		COMPANY_LIST = loadCompanies();
		for (Company c : COMPANY_LIST) {
			// System.out.println(c.getCompanyName());
		}
	}
	static {
		CITY_LIST = loadCities();
		for (City c : CITY_LIST) {
			// System.out.println(c.getCityName());
		}
	}

	static {
		USER_LIST = generateUsers();
	}
	static {
		TICKET_LIST = loadTickets();
		for (Ticket t : TICKET_LIST) {
			// System.out.println(t.getCompany().getCompanyName());
		}
	}
	static {
		FLIGHT_LIST = loadFlights();
		for (Flight f : FLIGHT_LIST) {
			// System.out.println(f.getList());
		}
	}
	static {
		RESERVATION_LIST = loadReservations();
		for (Reservation r : RESERVATION_LIST) {
			// System.out.println(r.getReservationUser().getUsername());
		}
	}

	/**
	 * Generise 10 korisnika birajuci nasumicno imena i prezimena iz liste
	 *
	 * @return
	 */

	public synchronized static List<Reservation> loadReservations() {
		List<Reservation> reservations = new ArrayList<>();
		File file = new File("Reservations.txt");
		String st;
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			while ((st = br.readLine()) != null) {
				System.out.println("dzoni ");
				Reservation r = new Reservation();
				String parts[] = st.split(",");
				r.setId();

				for (Flight f : FLIGHT_LIST) {
					if (Integer.parseInt(parts[1]) == f.getFlightId()) {
						r.setFlight(f);
					}
				}
				for (Ticket t : TICKET_LIST) {
					if (Integer.parseInt(parts[2]) == t.getTicketId()) {
						r.setTicket(t);
					}
				}

				for (User u : USER_LIST) {
					//System.out.println(u.getUsername());
					if (Integer.parseInt(parts[3]) == u.getId()) {
						r.setUser(u);
					}
				}
				reservations.add(r);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return reservations;

	}

	public static List<User> generateUsers() {
		List<User> users = new ArrayList<>();



	

		File file = new File("Users.txt");
		String st;
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			while ((st = br.readLine()) != null) {
				User u = new User();
				String parts[] = st.split(",");
				u.setId();
				u.setUsername(parts[1]);
				u.setPassword(parts[2]);
				u.setType(parts[3]);
				users.add(u);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return users;
	}

	public static List<User> getUsers() {
		return USER_LIST;
	}

	public static List<Company> getCompanies() {
		return COMPANY_LIST;
	}

	public static List<Flight> getFlights() {
		return FLIGHT_LIST;
	}
	public static List<Reservation> getRESERVATION_LIST() {
		return RESERVATION_LIST;
	}

	public static List<Ticket> getTickets() {

		return TICKET_LIST;
	}
	public static List<Ticket> getLAST_USED_TICKETS() {
		return LAST_USED_TICKETS;
	}
	public static void setLAST_USED_TICKETS(List<Ticket> lAST_USED_TICKETS) {
		LAST_USED_TICKETS = lAST_USED_TICKETS;
	}
	public static void setUSER_RESERVATIONS(List<Reservation> uSER_RESERVATIONS) {
		USER_RESERVATIONS = uSER_RESERVATIONS;
	}
	public static List<Reservation> getUSER_RESERVATIONS() {
		return USER_RESERVATIONS;
	}
	public static List<Ticket> getCOMPANY_TICKETS() {
		return COMPANY_TICKETS;
	}
	public static void setCOMPANY_TICKETS(List<Ticket> cOMPANY_TICKETS) {
		COMPANY_TICKETS = cOMPANY_TICKETS;
	}
	public static List<Ticket> getSEARCHED_TICKET_LIST() {
		return SEARCHED_TICKET_LIST;
	}
	public static void setSEARCHED_TICKET_LIST(List<Ticket> sEARCHED_TICKET_LIST) {
		SEARCHED_TICKET_LIST = sEARCHED_TICKET_LIST;
	}

	public static User getUserById(Integer id) {
		synchronized (LOCK) {
			return USER_LIST.get(id);
		}
	}
	public static List<Ticket> getPAGINATION_TICKETS() {
		return PAGINATION_TICKETS;
	}
	public static void setPAGINATION_TICKETS(List<Ticket> pAGINATION_TICKETS) {
		PAGINATION_TICKETS = pAGINATION_TICKETS;
	}

	public synchronized static List<Ticket> loadTickets() {
		List<Ticket> tickets = new ArrayList<>();
		File file = new File("Tickets.txt");

		String st;
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			while ((st = br.readLine()) != null) {
				Ticket t = new Ticket();
				String parts[] = st.split(",");

				
				for (Company c : COMPANY_LIST) {
					if (Integer.parseInt(parts[1]) == c.getId()) {
						t.setCompany(c);
					}
				}

				t.setOneWay(Boolean.parseBoolean(parts[2]));
				Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(parts[3]);

				t.setDepartDate(date1);
				t.setTicketId();
				if (!parts[4].equals("none")) {
					Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse(parts[4]);
					t.setReturnDate(date2);
				} else {
					t.setReturnDate(null);
				}
				t.setFlightId(Integer.parseInt(parts[5]));
				t.setCount(Integer.parseInt(parts[6]));

				tickets.add(t);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tickets;

	}
	public synchronized static void addUserToBase(User user) {
		try {
		FileWriter fw = new FileWriter("Users.txt", true);
		 BufferedWriter br = new BufferedWriter(fw);
		 br.write("\n" + user.getId() + "," + user.getUsername()+ "," + user.getPassword() + ","+ user.getType());
		 br.close();
		 fw.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public synchronized static void addTicketToBase(Ticket ticket) {
		try {
			FileWriter fw = new FileWriter("Tickets.txt", true);
			 BufferedWriter br = new BufferedWriter(fw);
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);

		
				if(ticket.getReturnDate() != null) {
				br.write("\n"+ticket.getTicketId() + "," + ticket.getCompany().getId() + "," + ticket.isOneWay() + ","
						+ dateFormat.format(ticket.getDepartDate()) + "," 
						+ dateFormat.format(ticket.getReturnDate()) + "," + ticket.getFlightId() + ","
						+ ticket.getCount());
				}else {
					br.write("\n"+ticket.getTicketId() + "," + ticket.getCompany().getId() + "," + ticket.isOneWay() + ","
							+ dateFormat.format(ticket.getDepartDate()) + "," 
							+ "none" + "," + ticket.getFlightId() + ","
							+ ticket.getCount());
				}
				br.close();
				fw.close();
			}
			
	 catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public synchronized static void refreshReservationsInBase(Reservation reservation) {
		try {
			FileWriter fw = new FileWriter("Reservations.txt", true);
			 BufferedWriter br = new BufferedWriter(fw);
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);

		
				
				br.write(reservation.getId() +"," + reservation.getFlight().getFlightId()
						+ "," + reservation.getTicket().getTicketId() +","
				+ reservation.getReservationUser().getId() + "\n");
				
				br.close();
				fw.close();
			}
			
	 catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public synchronized static void refreshFlightsInBase(Flight flight) {
		try {
			FileWriter fw = new FileWriter("Flights.txt", true);
			 BufferedWriter br = new BufferedWriter(fw);
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);

			String stringFlight = new String();
			for(Ticket t : flight.getList()) {
				stringFlight += t.getTicketId() + " ";
			}
			stringFlight = stringFlight.substring(0, stringFlight.length()-1);
			
				
				br.write(flight.getFlightId() +"," + stringFlight + "," + flight.getDepartureCity().getId() +"," + flight.getDestinationCity().getId() + "\n");
				
				br.close();
				fw.close();
			}
			
	 catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public synchronized static void refreshUsersInBase(User user) {
		try {
			FileWriter fw = new FileWriter("Users.txt", true);
			 BufferedWriter br = new BufferedWriter(fw);
			

		
				
				br.write( user.getId() +"," + user.getUsername() + "," + user.getPassword() +"," + user.getType()+ "\n");
				
				br.close();
				fw.close();
			}
			
	 catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public synchronized static void refreshTicketsInBase(Ticket ticket) {
		try {
		
			FileWriter fw = new FileWriter("Tickets.txt", true);
			 BufferedWriter br = new BufferedWriter(fw);
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
			System.out.println(ticket.getTicketId());
		
				if(!ticket.isOneWay()) {
				br.write(  ticket.getTicketId() +"," + ticket.getCompany().getId() + "," + ticket.isOneWay() +"," + dateFormat.format(ticket.getDepartDate()) + 
						"," +dateFormat.format(ticket.getReturnDate()) + "," + ticket.getFlightId() + "," + ticket.getCount()+"\n");
				}else {
					br.write(ticket.getTicketId() +"," + ticket.getCompany().getId() + "," + ticket.isOneWay() +"," + dateFormat.format(ticket.getDepartDate()) + 
							"," + "none" + "," + ticket.getFlightId() + "," + ticket.getCount()+ "\n");
				}
				br.close();
				fw.close();
			}
			
	 catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public synchronized static void refreshCompaniesInBase(Company company) {
		try {
			FileWriter fw = new FileWriter("Companies.txt", true);
			 BufferedWriter br = new BufferedWriter(fw);
			

		
				
				br.write(company.getCompanyName() + "," + company.getId() + "\n");
				
				br.close();
				fw.close();
			}
			
	 catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public static List<Company> loadCompanies() {
		List<Company> companies = new ArrayList<>();
		File file = new File("Companies.txt");
		String st;
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			while ((st = br.readLine()) != null) {
				Company c = new Company();
				String parts[] = st.split(",");

				c.setCompanyName(parts[0]);
				c.setId(Integer.parseInt(parts[1]));

				companies.add(c);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return companies;

	}

	public static List<City> loadCities() {
		List<City> cities = new ArrayList<>();
		File file = new File("Cities.txt");
		String st;
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			while ((st = br.readLine()) != null) {
				City c = new City();
				String parts[] = st.split(",");
				c.setId(Integer.parseInt(parts[1]));
				c.setCityName(parts[0]);

				cities.add(c);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return cities;
	}
	
	

	public static List<Flight> loadFlights() {
		List<Flight> flights = new ArrayList<>();
		File file = new File("Flights.txt");

		String st;
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			while ((st = br.readLine()) != null) {
				Flight f = new Flight();
				ArrayList<Ticket> flightTickets = new ArrayList<Ticket>();
				String parts[] = st.split(",");
				String tickets[] = parts[1].split(" ");
				for (int i = 0; i < tickets.length; i++) {
					for (Ticket t : TICKET_LIST) {
						if (Integer.parseInt(tickets[i]) == t.getTicketId()) {
							flightTickets.add(t);
						}
					}
				}
				f.setFlightId(Integer.parseInt(parts[0]));
				f.setList(flightTickets);
				for (City c : CITY_LIST) {
					
					if (Integer.parseInt(parts[2]) ==(c.getId())) {
						
						f.setDepartureCity(c);
					}
					if (Integer.parseInt(parts[3]) ==(c.getId())) {
						f.setDestinationCity(c);
					}
				}
				
				// ovde si stao, treba f.setcities, to radis tako sto ces napraviti metodu
				// makeCities i smestiti ih u listu
				// zatim uporediti id-jeve i to je to;

				flights.add(f);
				
			
			
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flights;
	}

	/*public static User addUser(User user) {

		/*
		 * Referenca 'user' se nalazi na steku trenutnog treda, zato sa njom mozemo
		 * raditi bez kljuca.
		 
		user.setId();

		
		 USER_LIST je zajednicki resurs, zato mu pristupamo pod kljucem.
		 
		synchronized (LOCK) {
			USER_LIST.add(user);
		}  Kada smo zavrsili sa zajednickim resursom, oslobadjamo kljuc

		return user;
	}*/

	public static User findUser(String username, String password) {
		synchronized (LOCK) {
			if(username == "" || password == "") {
				return null;
			}
			for (User u : USER_LIST) {
				if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
					return u;
				}
			}
		}
		return null;
	}

	public static User findUserByUsername(String username) {
		synchronized (LOCK) {
			for (User u : USER_LIST) {
				if (u.getUsername().equals(username)) {
					return u;
				}
			}
		}
		return null;
	}

	public synchronized static User makeUser(String username, String password, String type) {
		System.out.println("its blank!?");
		System.out.println(username + "/"+  password + "/" + type);
		if(username.equals("") || password.equals("") || type.equals("")) {
			System.out.println("its blank!?");
			return null;
		}
		User u = new User();
		u.setUsername(username);
		u.setPassword(password);
		u.setType(type);
		return u;
	}

	public synchronized  static Ticket makeTicket(Boolean oneWay, Date departDate, Date returnDate, int count) {
		Ticket t = new Ticket();
		System.out.println("help");
		System.out.println(oneWay + " " + departDate + " " + returnDate + " " + count);
		System.out.println("help");
		t.setOneWay(oneWay);
		t.setDepartDate(departDate);
		t.setReturnDate(returnDate);
		t.setCount(count);

		return t;
	}

	
}
