package org.mesonet.core

import android.os.HandlerThread
import android.os.Looper
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.runners.MockitoJUnitRunner
import org.powermock.api.mockito.PowerMockito
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows.shadowOf
import org.robolectric.shadows.ShadowLooper
import java.util.concurrent.CountDownLatch


@RunWith(RobolectricTestRunner::class)
@PrepareForTest(Looper::class, HandlerThread::class)
// Robolectric implementation of Looper does not truly allow multithreading. Not all logical tests are implemented here
class ThreadHandlerTests
{
    @Test(timeout = 10000)
    fun RunSpecificThreadTests()
    {
        val latch = CountDownLatch(2)

        val threadHandler = ThreadHandler()

        val thread = Thread()
        thread.run( {
            Looper.prepare()

            threadHandler.RunOnSpecificThread("SpecificThread",
                    Runnable {
                        latch.countDown()
                    },
                    Runnable {
                        latch.countDown()
                    })

            assertNotNull(threadHandler.mThreads["SpecificThread"])
            shadowOf(threadHandler.mThreads["SpecificThread"]?.first.looper).runToEndOfTasks()

            latch.await()
            threadHandler.CloseThreads()
        })

        latch.await()

        assertEquals(threadHandler.mThreads.size, 1)
        assertNotNull(threadHandler.mThreads["SpecificThread"])
    }



    @Test(timeout = 10000)
    fun RunEmptyNameThread()
    {
        val latch = CountDownLatch(2)

        val threadHandler = ThreadHandler()

        val thread = Thread()
        thread.run( {
            Looper.prepare()

            threadHandler.RunOnSpecificThread("",
                    Runnable {
                        latch.countDown()
                    },
                    Runnable {
                        latch.countDown()
                    })

            assertNotNull(threadHandler.mThreads[""])
            shadowOf(threadHandler.mThreads[""]?.first.looper).runToEndOfTasks()

            latch.await()
            threadHandler.CloseThreads()
        })

        latch.await()

        assertEquals(threadHandler.mThreads.size, 1)
        assertNotNull(threadHandler.mThreads[""])
    }


    @Test(timeout = 10000)
    fun NullRunnableThread()
    {
        val latch = CountDownLatch(1)

        val threadHandler = ThreadHandler()

        val thread = Thread()
        thread.run( {
            Looper.prepare()

            threadHandler.RunOnSpecificThread("SpecificThread",
                    Runnable {
                        latch.countDown()
                    },
                    null)

            shadowOf(threadHandler.mThreads["SpecificThread"]?.first.looper).runToEndOfTasks()

            latch.await()
            threadHandler.CloseThreads()
        })

        latch.await()
        assertEquals(threadHandler.mThreads.size, 1)
        assertNotNull(threadHandler.mThreads["SpecificThread"])
    }


    @Test(timeout = 10000)
    fun EmptyDerivedThreadNameTests()
    {
        val latch = CountDownLatch(2)
        val threadHandler = ThreadHandler()

        val thread = Thread()
        thread.run({
            Looper.prepare()

            threadHandler.Run("", Runnable {
                latch.countDown()
            }, Runnable {
                latch.countDown()
            })

            shadowOf(threadHandler.mThreads["0"]?.first.looper).runToEndOfTasks()

            latch.await()
            threadHandler.CloseThreads()
        })

        latch.await()

        assertEquals(threadHandler.mThreads.size, 1)
        assertNotNull(threadHandler.mThreads["0"])
    }


    @Test(timeout = 10000)
    fun DerivedThreadNameTests()
    {
        val latch = CountDownLatch(2)
        val threadHandler = ThreadHandler()

        val thread = Thread()
        thread.run({
            Looper.prepare()

            threadHandler.Run("GenericThreadName", Runnable {
                latch.countDown()
            }, Runnable {
                latch.countDown()
            })

            shadowOf(threadHandler.mThreads["GenericThreadName0"]?.first.looper).runToEndOfTasks()

            latch.await()
            threadHandler.CloseThreads()
        })

        latch.await()

        assertEquals(threadHandler.mThreads.size, 1)
        assertNotNull(threadHandler.mThreads["GenericThreadName0"])
    }
}