package entity;

import javax.persistence.*;

@Entity
@Table(name = "customers")

public class Customer {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//primary key
    private int id;

    @Column
    private String name;

    @Column
    private String kindOfActivity;

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

    public String getKindOfActivity() {
        return kindOfActivity;
    }

    @Override
    public String toString() {
        return "entity.Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", kindOfActivity='" + kindOfActivity + '\'' +
                '}';
    }

    public void setKindOfActivity(String kindOfActivity) {
        this.kindOfActivity = kindOfActivity;


    }
}
