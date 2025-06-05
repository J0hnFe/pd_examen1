package com.programacion.distribuida.db;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "purchase_orders")
public class PurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    Date deliveredOn;
    Date placedOn;
    Integer total;
    Integer status;

    @OneToOne
    @JoinColumn(name="customer_id", nullable=false)
    Customer customer;
}
