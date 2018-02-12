package by.pharmacy.service.validator;

import by.pharmacy.service.validator.impl.DrugValidator;
import by.pharmacy.service.validator.impl.UserValidator;

import java.util.HashMap;
import java.util.Map;

public final class ValidatorFactory {
    public final static ValidatorFactory instance = new ValidatorFactory();
    private final static Map<EntityType, Validator> validatorMap = new HashMap<>();

    private ValidatorFactory() {
        validatorMap.put(EntityType.DRUG, new DrugValidator());
        validatorMap.put(EntityType.USER, new UserValidator());
    }

    public static ValidatorFactory getInstance() {
        return instance;
    }

    public Validator getValidator(EntityType type) {
        return validatorMap.get(type);
    }

    public enum EntityType {
        DRUG, USER
    }
}
