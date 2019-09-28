package de.beuth.master.services;

public interface CustomListener<T, K>
{
    void getResult(T object);
    void getError(K object);
}