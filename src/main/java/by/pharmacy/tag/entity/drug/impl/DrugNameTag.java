package by.pharmacy.tag.entity.drug.impl;

import by.pharmacy.tag.entity.drug.DrugTag;

public class DrugNameTag extends DrugTag {
    private static final long serialVersionUID = 8247239168334164673L;

    @Override
    public String getBody() {
        return drug.getName();
    }
}
