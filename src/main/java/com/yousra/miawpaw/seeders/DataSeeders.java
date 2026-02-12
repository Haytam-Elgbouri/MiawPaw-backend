package com.yousra.miawpaw.seeders;


import com.yousra.miawpaw.security.models.entities.User;
import com.yousra.miawpaw.security.models.enums.Role;
import com.yousra.miawpaw.security.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataSeeders implements CommandLineRunner {

    private final UserRepository userRepository;

    @Override
    public void run(String... args){
        seedAdmin();
    }

    private void seedAdmin(){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "admin";
        String hashedPassword = encoder.encode(rawPassword);
        if (userRepository.count() == 0) {
            User admin = new User();
            admin.setFirstName("Admin");
            admin.setLastName("Ladmin");

            admin.setEmail("admin@gmail.com");
            admin.setPassword(hashedPassword);
            admin.setCIN("JB567890");
            admin.setPhone("+212 6396-21650");
            admin.setRole(Role.ROLE_ADMIN);
            admin.setIsActive(true);

            userRepository.save(admin);
        }
    }



}
