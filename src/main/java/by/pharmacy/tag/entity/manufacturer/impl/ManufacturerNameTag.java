package by.pharmacy.tag.entity.manufacturer.impl;


import by.pharmacy.tag.entity.manufacturer.ManufacturerTag;

public class ManufacturerNameTag extends ManufacturerTag {
    private static final long serialVersionUID = 6455850297985059844L;

    @Override
    protected String getBody() {
        return this.manufacturer.getName();
    }
}
