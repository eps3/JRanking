package cn.sheep3.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by sheep3 on 2017/10/17.
 */
@Data
@NoArgsConstructor
public class RegisterForm {
    @NotNull
    @Size(min=1, max=20)
    private String username;

    @NotNull
    @Size(min=1, max=100)
    @Email
    private String email;

    @NotNull
    @Size(min=6, max=30)
    private String password;
}
