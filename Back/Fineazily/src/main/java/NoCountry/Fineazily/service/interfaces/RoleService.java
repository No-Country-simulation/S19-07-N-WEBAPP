package NoCountry.Fineazily.service.interfaces;

import NoCountry.Fineazily.model.dto.request.RoleReq;
import NoCountry.Fineazily.model.dto.response.RoleRes;

import java.util.List;

public interface RoleService {

    void create(RoleReq roleReq);

    RoleRes findById(Long id);

    List<RoleRes>findAll();

    void update(Long id, RoleReq roleReq);

    void delete(Long id);

}
