
import dao.UserDaoJDBCImpl;
import model.User;

import java.util.Random;
import java.util.UUID;

public class PopulateDB {

    public static void main(String[] args) {
        Random random = new Random();
        UserDaoJDBCImpl dao = new UserDaoJDBCImpl();
        for (int i = 0; i < 1000; i++) {
            User user = new User(
                    UUID.randomUUID().toString(),
                    UUID.randomUUID() + "@MAIL.COM",
                    i + random.nextInt()
            );

            dao.addUser(
                    user);
        }

    }
}
