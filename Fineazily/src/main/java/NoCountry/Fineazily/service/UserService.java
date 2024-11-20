package NoCountry.Fineazily.service;

import NoCountry.Fineazily.exception.userExceptions.UserNotFoundException;
import NoCountry.Fineazily.model.entity.User;
import NoCountry.Fineazily.repostory.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService extends AService<User,Long>{
    private final UserRepository userRepository;
    private final String notFoundMessage = "There isn't an user with that id:";

    @Override
    public void create(User entity) {
        userRepository.save(entity);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(()-> new UserNotFoundException(notFoundMessage + id));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void update(User entity) {
        userRepository.save(findById(entity.getId()));
    }

    @Override
    public void delete(User entity) {
        userRepository.delete(entity);
    }
}
