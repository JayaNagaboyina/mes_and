package org.mesonet.cache.site


import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class FavoriteSiteRealmModel : RealmObject {
    @PrimaryKey
    private var mStid: String? = null


    fun GetName(): String? {
        return mStid
    }


    constructor() {}


    constructor(inStid: String) {
        mStid = inStid
    }
}
