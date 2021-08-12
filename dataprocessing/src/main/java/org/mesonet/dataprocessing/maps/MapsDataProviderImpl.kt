package org.mesonet.dataprocessing.maps

import android.content.Context
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import org.mesonet.core.PerContext
import org.mesonet.dataprocessing.ConnectivityStatusProvider
import org.mesonet.dataprocessing.PageStateInfo
import org.mesonet.models.maps.MapsList
import org.mesonet.network.DataProvider
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.collections.LinkedHashMap


@PerContext
class MapsDataProviderImpl @Inject constructor(internal var mDataProvider: DataProvider,
                                               internal var mConnectivityStatusProvider: ConnectivityStatusProvider): MapsDataProvider
{
    private var mLastUpdate = 0L

    private var mMapsSubject: BehaviorSubject<MapsList> = BehaviorSubject.create()
    private var mPageStateSubject: BehaviorSubject<PageStateInfo> = BehaviorSubject.create()

    private val mConnectivityObservable = mConnectivityStatusProvider.ConnectivityStatusObservable()

    override fun OnCreate(inContext: Context) {}

    override fun OnResume(inContext: Context) {

        if(!mMapsSubject.hasValue() || mLastUpdate == 0L || (mLastUpdate - Date().time) > 600000) {
            mConnectivityObservable.observeOn(Schedulers.computation()).subscribe(object : Observer<Boolean> {
                var disposable: Disposable? = null
                override fun onComplete() {}

                override fun onSubscribe(d: Disposable) {
                    disposable = d
                }

                override fun onNext(t: Boolean) {
                    if(mMapsSubject.hasValue())
                    {
                        mPageStateSubject.onNext(object: PageStateInfo{
                            override fun GetPageState(): PageStateInfo.PageState {
                                return PageStateInfo.PageState.kData
                            }

                            override fun GetErrorMessage(): String? {
                                return ""
                            }

                        })
                    }
                    else if(t)
                    {
                        mPageStateSubject.onNext(object: PageStateInfo{
                            override fun GetPageState(): PageStateInfo.PageState {
                                return PageStateInfo.PageState.kLoading
                            }

                            override fun GetErrorMessage(): String? {
                                return ""
                            }

                        })
                    }
                    else
                    {
                        mPageStateSubject.onNext(object: PageStateInfo{
                            override fun GetPageState(): PageStateInfo.PageState {
                                return PageStateInfo.PageState.kError
                            }

                            override fun GetErrorMessage(): String? {
                                return "No Connection"
                            }

                        })
                    }
                    disposable?.dispose()
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }
            })
        }

        if(mLastUpdate == 0L || (mLastUpdate - Date().time) > 600000)
        {
            mDataProvider.GetMaps().retryWhen { mConnectivityObservable }.observeOn(Schedulers.computation()).subscribe(object: Observer<MapsList>{
                var disposable: Disposable? = null
                override fun onComplete() {}
                override fun onSubscribe(d: Disposable)
                {
                    disposable = d
                }

                override fun onNext(t: MapsList) {
                    mLastUpdate = Date().time
                    mMapsSubject.onNext(FilterMapsList(t)?: object: MapsList{
                        override fun GetMain(): List<MapsList.Group>? {
                            return null
                        }

                        override fun GetSections(): LinkedHashMap<String, MapsList.GroupSection> {
                            return LinkedHashMap()
                        }

                        override fun GetProducts(): LinkedHashMap<String, MapsList.Product> {
                            return LinkedHashMap()
                        }
                    })

                    mPageStateSubject.onNext(object: PageStateInfo{
                        override fun GetPageState(): PageStateInfo.PageState {
                            return PageStateInfo.PageState.kData
                        }

                        override fun GetErrorMessage(): String? {
                            return ""
                        }

                    })

                    disposable?.dispose()
                    disposable = null
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }
            })
        }
    }

    override fun OnPause() {}
    override fun OnDestroy() {
        mMapsSubject.onComplete()
    }



    override fun GetMapsListObservable(): Observable<MapsList?>
    {
        return mMapsSubject
    }


    override fun GetPageStateObservable(): Observable<PageStateInfo>
    {
        return mPageStateSubject
    }


    internal fun FilterMapsList(inMapsList: MapsList?): MapsList?
    {
        inMapsList?.let {mapList ->
            mapList.GetMain()?.let {groups ->
                val filteredProducts = LinkedHashMap<String, MapsList.Product>()
                val filteredSections = LinkedHashMap<String, MapsList.GroupSection>()
                val filteredGroups = ArrayList<MapsList.Group>()

                filteredProducts.putAll(mapList.GetProducts().filter { !it.value.GetUrl().isNullOrBlank() })
                filteredSections.putAll(mapList.GetSections().filter { section -> section.value.GetProducts().any { filteredProducts.containsKey(it) } })
                filteredGroups.addAll(groups.filter { group -> group.GetSections().any { sectionName -> filteredSections.containsKey(sectionName) } })

                return object: MapsList {
                    override fun GetMain(): List<MapsList.Group>? {
                        return filteredGroups
                    }

                    override fun GetSections(): LinkedHashMap<String, MapsList.GroupSection> {
                        return filteredSections
                    }

                    override fun GetProducts(): LinkedHashMap<String, MapsList.Product> {
                        return filteredProducts
                    }

                }
            }
        }

        return null
    }



    override fun GetSections(inSectionIds: List<String>): Observable<LinkedHashMap<String, MapsList.GroupSection>> {
        return mMapsSubject.map { mapsList -> SortMapValues(mapsList.GetSections(), inSectionIds) }
    }


    override fun GetProducts(inProductIds: List<String>): Observable<LinkedHashMap<String, MapsList.Product>> {
        return mMapsSubject.map { mapsList -> SortMapValues(mapsList.GetProducts(), inProductIds) }
    }

    private fun <T> SortMapValues(inMap: HashMap<String, T>, inKeys: List<String>): LinkedHashMap<String, T> {
        val result: LinkedHashMap<String, T> = linkedMapOf()

        inKeys.forEach{key ->
            inMap[key]?.let {
                result[key] = it
            }
        }

        return result
    }
}

