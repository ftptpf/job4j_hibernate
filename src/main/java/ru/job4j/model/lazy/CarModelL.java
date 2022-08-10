package ru.job4j.model.lazy;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "lazy_car_models")
public class CarModelL {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private CarBrandL carBrandL;

    public static CarModelL of(String name, CarBrandL carBrandL) {
        CarModelL model = new CarModelL();
        model.name = name;
        model.carBrandL = carBrandL;
        return model;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CarBrandL getCarBrand() {
        return carBrandL;
    }

    public void setCarBrand(CarBrandL carBrandL) {
        this.carBrandL = carBrandL;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CarModelL carModelL = (CarModelL) o;
        return id == carModelL.id && Objects.equals(name, carModelL.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "CarModel{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", carBrand=" + carBrandL
                + '}';
    }
}
