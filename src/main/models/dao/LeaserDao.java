package models.dao;

import models.pojo.Leaser;

import java.util.List;

public interface LeaserDao {
    List<Leaser> getAll();
    Leaser getByEmailAndPassword(String email, String password);
}
