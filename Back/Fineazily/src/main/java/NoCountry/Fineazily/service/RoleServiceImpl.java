package NoCountry.Fineazily.service;

import NoCountry.Fineazily.exception.roleExceptions.RoleNotFoundException;
import NoCountry.Fineazily.model.dto.request.RoleReq;
import NoCountry.Fineazily.model.dto.response.RoleRes;
import NoCountry.Fineazily.model.entity.Role;
import NoCountry.Fineazily.model.mapper.RoleMapper;
import NoCountry.Fineazily.repostory.RoleRepository;
import NoCountry.Fineazily.service.interfaces.RoleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleMapper roleMapper;
    private final RoleRepository roleRepository;

    @Transactional
    @Override
    public void create(RoleReq roleReq) {
        Role role = roleMapper.toEntity(roleReq);
        roleRepository.save(role);
    }

    @Override
    public RoleRes findById(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RoleNotFoundException("Role not found with id: " + id));
        return roleMapper.toRoleRes(role);
    }

    @Override
    public List<RoleRes> findAll() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream().map(roleMapper::toRoleRes)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void update(Long id, RoleReq roleReq){
        Role role = roleRepository.findById(id)
               .orElseThrow(() -> new RoleNotFoundException("Role not found with id: " + id));
        roleMapper.updateRole(roleReq, role);
        roleRepository.save(role);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        roleRepository.findById(id)
                .orElseThrow(() -> new RoleNotFoundException("Role not found with id: " + id));
        roleRepository.deleteById(id);
    }
}
