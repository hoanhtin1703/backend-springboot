package com.example.BookStore.dao;

import java.util.List;

public interface DAO<T> {

    public void save(T t);

    public void update(T t);
}
