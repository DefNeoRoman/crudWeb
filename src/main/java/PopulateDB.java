import app.AppManager;
import model.User;
import service.UserService;

import java.util.Random;
import java.util.UUID;

public class PopulateDB {

    public static void main(String[] args) {
        Random random = new Random();
        UserService service = AppManager.getService();
        User admin = new User("admin", "admin@mail.ru", 90);
        admin.setRole("ADMIN");
        admin.setPassword("admin");
        service.addUser(admin);
        for (int i = 0; i < 100; i++) {
            User user = new User(UUID.randomUUID().toString(),
                    UUID.randomUUID() + "@MAIL.COM",
                    i + random.nextInt()
            );

            service.addUser(
                    user);
        }
        System.exit(0);
    }
}
