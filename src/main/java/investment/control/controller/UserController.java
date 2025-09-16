package investment.control.controller;

import investment.control.dto.UserRegisterDTO;
import investment.control.dto.UserResponseDTO;
import investment.control.service.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@Valid @RequestBody UserRegisterDTO dto){
        UserResponseDTO responseDTO = service.register(dto);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/users")
    public ResponseEntity<Page<UserResponseDTO>> findAll(Pageable pageable){
        Page<UserResponseDTO> getAll = service.findAll(pageable);
        return ResponseEntity.ok(getAll);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponseDTO> findById (@PathVariable Long id){
        UserResponseDTO responseDTO = service.findById(id);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/users/search")
    public ResponseEntity<UserResponseDTO> findByEmail (@RequestParam String email){
        UserResponseDTO responseDTO = service.findByEmail(email);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteById (@PathVariable Long id){
        service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
