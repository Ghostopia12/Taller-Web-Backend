package com.nur.usuarios;

import java.io.Serializable;

public class BusinessRulesValidateExeptions extends Exception implements Serializable {

    private IBusinessRules bronkenRule;
    private String detail;

    public BusinessRulesValidateExeptions(IBusinessRules bronkenRule) {
        this.bronkenRule = bronkenRule;
        this.detail = bronkenRule.getMessage();
    }

    public BusinessRulesValidateExeptions(String message) {
        super(message);
        this.detail = message;
    }

    @Override
    public String toString() {
        String name = (bronkenRule == null) ? "BusinessRule" : bronkenRule.getClass().getName();
        return name + ": " + detail;
    }
}

