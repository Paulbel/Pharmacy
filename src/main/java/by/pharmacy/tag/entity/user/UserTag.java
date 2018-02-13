package by.pharmacy.tag.entity.user;

import by.pharmacy.entity.User;
import by.pharmacy.tag.entity.EntityVisualisationTag;

public abstract class UserTag extends EntityVisualisationTag {
    protected User user;

    public void setUser(User user) {
        this.user = user;
    }
}
