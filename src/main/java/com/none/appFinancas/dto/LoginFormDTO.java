package com.none.appFinancas.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class LoginFormDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "Campo Usuario deve ser preenchido!")
    private String username;

    @NotEmpty(message = "O campo senha é necessário")
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
