package com.example.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.list.Car;
import com.google.common.collect.Collections2;

public class NewList {

	public static void main(String[] args) {
		// Essa instancia deveria ser criada fora do main, e utilizando o autowired do spring, como uma bean
		NewList a = new NewList();
		a.combineLists()
			.stream()
			.forEach(car -> System.out.println(car.getValue()));
		
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
				.withName("fit")
				.withColor("vermelho")
				.withValue(109999.99)
				.withItems(Arrays.asList("ar-condicionado digital", "suv"))
				.build();

		lista1.add(civic);
		lista1.add(fit);
		lista1.add(hrv);
		
		lista2.add(civic);
		lista2.add(fit);
		
		
		System.out.println();
		// Comeca a tratar para retornar
		return lista1.stream()
				.map(a -> discount(a))
				.collect(Collectors.toList());
	}
	
	public Car discount(Car car) {
		car.discount();
		return car;
	}

}
