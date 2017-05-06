package main.models.dao;

import main.models.pojo.Rent;

import java.sql.SQLException;
import java.util.List;

public interface RentDao {
    List<Rent> getAll();
    Rent getById(int id);

    int getCurrentRents() throws SQLException;
    int getFinishedRents() throws SQLException;
}
