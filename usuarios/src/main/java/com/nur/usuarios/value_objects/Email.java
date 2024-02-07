package com.nur.usuarios.value_objects;


import com.nur.usuarios.BusinessRulesValidateExeptions;
import com.nur.usuarios.ValueObject;
import jakarta.persistence.Embeddable;

@Embeddable
public class Email extends ValueObject {
    private String email;

    public Email() {
    }

    public Email(String email) throws Exception {
        if (email.length() < 7 || !email.contains("@") || !email.contains(".")){
            throw new BusinessRulesValidateExeptions("Email invalido");
        }
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
