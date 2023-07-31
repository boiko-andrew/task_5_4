package ru.netology.items;

import static ru.netology.utils.Utils.padRight;

public class Product extends Item {
    protected static final int STORE_DURATION_FIELD_LENGTH = 19;
    private int storeDuration;

    public Product(int id, String title, int price, int availableQty, int storeDuration) {
        super(id, title, price, availableQty);
        this.storeDuration = storeDuration;
    }

    public Product(Product anotherProduct) {
        super(anotherProduct.id, anotherProduct.title, anotherProduct.price, anotherProduct.availableQty);
        this.ratingAvg = anotherProduct.ratingAvg;
        this.ratingQty = anotherProduct.ratingQty;
        this.storeDuration = anotherProduct.storeDuration;
    }

    @Override
    public String toString() {
        return padRight(Integer.toString(id), ID_FIELD_LENGTH)
                + padRight(title, TITLE_FIELD_LENGTH)
                + padRight(Integer.toString(price), PRICE_FIELD_LENGTH)
                + padRight(Integer.toString(availableQty), AVAILABLE_QTY_FIELD_LENGTH)
                + padRight(String.format("%.2f", ratingAvg), RATING_AVG_FIELD_LENGTH)
                + padRight(Integer.toString(storeDuration), STORE_DURATION_FIELD_LENGTH);
    }

    public static String getCaption() {
        return padRight("Num.", ID_FIELD_LENGTH)
                + padRight("Product", TITLE_FIELD_LENGTH)
                + padRight("Price, rub./pce", PRICE_FIELD_LENGTH)
                + padRight("Available, pcs.", AVAILABLE_QTY_FIELD_LENGTH)
                + padRight("Rating", RATING_AVG_FIELD_LENGTH)
                + padRight("Store duration, days", STORE_DURATION_FIELD_LENGTH);
    }
}