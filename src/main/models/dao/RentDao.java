package main.models.dao;

import main.models.pojo.Rent;

import java.util.List;

public interface RentDao {
    List<Rent> getAll();
    Rent getById(int id);
}
