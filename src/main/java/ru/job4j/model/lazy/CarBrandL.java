package ru.job4j.model.lazy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "lazy_car_brands")
public class CarBrandL {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToMany(mappedBy = "carBrandL")
    private List<CarModelL> models = new ArrayList<>();

    public static CarBrandL of(String name) {
        CarBrandL carBrandL = new CarBrandL();
        carBrandL.name = name;
        return carBrandL;
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

    public List<CarModelL> getModels() {
        return models;
    }

    public void setModels(List<CarModelL> models) {
        this.models = models;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CarBrandL carBrandL = (CarBrandL) o;
        return id == carBrandL.id && Objects.equals(name, carBrandL.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "CarBrand{"
                + "id=" + id
                + ", name='" + name + '\''
                + '}';
    }
}
