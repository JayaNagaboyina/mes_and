package org.mesonet.app.radar

import android.content.Context
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.util.AttributeSet
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import org.mesonet.app.R


class PlayPauseButton(mContext: Context, inAttrs: AttributeSet) : FloatingActionButton(mContext, inAttrs), Observer<Boolean> {

    var mPlayPauseDisposable: Disposable? = null
    internal fun SetPlayPauseState(inPlayPauseState: Observable<Boolean>) {
        mPlayPauseDisposable?.dispose()
        inPlayPauseState.observeOn(AndroidSchedulers.mainThread())?.subscribe(this)
    }


    override fun onComplete() {}
    override fun onSubscribe(d: Disposable)
    {
        mPlayPauseDisposable = d
    }

    override fun onError(e: Throwable) {}

    override fun onNext(t: Boolean) {
        if (t)
            setImageResource(R.drawable.ic_pause_white_36dp)
        else
            setImageResource(R.drawable.ic_play_arrow_white_36dp)
    }

    fun Dispose()
    {
        mPlayPauseDisposable?.dispose()
    }
}