package main.models.pojo;

public class CarModel {

    protected Integer id;
    protected String manufacturer;
    protected String model;
    protected String gear;
    protected int power;

    public CarModel() {
    }

    public CarModel(String manufacturer, String model, String gear, int power) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.gear = gear;
        this.power = power;
    }

    public CarModel(int id, String manufacturer, String model, String gear, int power) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.model = model;
        this.gear = gear;
        this.power = power;
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

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String value) {
        this.manufacturer = value;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String value) {
        this.model = value;
    }

    public String getGear() {
        return gear;
    }

    public void setGear(String value) {
        this.gear = value;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int value) {
        this.power = value;
    }

    @Override
    public String toString() {
        return "CarModel{" +
                "id=" + id +
                ", manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                ", gear='" + gear + '\'' +
                ", power=" + power +
                '}';
    }
}
