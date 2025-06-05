package com.programacion.distribuida.db;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "line_items")
public class LineItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    Integer quantity;
    String isbn;
    Integer orderId;

}
