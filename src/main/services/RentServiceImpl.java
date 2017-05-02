package main.services;

import main.models.dao.RentDao;
import main.models.dao.RentDaoImpl;
import main.models.pojo.Rent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentServiceImpl implements RentService {
    private final static Logger logger = LogManager.getLogger(RentServiceImpl.class);

    private RentDao rd;

    @Autowired
    public void setRd(RentDao rd) {
        this.rd = rd;
    }

    @Override
    public List<Rent> getAllRents() {
        return rd.getAll();
    }
}
