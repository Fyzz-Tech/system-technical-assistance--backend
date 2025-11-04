package com.technical_assistance.project.dtos.sale;

import com.technical_assistance.project.dtos.product.ProductItemRequestDTO;
import com.technical_assistance.project.enuns.PaymentMethod;
import com.technical_assistance.project.enuns.StatusSale;

import java.util.List;

public record SaleRequestDTO(
        String clientId,
        List<ProductItemRequestDTO> items,
        StatusSale status,
        PaymentMethod paymentMethod
) {}
