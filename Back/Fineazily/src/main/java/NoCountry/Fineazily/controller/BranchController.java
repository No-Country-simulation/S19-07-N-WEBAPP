package NoCountry.Fineazily.controller;

import NoCountry.Fineazily.model.dto.BranchDTO;
import NoCountry.Fineazily.model.entity.Branch;
import NoCountry.Fineazily.model.mapper.BranchMapper;
import NoCountry.Fineazily.service.BranchService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/apí/branch")
public class BranchController {
    private final BranchService branchService;
    private final BranchMapper branchMapper;


    @PostMapping("/")
    public ResponseEntity<?> registerBranch(@Valid @RequestBody BranchDTO dto) {
        branchService.create(branchMapper.toEntity(dto));
        return ResponseEntity.ok("Branch created successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBranchById(@PathVariable @NotNull(message = "the branch id cannot be null") Long id) {
        return ResponseEntity.ok(branchService.findById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllBranches(){
        List<Branch> branches= branchService.findAll();
        if(branches != null && !branches.isEmpty()){
            return ResponseEntity.ok(branches);
        }
       return new ResponseEntity<>("no branches to show",HttpStatus.NO_CONTENT);
    }

    @GetMapping("/")
    public ResponseEntity<?> getBranchesPaginated() {
        //needs to be implemented
        return ResponseEntity.ok("");
    }

    @PatchMapping("/")
    public ResponseEntity<?> updateBranch(@Valid @RequestBody BranchDTO dto){
        branchService.update(dto);
        return ResponseEntity.ok("Branch updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBranch(@PathVariable @NotNull(message = "the branch id cannot be null") Long id){
        branchService.delete(id);
        return ResponseEntity.ok("branch deleted");
    }

}
