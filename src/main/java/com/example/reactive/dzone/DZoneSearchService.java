package com.example.reactive.dzone;

import rx.Observable;

public class DZoneSearchService {

	Observable<DZoneDoc> getAllDocs() {
		return Observable.from(DZoneDBDao.get().getAllDocFromDB());
	}

}