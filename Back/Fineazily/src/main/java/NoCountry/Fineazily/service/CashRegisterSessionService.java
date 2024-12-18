package NoCountry.Fineazily.service;

import NoCountry.Fineazily.model.entity.CashRegisterSession;
import NoCountry.Fineazily.repostory.CashRegisterSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CashRegisterSessionService {
    private final CashRegisterSessionRepository repository;
    private final String notFound= "there isn't a session with that id:";
    private final CashRegisterSessionMapper

    public void createSession(SessionRequest){

    }

    public CashRegisterSession findById(Long id){
        return null;
    }
}
