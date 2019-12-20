package com.none.appFinancas.service;

import com.none.appFinancas.entity.User;
import com.none.appFinancas.repository.UserRepository;
import com.none.appFinancas.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username não encontrado"));

        try {
            return new UserSS(user.getId(), user.getUsername(),
                    user.getPassword(), user.getProfile());
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Id de Profile incorreto!");
        }
    }
}
