import dao.UserDao;
import entity.User;

import java.util.Random;
import java.util.UUID;

public class PopulateDB {
    public static void main(String[] args) {
        Random random = new Random();
        try(UserDao dao = new UserDao()){
            for(int i=0; i<1000000; i++){
                dao.addUser(new User(UUID.randomUUID().toString(),UUID.randomUUID()+"@MAIL.COM",i+random.nextInt()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
