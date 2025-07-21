package com.develhope.co.biblioteca_prova.service;

import com.develhope.co.biblioteca_prova.auth.CustomUserDetails;
import com.develhope.co.biblioteca_prova.models.Utente;
import com.develhope.co.biblioteca_prova.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UtenteRepository utenteRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Utente> opt = utenteRepo.findByUsername(username);
        if (opt.isEmpty()) {
            throw new UsernameNotFoundException("Utente non trovato");
        }
        return new CustomUserDetails(opt.get());
    }
}
