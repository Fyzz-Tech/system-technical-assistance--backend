package com.technical_assistance.project.controllers;

import com.technical_assistance.project.dtos.sale.OverviewDTO;
import com.technical_assistance.project.dtos.sale.SaleDetailsDTO;
import com.technical_assistance.project.dtos.sale.SaleRequestDTO;
import com.technical_assistance.project.dtos.sale.SaleResponseDTO;
import com.technical_assistance.project.entities.Sale;
import com.technical_assistance.project.mapper.SaleMapper;
import com.technical_assistance.project.services.SaleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sales")
public class SaleController {

    private final SaleService service;
    private final SaleMapper mapper;

    @GetMapping
    public ResponseEntity<List<OverviewDTO>> overview() {
        List<OverviewDTO> sales = service.overview();
        return ResponseEntity.ok(sales);
    }

    @GetMapping("/{saleId}")
    public ResponseEntity<SaleDetailsDTO> details(@PathVariable String saleId) {
        SaleDetailsDTO response = service.saleDetails(saleId);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<SaleResponseDTO> create(@RequestBody @Valid SaleRequestDTO dto) {
        Sale newSale = service.create(dto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(newSale.getId())
                .toUri();
        SaleResponseDTO response = mapper.toResponseDTO(newSale);
        return ResponseEntity.created(location).body(response);
    }

    @DeleteMapping("/{saleId}")
    public ResponseEntity<Void> delete(@PathVariable String saleId) {
        service.delete(saleId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/invoicing")
    public ResponseEntity<Double> invoicing(){
        double totalInvoicing = service.invoicing();
        return ResponseEntity.ok(totalInvoicing);
    }

    @GetMapping("/averageTicket")
    public ResponseEntity<Double> averageTicket(){
        double averageTicket = service.averageTicket();
        return ResponseEntity.ok(averageTicket);
    }

    @GetMapping("/countSales")
    public ResponseEntity<Long> countSales(){
        Long sales = service.countSales();
        return ResponseEntity.ok(sales);
    }
}
