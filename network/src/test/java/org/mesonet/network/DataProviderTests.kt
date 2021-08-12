
package org.mesonet.network

import android.os.Looper
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertNull
import org.junit.Test
import org.junit.runner.RunWith
import org.powermock.core.classloader.annotations.PrepareForTest
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows.shadowOf
import java.net.HttpURLConnection
import java.util.concurrent.CountDownLatch

@RunWith(RobolectricTestRunner::class)
@PrepareForTest(Looper::class)
class DataProviderTests
{
    @Test(timeout = 10000)
    fun DownloadEmptyUrlTests()
    {
        val latch = CountDownLatch(1)
        val downloader = DataProvider(ThreadHandler())
        val downloadInfo = DataProvider.DownloadInfo("",-1)
        val result = downloader.Download(downloadInfo, object: DataProvider.DownloadCallback{
            override fun DownloadComplete(inResponseCode: Int, inResult: String?) {
                throw Exception("Should have failed")
            }

            override fun DownloadFailed() {
                latch.countDown()
            }

        })

        latch.await()

        assertNotNull(result)
        assert(!(result.mResponseCode >= HttpURLConnection.HTTP_OK && result.mResponseCode < HttpURLConnection.HTTP_MULT_CHOICE))
        assertNull(result.mResponse)
        assert(result.mFailed)

        downloader.mThreadHandler.CloseThreads()
    }



    @Test(timeout = 10000)
    fun DownloadBadFormatUrlTests()
    {
        val latch = CountDownLatch(1)
        val downloader = DataProvider(ThreadHandler())
        val downloadInfo = DataProvider.DownloadInfo("bad format bad format",-1)
        val result = downloader.Download(downloadInfo, object: DataProvider.DownloadCallback{
            override fun DownloadComplete(inResponseCode: Int, inResult: String?) {
                throw Exception("Should have Failed")
            }

            override fun DownloadFailed() {
                latch.countDown()
            }

        })

        latch.await()

        assertNotNull(result)
        assert(!(result.mResponseCode >= HttpURLConnection.HTTP_OK && result.mResponseCode < HttpURLConnection.HTTP_MULT_CHOICE))
        assertNull(result.mResponse)
        assert(result.mFailed)

        downloader.mThreadHandler.CloseThreads()
    }


    @Test(timeout = 40000)
    fun DownloadInvalidUrlTests()
    {
        val latch = CountDownLatch(1)
        val downloader = DataProvider(ThreadHandler())
        val downloadInfo = DataProvider.DownloadInfo("https://www.bekahtesting.com/",-1)
        val result = downloader.Download(downloadInfo, object: DataProvider.DownloadCallback{
            override fun DownloadComplete(inResponseCode: Int, inResult: String?) {
                throw Exception("Should have failed")
            }

            override fun DownloadFailed() {
                latch.countDown()
            }
        })

        latch.await()

        assertNotNull(result)
        assert(!(result.mResponseCode >= HttpURLConnection.HTTP_OK && result.mResponseCode < HttpURLConnection.HTTP_MULT_CHOICE))
        assertNull(result.mResponse)
        assert(result.mFailed)

        downloader.mThreadHandler.CloseThreads()
    }



    @Test(timeout = 10000)
    fun DownloadGoodUrlTests()
    {
        val latch = CountDownLatch(1)
        val downloader = DataProvider(ThreadHandler())
        val downloadInfo = DataProvider.DownloadInfo("https://www.mesonet.org/index.php/weather/local",-1)
        val result = downloader.Download(downloadInfo, object: DataProvider.DownloadCallback{
            override fun DownloadComplete(inResponseCode: Int, inResult: String?) {
                latch.countDown()
            }

            override fun DownloadFailed() {
                throw Exception("Should have succeeded")
            }

        })

        latch.await()

        assertNotNull(result)
        assert(result.mResponseCode >= HttpURLConnection.HTTP_OK && result.mResponseCode < HttpURLConnection.HTTP_MULT_CHOICE)
        assertNotNull(result.mResponse)
        assert(!result.mFailed)

        downloader.mThreadHandler.CloseThreads()
    }


    @Test(timeout = 10000)
    fun SingleUpdateEmptyUrlTests()
    {
        val latch = CountDownLatch(1)
        val downloader = DataProvider(ThreadHandler())

        downloader.SingleUpdate("", object: DataProvider.DownloadCallback{
            override fun DownloadComplete(inResponseCode: Int, inResult: String?) {
                throw Exception("Should have failed")
            }

            override fun DownloadFailed() {
                latch.countDown()
            }

        })

        shadowOf(downloader.mThreadHandler.mThreads["Download0"]?.first.looper).runToEndOfTasks()

        latch.await()

        downloader.mThreadHandler.CloseThreads()
    }


    @Test(timeout = 10000)
    fun SingleUpdateBadUrlTests()
    {
        val latch = CountDownLatch(1)
        val downloader = DataProvider(ThreadHandler())

        downloader.SingleUpdate("bad format bad format bad format", object: DataProvider.DownloadCallback{
            override fun DownloadComplete(inResponseCode: Int, inResult: String?) {
                throw Exception("Should have failed")
            }

            override fun DownloadFailed() {
                latch.countDown()
            }

        })

        shadowOf(downloader.mThreadHandler.mThreads["Download0"]?.first.looper).runToEndOfTasks()

        latch.await()

        downloader.mThreadHandler.CloseThreads()
    }



    @Test(timeout = 40000)
    fun SingleUpdateInvalidUrlTests()
    {
        val latch = CountDownLatch(1)
        val downloader = DataProvider(ThreadHandler())

        downloader.SingleUpdate("https://www.bekahtesting.com/", object: DataProvider.DownloadCallback{
            override fun DownloadComplete(inResponseCode: Int, inResult: String?) {
                throw Exception("Should have failed")
            }

            override fun DownloadFailed() {
                latch.countDown()
            }

        })

        shadowOf(downloader.mThreadHandler.mThreads["Download0"]?.first.looper).runToEndOfTasks()

        latch.await()

        downloader.mThreadHandler.CloseThreads()
    }


    @Test(timeout = 10000)
    fun SingleUpdateGoodUrlTests()
    {
        val latch = CountDownLatch(1)
        val downloader = DataProvider(ThreadHandler())

        downloader.SingleUpdate("https://www.mesonet.org/index.php/weather/local/", object: DataProvider.DownloadCallback{
            override fun DownloadComplete(inResponseCode: Int, inResult: String?) {
                latch.countDown()
            }

            override fun DownloadFailed() {
                throw Exception("Should have Succeeded")
            }

        })

        shadowOf(downloader.mThreadHandler.mThreads["Download0"]?.first.looper).runToEndOfTasks()

        latch.await()

        downloader.mThreadHandler.CloseThreads()
    }



    @Test(timeout = 10000)
    fun TimedUpdateEmptyUrlTests()
    {
        val latch = CountDownLatch(2)
        val downloader = DataProvider(ThreadHandler())

        downloader.StartDownloads("", object: DataProvider.DownloadCallback{
            override fun DownloadComplete(inResponseCode: Int, inResult: String?) {
                throw Exception("Should have failed")
            }

            override fun DownloadFailed() {
                latch.countDown()
            }

        }, 5000)

        shadowOf(downloader.mThreadHandler.mThreads["Download0"]?.first.looper).runToEndOfTasks()

        latch.await()

        downloader.mThreadHandler.CloseThreads()

        assert(downloader.IsUpdating())
        downloader.StopDownloads()
        assert(!downloader.IsUpdating())
    }


    @Test(timeout = 10000)
    fun TimedUpdateBadUrlTests()
    {
        val latch = CountDownLatch(2)
        val downloader = DataProvider(ThreadHandler())

        downloader.StartDownloads("bad format bad format bad format", object: DataProvider.DownloadCallback{
            override fun DownloadComplete(inResponseCode: Int, inResult: String?) {
                throw Exception("Should have failed")
            }

            override fun DownloadFailed() {
                latch.countDown()
            }

        }, 5000)

        shadowOf(downloader.mThreadHandler.mThreads["Download0"]?.first.looper).runToEndOfTasks()

        latch.await()

        downloader.mThreadHandler.CloseThreads()

        assert(downloader.IsUpdating())
        downloader.StopDownloads()
        assert(!downloader.IsUpdating())
    }



    @Test(timeout = 80000)
    fun TimedUpdateInvalidUrlTests()
    {
        val latch = CountDownLatch(2)
        val downloader = DataProvider(ThreadHandler())

        downloader.StartDownloads("https://www.bekahtesting.com/", object: DataProvider.DownloadCallback{
            override fun DownloadComplete(inResponseCode: Int, inResult: String?) {
                throw Exception("Should have failed")
            }

            override fun DownloadFailed() {
                latch.countDown()
            }

        }, 5000)

        shadowOf(downloader.mThreadHandler.mThreads["Download0"]?.first.looper).runToEndOfTasks()

        latch.await()

        downloader.mThreadHandler.CloseThreads()

        assert(downloader.IsUpdating())
        downloader.StopDownloads()
        assert(!downloader.IsUpdating())
    }


    @Test(timeout = 10000)
    fun TimedUpdateGoodUrlTests()
    {
        val latch = CountDownLatch(2)
        val downloader = DataProvider(ThreadHandler())

        downloader.StartDownloads("https://www.mesonet.org/index.php/weather/local/", object: DataProvider.DownloadCallback{
            override fun DownloadComplete(inResponseCode: Int, inResult: String?) {
                latch.countDown()
            }

            override fun DownloadFailed() {
                throw Exception("Should have succeeded")
            }

        }, 5000)

        shadowOf(downloader.mThreadHandler.mThreads["Download0"]?.first.looper).runToEndOfTasks()

        latch.await()

        downloader.mThreadHandler.CloseThreads()

        assert(downloader.IsUpdating())
        downloader.StopDownloads()
        assert(!downloader.IsUpdating())
    }
}