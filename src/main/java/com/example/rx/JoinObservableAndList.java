package com.example.rx;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.list.Car;

import rx.Observable;

public class JoinObservableAndList {
	
	public static void main(String[] args) {

		// Lista de entradas 
		List<Car> requestList = new ArrayList<>();
		
		Car civic = new Car.Builder()
				.withName("civic")
				.withColor("branco")
				.withValue(89999.99)
				.withItems(Arrays.asList("ar-condicionado digital", "banco de couro", "direcao eletrica"))
				.build();
		
		Car hrv = new Car.Builder()
				.withName("hrv")
				.withColor("branco")
				.withValue(109999.99)
				.withItems(Arrays.asList("ar-condicionado digital", "banco de couro", "direcao eletrica"))
				.build();
		
		Car wrv = new Car.Builder()
				.withName("wrv")
				.withColor("branco")
				.withValue(79999.99)
				.withItems(Arrays.asList("ar-condicionado digital", "banco de couro", "direcao eletrica"))
				.build();
		
		Car crv = new Car.Builder()
				.withName("crv")
				.withColor("branco")
				.withValue(139999.99)
				.withItems(Arrays.asList("ar-condicionado digital", "banco de couro", "direcao eletrica"))
				.build();
		
		requestList.add(civic);
		requestList.add(hrv);
		requestList.add(wrv);
		requestList.add(crv);		
		
		// Seria o retorno do banco em um objeto distinto, geralmente e um ResultSetFuture, o Observable possui integracao
		List<Vehicle> databaseList = new ArrayList<>();
				
		Vehicle civicBd = new Vehicle.Builder()
						.withName("civic")
						.withColor("branco")
						.withValue(9.00)
						.withBrand("honda")
						.withItems(Arrays.asList("ar-condicionado digital", "banco de couro", "direcao eletrica"))
						.build();
				
		Vehicle fitBd = new Vehicle.Builder()
						.withName("fit")
						.withColor("branco")
						.withValue(6.00)
						.withBrand("honda")
						.withItems(Arrays.asList("ar-condicionado digital", "banco de couro", "direcao eletrica"))
						.build();
				
		Vehicle hrvBd = new Vehicle.Builder()
						.withName("hrv")
						.withColor("branco")
						.withValue(5.00)
						.withBrand("honda")
						.withItems(Arrays.asList("ar-condicionado digital", "banco de couro", "direcao eletrica"))
						.build();
			
		// Se comentar o civicBd, quer dizer que nao encontrou no BD e portanto deve cair no switchIfEmpty
		databaseList.add(civicBd);
		
		// Exemplo caso o banco retorne mais dados que estamos requisitando
		databaseList.add(fitBd);
		databaseList.add(hrvBd);
				
		// Lista para Observable e em seguida como gravar em uma nova lista
		Observable<Vehicle> memoryList = Observable.from(databaseList);
		
		// Tempo nao esta condizendo, simples teste na chamada de 2 metodos
		Long initialTime2 = System.nanoTime();		
			Observable.from(requestList)
				.flatMap(car -> nothing(car));
		Long differenceTime2 = System.nanoTime() - initialTime2;
		System.out.println("Metodo mais simples: " + differenceTime2 + "\n");
		
		// Inves do Observable responsavel pelo processamento individual das requisicoes ir no banco N vezes, ele busca N vezes na lista em memória que foi o retorno o BD
		Long initialTime = System.nanoTime();
			Observable.from(requestList)
				.flatMap(request -> process(request, memoryList))
				.subscribe(a -> System.out.println(a.getName() + " " + a.getValue()));
		Long differenceTime = System.nanoTime() - initialTime;
		System.out.println("\nMetodo que varre a lista: " + differenceTime);
	}
	
	private static Observable<Car> nothing(Car car) {
		return Observable.just(car);
	}
	
		
	private static Observable<Vehicle> process(Car request, Observable<Vehicle> memoryList) {
		return findInMemory(request, memoryList.replay().autoConnect())
			.switchIfEmpty(create(request));
	}
	
	private static Observable<Vehicle> findInMemory(Car car, Observable<Vehicle> memoryList) {
		return memoryList.filter(v -> v.getName().contains(car.getName()) == true);
	}
	
	private static Observable<Vehicle> create(Car car) {
		System.out.println("entrei");
		Vehicle a = new Vehicle.Builder()
				.withName(car.getName())
				.withColor(car.getColor())
				.withValue(car.getValue())
				.withBrand("honda")
				.withItems(Arrays.asList("ar-condicionado digital", "banco de couro", "direcao eletrica"))
				.build();
				
		return Observable.just(a);
	}
	
}
