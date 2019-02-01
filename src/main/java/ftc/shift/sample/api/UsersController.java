package ftc.shift.sample.api;


import ftc.shift.sample.models.User;
import ftc.shift.sample.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
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

    @DeleteMapping(USER_PATH + "/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {
        if (service.provideUser(id) == null){
            return ResponseEntity.notFound().build();
        }
        else{
            service.deleteUser(id);
            return ResponseEntity.ok().build();
        }
    }

    @PostMapping(USER_PATH)
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User result = service.createUser(user);
        if (!user.getName().isEmpty()) {
            return ResponseEntity.ok(result);
        }
        else{
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping(USER_PATH + "/{id}")
    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody User user) {
        User userServer = service.provideUser(id);
        User result = null;
        if ((userServer != null) & (id.equals(user.getId())) & (userServer.getId().equals(id)) & (user.getBalance() >= 0) & (user.getDebt() >= 0) ) {
            result = service.updateUser(user);
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping(USER_PATH + "/{id}/addcash")
    public ResponseEntity<User> addCash(@PathVariable String id, @RequestParam (required = true) Integer sum){
        User user = service.provideUser(id);
        System.out.println("ADDCASH WAS CALLED");
        if (user == null){
            return ResponseEntity.badRequest().build();
        }
        else {
            user.setBalance(user.getBalance() + sum);
            return updateUser(id, user);
        }
    }

    @PatchMapping(USER_PATH + "/{id}/lowerdebt")
    public ResponseEntity<User> lowerDebt(@PathVariable String id, @RequestParam (required = true) Integer sum){
        User user = service.provideUser(id);
        System.out.println("LOWERDEBT WAS CALLED");
        if (user == null){
            return ResponseEntity.badRequest().build();
        }
        else {
            if (user.getBalance() >= sum) {
                if (sum > user.getDebt()){
                    user.setBalance(user.getBalance() - user.getDebt());
                    user.setDebt(0);
                }
                else{
                    user.setBalance(user.getBalance() - sum);
                    user.setDebt(user.getDebt() - sum);
                }
                return updateUser(id, user);
            }
            else{
                return ResponseEntity.badRequest().build();
            }
        }
    }

    @PostMapping(USER_PATH + "/{id}")
    public ResponseEntity<User> updateUserPost(@PathVariable String id, @RequestBody User user) {
        return updateUser(id, user);
    }

}