package by.pharmacy;

import by.pharmacy.dao.exception.DAOException;
import by.pharmacy.dao.impl.SQLDrugDAO;
import by.pharmacy.entity.Language;

public class Main {
    public static void main(String [] args){
/*        Drug drug = new Drug();
        drug.setDescription("Препарат");
        drug.setDosage(2);
        drug.setDescription("Норм такой");
        drug.setAmount(3);
        drug.setName("Препаратик");
        SQLDrugDAO sqlDrugDAO = new SQLDrugDAO();
        try {
            sqlDrugDAO.addDrug(drug,Language.RUSSIAN);
        } catch (DAOException e) {
            e.printStackTrace();
        }*/

        SQLDrugDAO sqlDrugDAO = new SQLDrugDAO();
        try {
            System.out.println(sqlDrugDAO.getAll(Language.RUSSIAN));
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }
}
