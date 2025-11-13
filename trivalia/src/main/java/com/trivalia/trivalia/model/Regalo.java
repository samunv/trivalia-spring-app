package com.trivalia.trivalia.model;

import com.trivalia.trivalia.enums.Item;

public class Regalo {

    private Item item;

    private Integer cantidad;

    public Regalo(Item item, Integer cantidad) {
        this.item = item;
        this.cantidad = cantidad;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

}
