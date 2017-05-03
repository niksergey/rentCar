package main.models.pojo;

public class Car {
    private Integer id;
    private Integer carModelId;
    private CarModel carModel;
    private String vin;
    private int year;

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

    public Car(Integer carModelId, String vin, int year) {
        this.carModelId = carModelId;
        this.vin = vin;
        this.year = year;
    }

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
