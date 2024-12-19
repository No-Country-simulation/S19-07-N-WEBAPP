package NoCountry.Fineazily.service;

import NoCountry.Fineazily.exception.CashRegisterSessionNotFoundException;
import NoCountry.Fineazily.model.dto.request.CashRegisterSessionRequest;
import NoCountry.Fineazily.model.entity.CashRegisterSession;
import NoCountry.Fineazily.model.mapper.CashRegisterSessionMapper;

import NoCountry.Fineazily.repostory.CashRegisterSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CashRegisterSessionService {
    private final CashRegisterSessionRepository repository;
    private final CashRegisterSessionMapper mapper;
    private final String sessionNotFound = "there isn't a session with that id:";

    public void createSession(CashRegisterSessionRequest dto) {
        repository.save(mapper.toEntity(dto));
    }

    public CashRegisterSession findById(Long sessionId) {
        return repository.findById(sessionId)
                .orElseThrow(() -> new CashRegisterSessionNotFoundException(sessionNotFound));
    }

    public void findAllActiveSessions() {

    }

    public void updateSession(CashRegisterSessionRequest dto, Long id) {
        CashRegisterSession session = findById(id);
        mapper.updateExistingEntity(dto, session);
        repository.save(session);
    }
}
