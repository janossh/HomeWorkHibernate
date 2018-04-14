package entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "developers")
public class Developer {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//primary key
    private int id;

    @Column
    private String name;

    @Column
    private int age;

    @Column
    private String sex;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="company_id")
    private T t;

    @Column
    private int salary;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Developers_Projects",
            joinColumns = { @JoinColumn(name = "developer") },
            inverseJoinColumns = { @JoinColumn(name = "project") }
    )
    Set<Project> projects = new HashSet<Project>();


    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Developers_Skills",
            joinColumns = { @JoinColumn(name = "developer") },
            inverseJoinColumns = { @JoinColumn(name = "skill") }
    )
    Set<Skill> skills = new HashSet<Skill>();

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Developer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", t=" + t +
                ", salary=" + salary +
                '}';
    }
}
