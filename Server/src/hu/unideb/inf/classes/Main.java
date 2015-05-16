package hu.unideb.inf.classes;

import com.darkfalcon.java.services.UserService;
import com.darkfalcon.java.services.UserServiceImpl;
import com.darkfalcon.java.vo.user.UserVO;


public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        UserVO user = userService.getUserByName("darkfalcon");
        System.out.println(user.getUsername() + " - " + user.getPassword());
    }
}
