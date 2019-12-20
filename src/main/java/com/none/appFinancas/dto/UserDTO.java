package com.none.appFinancas.dto;

import com.none.appFinancas.enums.ProfileUser;

import java.util.Set;
import java.util.stream.Collectors;

public class UserDTO {

    private Long id;
    private String nome;
    private String username;
    private Set<ProfileUser> profiles;


    public UserDTO(Long id, String nome, String username, Set<ProfileUser> profiles) {
        this.id = id;
        this.nome = nome;
        this.username = username;
        this.profiles = profiles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<ProfileUser> getProfile() {
        return profiles;
    }

    public void setProfile(Set<ProfileUser> profiles) {
        this.profiles = profiles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", username='" + username + '\'' +
                '}';
    }


}
