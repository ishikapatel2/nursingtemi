package com.example.nursingtemi;


import java.io.Serializable;

public class DeliveryItem implements Serializable
{

    private String item;
    private String quantity;

    public DeliveryItem(String item, String quantity)
    {
        this.item = item;
        this.quantity = quantity;
    }

    public String getItem()
    {
        return item;
    }

    public String getQuantity()
    {
        return quantity;
    }
}
