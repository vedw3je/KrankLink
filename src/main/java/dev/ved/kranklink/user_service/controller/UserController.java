package dev.ved.kranklink.user_service.controller;

import dev.ved.kranklink.user_service.dto.UserResponse;
import dev.ved.kranklink.user_service.requests.LoginRequest;
import dev.ved.kranklink.user_service.entity.User;
import dev.ved.kranklink.user_service.requests.RegisterRequest;
import dev.ved.kranklink.user_service.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/api/user")
public class UserController {


    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        try {
            boolean success = userService.loginUser(loginRequest);

            if (success) {
                Optional<User> optionalUser = userService.getUserByUsername(loginRequest.getUsername());

                if (optionalUser.isPresent()) {
                    final User savedUser = optionalUser.get();
                    UserResponse userResponse = new UserResponse(
                            savedUser.getId(),
                            savedUser.getUsername(),
                            savedUser.getEmail(),
                            savedUser.getPhoneNumber(),
                            savedUser.getRole()

                    );
                    return ResponseEntity.ok(userResponse);
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .body("User not found");
                }
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Invalid username or password");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error occurred: " + e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest request) {
        try {
            User savedUser = userService.registerUser(request);

            UserResponse response = new UserResponse(
                    savedUser.getId(),
                    savedUser.getUsername(),
                    savedUser.getEmail(),
                    savedUser.getPhoneNumber(),
                    savedUser.getRole()
            );

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }




}
