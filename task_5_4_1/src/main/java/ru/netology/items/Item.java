package ru.netology.items;

import static ru.netology.utils.Utils.padRight;

public class Item implements Ratable {

    protected static final int ID_FIELD_LENGTH = 7;
    protected static final int TITLE_FIELD_LENGTH = 10;
    protected static final int PRICE_FIELD_LENGTH = 18;
    protected static final int AVAILABLE_QTY_FIELD_LENGTH = 18;
    protected static final int RATING_AVG_FIELD_LENGTH = 9;

    protected int id;
    protected String title;
    protected int price;
    protected int availableQty;
    protected double ratingAvg;
    protected int ratingQty;

    protected static final int MIN_RATING = 0;
    protected static final int MAX_RATING = 5;

    public Item(int id, String title, int price, int availableQty) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.availableQty = availableQty;
        this.ratingAvg = 0;
        this.ratingQty = 0;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public int getAvailableQty() {
        return availableQty;
    }

    public double getRatingAvg() {
        return ratingAvg;
    }

    public void setAvailableQty(int availableQty) {
        this.availableQty = availableQty;
    }

    @Override
    public void rate(int rating) {
        if (MIN_RATING <= rating && rating <= MAX_RATING) {
            double ratingSum = this.ratingAvg * this.ratingQty + rating;
            this.ratingQty++;
            this.ratingAvg = ratingSum / this.ratingQty;
        } else {
            System.out.println("Wrong rating value. Your rating was not taken into account.");
        }
    }

    @Override
    public String toString() {
        return padRight(Integer.toString(id), ID_FIELD_LENGTH)
                + padRight(title, TITLE_FIELD_LENGTH)
                + padRight(Integer.toString(price), PRICE_FIELD_LENGTH)
                + padRight(Integer.toString(availableQty), AVAILABLE_QTY_FIELD_LENGTH)
                + padRight(String.format("%.2f", ratingAvg), RATING_AVG_FIELD_LENGTH);
    }

    public static String getCaption() {
        return padRight("Num.", ID_FIELD_LENGTH)
                + padRight("Product", TITLE_FIELD_LENGTH)
                + padRight("Price, rub./pce", PRICE_FIELD_LENGTH)
                + padRight("Available, pcs.", AVAILABLE_QTY_FIELD_LENGTH)
                + padRight("Rating", RATING_AVG_FIELD_LENGTH);
    }
}