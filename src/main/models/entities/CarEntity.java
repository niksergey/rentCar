package main.models.entities;

import javax.persistence.*;

@Entity
@Table(name = "car", schema = "public", catalog = "rentcar")
public class CarEntity {
    private int id;
    private String vin;
    private Integer year;
    private Boolean rented;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "vin")
    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    @Basic
    @Column(name = "year")
    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Basic
    @Column(name = "rented")
    public Boolean getRented() {
        return rented;
    }

    public void setRented(Boolean rented) {
        this.rented = rented;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CarEntity carEntity = (CarEntity) o;

        if (id != carEntity.id) return false;
        if (vin != null ? !vin.equals(carEntity.vin) : carEntity.vin != null) return false;
        if (year != null ? !year.equals(carEntity.year) : carEntity.year != null) return false;
        if (rented != null ? !rented.equals(carEntity.rented) : carEntity.rented != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (vin != null ? vin.hashCode() : 0);
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + (rented != null ? rented.hashCode() : 0);
        return result;
    }
}
