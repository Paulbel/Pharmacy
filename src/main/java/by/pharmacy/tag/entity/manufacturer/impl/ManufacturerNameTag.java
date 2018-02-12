package by.pharmacy.tag.entity.manufacturer.impl;


import by.pharmacy.tag.entity.manufacturer.ManufacturerTag;

public class ManufacturerNameTag extends ManufacturerTag {
    @Override
    protected String getBody() {
        return this.manufacturer.getName();
    }
}
