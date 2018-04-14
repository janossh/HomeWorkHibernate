package entity;

import javax.persistence.*;

@Entity
@Table(name = "companies")
public class T {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//primary key
    private int id;

    @Column
    private String name;

    @Column
    private int numberOfEmploees;

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

    public int getNumberOfEmploees() {
        return numberOfEmploees;
    }

    public void setNumberOfEmploees(int numberOfEmploees) {
        this.numberOfEmploees = numberOfEmploees;
    }

    @Override
    public String toString() {
        return "entity.T{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", numberOfEmploees=" + numberOfEmploees +
                '}';
    }
}
