package by.optics.entity;

import java.util.List;

public class Doctor extends User {
    private String phoneNumber;

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
