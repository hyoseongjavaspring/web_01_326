package kr.hs.dgsw.web_01_326.Controller;

import kr.hs.dgsw.web_01_326.Domain.User;
import kr.hs.dgsw.web_01_326.Repository.CommentRepository;
import kr.hs.dgsw.web_01_326.Repository.UserRepository;
import kr.hs.dgsw.web_01_326.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public List<User> list(){
        return this.userService.listAllUsers();
    }

    @GetMapping("/user/{email}")
    public User view(@PathVariable String email){
        return this.userService.view(email);
    }


    @PostMapping("/user")
    public User add(@RequestBody User user){
        return this.userService.add(user);
    }

    @PutMapping("/user")
    public User update(@RequestBody User user){
        return this.userService.update(user);
    }

    @DeleteMapping("/user/{id}")
    public boolean delete(@PathVariable Long id){
        return this.userService.delete(id);
    }
}
