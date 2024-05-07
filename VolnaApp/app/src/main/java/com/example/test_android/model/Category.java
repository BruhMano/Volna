package com.example.test_android.model;

public enum Category {
    action("боевик"),
    adventure("приключение"),
    animation("мультфильм"),
    comedy("комедия"),
    crime("криминал"),
    drama("драма"),
    fantasy("фантастика"),
    history("исторический"),
    horror("ужасы");

    private final String localName;

    Category(String localName) {
        this.localName = localName;
    }

    public String getLocalName() {
        return localName;
    }
}
