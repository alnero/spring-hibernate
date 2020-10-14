package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));
      userService.add(new User("User5", "Lastname5", "user5@mail.ru", new Car("model5")));
      userService.add(new User("User6", "Lastname6", "user6@mail.ru", new Car("SAME_model")));
      userService.add(new User("User7", "Lastname7", "user7@mail.ru", new Car("model7")));
      userService.add(new User("User8", "Lastname8", "user8@mail.ru", new Car("SAME_model")));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println(user);
      }

      User userWithCar1 = userService.getByCar(1, "model5");
      User userWithCar2 = userService.getByCar(2, "SAME_model");
      User userWithCar3 = userService.getByCar(3, "model7");
      User userWithCar4 = userService.getByCar(4, "SAME_model");
      List<User> usersWithCars = Arrays.asList(userWithCar1, userWithCar2, userWithCar3, userWithCar4);
      for (User user : usersWithCars) {
         System.out.println(user);
      }

      context.close();
   }
}
