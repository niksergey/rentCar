//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.04.23 at 12:13:48 AM MSK 
//


package main.models.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

public class Car {
    Integer id;
    Integer carModelId;
    CarModel carModel;
    String vin;
    int year;

    public Car() {
    }

//    public Car(CarModel carModel, String vin, int year) {
//        this.carModel = carModel;
//        this.vin = vin;
//        this.year = year;
//    }
//
//    public Car(Integer id, CarModel carModel, String vin, int year) {
//        this.id = id;
//        this.carModel = carModel;
//        this.vin = vin;
//        this.year = year;
//    }

    public Car(Integer id, Integer carModelId, String vin, int year) {
        this.id = id;
        this.carModelId = carModelId;
        this.vin = vin;
        this.year = year;
    }

    public Integer getCarModelId() {
        return carModelId;
    }

    public void setCarModelId(Integer carModelId) {
        this.carModelId = carModelId;
    }

    public boolean isNew() {
        return (this.id == null);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer value) {
        this.id = value;
    }

    public CarModel getCarModel() {
        return carModel;
    }

    public void setCarModel(CarModel value) {
        this.carModel = value;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String value) {
        this.vin = value;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int value) {
        this.year = value;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", carModel=" + carModel +
                ", vin='" + vin + '\'' +
                ", year=" + year +
                '}';
    }
}
