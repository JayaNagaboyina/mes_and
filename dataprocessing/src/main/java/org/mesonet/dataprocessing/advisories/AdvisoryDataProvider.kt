package org.mesonet.dataprocessing.advisories

import io.reactivex.Observable
import org.mesonet.dataprocessing.LifecycleListener
import org.mesonet.dataprocessing.PageStateInfo
import org.mesonet.models.advisories.Advisory

interface AdvisoryDataProvider: LifecycleListener
{
    fun GetDataObservable(): Observable<Advisory.AdvisoryList>
    fun GetPageStateObservable(): Observable<PageStateInfo>
}