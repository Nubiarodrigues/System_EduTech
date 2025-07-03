package com.edutech.backend.services;

import com.edutech.backend.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {

    private final AdministratorRepository administratorRepository;
    private final CoordinatorRepository coordinatorRepository;
    private final OperatorRepository operatorRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return
                administratorRepository.findByEmail(username).map(admin -> (UserDetails) admin)
                .or(() -> coordinatorRepository.findByEmail(username).map(coord -> (UserDetails) coord))
                .or(() -> operatorRepository.findByEmail(username).map(op -> (UserDetails) op))
                .or(() -> studentRepository.findByEmail(username).map(stu -> (UserDetails) stu))
                .or(() -> teacherRepository.findByEmail(username).map(teacher -> (UserDetails) teacher))
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }
}


