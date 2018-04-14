package entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "skills")
public class Skill {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//primary key
    private int id;

    @Column
    private String skill;

    @Column
    private String level;

    @ManyToMany(mappedBy = "skills")
    private Set<Developer> developers = new HashSet<Developer>();

    public Set<Developer> getDevelopers() {
        return developers;
    }

    public void setDevelopers(Set<Developer> developers) {
        this.developers = developers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "id=" + id +
                ", skill='" + skill + '\'' +
                ", level='" + level + '\'' +
                '}';
    }
}
