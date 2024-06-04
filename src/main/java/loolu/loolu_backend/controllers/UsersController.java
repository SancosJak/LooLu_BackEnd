package loolu.loolu_backend.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import loolu.loolu_backend.dto.NewUserDto;
import loolu.loolu_backend.dto.UserDto;
import loolu.loolu_backend.services.validation.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UsersController {

    private final UsersService usersService;

    @RequestMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody @Valid NewUserDto newUser){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(usersService.register(newUser));
    }
}
