package model.activity;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by Kamil on 05-Apr-17.
 */
public class Activity implements Serializable{
    private long id;
    private String name;
    private long UserId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    private LocalDate startDate;
}
