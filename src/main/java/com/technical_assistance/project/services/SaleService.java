package com.technical_assistance.project.services;

import com.technical_assistance.project.dtos.sale.OverviewDTO;
import com.technical_assistance.project.dtos.sale.SaleDetailsDTO;
import com.technical_assistance.project.dtos.sale.SaleRequestDTO;
import com.technical_assistance.project.entities.Client;
import com.technical_assistance.project.entities.Product;
import com.technical_assistance.project.entities.ProductItem;
import com.technical_assistance.project.entities.Sale;
import com.technical_assistance.project.enuns.StatusSale;
import com.technical_assistance.project.exceptions.ResourceNotFoundException;
import com.technical_assistance.project.mapper.SaleMapper;
import com.technical_assistance.project.repositories.ClientRepository;
import com.technical_assistance.project.repositories.ProductRepository;
import com.technical_assistance.project.repositories.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SaleService {

    private final SaleRepository repository;
    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;
    private final SaleMapper mapper;

    public List<OverviewDTO> overview(){
        return repository.findAll().stream().map(OverviewDTO::new).toList();
    }

    public SaleDetailsDTO saleDetails(String id){
        Sale sale = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sale com ID: " + id + " não existe;"));
        return new SaleDetailsDTO(sale);
    }

    @Transactional
    public Sale create(SaleRequestDTO dto){
        Client client = clientRepository.findById(dto.clientId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente com ID: " + dto.clientId() + " não existe;"));

        Sale sale = mapper.toEntity(dto);
        sale.setClient(client);
        sale.setDateHour(LocalDateTime.now());

        List<ProductItem> items = dto.items() == null ? new ArrayList<>() :
                dto.items().stream()
                        .map(i -> new ProductItem(i.getProductId(), i.getQuantity()))
                        .collect(Collectors.toList());

        sale.setItems(items);

        sale.getItems().forEach(this::prepareCreateSale);

        double total = sale.getItems().stream()
                .mapToDouble(ProductItem::getTotalValueItemProduct)
                .sum();

        sale.setTotalValue(total);

        return repository.save(sale);
    }

    @Transactional
    public Sale update(String id, SaleRequestDTO dto){
        Sale current = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sale com ID: " + id + " não existe;"));
        mapper.updateSaleFromDTO(dto, current);

        Client client = clientRepository.findById(dto.clientId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente com ID: " + dto.clientId() + " não existe;"));
        current.setClient(client);


        List<ProductItem> items = dto.items() == null ? new ArrayList<>() :
                dto.items().stream()
                        .map(i -> new ProductItem(i.getProductId(), i.getQuantity()))
                        .collect(Collectors.toList());

        current.setItems(items);

        current.getItems().forEach(this::prepareCreateSale);

        double total = current.getItems().stream()
                .mapToDouble(ProductItem::getTotalValueItemProduct)
                .sum();

        current.setTotalValue(total);

        return repository.save(current);
    }


    @Transactional
    public void delete(String id){
        Sale sale = repository.findById(id).orElseThrow(()  -> new ResourceNotFoundException("A venda com ID: " + id + " não existe;"));
        repository.delete(sale);
    }

    private void prepareCreateSale(ProductItem item){
        Product product = productRepository.findById(item.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("O produto com ID: " + item.getProductId() + " não existe."));

        item.setProductName(product.getName());
        item.setProductPrice(product.getUnitPrice());
    }

    public Double invoicing(){
        List<Sale> sales = repository.findAll();
        return sales.stream()
                .mapToDouble(Sale::getTotalValue)
                .sum();
    }

    public Double averageTicket() {
        List<Sale> sales = repository.findAll();
        return sales.stream()
                .mapToDouble(Sale::getTotalValue)
                .average()
                .orElse(0.0);
    }

    public Long countSales(){
        long paidCount = repository.findAll().stream()
                .filter(s -> s.getStatus() == StatusSale.PAGO)
                .count();
        return paidCount;
    }
}
