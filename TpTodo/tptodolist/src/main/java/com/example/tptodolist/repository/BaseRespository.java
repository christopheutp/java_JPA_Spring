package com.example.tptodolist.repository;

public interface BaseRespository<T> {

    public boolean create(T element);

    public boolean update(T element);

    public boolean delete(T element);

    public T findById(int id);
}
