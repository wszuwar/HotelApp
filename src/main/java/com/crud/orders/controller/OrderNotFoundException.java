package com.crud.orders.controller;

public class OrderNotFoundException  extends Exception{
    public OrderNotFoundException(){
        super("Id Not Found");
    }

}
