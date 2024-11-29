package NoCountry.Fineazily.controller;

import NoCountry.Fineazily.util.ApiResponse;
import NoCountry.Fineazily.model.dto.UserDto;
import NoCountry.Fineazily.model.entity.User;
import NoCountry.Fineazily.model.mapper.UserMapper;
import NoCountry.Fineazily.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name="User")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService service;
    private final UserMapper userMapper;

    @Operation(summary = "Create a new user", description = "Creates a new user using the provided UserDto")
    @PostMapping
    public ResponseEntity<ApiResponse<UserDto>> create(@RequestBody @Valid UserDto userDto) {
        service.create(userMapper.toEntity(userDto));
        ApiResponse<UserDto> response = new ApiResponse<>(201, "Usuario creado con éxito", userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Get all users", description = "Fetches a list of all users")
    @GetMapping
    public ResponseEntity<ApiResponse<List<UserDto>>> findAll() {
        List<UserDto> users = userMapper.toDtoList(service.findAll());
        ApiResponse<List<UserDto>> response = new ApiResponse<>(200, "Usuarios encontrados", users);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get user by ID", description = "Fetches a user based on the provided ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDto>> findById(@PathVariable Long id) {
        User user = service.findById(id);
        ApiResponse<UserDto> response = new ApiResponse<>(200, "Usuario encontrado", userMapper.toDto(user));
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Update user by ID", description = "Updates the information of a user using the provided ID and UserDto")
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


    @Operation(summary = "Delete user by ID", description = "Deletes a user based on the provided ID")
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
