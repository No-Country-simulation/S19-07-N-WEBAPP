package NoCountry.Fineazily.controller;

import NoCountry.Fineazily.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @PostMapping()
    public ResponseEntity<?> createUser(@RequestBody UserDto dto){

    }
}
