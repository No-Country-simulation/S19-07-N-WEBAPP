package NoCountry.Fineazily.service;

import NoCountry.Fineazily.exception.roleExceptions.RoleNotFoundException;
import NoCountry.Fineazily.model.dto.request.RoleReq;
import NoCountry.Fineazily.model.dto.response.RoleRes;
import NoCountry.Fineazily.model.entity.Role;
import NoCountry.Fineazily.model.mapper.RoleMapper;
import NoCountry.Fineazily.repostory.RoleRepository;
import NoCountry.Fineazily.service.interfaces.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleMapper roleMapper;
    private final RoleRepository roleRepository;

    private Role findRoleByIdOrThrow(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new RoleNotFoundException("Role not found with id: " + id));
    }

    @Override
    public void create(RoleReq roleReq) {
        Role role = roleMapper.toEntity(roleReq);
        roleRepository.save(role);
    }

    @Override
    public RoleRes findById(Long id) {
        Role role =findRoleByIdOrThrow(id);
        return roleMapper.toDto(role);
    }

    @Override
    public List<RoleRes> findAll() {
        return roleRepository.findAll().stream()
                .map(roleMapper::toDto)
                .toList();
    }

    @Override
    public void update(Long id, RoleReq roleReq){
        Role role =findRoleByIdOrThrow(id);
        roleMapper.updateRole(roleReq, role);
        roleRepository.save(role);
    }

    @Override
    public void delete(Long id) {
        Role role = findRoleByIdOrThrow(id);
        roleRepository.deleteById(role.getId());
    }
}
