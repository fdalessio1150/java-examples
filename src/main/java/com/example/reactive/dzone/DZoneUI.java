package com.example.reactive.dzone;

import rx.schedulers.Schedulers;
import rx.Observable;

public class DZoneUI {
	
	private DZoneSearchService dzoneService = new DZoneSearchService();

	public Observable<String> printAllDocs() {
		// dzoneService.getAllDocs().subscribe(System.out::println);
		return dzoneService.getAllDocs()
				.map(doc -> doc.getName());
	}
	
    public void printJavaRefCardz() {
    	dzoneService.getAllDocs()
    		.filter(docs -> "Refcardz".equalsIgnoreCase(docs.getType()))
    	//	.filter(doc -> doc.getName().contains("Java"))
    		.subscribe(System.out::println);
    }

	public static void main(String[] args) {
		DZoneUI UI = new DZoneUI();
		UI.printAllDocs().subscribe(System.out::println);
		UI.printJavaRefCardz();
	}
}