package com.programacion.distribuida.repo;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
@Transactional
public class LineItemRepo implements PanacheRepositoryBase<LineItemRepo, Integer> {

    public List<LineItemRepo> findByOrderId(Integer orderId) {
        String query = "SELECT li FROM LineItem li WHERE li.orderId = :orderId";
        return find(query, orderId).list();
    }

}
