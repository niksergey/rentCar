package main.models.pojo;

import java.sql.Timestamp;
import java.util.Date;

public class Rent {
    private Integer id;
    private Date startTime;
    private Date finishDate;
    private Leaser leaser;
    private Car car;

    public Rent() {
    }

    public Rent(Timestamp startTime, Timestamp finishDate, Leaser leaser, Car car) {
        this.startTime = startTime;
        this.finishDate = finishDate;
        this.leaser = leaser;
        this.car = car;
    }

    public Rent(int id, Timestamp startTime, Timestamp finishDate, Leaser leaser, Car car) {
        this.id = id;
        this.startTime = startTime;
        this.finishDate = finishDate;
        this.leaser = leaser;
        this.car = car;
    }

    private Date TmsToDate(Timestamp stamp) {
        return new Date(stamp.getTime());
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public Leaser getLeaser() {
        return leaser;
    }

    public void setLeaser(Leaser leaser) {
        this.leaser = leaser;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
