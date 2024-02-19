package me.dio.controller;

import me.dio.domain.model.User;
import me.dio.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<User> findById(@PathVariable Long id) {
    User userId = userService.findById(id);

    return ResponseEntity.ok(userId);
  }

  @PostMapping
  public ResponseEntity<User> createUser(@RequestBody User user) {
    User userCreated = userService.create(user);
    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}").buildAndExpand(userCreated.getId()).toUri();
    return ResponseEntity.created(location).body(userCreated);
  }
}
