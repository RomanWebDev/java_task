package com.task.template.REST.models;

import org.omg.CORBA.INTERNAL;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class Todo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Todo name is required.")
    @Basic(optional = false)
    private String title;
    private String description;
    private Boolean is_done;

    private Date date = new Date();

    public Todo() { }

    public Todo(Long id, @NotNull(message = "Todo name is required.") String title, String description, Boolean is_done, Date date) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.is_done = is_done;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setName(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsDone() {
        return is_done;
    }

    public void setIsDone(Boolean is_done) {
        this.is_done = is_done;
    }

    public String getDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
        return  format.format( date );
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
