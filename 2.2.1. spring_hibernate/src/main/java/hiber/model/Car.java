package hiber.model;

import javax.persistence.*;


@Entity
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "models")
    private String models;
    @Column(name = "series")
    private int series;

    public Car() {
    }

    public Car(String models, int series) {
        this.models = models;
        this.series = series;
    }

    public String getModels() {
        return models;
    }

    public void setModels(String models) {
        this.models = models;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", models='" + models + '\'' +
                ", series=" + series +
                '}';
    }
}
