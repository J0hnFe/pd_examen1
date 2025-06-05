package com.programacion.distribuida.rest;


import com.programacion.distribuida.db.LineItem;
import com.programacion.distribuida.db.PurchaseOrder;
import com.programacion.distribuida.dtos.PurchaseOrderDto;
import com.programacion.distribuida.repo.CustomerRepo;
import com.programacion.distribuida.repo.LineItemRepo;
import com.programacion.distribuida.repo.PurchaseOrderRepo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
@Transactional
public class CustomerRest {

    @Inject
    CustomerRepo customerRepo;

    @Inject
    LineItemRepo lineItemRepo;

    @Inject
    PurchaseOrderRepo purchaseOrderRepo;

    @Inject
    ModelMapper mapper;

    @Inject
    @ConfigProperty(name = "quarkus.http.port")
    Integer httpPort;

    // Listar ordenes compra cliente
    @GET
    @Path("/{id}")
    public List<PurchaseOrder> findById(@PathParam("id") Integer id) {
        var obj = purchaseOrderRepo.findByCustomer(id);
        return obj.stream().map(obj1 -> obj1).collect(Collectors.toList());
    }

    // Mostrar detaller de compra particular
    @GET
    @Path("/purchase/{id}")
    public Response findByIdPurchase(@PathParam("id") Integer id) {
        var purchaseOrder = purchaseOrderRepo.findByIdOptional(id);
        if (purchaseOrder.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .build();
        }

        var customerOfPurchase = purchaseOrder.map(PurchaseOrder::getCustomer);
        if (customerOfPurchase.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .build();
        }

        // mapear los datos de purchase al purchaseDto junto con los del customer obtenido
        PurchaseOrderDto purchaseDto = mapper.map(purchaseOrder.get(), PurchaseOrderDto.class);
        purchaseDto.setBooks(lineItemRepo.findByOrderId(id));
        purchaseDto.setCustomerName(customerOfPurchase.get().getName());
        purchaseDto.setCustomerEmail(customerOfPurchase.get().getEmail());
        purchaseDto.setPlacedOn(purchaseOrder.get().getPlacedOn());
        purchaseDto.setDeliveredOn(purchaseOrder.get().getDeliveredOn());

        return Response.ok(purchaseDto).build();
    }
}
