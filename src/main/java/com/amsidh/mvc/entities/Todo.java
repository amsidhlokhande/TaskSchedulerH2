package com.amsidh.mvc.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "TODO")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Todo {

    @Id
    @GeneratedValue
    private Integer id;
    private String user;
    @Column(name = "description")
    private String desc;
    private String targetDate;
    private Boolean isDone;
    @Override
    public String toString() {
        return String.format(
                "Todo [id=%s, user=%s, desc=%s, targetDate=%s, isDone=%s]", id,
                user, desc, targetDate, isDone);
    }
}
