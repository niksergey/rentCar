package main.services;

import main.models.pojo.Rent;

import java.sql.SQLException;
import java.util.List;

public interface RentService {
    List<Rent> getAllRents();
    int getNumberCurrentRents() throws SQLException;
    int getNumberFinishedRents() throws SQLException;
}
