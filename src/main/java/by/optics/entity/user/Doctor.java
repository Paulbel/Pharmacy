package by.optics.entity.user;

import by.optics.entity.Visit;

import java.util.List;

public class Doctor extends User {

    @Override
    public String toString() {
        return "Doctor{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", visits=" + visits +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    private List<Visit> visits;
}
