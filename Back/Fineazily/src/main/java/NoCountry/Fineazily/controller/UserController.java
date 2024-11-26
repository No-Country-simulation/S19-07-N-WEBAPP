package NoCountry.Fineazily.controller;

import NoCountry.Fineazily.model.dto.UserDto;
import NoCountry.Fineazily.model.entity.User;
import NoCountry.Fineazily.model.mapper.UserMapper;
import NoCountry.Fineazily.service.UserService;
import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService service;
    private final UserMapper userMapper;

    @PostMapping("/")
    public ResponseEntity<?> createUser(@RequestBody @Valid UserDto dto) {
        service.create(userMapper.toEntity(dto));
        return ResponseEntity.ok("User created successfully");
    }
}
