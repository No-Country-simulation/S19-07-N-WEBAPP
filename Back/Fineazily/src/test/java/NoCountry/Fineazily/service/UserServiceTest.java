package NoCountry.Fineazily.service;

import NoCountry.Fineazily.model.dto.UserDto;
import NoCountry.Fineazily.model.entity.User;
import NoCountry.Fineazily.repostory.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class UserServiceTest {

    private User user;

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void create() {
        //Given
        UserDto dto = new UserDto(
                "John",
                "john@correo.com",
                "password"
        );

        User user = new User(dto);

        //When
        userService.create(user);

        //Then
        Mockito.verify(userRepository, Mockito.times(1)).save(user);
    }

    @Test
    void findById() {
    }

    @Test
    void testCamposValidos(){

    }

    @Test
    void testCamposFaltantes(){
        UserDto dto = new UserDto(
                "",
                "",
                ""
        );

        User user = new User(dto);

        //When
        userService.create(user);

        //Then
        Mockito.verify(userRepository, Mockito.times(1)).save(user);

    }
}