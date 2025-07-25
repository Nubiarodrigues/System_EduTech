package com.edutech.backend.utils;

import com.edutech.backend.entities.User;
import com.edutech.backend.exceptions.ResourceNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserLoggedUtils {

    public User getUserLogged(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(principal instanceof User){
            return (User) principal;
        }
        throw new ResourceNotFoundException("Usuário não autenticado ou tipo inválido.");
    }

    public Long getUserLoggedId(){
        return getUserLogged().getId();
    }

    public Long getSchoolUserLogged(){
        return getUserLogged().getIdSchool();
    }
}