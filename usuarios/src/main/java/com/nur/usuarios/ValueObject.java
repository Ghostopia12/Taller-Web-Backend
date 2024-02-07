package com.nur.usuarios;

public abstract class ValueObject {
    protected static void checkRule(IBusinessRules rule) throws Exception {
        if (rule == null) {
            throw new IllegalArgumentException("Rule cannot be null");
        }
        if (!rule.isValid()) {
            throw new BusinessRulesValidateExeptions(rule);
        }
    }
}
