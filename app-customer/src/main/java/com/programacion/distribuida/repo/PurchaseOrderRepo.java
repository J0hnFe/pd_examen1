package com.programacion.distribuida.repo;

import com.programacion.distribuida.db.PurchaseOrder;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
@Transactional
public class PurchaseOrderRepo implements PanacheRepositoryBase<PurchaseOrder, Integer> {
    public List<PurchaseOrder> findByCustomer(Integer customerId) {
        String query = "SELECT po FROM PurchaseOrder po WHERE po.customer.id = :customerId";
        return find(query, customerId).list();
    }
}
