package ticket;

import app.Ticket;
import app.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Repository se ovde bavi imitacijom komunikacije sa bazom
 */
public class TicketRepository {

    private static String[] NAME_LIST = {"Uganda", "Serbia", "Albania", "Bulgaria", "Turkey", "Montenegro"};
    
    private static List<Ticket> ticket_LIST;

    /**
     * Java identifikuje objekte po njihovom hashCode-u.
     * Izmenom vrednosti atributa objekta, i hashCode objekta se menja.
     * Zato je najbolje kao monitor koristiti final Object().
     */
    private static final Object LOCK = new Object();

    static {
    	ticket_LIST = generateTickets();
    }

    /**
     * Generise 10 korisnika birajuci nasumicno imena i prezimena iz liste
     *
     * @return
     */
    private static List<Ticket> generateTickets() {
        List<Ticket> tickets = new ArrayList<>();
        Random random = new Random();

        Ticket ticket = new Ticket();
        ticket.setTest("lolz");
    

        tickets.add(ticket);
        for (int i = 0; i < 10; i++) {

            ticket = new Ticket();
            
            ticket.setTest(NAME_LIST[random.nextInt(NAME_LIST.length)]);
           
            tickets.add(ticket);
        }
        return tickets;
    }

    public static List<Ticket> getTickets() {
        return ticket_LIST;
    }

   

}
