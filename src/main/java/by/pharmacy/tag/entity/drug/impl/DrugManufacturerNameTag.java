package by.pharmacy.tag.entity.drug.impl;

import by.pharmacy.tag.entity.drug.DrugTag;

public class DrugManufacturerNameTag extends DrugTag {
    private static final long serialVersionUID = 5676343074163341964L;

    @Override
    protected String getBody() {
        return this.drug.getManufacturer().getName();
    }
}
