package com.develhope.co.biblioteca_prova.auth;

import com.develhope.co.biblioteca_prova.models.Utente;
import com.develhope.co.biblioteca_prova.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserInitializationRunner implements ApplicationRunner {
    @Autowired
    private UtenteRepository utenteRepo;
    @Autowired
    private PasswordEncoder pwEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<Utente> utenti = utenteRepo.findAll();
//        for (Utente u : utenti) {
//            u.setPassword(pwEncoder.encode("password"));
//            utenteRepo.save(u);
//        }
        System.out.println(pwEncoder.encode("password"));
    }
}
