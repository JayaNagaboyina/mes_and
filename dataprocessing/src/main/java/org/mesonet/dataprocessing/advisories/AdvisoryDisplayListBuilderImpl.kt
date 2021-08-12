package org.mesonet.dataprocessing.advisories

import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.schedulers.Schedulers
import org.mesonet.core.PerContext
import org.mesonet.models.advisories.Advisory
import org.mesonet.models.advisories.AdvisoryModel
import org.mesonet.network.DataProvider
import javax.inject.Inject


@PerContext
class AdvisoryDisplayListBuilderImpl @Inject constructor(): AdvisoryDisplayListBuilder {
    @Inject
    lateinit var mDataProvider: DataProvider

    override fun BuildList(inOriginalList: MutableList<Advisory>?): Observable<ArrayList<Pair<AdvisoryDisplayListBuilder.AdvisoryDataType, AdvisoryDisplayListBuilder.AdvisoryData>>> {
        return Observable.create (ObservableOnSubscribe<ArrayList<Pair<AdvisoryDisplayListBuilder.AdvisoryDataType, AdvisoryDisplayListBuilder.AdvisoryData>>>{
            val endResult = java.util.ArrayList<Pair<AdvisoryDisplayListBuilder.AdvisoryDataType, AdvisoryDisplayListBuilder.AdvisoryData>>()

            var advisories = inOriginalList

            if (advisories != null) {
                var currentLevel: AdvisoryModel.AdvisoryLevel? = null
                var currentType: AdvisoryModel.AdvisoryType? = null

                advisories = Sort(advisories)

                if (advisories != null) {
                    for (i in advisories.indices) {
                        var advisoryType = ""

                        if (advisories[i].GetType() != null)
                            advisoryType = advisories[i].GetType().toString()

                        if (advisories[i].GetLevel() != null) {
                            if (!advisoryType.isEmpty())
                                advisoryType += " "

                            advisoryType += advisories[i].GetLevel()
                        }

                        if (advisoryType.isEmpty())
                            advisoryType = "Other"

                        var counties = ""
                        var url: String? = null

                        if (advisories[i].GetFilePath() != null)
                            url = mDataProvider.GetAdvisoryUrl(advisories[i].GetFilePath()?: "")
                        if (advisories[i].GetCounties() != null)
                            counties = MakeCountyListString(advisories[i].GetCounties()?: ArrayList())
                        if (counties.isEmpty())
                            counties = "Unknown Location"
                        if (advisories[i].GetLevel() != currentLevel || advisories[i].GetType() != currentType) {
                            endResult.add(Pair(AdvisoryDisplayListBuilder.AdvisoryDataType.kHeader, AdvisoryDataImpl(advisoryType, "", url)))

                            currentLevel = advisories[i].GetLevel()
                            currentType = advisories[i].GetType()
                        }

                        endResult.add(Pair(AdvisoryDisplayListBuilder.AdvisoryDataType.kContent, AdvisoryDataImpl(advisoryType, counties, url)))
                    }
                }
            }

            it.onNext(endResult)
        }).subscribeOn(Schedulers.computation())
    }


    internal fun Sort(inOriginal: MutableList<Advisory>?): ArrayList<Advisory>? {
        if (inOriginal == null)
            return null

        val result = java.util.ArrayList(inOriginal)

        result.sortWith(Comparator { o1, o2 ->
            var sortResult = 0

            if (o1.GetLevel() == null && o2.GetLevel() != null)
                sortResult = 1

            if (sortResult == 0 && o2.GetLevel() == null && o1.GetLevel() != null)
                sortResult = -1

            if (sortResult == 0 && o2.GetLevel() != null)
                sortResult = o2?.GetLevel()?.CompareTo(o1?.GetLevel())?: 1

            if (sortResult == 0 && o1.GetType() == null && o2.GetType() != null)
                sortResult = 1

            if (sortResult == 0 && o2.GetType() == null && o1.GetType() != null)
                sortResult = -1

            if (sortResult == 0 && o2.GetType() != null)
                sortResult = o2?.GetType()?.CompareTo(o1?.GetType())?: 1

            sortResult
        })

        return result
    }


    internal fun MakeCountyListString(inCounties: List<String>): String {
        val result = StringBuilder()

        for (i in inCounties.indices) {

            var countyName = inCounties[i]

            try {
                countyName = AdvisoryModel.County.valueOf(countyName).toString()
            } catch (e: IllegalArgumentException) {

            }

            if (!countyName.isEmpty()) {
                if (i > 0)
                    result.append(", ")

                result.append(countyName)
            }
        }

        return result.toString()
    }

    class AdvisoryDataImpl(val mType: String, val mCounties: String, val mUrl: String?): AdvisoryDisplayListBuilder.AdvisoryData
    {
        override fun AdvisoryType(): String {
            return mType
        }

        override fun Counties(): String {
            return mCounties
        }

        override fun Url(): String? {
            return mUrl
        }

    }
}