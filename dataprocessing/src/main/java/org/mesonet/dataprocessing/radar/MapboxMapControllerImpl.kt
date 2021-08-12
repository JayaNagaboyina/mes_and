package org.mesonet.dataprocessing.radar

import com.mapbox.mapboxsdk.camera.CameraPosition
import com.mapbox.mapboxsdk.geometry.LatLng
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.Observables
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import org.mesonet.core.PerFragment
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicBoolean

import javax.inject.Inject



@PerFragment
class MapboxMapControllerImpl @Inject constructor(): MapboxMapController
{
    private var mSelectedImageIndexSubject: BehaviorSubject<Int> = BehaviorSubject.create()

    private var mPlayPauseTimerDisposable: Disposable? = null

    private var mCameraSubject: BehaviorSubject<CameraPosition> = BehaviorSubject.create()

    private var mPlaySubject: BehaviorSubject<Boolean> = BehaviorSubject.create()

    private var mFrameCount = 0

    private var mTransparencySubject: BehaviorSubject<Float> = BehaviorSubject.create()


    init {
        mPlaySubject.onNext(false)
        mTransparencySubject.onNext(0.7f)
        mSelectedImageIndexSubject.onNext(0)

        Observables.combineLatest(Observable.interval(0, 1, TimeUnit.SECONDS),
                mPlaySubject)
        {
            tick, play ->

            Pair(tick, play)
        }.observeOn(Schedulers.computation()).subscribe (object: Observer<Pair<Long, Boolean>>{
            override fun onComplete() {}
            override fun onSubscribe(d: Disposable) {
                mPlayPauseTimerDisposable = d
            }

            override fun onNext(t: Pair<Long, Boolean>) {
                synchronized(mSelectedImageIndexSubject)
                {
                    if(t.second) {
                        var selectIndex = 0

                        if (mSelectedImageIndexSubject.hasValue())
                            selectIndex = mSelectedImageIndexSubject.value ?: 0

                        selectIndex--

                        if (selectIndex < 0) {
                            selectIndex = mFrameCount - 1
                        }

                        if (selectIndex >= mFrameCount)
                            selectIndex = 0

                        mSelectedImageIndexSubject.onNext(selectIndex)
                    }
                }
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
                mPlaySubject.onNext(false)
            }

        })
    }


    override fun SetCameraPosition(inLat: Double, inLon: Double, inZoom: Double) {
        mCameraSubject.onNext(CameraPosition.Builder()
                .target(LatLng(inLat, inLon))
                .zoom(inZoom)
                .build())
    }


    override fun GetCameraPositionObservable(): Observable<CameraPosition>
    {
        return mCameraSubject
    }



    override fun SetFrameCount(inFrameCount: Int)
    {
        mFrameCount = inFrameCount
    }



    override fun GetActiveImageIndexObservable(): Observable<Int>
    {
        return mSelectedImageIndexSubject
    }


    override fun GetPlayPauseStateObservable(): Observable<Boolean>
    {
        return mPlaySubject
    }


    override fun TogglePlay()
    {
        mPlaySubject.onNext(mPlaySubject.value != true)
    }



    override fun GetTransparencySubject(): BehaviorSubject<Float>
    {
        return mTransparencySubject
    }


    override fun Dispose()
    {
        mPlayPauseTimerDisposable?.dispose()
        mPlayPauseTimerDisposable = null
        mSelectedImageIndexSubject.onComplete()
        mTransparencySubject.onComplete()
        mCameraSubject.onComplete()
    }
}