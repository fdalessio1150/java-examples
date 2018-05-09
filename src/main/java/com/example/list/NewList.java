package com.example.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.example.list.Car;

public class NewList {

	public static void main(String[] args) {
		// Essa inst�ncia deveria ser criada fora do main, e utilizando o autowired do spring, como uma bean
		NewList a = new NewList();
		a.combineLists()
			.stream()
			.forEach(car -> System.out.println(car.getValue()));
		
	}

	public List<Car> combineLists() {
		// Recebe os items de uma requisi��o 
		List<Car> lista1 = new ArrayList<>();
		
		Car civic = new Car.Builder()
				.withName("civic")
				.withColor("branco")
				.withValue(89999.99)
				.withItems(Arrays.asList("ar-condicionado digital", "banco de couro", "dire��o el�trica"))
				.build();
		
		Car fit = new Car.Builder()
				.withName("fit")
				.withColor("vermelho")
				.withValue(59999.99)
				.withItems(Arrays.asList("ar-condicionado manual", "dire��o el�trica", "vidros el�tricos"))
				.build();

		lista1.add(civic);
		lista1.add(fit);
		
		// Come�a a tratar para retornar
		return lista1.stream()
				.map(a -> discount(a))
				.collect(Collectors.toList());
	}
	
	public Car discount(Car car) {
		car.discount();
		return car;
	}

}
