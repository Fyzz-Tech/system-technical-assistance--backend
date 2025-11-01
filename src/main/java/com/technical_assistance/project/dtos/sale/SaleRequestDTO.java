package com.technical_assistance.project.dtos.sale;

import com.technical_assistance.project.entities.ProductItem;
import com.technical_assistance.project.enuns.PaymentMethod;
import com.technical_assistance.project.enuns.StatusSale;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record SaleRequestDTO(@NotNull PaymentMethod paymentMethod, @NotNull StatusSale status, @NotNull List<ProductItem> items, @NotNull String clientId) {
}
