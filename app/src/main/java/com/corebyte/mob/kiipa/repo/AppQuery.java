package com.corebyte.mob.kiipa.repo;

public interface AppQuery {

    String CATEGORY_FETCH_ALL = "SELECT * FROM categories";
    String CATEGORY_FETCH_BY_ID = "SELECT * FROM categories WHERE id = :id";

}
