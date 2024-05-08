package com.project.paypal.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "invoices")
public class OrderInvoice {

    @Id
    private String id;
    private String invoiceId;
    private String currency_code;
    private String item_name;
    private String item_quantity;
    private String items_value;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getCurrency_code() {
        return currency_code;
    }

    public void setCurrency_code(String currency_code) {
        this.currency_code = currency_code;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_quantity() {
        return item_quantity;
    }

    public void setItem_quantity(String item_quantity) {
        this.item_quantity = item_quantity;
    }

    public String getItems_value() {
        return items_value;
    }

    public void setItems_value(String items_value) {
        this.items_value = items_value;
    }

    public OrderInvoice() {
    }

    public OrderInvoice(String id, String invoiceId, String currency_code, String item_name, String item_quantity, String items_value) {
        this.id = id;
        this.invoiceId = invoiceId;
        this.currency_code = currency_code;
        this.item_name = item_name;
        this.item_quantity = item_quantity;
        this.items_value = items_value;
    }
}
