package org.mesonet.dataprocessing.filterlist

import io.reactivex.Observable
import io.reactivex.Single
import org.mesonet.dataprocessing.BasicListData

interface FilterListDataProvider {
    fun CurrentSelection(): String
    fun AsBasicListData(): Observable<Pair<Map<String, BasicListData>, String>>
}