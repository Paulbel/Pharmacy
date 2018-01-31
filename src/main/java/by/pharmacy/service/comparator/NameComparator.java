package by.pharmacy.service.comparator;

import by.pharmacy.entity.Drug;

import java.util.Comparator;

public class NameComparator implements Comparator<Drug> {
    @Override
    public int compare(Drug o1, Drug o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
