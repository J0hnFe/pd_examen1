package com.programacion.distribuida.repo;

import com.programacion.distribuida.db.Customer;
import com.programacion.distribuida.db.PurchaseOrder;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
@Transactional
public class CustomerRepo implements PanacheRepositoryBase<Customer,Integer> {

    // Listar ordenes compra cliente (buscar cliente y listar ordenes)
    public List<Customer> findByCustomer(Integer idPurchase) {
        String query = "SELECT o.customer FROM PurchaseOrder o WHERE o.id = ?1";

        return this.list(query, idPurchase);
    }

    // Detaller orden compra en particular (buscar por id y devolver orden)
    /*
    Nombre cliente
    email cliente
    fecha compra
    lista de libros en la orden de compra: isbn, titulo, precio, nombre autor
     */
    public Integer findByPurchaseOrder(Integer idPurchase) {
        String query = "SELECT o FROM PurchaseOrder o WHERE o.id = ?1";

        return this.find(query, idPurchase).firstResult().getId();
    }

}
