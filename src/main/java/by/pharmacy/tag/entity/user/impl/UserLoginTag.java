package by.pharmacy.tag.entity.user.impl;

import by.pharmacy.tag.entity.user.UserTag;

public class UserLoginTag extends UserTag {
    private static final long serialVersionUID = 2888537369947058625L;

    @Override
    protected String getBody() {
        return user.getLogin();
    }
}
