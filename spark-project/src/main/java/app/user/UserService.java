package app.user;

import java.util.List;

import app.Ticket;
import app.User;

/**
 * Servisni sloj se bavi svom "biznis logikom"
 */
public class UserService {

    public static List<User> getUsers(){
        return UserRepository.getUsers();
    }
    
    public static List<Ticket> getTableInfo(){
    	return UserRepository.getTickets();
    }

    public static User getUserById(Integer id){
        return UserRepository.getUserById(id);
    }

 /*   public static User addUser(User user){
        return UserRepository.addUser(user);
    } */

    public static User findUser(String username, String password){
        return UserRepository.findUser(username, password);
    }

}
