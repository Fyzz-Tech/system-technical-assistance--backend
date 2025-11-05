package com.technical_assistance.project.dtos.sale;

import com.technical_assistance.project.dtos.product.ProductItemRequestDTO;
import com.technical_assistance.project.entities.ProductItem;
import com.technical_assistance.project.entities.Sale;
import com.technical_assistance.project.enuns.PaymentMethod;
import com.technical_assistance.project.enuns.StatusSale;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaleRequestDTO {

    @NotNull private String clientId;
    @NotNull private List<ProductItemRequestDTO> items = new ArrayList<>();
    @NotNull private StatusSale status;
    @NotNull private PaymentMethod paymentMethod;


    public Sale toEntity() {
        Sale sale = new Sale();
        sale.setStatus(this.status);
        sale.setPaymentMethod(this.paymentMethod);
        if (this.items != null) {
            List<ProductItem> productItems = new ArrayList<>();
            for (ProductItemRequestDTO itemDTO : this.items) {
                ProductItem item = new ProductItem();
                item.setProductId(itemDTO.getProductId());
                item.setQuantity(itemDTO.getQuantity());
                productItems.add(item);
            }
            sale.setItems(productItems);
        }
        return sale;
    }
}
