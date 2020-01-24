package com.example.demo.rest;

import com.example.demo.model.User;
import com.example.demo.repository.UsersRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UsersController {

    private UsersRepository repository;

    public UsersController(UsersRepository repository) {
        this.repository = repository;
        User user = new User();
        user.setName("Vano");
        repository.save(user);
    }

    @GetMapping("/users")
    public List<UserDto> allUsers() {
        return repository.findAll().stream()
                .map(user -> new UserDto() {{
                    setId("" + user.getId());
                    setName(user.getName());
                }})
                .collect(Collectors.toList());
    }


}
