package com.example.reactive.dzone;

public class DZoneDBDao {

    private static DZoneDBDao service = new DZoneDBDao();
    
    public static DZoneDBDao get() {
        return service;
    }
    
    public DZoneDoc[] getAllDocFromDB() {
        return produceDocs();
    }
    
    private DZoneDoc[] produceDocs() {
        DZoneDoc[] array = {
            DZoneDoc.create("Java Microservice", "Refcardz"),
            DZoneDoc.create("RX Java", "Article"),
            DZoneDoc.create("IOT in Action", "Refcardz"),
            DZoneDoc.create("Java8 in Action", "Refcardz"),
        };
        return array;
    }
    
}
