package by.pharmacy.dao.impl;

import by.pharmacy.BaseDAOTest;
import by.pharmacy.dao.ManufacturerDAO;
import by.pharmacy.entity.Drug;
import by.pharmacy.entity.Language;
import by.pharmacy.entity.Manufacturer;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class ManufacturerDAOImplTest extends BaseDAOTest {
    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    @Ignore
    public void testAddManufacturer() throws Exception {
        ManufacturerDAO manufacturerDAO = new ManufacturerDAOImpl();
        manufacturerDAO.addManufacturer(initManufacturer(), Language.RU);

    }


    private Manufacturer initManufacturer(){
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setEmail("www.academpharm.by");
       // manufacturer.setCountry("Республика Беларусь");
        manufacturer.setPhoneNumber("(017) 263-67-70");
        manufacturer.setName("«Академфарм» ГП");
        manufacturer.setAddress("г. Минск, ул. Академика В. Ф. Купревича, д. 5, корп. 3");
        return manufacturer;
    }
/*INSERT INTO manufacturer (phone_number, country_code, email) VALUES
  ('(017) 263-67-70', 'by', 'www.academpharm.by'),*/
      //('russian', 1, '«Академфарм» ГП', 'г. Минск, ул. Академика В. Ф. Купревича, д. 5, корп. 3'),
    @Test
    public void testGetManufacturers() throws Exception {
    }

}