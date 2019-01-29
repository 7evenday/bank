package ftc.shift.sample.repositories;

import ftc.shift.sample.models.User;
import org.springframework.stereotype.Repository;

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
        userCache.put(String.valueOf(1), new User("1", "Попов Александр Иванович", 1500, 0));
        userCache.put(String.valueOf(2), new User("2", "Бабуров Никита Александрович", 100, 0));
        userCache.put(String.valueOf(3), new User("3", "Гончаров Сергей Анатольевич", 2700, 0));
        userCache.put(String.valueOf(4), new User("4", "Варнакин Илья Сергеевич", 687, 0));
        userCache.put(String.valueOf(5), new User("5", "Гончаров Сергей Анатольевич", 1999, 0));
        userCache.put(String.valueOf(6), new User("6", "Береснев Дмитрий Евгеньевич", 1488, 0));
        userCache.put(String.valueOf(7), new User("7", "Висков Дмитрий Олегович", 1345, 0));
        userCache.put(String.valueOf(8), new User("8", "Зубарев Даниил Евгеньевич", 564, 0));
        userCache.put(String.valueOf(9), new User("9", "Осин Виктор Викторович", 2345, 0));
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
        int counter = getAllUsers().size() + 1;
        user.setId(String.valueOf(counter + 1));
        userCache.put(user.getId(), user);
        return user;
    }

    @Override
    public Collection<User> getAllUsers() {
        return userCache.values();
    }
}
