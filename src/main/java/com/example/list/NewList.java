package com.example.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.list.Car;
import com.google.common.collect.Collections2;

import rx.Observable;

public class NewList {

	public static void main(String[] args) {
		// Essa instancia deveria ser criada fora do main, e utilizando o autowired do spring, como uma bean
		NewList a = new NewList();
		a.combineLists().stream().forEach(car -> System.out.println(car.getValue()));
	}

	public List<Car> combineLists() {
		// Recebe os items de uma requisicao 
		List<Car> lista1 = new ArrayList<>();
		List<Car> lista2 = new ArrayList<>();
						
		Car civic = new Car.Builder()
				.withName("civic")
				.withColor("branco")
				.withValue(89999.99)
				.withItems(Arrays.asList("ar-condicionado digital", "banco de couro", "direcao eletrica"))
				.build();
		
		Car fit = new Car.Builder()
				.withName("fit")
				.withColor("vermelho")
				.withValue(59999.99)
				.withItems(Arrays.asList("ar-condicionado manual", "direcao eletrica", "vidros eletricos"))
				.build();
		
		Car hrv = new Car.Builder()
				.withName("hrv")
				.withColor("vermelho")
				.withValue(109999.99)
				.withItems(Arrays.asList("ar-condicionado digital", "suv"))
				.build();
		
		lista1.add(civic);
		lista1.add(fit);
		lista1.add(hrv);
		lista2.add(civic);
		lista2.add(fit);
		
		// Retorno de após consultar banco de dados
		List<Vehicle> lista3 = new ArrayList<>();
		
		Vehicle fitBD = new Vehicle.Builder()
				.withName("fit")
				.withColor("vermelho")
				.withValue(59999.99)
				.withItems(Arrays.asList("ar-condicionado manual", "direcao eletrica", "vidros eletricos"))
				.build();

		lista3.add(fitBD);
		
		// Pode ser utilizado quando retornar uma lista de objetos de um banco de dados e precisar criar uma lista contendo o que nao foi encontrado
		Observable.from(getListDifference(lista1, lista3)).subscribe(a -> System.out.println(a.getName()));
		 
		// Comecar a tratar para retornar
		return lista1.parallelStream()
				.map(car -> getDiscount(car))
				.collect(Collectors.toList());
	}
	
	private List<Car> getListDifference(List<Car> lista1, List<Vehicle> lista3) {
		List<Car> lista4 = new ArrayList<>(lista1);

		for(Iterator<Vehicle> i = lista3.iterator(); i.hasNext();) {
			String name = i.next().getName();
			lista4.removeIf(a -> a.getName().contains(name));
		}
		return lista4;
	}

	public Car getDiscount(Car car) {
		car.discount();
		return car;
	}
	
}
