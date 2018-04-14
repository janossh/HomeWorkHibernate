package entity;

import org.hibernate.type.BigDecimalType;
import org.hibernate.type.StringType;

import java.math.BigDecimal;

public class ResultOfQuery {
    private String name;
    private String description;
    private BigDecimal countOfDeveloper;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getCountOfDeveloper() {
        return countOfDeveloper;
    }

    public void setCountOfDeveloper(BigDecimal countOfDeveloper) {
        this.countOfDeveloper = countOfDeveloper;
    }
}
