package NoCountry.Fineazily.controller;

import NoCountry.Fineazily.util.ApiResponse;
import NoCountry.Fineazily.model.dto.UserDto;
import NoCountry.Fineazily.model.entity.User;
import NoCountry.Fineazily.model.mapper.UserMapper;
import NoCountry.Fineazily.service.UserService;
import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService service;
    private final UserMapper userMapper;

    @PostMapping
    public ResponseEntity<ApiResponse<UserDto>> create(@RequestBody @Valid UserDto userDto) {
        service.create(userMapper.toEntity(userDto));
        ApiResponse<UserDto> response = new ApiResponse<>(201, "Usuario creado con éxito", userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserDto>>> findAll() {
        List<UserDto> users = userMapper.toDtoList(service.findAll());
        ApiResponse<List<UserDto>> response = new ApiResponse<>(200, "Usuarios encontrados", users);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDto>> findById(@PathVariable Long id) {
        User user = service.findById(id);
        ApiResponse<UserDto> response = new ApiResponse<>(200, "Usuario encontrado", userMapper.toDto(user));
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDto>> update(@PathVariable Long id, @RequestBody @Valid UserDto userDto) {
        User existingUser = service.findById(id);
        existingUser = userMapper.toEntity(userDto);
        existingUser.setId(id);
        service.update(existingUser);
        ApiResponse<UserDto> response = new ApiResponse<>(200, "Usuario actualizado con éxito",
                
                userMapper.toDto(existingUser));
        return ResponseEntity.ok(response);
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        User user = service.findById(id); 
        if (user != null) { 
            service.delete(id);  // Aquí pasas el ID en lugar del objeto User
            ApiResponse<Void> response = new ApiResponse<>(200, "Usuario eliminado con éxito", null);
            return ResponseEntity.ok(response);
        } else {
            ApiResponse<Void> response = new ApiResponse<>(404, "Usuario no encontrado", null);
            return ResponseEntity.status(404).body(response);
        }
    }

    
    

}
