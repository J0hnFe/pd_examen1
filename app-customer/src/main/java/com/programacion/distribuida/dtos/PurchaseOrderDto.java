package com.programacion.distribuida.dtos;


import com.programacion.distribuida.repo.LineItemRepo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.awt.print.Book;
import java.util.Date;
import java.util.List;

@ToString
@Getter
@Setter
public class PurchaseOrderDto {
    private String customerName;
    private String customerEmail;
    private Date deliveredOn;
    private Date placedOn;

    private List<LineItemRepo> books;

}
