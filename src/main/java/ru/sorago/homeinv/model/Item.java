package ru.sorago.homeinv.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "items")
@Data
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private User owner;

    @ManyToOne(fetch = FetchType.EAGER)
    private ItemType type;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "item", fetch = FetchType.EAGER)
    private Set<ItemProp> props;

    private void addProp(ItemProp prop) {
        props.add(prop);
    }
}
