package NoCountry.Fineazily.controller;

import NoCountry.Fineazily.model.dto.ApiResponse;
import NoCountry.Fineazily.model.dto.request.RoleReq;
import NoCountry.Fineazily.model.dto.response.RoleRes;
import NoCountry.Fineazily.service.interfaces.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="Roles")
@RestController
@RequestMapping("${api.base}/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @Operation(summary = "Create a new role", description = "Allows creating a new role with the specified details.")
    @PostMapping()
    public ResponseEntity<?> createRole(@RequestBody @Valid RoleReq roleReq) {
        roleService.create(roleReq);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(true,"Role created successfully", null));
    }

    @Operation(summary = "Get role by ID", description = "Retrieves a role by its unique identifier.")
    @GetMapping("/{id}")
    public ResponseEntity<?> getRoleById(@PathVariable Long id) {
        RoleRes roleRes = roleService.findById(id);
        return ResponseEntity.ok(new ApiResponse<>(true,roleRes));
    }

    @Operation(summary = "Get all roles", description = "Fetches all available roles in the system.")
    @GetMapping()
    public ResponseEntity<?> getAllRoles() {
        List<RoleRes> response = roleService.findAll();
        return ResponseEntity.ok().body(new ApiResponse<>(response));
    }

    @Operation(summary = "Update a role", description = "Updates an existing role with the provided details.")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateRole(@PathVariable Long id, @RequestBody @Valid RoleReq roleReq) {
        roleService.update(id, roleReq);
        return ResponseEntity.ok().body(new ApiResponse<>(true,"Role updated successfully", null));
    }

    @Operation(summary = "Delete a role", description = "Deletes a role by its unique identifier.")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable Long id) {
        roleService.delete(id);
        return ResponseEntity.ok().body(new ApiResponse<>(true,"Role deleted successfully", null));
    }
}
