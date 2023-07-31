package ru.netology.assortments;

import ru.netology.items.Item;
import ru.netology.items.Product;

import java.util.ArrayList;
import java.util.List;

public class Assortment {
    List<Product> assormentList;

    public Assortment() {
        this.assormentList = new ArrayList<>();
    }

    public List<Product> getAssormentList() {
        return assormentList;
    }

    public void addProduct(Product product) {
        this.assormentList.add(product);
    }

    public int getSize() {
        return this.assormentList.size();
    }

    public void printAssortment() {
        System.out.println(Product.getCaption());
        for (Product product : assormentList) {
            System.out.println(product);
        }
        System.out.println();
    }
}