package investment.control.userTest;

import investment.control.dto.userDTO.UserRequestDTO;
import investment.control.dto.userDTO.UserResponseDTO;
import investment.control.model.User;
import investment.control.repository.UserRepository;
import investment.control.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private UserRequestDTO dto;

    @BeforeEach
    public void setUp() {
        dto = new UserRequestDTO(
                "felix",
                "felix@test.com",
                "test123"
        );
    }

    @Test
    public void mustRegisterUser() {

        when(passwordEncoder.encode("test123")).thenReturn("EncryptedPassword");

        User saveUser = new User();
        saveUser.setId(1L);
        saveUser.setName(dto.name());
        saveUser.setEmail(dto.email());
        saveUser.setPassword("EncryptedPassword");

        when(userRepository.save(any(User.class))).thenReturn(saveUser);

        UserResponseDTO responseDTO = userService.register(dto);

        assertNotNull(responseDTO);
        assertEquals(1L, saveUser.getId());
        assertEquals("felix", saveUser.getName());
        assertEquals("felix@test.com", saveUser.getEmail());

        verify(userRepository).save(any(User.class));
    }

    @Test
    public void ListOfUsers() {
        User saveUser1 = new User();
        saveUser1.setId(1L);
        saveUser1.setName("felix1");
        saveUser1.setEmail("felix@test1.com");

        User saveUser2 = new User();
        saveUser2.setId(2L);
        saveUser2.setName("felix2");
        saveUser2.setEmail("felix@test2.com");

        List<User> users = List.of(saveUser1, saveUser2);
        Pageable pageable = Pageable.unpaged();
        Page<User> page = new org.springframework.data.domain.PageImpl<>(users, pageable, users.size());

            when(userRepository.findAll(any(Pageable.class))).thenReturn(page);

        Page<UserResponseDTO> result = userService.findAll(pageable);

        assertNotNull(result);
        assertEquals(2, result.getTotalElements());
        assertEquals(1L, result.getContent().getFirst().id());
        assertEquals("felix1", result.getContent().get(0).name());
        assertEquals("felix@test1.com", result.getContent().get(0).email());
        assertEquals(2L, result.getContent().get(1).id());
        assertEquals("felix2", result.getContent().get(1).name());
        assertEquals("felix@test2.com", result.getContent().get(1).email());

        verify(userRepository).findAll(pageable);
    }
}