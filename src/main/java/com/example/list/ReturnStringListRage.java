package com.example.list;

import java.util.ArrayList;
import java.util.List;
import org.joda.time.LocalDate;
import org.joda.time.Period;

import io.reactivex.Observable;

public class ReturnStringListRage {

	public static void main(String[] args) {
		String fromDate = "2018-01-01";
		String toDate = "2018-05-01";
		getBucketDate(fromDate, toDate).stream().forEach(a -> System.out.println(a));
		
		Long startTime = System.nanoTime();
		Observable<Integer> a = getBucketDate2().replay().autoConnect();
		a.subscribe(
				//d -> System.out.println(d)
				);
		a.subscribe(
				//d -> System.out.println(d)
				);
		a.subscribe(
				//d -> System.out.println(d)
				);
		a.subscribe(
				//d -> System.out.println(d)
				);
		a.subscribe(
				//d -> System.out.println(d)
				);
		a.subscribe(
				//d -> System.out.println(d)
				);
		System.out.println("Observable: " + (System.nanoTime() - startTime));
		
		Long startTime2 = System.nanoTime();
		List<Integer> list = getBucketDate3();
		for (int cont = 0; cont < list.size(); cont++) {
			//System.out.println(list.get(cont));
		}
		System.out.println("Forzao maroto: " + (System.nanoTime() - startTime2));
		
	}

	// Se alterar para 4 milhoes o observable fica mais rapido
	private static List<Integer> getBucketDate3() {
		List<Integer> num = new ArrayList<>();
		for (int i = 0; i < 3000000; i++) {
			num.add(i);
		}
		return num;
	}
	
	// Se alterar para 4 milhoes o observable fica mais rapido
	private static Observable<Integer> getBucketDate2() {
		return Observable.range(1, 3000000).filter(c -> c > 10);
	}

	public static List<String> getBucketDate(String fromDate, String toDate) {
		List<String> bucketDate = new ArrayList<>();
		LocalDate date1 = new LocalDate(fromDate);
		LocalDate date2 = new LocalDate(toDate);
		
		Boolean first = true;
		
		while (date1.isBefore(date2)) {
			if (first == true) {
				bucketDate.add(date1.toString("yyyy-MM"));
				first = false;
			}
			date1 = date1.plus(Period.months(1));
			bucketDate.add(date1.toString("yyyy-MM"));
		}
		
		return bucketDate;
	}

}
