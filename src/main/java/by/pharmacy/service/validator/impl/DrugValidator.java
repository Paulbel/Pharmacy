package by.pharmacy.service.validator.impl;

import by.pharmacy.entity.Drug;
import by.pharmacy.service.validator.Validator;

public class DrugValidator extends Validator {
    private final static String NAME_REGEX = "([A-Za-zА-Яа-я\\d._+]){1,70}";
    private final static String DESCRIPTION_REGEX = "([A-Za-zА-Яа-я\\d._+]){1,200}";
    private final static String COMPOSITION_REGEX = "([A-Za-zА-Яа-я\\d._+]){1,100}";

    @Override
    public <T> boolean validate(T obj) {
        if (!(obj instanceof Drug)) {
            return false;
        }
        Drug drug = (Drug) obj;
        String description = drug.getDescription();
        String composition = drug.getComposition();
        boolean valid = true;

        if (description != null) {
            valid = checkParameter(description, DESCRIPTION_REGEX);
        }
        if (valid && composition != null) {
            valid = checkParameter(composition, COMPOSITION_REGEX);
        }
        return valid &&
                checkParameter(drug.getName(), NAME_REGEX) &&
                validateId(drug.getManufacturer().getId()) &&
                drug.getNumber() >= 0 && drug.getPrice() > 0;
    }
}
