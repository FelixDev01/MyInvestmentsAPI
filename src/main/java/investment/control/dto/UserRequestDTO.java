package investment.control.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRequestDTO(

        @NotBlank(message = "name is required")
        String name,

        @NotBlank(message = "email is required")
        @Email(message = "Write a valid email format")
        String email,

        @NotBlank(message = "password is required")
        String password) {




}
