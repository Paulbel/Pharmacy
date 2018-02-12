package by.pharmacy.tag.entity.manufacturer;

import by.pharmacy.entity.Manufacturer;
import by.pharmacy.tag.entity.EntityVisualisationTag;

public abstract class ManufacturerTag extends EntityVisualisationTag {
    private static final long serialVersionUID = -8375925204524921395L;

    protected Manufacturer manufacturer;

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }
}
