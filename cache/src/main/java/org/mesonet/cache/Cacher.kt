package org.mesonet.cache

import android.content.Context
import io.reactivex.Observable
import io.reactivex.Single
import io.realm.RealmModel
import io.realm.RealmResults

interface Cacher {
    fun <T: RealmModel> SaveToCache(inContext: Context, inClass: Class<T>, inList: List<T>): Single<Int>
    fun <T : RealmModel> FindAll(inContext: Context, inClass: Class<T>): Single<RealmResults<T>>
}