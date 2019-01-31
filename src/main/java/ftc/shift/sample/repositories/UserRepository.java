package ftc.shift.sample.repositories;

import java.util.*;
import ftc.shift.sample.models.User;

import java.util.Collection;

/**
 * Интерфейс для получения данных по книгам
 */
public interface UserRepository {

    User fetchUser(String id);

    User updateUser(User user);

    //User updateAccount(User user);

    User createUser(User user);

    List<User> getAllUsers();

    void deleteUser(String id);
}
