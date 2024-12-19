package NoCountry.Fineazily.service;

import NoCountry.Fineazily.exception.cashRegisterSessionExceptions.CashRegisterSessionNotFoundException;
import NoCountry.Fineazily.exception.cashRegisterSessionExceptions.LastSessionNotEndedException;
import NoCountry.Fineazily.model.dto.request.CashRegisterSessionRequest;
import NoCountry.Fineazily.model.dto.response.CashRegisterSessionResponse;
import NoCountry.Fineazily.model.entity.CashRegister;
import NoCountry.Fineazily.model.entity.CashRegisterSession;
import NoCountry.Fineazily.model.entity.Employee;
import NoCountry.Fineazily.model.mapper.CashRegisterSessionMapper;

import NoCountry.Fineazily.repostory.CashRegisterSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CashRegisterSessionService {
    private final CashRegisterSessionRepository repository;
    private final CashRegisterSessionMapper mapper;
    private final EmployeeService employeeService;
    private final CashRegisterService cashRegisterService;

    public void createSession(CashRegisterSessionRequest dto) {
        CashRegisterSession crs = mapper.toEntity(dto);
        if (crs.getStarDateTime() == null) {
            crs.setStarDateTime(LocalDateTime.now());
        }

        Employee emp = employeeService.findById(dto.employeeId());
        CashRegister cr = cashRegisterService.findById(dto.cashRegisterId());
        validateSessionCreation(emp, cr);

        crs.setEmployee(emp);
        crs.setCashRegister(cr);
        repository.save(mapper.toEntity(dto));
    }

    public CashRegisterSession findById(Long sessionId) {
        return repository.findById(sessionId)
                .orElseThrow(() -> new CashRegisterSessionNotFoundException("there isn't a session with that id:" + sessionId));
    }

    public CashRegisterSessionResponse findSessionById(Long sessionId) {
        return mapper.toDto(findById(sessionId));
    }

    public List<CashRegisterSessionResponse> findAllActiveSessions() {
        return repository.findAllByEndDateTimeIsNull()
                .stream().map(mapper::toDto).toList();
    }

    public List<CashRegisterSessionResponse> findAllSessions() {
        return repository.findAll()
                .stream().map(mapper::toDto).toList();
    }

    public void updateSession(CashRegisterSessionRequest dto, Long id) {
        CashRegisterSession session = findById(id);
        mapper.updateExistingEntity(dto, session);
        repository.save(session);
    }

    public void deleteSession(Long id) {
        CashRegisterSession crs = findById(id);
        repository.delete(crs);
    }

    //-------------------other methods ---------------------------
    public void endSession(Long sessionId) {
        CashRegisterSession crs = findById(sessionId);
        crs.setEndDateTime(LocalDateTime.now());
        repository.save(crs);
    }

    private void validateSessionCreation(Employee emp, CashRegister cashRegister) {
        if (hasActiveSession(emp.getSessions())) {
            throw new LastSessionNotEndedException("The employee already with the id:" + emp.getId() + "has a session without finish");
        }
        if (hasActiveSession(cashRegister.getSessions())) {
            throw new LastSessionNotEndedException("The cash register with the id:" + cashRegister.getId() + "already has a session without finish");
        }
    }

    private boolean hasActiveSession(List<CashRegisterSession> sessions) {
        if (sessions == null || sessions.isEmpty()) {
            return false;
        }
        return sessions.get(sessions.size() - 1).getEndDateTime() == null;
    }
}
