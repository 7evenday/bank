package ftc.shift.sample.api;


import ftc.shift.sample.models.User;
import ftc.shift.sample.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class UsersController {

    private static final String USER_PATH = "/api/users";

    @Autowired
    private UserService service;

    @GetMapping(USER_PATH + "/{id}")
    public ResponseEntity<User> readUser(@PathVariable String id) {
        User user = service.provideUser(id);

        if (null == user) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(user);
        }
    }

    @GetMapping(USER_PATH)
    public ResponseEntity<Collection<User>> listUsers() {
        Collection<User> users = service.provideUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping(USER_PATH)
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User result = service.createUser(user);
        return ResponseEntity.ok(result);
    }

    @PatchMapping(USER_PATH + "/{id}")
    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody User user) {
        User result = service.updateUser(user);
        return ResponseEntity.ok(result);
    }
}