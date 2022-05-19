package app.user;

import java.io.File;
import java.io.IOException;

import app.Company;
import app.Flight;
import app.Reservation;
import app.Ticket;
import app.User;

import static spark.Spark.*;

public class Main {

    public static void main(String[] args) throws IOException {

    	startEverything();
        // 4567 je podrazumevani port
        port(8080);
        staticFiles.externalLocation(new File("./static").getCanonicalPath());
        enableCORS();

        // Rute se sastoje od http metode, url-a i callback-a
        get("/rest/users/login",          UserController.login);
        get("/rest/users",                UserController.getUsers);
        get("/rest/users/table", 		  UserController.getTableInfo);
        get("/rest/users/admin", 		  UserController.makeUsers);
        get("/rest/users/adminTickt", 		  UserController.makeTickets);
        get("/rest/users/adminCompanies", 		  UserController.getCompanies);
        get("/rest/users/adminFlights", 		  UserController.getFlights);
        get("/rest/users/srchTkt", 		  UserController.searchTickets);
        get("/rest/users/cmpnyTickets", 		  UserController.getCompanyTickets);
        get("/rest/users/userres", 		  UserController.getUsersReservations);
        get("/rest/users/pagination",          UserController.pagination);
        get("/rest/users/tktDelete",          UserController.ticketDelete);
        get("/rest/users/tktReserve",          UserController.reserveTicket);
        get("/rest/users/tktChange",          UserController.ticketChange);
        get("/rest/users/rnmCompany",          UserController.renameCompany);
        get("/rest/users/cr8cmp",          UserController.createCompany);
        get("/rest/users/dltComp",          UserController.deleteCompany);
        get("/rest/users/rsvDelete",          UserController.reservationDelete);

        
        
    }
     static void startEverything() {
    	
    	for(User r : UserRepository.getUsers()) {
            UserRepository.refreshUsersInBase(r);
    	}
    	
    }
    private static void enableCORS() {

        options("/*", (request, response) -> {

            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }

            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }

            return "OK";
        });

        before((request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Request-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
            response.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
            // Note: this may or may not be necessary in your particular application
            response.type("application/json");
        });
    }
}
