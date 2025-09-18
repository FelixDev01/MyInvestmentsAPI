package investment.control.service;

import investment.control.dto.UserRequestDTO;
import investment.control.dto.UserResponseDTO;
import investment.control.model.User;
import investment.control.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    /// POST METHOD
    @Transactional
    public UserResponseDTO register (UserRequestDTO dto){
        if (repository.existsByEmail(dto.email())) {
            throw new RuntimeException("Email already registered");
        }
        User user = new User();
        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setPassword(passwordEncoder.encode(dto.password()));
        user.setRoles(List.of("ROLE_USER"));

        User saveUser = repository.save(user);

        return new UserResponseDTO(saveUser);
    }

    /// GET ALL METHOD
    public Page<UserResponseDTO> findAll(Pageable pageable){
        Page<User> users = repository.findAll(pageable);
        return users.map(UserResponseDTO::new);
    }

    /// GET BY EMAIL METHOD
    public UserResponseDTO findByEmail(String email){
        User user = repository.findByEmail(email)
                .orElseThrow(()-> new RuntimeException("Email not Found"));
        return new UserResponseDTO(user);
    }

    /// GET BY ID METHOD
    public UserResponseDTO findById(Long id){
        User user = repository.findById(id)
                .orElseThrow(()-> new RuntimeException("Id not Found"));
        return new UserResponseDTO(user);
    }

    /// UPDATE METHOD - AFTER

    /// DELETE METHOD
    @Transactional
    public void deleteUser(Long id){
        repository.deleteById(id);
    }
}
