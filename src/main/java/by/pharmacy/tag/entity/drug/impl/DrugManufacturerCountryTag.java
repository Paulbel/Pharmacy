package by.pharmacy.tag.entity.drug.impl;

import by.pharmacy.tag.entity.drug.DrugTag;

public class DrugManufacturerCountryTag extends DrugTag {
    private static final long serialVersionUID = -7469159454935690804L;

    @Override
    protected String getBody() {
        return drug.getManufacturer().getCountry().getName();
    }
}
