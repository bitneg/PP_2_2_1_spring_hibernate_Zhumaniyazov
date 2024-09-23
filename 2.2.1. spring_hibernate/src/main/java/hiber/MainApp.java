package hiber;

import hiber.config.AppConfig;
import hiber.model.User;
import hiber.model.Car;

import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.logging.Logger;


public class MainApp {
    public static void main(String[] args) {
        //Logger logger = Logger.getLogger(getClass().getName());
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = context.getBean(UserService.class);
        User user1 = new User("Max", "Two", "Maxtwo@gmail.com");
        Car car1 = new Car("Porsche", 2000);
        user1.setCar(car1);
        car1.setUser(user1);
        User user2 = new User("Bro", "One", "oneBro@gmail.com");
        Car car2 = new Car("Toyota", 2012);
        user2.setCar(car2);
        car2.setUser(user2);
        userService.add(user1);
        userService.add(user2);
        System.out.println("Пользователи:");
        for (User user : userService.listUsers()) {
            System.out.println(user.getFirstName() + " " + user.getLastName() + " - Car: " + user.getCar().getModel());
        }
        User foundUser = userService.findByCarModelAndSeries("Porsche", 2000);
        System.out.println("Found user: " + foundUser.getFirstName() + " " + foundUser.getLastName());

        context.close();
    }
}
