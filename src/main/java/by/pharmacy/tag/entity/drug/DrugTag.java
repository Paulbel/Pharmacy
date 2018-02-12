package by.pharmacy.tag.entity.drug;

import by.pharmacy.entity.Drug;
import by.pharmacy.tag.entity.EntityVisualisationTag;

public abstract class DrugTag extends EntityVisualisationTag {
    private static final long serialVersionUID = 5035643944923937961L;

    protected Drug drug;

    public void setDrug(Drug drug) {
        this.drug = drug;
    }
}
