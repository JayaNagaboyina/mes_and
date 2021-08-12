package org.mesonet.cache.site

import android.content.Context
import io.reactivex.*
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.mesonet.cache.Cacher
import org.mesonet.core.PerContext
import org.mesonet.models.site.MesonetSiteList

import javax.inject.Inject


@PerContext
class SiteCacheImpl @Inject
constructor(): SiteCache {
    @Inject
    internal lateinit var mCacher: Cacher

    @Inject
    internal lateinit var mConverter: SiteListConverter


    override fun SaveSites(inContext: Context, inMesonetSites: Map<String, MesonetSiteList.MesonetSite>) {
        Single.create(SingleOnSubscribe<List<SiteRealmModel>> {
            it.onSuccess(mConverter.SitesToRealmModels(inMesonetSites))
        }).subscribeOn(Schedulers.computation()).flatMap { mCacher.SaveToCache(inContext, SiteRealmModel::class.java, it) }.subscribe(object: SingleObserver<Int>{
            override fun onSuccess(t: Int) {}
            override fun onSubscribe(d: Disposable) {}
            override fun onError(e: Throwable) {
                e.printStackTrace()
            }

        })
    }


    override fun GetSites(inContext: Context): Single<MesonetSiteList> {
        return mCacher.FindAll(inContext, SiteRealmModel::class.java).map{
            mConverter.SitesFromRealmModels(it)
        }
    }


    override fun SaveFavorites(inContext: Context, inFavorites: List<String>){
        Single.create(SingleOnSubscribe<List<FavoriteSiteRealmModel>> {
            it.onSuccess(mConverter.FavoritesToRealmModels(inFavorites))
        }).flatMap {
            mCacher.SaveToCache(inContext, FavoriteSiteRealmModel::class.java, it)
        }.subscribeOn(Schedulers.computation()).subscribe(object: SingleObserver<Int> {
            override fun onSubscribe(d: Disposable) {}
            override fun onSuccess(t: Int) {}
            override fun onError(e: Throwable) {
                e.printStackTrace()
            }
        })
    }


    override fun GetFavorites(inContext: Context): Single<MutableList<String>> {
        return mCacher.FindAll(inContext, FavoriteSiteRealmModel::class.java).map {
            mConverter.FavoriteFromRealmModels(it)
        }
    }
}