package by.pharmacy.tag.entity.manufacturer.impl;

import by.pharmacy.tag.entity.manufacturer.ManufacturerTag;

public class ManufacturerCountryTag extends ManufacturerTag {
    private static final long serialVersionUID = -6280546961794115502L;

    @Override
    protected String getBody() {
        return this.manufacturer.getCountry().getName();
    }
}
