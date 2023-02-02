package com.harishverma.blog.payloads;

import com.harishverma.blog.entities.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserDTO {
    private int id;

    @NotEmpty
    @Size(min = 4,message = "Username must be min of 4 characters")
    private String name;

    @Email(message = "Email address is not valid")
    private String email;

    @NotEmpty
    @Size(min = 3, max = 10, message = "Password should be min of 3 characters and less than 10 characters")
    private String password;

    @NotEmpty(message = "About should not be empty")
    private String about;
}
