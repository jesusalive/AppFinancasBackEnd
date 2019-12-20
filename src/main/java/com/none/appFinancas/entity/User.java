package com.none.appFinancas.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.none.appFinancas.enums.ProfileUser;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    @JsonIgnore
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "userProfiles")
    private Set<Integer> profiles = new HashSet<>();

    public User() {
        addProfile(ProfileUser.USER);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public User(String name, String username, String password) {
        if(name.trim().isEmpty()){
            throw new RuntimeException("Nome não pode ser nulo");
        }

        if(username.trim().isEmpty()){
            throw new RuntimeException("Username não pode ser nulo");
        }

        if(password.trim().isEmpty()){
            throw new RuntimeException("Senha não pode ser nulo(a)");
        }

        this.name = name;
        this.username = username;
        this.password = password;
        addProfile(ProfileUser.USER);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Set<ProfileUser> getProfile() throws IllegalAccessException {
        Set<ProfileUser> collect = new HashSet<>();
        for (Integer profile : profiles) {
            ProfileUser profileUser = ProfileUser.toEnum(profile);
            collect.add(profileUser);
        }
        return collect;
    }

    public void addProfile(ProfileUser profile){
        this.profiles.add(profile.getCod());
    }
}

