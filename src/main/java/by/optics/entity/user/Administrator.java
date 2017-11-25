package by.optics.entity.user;


public class Administrator extends User {

    @Override
    public String toString() {
        return "Administrator{" +
                "bannedList=" +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


}
