package by.optics.entity.user;

import java.util.List;

public class Administrator extends User {
    private List<User> bannedList;

    @Override
    public String toString() {
        return "Administrator{" +
                "bannedList=" + bannedList +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public List<User> getBannedList() {
        return bannedList;
    }

    public void setBannedList(List<User> bannedList) {
        this.bannedList = bannedList;
    }
}
