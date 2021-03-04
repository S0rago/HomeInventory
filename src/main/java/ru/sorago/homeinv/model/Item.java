package ru.sorago.homeinv.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "items")
@Data
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private ItemType type;

    @Column(nullable = false)
    private String name;

}
