package com.none.appFinancas.dto;

import com.none.appFinancas.enums.ProfileUser;

public class UserDTO {

    private Long id;
    private String nome;
    private String username;
    private ProfileUser profile;


    public UserDTO(Long id, String nome, String username, ProfileUser profile) {
        this.id = id;
        this.nome = nome;
        this.username = username;
        this.profile = profile;
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

    public ProfileUser getProfile() {
        return profile;
    }

    public void setProfile(ProfileUser profile) {
        this.profile = profile;
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
