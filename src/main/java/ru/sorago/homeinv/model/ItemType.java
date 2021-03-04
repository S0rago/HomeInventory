package ru.sorago.homeinv.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "types")
@Data
public class ItemType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;
}
