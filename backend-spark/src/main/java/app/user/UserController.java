package app.user;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import com.google.gson.Gson;

import app.City;
import app.Company;
import app.Flight;
import app.Reservation;
import app.Ticket;
import app.User;
import app.authentication.AuthService;
import spark.Request;
import spark.Response;
import spark.Route;

public class UserController {

	static int bottomLimit = -4;
	static int topLimit = 0;
	static boolean increase = true;
	private static Gson gson = new Gson();

	public static Route login = (Request request, Response response) -> {
		User user = UserService.findUser(request.queryParams("username"), request.queryParams("password"));
		response.type("application/json");

		if (user != null) {

			user.setJWTToken(AuthService.generateJWT(user));
			return gson.toJson(user);
		} else {
			return "You cant leave fields blank!";
		}
	};

	public static Route makeUsers = (Request request, Response response) -> {
		System.out.println("making");
		User user = UserRepository.makeUser(request.queryParams("username"), request.queryParams("password"),
				request.queryParams("type"));
		if (user == null) {
			return "You cant leave any of the fields blank!";
		}
		for (User u : UserRepository.getUsers()) {
			if (u.getUsername().equals(user.getUsername())) {
				return "Username is already taken";
			}
		}
		response.type("application/json");

		user.setId();
		System.out.println(request.queryParams("type"));
		System.out.println("adding to base");
		System.out.println(request.queryParams("password"));
		UserRepository.addUserToBase(user);
		UserRepository.getUsers().add(user);

		return gson.toJson(user);

	};
	public static Route ticketDelete = (Request request, Response response) -> {
		System.out.println("deleting ticket with id  = ");
		if (AuthService.isAdmin(request.headers("Authorization"))) {
			System.out.println(request.queryParams("ticketId"));
			Ticket ticketToDelete = null;
			for (Ticket t : UserRepository.getTickets()) {
				if (t.getTicketId() == Integer.parseInt(request.queryParams("ticketId"))) {
					ticketToDelete = t;

				}
			}
			UserRepository.getTickets().remove(ticketToDelete);

			PrintWriter writer = new PrintWriter("Tickets.txt");
			writer.print("");
			writer.close();

			for (Ticket ticket : UserRepository.getTickets()) {
				UserRepository.refreshTicketsInBase(ticket);
			}
			System.out.println("okay?");
			response.type("application/json");

			return "Deleting ticket...";
		} else {
			return "You cant delete ticket because you are not admin!";
		}

	};

	public static Route reservationDelete = (Request request, Response response) -> {
		User userThatHasRes = new User();
		for (Reservation r : UserRepository.getRESERVATION_LIST()) {
			if (r.getId() == Integer.parseInt(request.queryParams("reservationTicketsId"))) {
				userThatHasRes = r.getReservationUser();
				System.out.println("deleting reservation with id  = ");

				if (r.isOutDated() == false) {
					System.out.println(r.isOutDated());
					System.out.println("deleting _ " + r.getId());
					UserRepository.getRESERVATION_LIST().remove(r);
				} else {
					System.out.println("you cant delete it now");
					return "you cant delete it now";
				}
			}
		}

		PrintWriter writer = new PrintWriter("Reservations.txt");
		writer.print("");
		writer.close();
		for (Reservation r : UserRepository.getRESERVATION_LIST()) {
			UserRepository.refreshReservationsInBase(r);
		}
		return "hey reservationDelete";

	};
	public static Route renameCompany = (Request request, Response response) -> {

		if (AuthService.isAdmin(request.headers("Authorization"))) {

			for (Company c : UserRepository.getCompanies()) {
				if (c.getCompanyName().equals(request.queryParams("newName"))) {
					return "cant rename because name is already taken";
				}
			}

			for (Company c : UserRepository.getCompanies()) {

				if (c.getId() == Integer.parseInt(request.queryParams("company"))) {
					c.setCompanyName(request.queryParams("newName"));
				}
			}

			PrintWriter writer = new PrintWriter("Companies.txt");
			writer.print("");
			writer.close();
			for (Company c : UserRepository.getCompanies()) {
				UserRepository.refreshCompaniesInBase(c);
			}

			response.type("application/json");

			return "Renaming ticket...";
		} else {
			return "You cant rename ticket because you are not admin!";
		}
	};

	public static Route createCompany = (Request request, Response response) -> {

		if (AuthService.isAdmin(request.headers("Authorization"))) {

			for (Company c : UserRepository.getCompanies()) {
				if (c.getCompanyName().equals(request.queryParams("newCompanyName"))) {
					return "Cant create new company because name is already taken";
				}
			}
			// upravo cemo odraditi prilicno jadno resenje jer ******, ne mozemo sad da
			// menjamo id-ejeve kompanija
			int max = 0;
			for (Company c : UserRepository.getCompanies()) {
				if (c.getId() > max) {
					max = c.getId();
				}
			}
			Company newComp = new Company();
			newComp.setCompanyName(request.queryParams("newCompanyName"));
			newComp.setId(max + 1);
			UserRepository.getCompanies().add(newComp);

			PrintWriter writer = new PrintWriter("Companies.txt");
			writer.print("");
			writer.close();
			for (Company c : UserRepository.getCompanies()) {
				UserRepository.refreshCompaniesInBase(c);
			}

			response.type("application/json");

			return "creating Company...";
		} else {
			return "You cant create company because you are not admin!";
		}
	};

	public static Route deleteCompany = (Request request, Response response) -> {
		// deletedCompanyName je ustvari deletedCompanyId
		System.out.println("1");
		ArrayList<Ticket> ticketsToDelete = new ArrayList<Ticket>();
		ArrayList<Reservation> reservationsToDelete = new ArrayList<Reservation>();
		if (AuthService.isAdmin(request.headers("Authorization"))) {
			try {
			Company tempComp = null;
			for (Company c : UserRepository.getCompanies()) {
				if (Integer.parseInt(request.queryParams("deletedCompanyName")) == c.getId()) {
					tempComp = c;
				}
			}
			System.out.println("2");
			for(Reservation r : UserRepository.getRESERVATION_LIST()) {
				if(r.getTicket().getCompany() == tempComp) {
					reservationsToDelete.add(r);
				}
			}
			System.out.println("3");

			for(Ticket t : UserRepository.getTickets()) {
				if(t.getCompany() == tempComp) {
					ticketsToDelete.add(t);
				}
			}
			
			System.out.println("4");
			UserRepository.getCompanies().remove(tempComp);

			PrintWriter writer = new PrintWriter("Companies.txt");
			writer.print("");
			writer.close();
			for (Company c : UserRepository.getCompanies()) {
				UserRepository.refreshCompaniesInBase(c);
			}
		
			
			for(Ticket t : ticketsToDelete) {
				UserRepository.getTickets().remove(t);
			}
			for(Reservation r : reservationsToDelete ) {
				UserRepository.getRESERVATION_LIST().remove(r);
			}
			PrintWriter writer2 = new PrintWriter("Tickets.txt");
			writer2.print("");
			writer2.close();
			for (Ticket t : UserRepository.getTickets()) {
				UserRepository.refreshTicketsInBase(t);
			}
		
			
			PrintWriter writer3 = new PrintWriter("Reservations.txt");
			writer3.print("");
			writer3.close();
			for (Reservation r : UserRepository.getRESERVATION_LIST()) {
				UserRepository.refreshReservationsInBase(r);;
			}
			System.out.println("3");
			response.type("application/json");
			}catch(Exception e) {
				e.printStackTrace();
			}
			return "deeleting company";
		} else {
			return "you cant delete company because you are not admin!";
		}
	};
	public static Route ticketChange = (Request request, Response response) -> {
		Company tempComp = null;
		SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
		if (AuthService.isAdmin(request.headers("Authorization"))) {
			System.out.println(request.queryString());
			try {
			if(request.queryParams("company").equals("")) {
				System.out.println("1");
				return "Fields cant be blank!";
			}
			if(request.queryParams("flightId").equals("")) {
				System.out.println("2");
				return "Fields cant be blank!";
			}
			if(request.queryParams("departDate").equals("")) {
				System.out.println("23");
				return "Fields cant be blank!";
			}
			if(request.queryParams("count").equals("")) {
				System.out.println("14");
				return "Fields cant be blank!";
			}}catch(Exception e ) {
				e.printStackTrace();
			}
			System.out.println("1");
			Date departDate = parser.parse(request.queryParams("departDate"));
			if ((request.queryParams("retunDate")) != "" ) {
				Date returnDate = parser.parse(request.queryParams("returnDate"));
				if (returnDate.before(departDate)) {
					return "Return date cant be before depart date!";
				}
			}
			
			for (Company c : UserRepository.getCompanies()) {
				if (request.queryParams("company").equals(c.getCompanyName())) {
					tempComp = c;
				}
			}
			for (Ticket t : UserRepository.getTickets()) {
				if (t.getTicketId() == Integer.parseInt(request.queryParams("ticketId"))) {
					t.setCompany(tempComp);
					t.setFlightId(Integer.parseInt(request.queryParams("flightId")));
					t.setCount(Integer.parseInt(request.queryParams("count")));
				
						
						t.setDepartDate(departDate);
						t.setOneWay(true);
						t.setReturnDate(null);
						if ((request.queryParams("retunDate")) != "" ) {
						Date returnDate = parser.parse(request.queryParams("returnDate"));
						t.setReturnDate(returnDate);
						t.setOneWay(false);
					}
						System.out.println(t.getReturnDate());

				}
			}

			PrintWriter writer = new PrintWriter("Tickets.txt");
			writer.print("");
			writer.close();
			for (Ticket t : UserRepository.getTickets()) {
				UserRepository.refreshTicketsInBase(t);
			}
			response.type("application/json");

			return "hey";
		} else {
			return "you are not admin, you cant change tickets!";
		}
	};

	public static Route reserveTicket = (Request request, Response response) -> {

		Reservation r = new Reservation();
		if (!AuthService.isAdmin(request.headers("Authorization"))) {

			r.setId();
			User reservationUser = null;
			Ticket reservationTicket = null;
			Flight reservationFlight = null;
			int reservationFlightId = -10;
			for (User user : UserRepository.getUsers()) {
				if (user.getUsername().equals(request.queryParams("username"))) {
					reservationUser = user;
				}
			}
			for (Ticket ticket : UserRepository.getTickets()) {
				if (ticket.getTicketId() == Integer.parseInt(request.queryParams("ticketId"))) {
					ticket.setCount(ticket.getCount() - 1);

					if (ticket.getCount() < 1) {
						UserRepository.getTickets().remove(ticket);
					}

					reservationTicket = ticket;
					reservationFlightId = ticket.getFlightId();
				}
			}

			for (Flight flight : UserRepository.getFlights()) {
				if (flight.getFlightId() == reservationFlightId) {
					reservationFlight = flight;
				}
			}
			r.setReservationUser(reservationUser);
			r.setTicket(reservationTicket);
			r.setFlight(reservationFlight);

			UserRepository.getRESERVATION_LIST().add(r);

			PrintWriter writer = new PrintWriter("Reservations.txt");
			writer.print("");
			writer.close();
			for (Reservation res : UserRepository.getRESERVATION_LIST()) {
				UserRepository.refreshReservationsInBase(res);

			}
			
			PrintWriter writer2 = new PrintWriter("Tickets.txt");
			writer2.print("");
			writer2.close();
			for (Ticket ticketo : UserRepository.getTickets()) {
				UserRepository.refreshTicketsInBase(ticketo);
			}
			response.type("application/json");

			return "hey";
		} else {
			return "you are not user, you cant reserve a ticket!";
		}

	};

	public static Route getCompanyTickets = (Request request, Response response) -> {

		if (!AuthService.isAdmin(request.headers("Authorization"))) {
			UserRepository.setCOMPANY_TICKETS(new ArrayList<Ticket>());
			Company theCompany = null;
			for (Company c : UserRepository.getCompanies()) {
				if (c.getId() == Integer.parseInt(request.queryParams("companyId"))) {
					theCompany = c;
				}
			}
			for (Ticket t : UserRepository.getTickets()) {
				if (theCompany.getId() == t.getCompany().getId()) {
					UserRepository.getCOMPANY_TICKETS().add(t);
				}
			}
			UserRepository.setLAST_USED_TICKETS(UserRepository.getCOMPANY_TICKETS());
			return gson.toJson(UserRepository.getCOMPANY_TICKETS());
		} else {
			return "Only user can see company tickets";
		}

	};

	public static Route getUsersReservations = (Request request, Response response) -> {
		UserRepository.setUSER_RESERVATIONS(new ArrayList<Reservation>());
		UserRepository.setLAST_USED_TICKETS(new ArrayList<Ticket>());
		String username = request.queryParams("username");

		if (!AuthService.isAdmin(request.headers("Authorization"))) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
			// dateLimit nam je danasnji dan minus 24 sata
			Date dateLimit = new Date(System.currentTimeMillis() - TimeUnit.DAYS.toMillis(1));
			Date currentDate = new Date(System.currentTimeMillis());
			for (Reservation r : UserRepository.getRESERVATION_LIST()) {

				if (r.getTicket().getDepartDate().compareTo(currentDate) > 0) {
					r.setExpired(false);

				} else {
					r.setExpired(true);

				}

				if (r.getTicket().getDepartDate().compareTo(dateLimit) > 0) {
					r.setOutDated(false);
				} else {
					r.setOutDated(true);

				}
				if (r.getReservationUser().getUsername().equals(username)) {
					UserRepository.getLAST_USED_TICKETS().add(r.getTicket());
					UserRepository.getUSER_RESERVATIONS().add(r);
				}
			}
			response.type("application/json");

			return gson.toJson(UserRepository.getUSER_RESERVATIONS());
		} else {
			return "You cant see reservations because u are admin";
		}

	};
	public static Route pagination = (Request request, Response response) -> {
		int nextOrPrev = Integer.parseInt(request.queryParams("nextOrPrevious"));
		if (bottomLimit < -4) {
			bottomLimit = 4;
			topLimit = 0;
		}
		if (nextOrPrev == 1) {
			topLimit = topLimit + 4;
			bottomLimit = bottomLimit + 4;
		} else if (nextOrPrev == 2) {
			topLimit = topLimit - 4;
			bottomLimit = bottomLimit - 4;
		}

		ArrayList<Ticket> ticketsWeSend = new ArrayList<Ticket>();
		int temp = -1;
		for (Ticket t : UserRepository.getLAST_USED_TICKETS()) {

			temp++;
			if (temp < bottomLimit) {
				continue;
			}
			if (temp == topLimit) {
				break;
			}
			ticketsWeSend.add(t);
		}
		response.type("application/json");

		return gson.toJson(ticketsWeSend);

	};

	public static Route searchTickets = (Request request, Response response) -> {
		bottomLimit = -4;
		topLimit = 0;
		// STdepartureCity STdestinationCity STdepartDate STreturnDate SToneWay STtwoWay
		if (!AuthService.isAdmin(request.headers("Authorization"))) {
			UserRepository.setSEARCHED_TICKET_LIST(new ArrayList<Ticket>());
			City c1 = new City();
			City c2 = new City();
			SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
			Date departDate = null;
			Date returnDate = null;

			try {
				departDate = parser.parse(request.queryParams("STdepartDate"));

			} catch (Exception e) {

			}
			try {

				returnDate = parser.parse(request.queryParams("STreturnDate"));
			} catch (Exception e) {

			}
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);

			/*
			 * c1.setCityName(request.queryParams("STdepartureCity"));
			 * c2.setCityName(request.queryParams("STdestinationCity"));
			 */
			String searchedDepartCity = request.queryParams("STdepartureCity");
			String searchedDestinationCity = request.queryParams("STdestinationCity");
			Boolean oneWay = Boolean.parseBoolean(request.queryParams("SToneWay"));
			Boolean twoWay = Boolean.parseBoolean(request.queryParams("STtwoWay"));

			int temp = -1;

			// ako je drugi veci vraca -1, prvi veci= 1, isti = 0
			for (Ticket t : UserRepository.getTickets()) {
				String departureCity = " ";
				String returnCity = " ";
				for (Flight f : UserRepository.getFlights()) {
					if (f.getFlightId() == t.getFlightId()) {
						returnCity = f.getDestinationCity().getCityName();
						departureCity = f.getDepartureCity().getCityName();
					}
				}

				if (!searchedDepartCity.equals("") && !searchedDepartCity.equals(departureCity)) {
					continue;
				}
				if (!searchedDestinationCity.equals("") && !searchedDestinationCity.equals(returnCity)) {
					continue;
				}
				if (oneWay == true && t.isOneWay() == false && twoWay == false) {
					continue;
				}
				if (twoWay == true && oneWay == false && t.isOneWay() == true) {
					continue;
				}
				if (departDate != null && departDate.compareTo(t.getDepartDate()) > 0) {
					continue;
				}
				if (returnDate != null) {
					if (t.getReturnDate() != null) {
						if (returnDate.compareTo(t.getReturnDate()) > 0) {
							continue;
						}
					} else {
						continue;
					}

				}

				UserRepository.getSEARCHED_TICKET_LIST().add(t);

			}

			response.type("application/json");

			UserRepository.setLAST_USED_TICKETS(UserRepository.getSEARCHED_TICKET_LIST());
			return gson.toJson(UserRepository.getSEARCHED_TICKET_LIST());
		} else {
			return "You are not User! Behave yourself";
		}
	};

	public static Route makeTickets = (Request request, Response response) -> {

		SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
		if (AuthService.isAdmin(request.headers("Authorization"))) {
			System.out.println("check this __><___");
			System.out.println(request.queryParams("company").equals(""));
			if(request.queryParams("company").equals("") || request.queryParams("departDate").equals("") ||
					request.queryParams("flight").equals("") || request.queryParams("count").equals("")) {
				return "Fields cant be blank!";
			}
			Ticket ticket = new Ticket();
			Date departDate = parser.parse(request.queryParams("departDate"));
			if (!request.queryParams("returnDate").equals("")) {
				Date returnDate = parser.parse(request.queryParams("returnDate"));
				ticket.setReturnDate(returnDate);
				if (returnDate.before(departDate)) {
					return "Return date cant be before depart date!";
				}
			}

			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);

			ticket.setOneWay(Boolean.parseBoolean(request.queryParams("oneWay")));
			ticket.setDepartDate(departDate);

			ticket.setCount(Integer.parseInt(request.queryParams("count")));
			;
			// Ticket ticket =
			// UserRepository.makeTicket(Boolean.parseBoolean(request.queryParams("oneWay")),
			// departDate,
			// returnDate, Integer.parseInt(request.queryParams("count")));
			for (Company c : UserRepository.getCompanies()) {
				if (request.queryParams("company").equals(c.getCompanyName())) {
					ticket.setCompany(c);
				}
			}
			ticket.setFlightId(Integer.parseInt(request.queryParams("flight")));
			response.type("application/json");

			ticket.setTicketId();

			UserRepository.getTickets().add(ticket);
			UserRepository.addTicketToBase(ticket);
			return gson.toJson(ticket);
		} else {
			return "You are not admin, you cant make tickets";
		}

	};
	public static Route getUsers = (Request request, Response response) -> {
		response.type("application/json");

		if (AuthService.isAuthorized(request.headers("Authorization"))) {

			return gson.toJson(UserService.getUsers());
		}

		response.status(401);
		return "";
	};

	public static Route getTableInfo = (Request request, Response response) -> {
		response.type("application/json");
		bottomLimit = -4;
		topLimit = 0;
		System.out.println(bottomLimit);
		System.out.println(topLimit);
		UserRepository.setLAST_USED_TICKETS(UserService.getTableInfo());
		return gson.toJson(UserService.getTableInfo());

	};

	public static Route getCompanies = (Request request, Response response) -> {
		response.type("application/json");

		return gson.toJson(UserRepository.getCompanies());

	};

	public static Route getFlights = (Request request, Response response) -> {
		response.type("application/json");

		return gson.toJson(UserRepository.getFlights());

	};

}
