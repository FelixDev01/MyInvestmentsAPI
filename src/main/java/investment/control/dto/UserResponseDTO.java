package investment.control.dto;

import investment.control.model.User;

public record UserResponseDTO(

        Long id,

        String name,

        String email) {

    public UserResponseDTO(User saveUser) {
        this(saveUser.getId(), saveUser.getName(), saveUser.getEmail());
    }
}
