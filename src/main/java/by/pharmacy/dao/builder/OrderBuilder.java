package by.pharmacy.dao.builder;

import by.pharmacy.entity.Order;

import java.sql.SQLException;

public interface OrderBuilder {
    void createOrder();

    void buildClient() throws SQLException;


    void buildDrug() throws SQLException;

    void buildOrderInfo() throws SQLException;

    void buildFullOrder() throws SQLException;

    Order getOrder();
}
