package org.mesonet.dataprocessing.advisories

import io.reactivex.Observable
import org.mesonet.models.advisories.Advisory

interface AdvisoryDisplayListBuilder
{
    enum class AdvisoryDataType {
        kHeader, kContent
    }

    fun BuildList(inOriginalList: MutableList<Advisory>?): Observable<ArrayList<Pair<AdvisoryDataType, AdvisoryData>>>

    interface AdvisoryData {
        fun AdvisoryType(): String
        fun Counties(): String
        fun Url(): String?
    }
}