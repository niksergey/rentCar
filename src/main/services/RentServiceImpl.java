package main.services;

import main.models.dao.RentDao;
import main.models.dao.RentDaoImpl;
import main.models.pojo.Rent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class RentServiceImpl implements RentService {
    private final static Logger logger = LogManager.getLogger(RentServiceImpl.class);

    private static RentDao rd = new RentDaoImpl();

    @Override
    public List<Rent> getAllRents() {
        return rd.getAll();
    }
}
