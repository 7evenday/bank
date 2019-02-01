package ftc.shift.sample.repositories;

import ftc.shift.sample.models.User;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.UUID;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Реализиция, хранящая все данные в памяти приложения
 */
@Repository
public class InMemoryUserRepository implements UserRepository {

    private Map<String, User> userCache = new HashMap<>();


    public InMemoryUserRepository() {
        String tmp = UUID.randomUUID().toString();
        userCache.put(tmp, new User(tmp, "Попов Александр Иванович", 1500, 0));
        tmp = UUID.randomUUID().toString();
        userCache.put(tmp, new User(tmp, "Бабуров Никита Александрович", 100, 600));
        tmp = UUID.randomUUID().toString();
        userCache.put(tmp, new User(tmp, "Гончаров Сергей Анатольевич", 2700, 357));
        tmp = UUID.randomUUID().toString();
        userCache.put(tmp, new User(tmp, "Варнакин Илья Сергеевич", 687, 5));
        /*tmp = UUID.randomUUID().toString();
        userCache.put(tmp, new User(tmp, "Гончаров Сергей Анатольевич", 1999, 0));
        tmp = UUID.randomUUID().toString();
        userCache.put(tmp, new User(tmp, "Береснев Дмитрий Евгеньевич", 1488, 0));
        tmp = UUID.randomUUID().toString();
        userCache.put(tmp, new User(tmp, "Висков Дмитрий Олегович", 1345, 0));
        tmp = UUID.randomUUID().toString();
        userCache.put(tmp, new User(tmp, "Зубарев Даниил Евгеньевич", 564, 0));
        tmp = UUID.randomUUID().toString();
        userCache.put(tmp, new User(tmp, "Осин Виктор Викторович", 2345, 0));
        tmp = UUID.randomUUID().toString();
        userCache.put(tmp, new User(tmp, "Павлова Мария Александровна", 256, 0));
        tmp = UUID.randomUUID().toString();
        userCache.put(tmp, new User(tmp, "Осипова Дарья Батьковна", 56, 0));
        tmp = UUID.randomUUID().toString();
        userCache.put(tmp, new User(tmp, "Loqiemean мой альбом никому не понравится", 666, 666));
        tmp = UUID.randomUUID().toString();
        userCache.put(tmp, new User(tmp, "Оксюморон Анна Витальевна", 777, 0));
        tmp = UUID.randomUUID().toString();
        userCache.put(tmp, new User(tmp, "Эпова Александа Анатольева", 1200, 0));
        tmp = UUID.randomUUID().toString();
        userCache.put(tmp, new User(tmp, "Хаски из клекти", 220, 0));*/
    }

    @Override
    public User fetchUser(String id) {
        return userCache.get(id);
    }

    @Override
    public User updateUser(User user) {
        userCache.put(user.getId(), user);
        return user;
    }
    
    @Override
    public User createUser(User user) {
        if (!user.getName().isEmpty()) {
            user.setId(String.valueOf(UUID.randomUUID()));
            if (user.getBalance() == null)
                user.setBalance(0);
            user.setDebt(0);
            userCache.put(user.getId(), user);
            return user;
        }
        else {
            return null;
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>(userCache.values());
        return users;
    }

    @Override
    public void deleteUser(String id) {
        userCache.remove(id);
    }
}
