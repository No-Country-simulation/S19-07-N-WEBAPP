package NoCountry.Fineazily.service;

import NoCountry.Fineazily.exception.userExceptions.UserNotFoundException;
import NoCountry.Fineazily.model.entity.User;
import NoCountry.Fineazily.repostory.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService extends AService<User, Long> {
    private final UserRepository userRepository;
    private final String notFound = "There isn't an user with that id: ";

    @Override
    public void create(User entity) {
        entity.setCreationDate(LocalDate.now());
        //needs to define rol setting
        userRepository.save(entity);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(notFound + id));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void update(User entity) {
        if (userRepository.existsById(entity.getId())) {
            userRepository.save(entity);
        } else {
            throw new UserNotFoundException(notFound + entity.getId());
        }
    }

  
    @Override
public void delete(Long id) {
    userRepository.deleteById(id);
}

}
