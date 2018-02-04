package by.pharmacy.dao.builder.impl;

import by.pharmacy.dao.builder.OrderBuilder;
import by.pharmacy.entity.Drug;
import by.pharmacy.entity.Order;
import by.pharmacy.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderBuilderImpl implements OrderBuilder {
    protected Order order;
    protected ResultSet resultSet;


    public OrderBuilderImpl(ResultSet resultSet) {
        this.resultSet = resultSet;
    }


    @Override
    public void createOrder() {
        this.order = new Order();
    }

    @Override
    public void buildClient() throws SQLException {
        String login = resultSet.getString("user.login");
        User user = new User();
        user.setLogin(login);
        order.setClient(user);
    }


    @Override
    public void buildDrug() throws SQLException {
        Drug drug = new Drug();
        double price = resultSet.getDouble("drug.price");
        String drugName = resultSet.getString("drug_translate.name");
        drug.setPrice(price);
        drug.setName(drugName);
        order.setDrug(drug);
    }

    @Override
    public void buildOrderInfo() throws SQLException {
        int drugNumber = resultSet.getInt("orders.number");
        java.util.Date date = resultSet.getDate("orders.date");

        order.setDate(date);
        order.setNumber(drugNumber);
    }

    @Override
    public Order getOrder() {
        return this.order;
    }
}
