package org.mesonet.dataprocessing.advisories

import android.os.Looper
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Test
import org.junit.runner.RunWith
import org.mesonet.models.advisories.Advisory
import org.mesonet.models.advisories.AdvisoryModel
import org.powermock.core.classloader.annotations.PrepareForTest
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows
import java.util.*
import java.util.concurrent.CountDownLatch
import kotlin.collections.ArrayList


@RunWith(RobolectricTestRunner::class)
@PrepareForTest(Looper::class)
class AdvisoryDisplayListBuilderTests
{
    @Test
    fun MakeEmptyCountyListStringTest()
    {
        val listBuilder = AdvisoryDisplayListBuilder()
        val startList = ArrayList<String>()

        val result = listBuilder.MakeCountyListString(startList)

        assertNotNull(result)
        assertEquals(result, "")
    }


    @Test
    fun BrokenMakeCountyListStringTest()
    {
        val listBuilder = AdvisoryDisplayListBuilder()
        val startList = Arrays.asList("OKC141","OKC143","","OKC147","OKC149","OKC151","OKC153","OKZ001","OKZ002","OKZ003")

        val result = listBuilder.MakeCountyListString(startList)

        assertNotNull(result)
        assertEquals(result, "Tillman, Tulsa, Washington, Washita, Woods, Woodward, Cimarron, Texas, Beaver")
    }


    @Test
    fun BadCountyMakeCountyListStringTest()
    {
        val listBuilder = AdvisoryDisplayListBuilder()
        val startList = Arrays.asList("OKC141","OKC143","bad format","OKC147","OKC149","OKC151","OKC153","OKZ001","OKZ002","OKZ003")

        val result = listBuilder.MakeCountyListString(startList)

        assertNotNull(result)
        assertEquals(result, "Tillman, Tulsa, bad format, Washington, Washita, Woods, Woodward, Cimarron, Texas, Beaver")
    }



    @Test
    fun BadFileMakeCountyListStringTest()
    {
        val listBuilder = AdvisoryDisplayListBuilder()
        val startList = Arrays.asList("bad format bad format bad format")

        val result = listBuilder.MakeCountyListString(startList)

        assertNotNull(result)
        assertEquals(result, "bad format bad format bad format")
    }


    @Test
    fun GoodMakeCountyListStringTest()
    {
        val listBuilder = AdvisoryDisplayListBuilder()
        val startList = Arrays.asList("OKC141","OKC143","OKC145","OKC147","OKC149","OKC151","OKC153","OKZ001","OKZ002","OKZ003")

        val result = listBuilder.MakeCountyListString(startList)

        assertNotNull(result)
        assertEquals(result, "Tillman, Tulsa, Wagoner, Washington, Washita, Woods, Woodward, Cimarron, Texas, Beaver")
    }



    @Test
    fun SortNullTest()
    {
        val listBuilder = AdvisoryDisplayListBuilder()

        val result = listBuilder.Sort(null)

        assertNull(result)
    }



    @Test
    fun SortEmptyListTest()
    {
        val listBuilder = AdvisoryDisplayListBuilder()

        val result = listBuilder.Sort(ArrayList())

        assertNotNull(result)
        assert(result?.isEmpty())
    }



    @Test
    fun SortListTest()
    {
        val advisoryList = ArrayList<Advisory>()

        advisoryList.add(object: Advisory{
            override fun GetLevel(): AdvisoryModel.AdvisoryLevel? {
                return AdvisoryModel.AdvisoryLevel.O
            }

            override fun GetType(): AdvisoryModel.AdvisoryType? {
                return null
            }

            override fun GetFilePath(): String? {
                return null
            }

            override fun GetCounties(): List<String>? {
                return null
            }
        })

        advisoryList.add(object: Advisory{
            override fun GetLevel(): AdvisoryModel.AdvisoryLevel? {
                return AdvisoryModel.AdvisoryLevel.A
            }

            override fun GetType(): AdvisoryModel.AdvisoryType? {
                return AdvisoryModel.AdvisoryType.LB
            }

            override fun GetFilePath(): String? {
                return null
            }

            override fun GetCounties(): List<String>? {
                return null
            }

        })
        advisoryList.add(object: Advisory{
            override fun GetLevel(): AdvisoryModel.AdvisoryLevel? {
                return AdvisoryModel.AdvisoryLevel.F
            }

            override fun GetType(): AdvisoryModel.AdvisoryType? {
                return null
            }

            override fun GetFilePath(): String? {
                return null
            }

            override fun GetCounties(): List<String>? {
                return null
            }
        })

        advisoryList.add(object: Advisory{
            override fun GetLevel(): AdvisoryModel.AdvisoryLevel? {
                return AdvisoryModel.AdvisoryLevel.W
            }

            override fun GetType(): AdvisoryModel.AdvisoryType? {
                return AdvisoryModel.AdvisoryType.AF
            }

            override fun GetFilePath(): String? {
                return null
            }

            override fun GetCounties(): List<String>? {
                return null
            }

        })

        advisoryList.add(object: Advisory{
            override fun GetLevel(): AdvisoryModel.AdvisoryLevel? {
                return AdvisoryModel.AdvisoryLevel.O
            }

            override fun GetType(): AdvisoryModel.AdvisoryType? {
                return AdvisoryModel.AdvisoryType.MA
            }

            override fun GetFilePath(): String? {
                return null
            }

            override fun GetCounties(): List<String>? {
                return null
            }
        })

        advisoryList.add(object: Advisory{
            override fun GetLevel(): AdvisoryModel.AdvisoryLevel? {
                return null
            }

            override fun GetType(): AdvisoryModel.AdvisoryType? {
                return null
            }

            override fun GetFilePath(): String? {
                return null
            }

            override fun GetCounties(): List<String>? {
                return null
            }
        })

        advisoryList.add(object: Advisory{
            override fun GetLevel(): AdvisoryModel.AdvisoryLevel? {
                return AdvisoryModel.AdvisoryLevel.Y
            }

            override fun GetType(): AdvisoryModel.AdvisoryType? {
                return AdvisoryModel.AdvisoryType.LO
            }

            override fun GetFilePath(): String? {
                return null
            }

            override fun GetCounties(): List<String>? {
                return null
            }

        })

        advisoryList.add(object: Advisory{
            override fun GetLevel(): AdvisoryModel.AdvisoryLevel? {
                return AdvisoryModel.AdvisoryLevel.N
            }

            override fun GetType(): AdvisoryModel.AdvisoryType? {
                return null
            }

            override fun GetFilePath(): String? {
                return null
            }

            override fun GetCounties(): List<String>? {
                return null
            }
        })

        advisoryList.add(object: Advisory{
            override fun GetLevel(): AdvisoryModel.AdvisoryLevel? {
                return null
            }

            override fun GetType(): AdvisoryModel.AdvisoryType? {
                return null
            }

            override fun GetFilePath(): String? {
                return null
            }

            override fun GetCounties(): List<String>? {
                return null
            }
        })

        advisoryList.add(object: Advisory{
            override fun GetLevel(): AdvisoryModel.AdvisoryLevel? {
                return null
            }

            override fun GetType(): AdvisoryModel.AdvisoryType? {
                return AdvisoryModel.AdvisoryType.FZ
            }

            override fun GetFilePath(): String? {
                return null
            }

            override fun GetCounties(): List<String>? {
                return null
            }
        })

        advisoryList.add(object: Advisory{
            override fun GetLevel(): AdvisoryModel.AdvisoryLevel? {
                return null
            }

            override fun GetType(): AdvisoryModel.AdvisoryType? {
                return AdvisoryModel.AdvisoryType.BS
            }

            override fun GetFilePath(): String? {
                return null
            }

            override fun GetCounties(): List<String>? {
                return null
            }
        })

        advisoryList.add(object: Advisory{
            override fun GetLevel(): AdvisoryModel.AdvisoryLevel? {
                return AdvisoryModel.AdvisoryLevel.W
            }

            override fun GetType(): AdvisoryModel.AdvisoryType? {
                return AdvisoryModel.AdvisoryType.BS
            }

            override fun GetFilePath(): String? {
                return null
            }

            override fun GetCounties(): List<String>? {
                return null
            }
        })

        advisoryList.add(object: Advisory{
            override fun GetLevel(): AdvisoryModel.AdvisoryLevel? {
                return null
            }

            override fun GetType(): AdvisoryModel.AdvisoryType? {
                return null
            }

            override fun GetFilePath(): String? {
                return null
            }

            override fun GetCounties(): List<String>? {
                return null
            }
        })

        advisoryList.add(object: Advisory{
            override fun GetLevel(): AdvisoryModel.AdvisoryLevel? {
                return null
            }

            override fun GetType(): AdvisoryModel.AdvisoryType? {
                return AdvisoryModel.AdvisoryType.MA
            }

            override fun GetFilePath(): String? {
                return null
            }

            override fun GetCounties(): List<String>? {
                return null
            }
        })

        val listBuilder = AdvisoryDisplayListBuilder()

        val result = listBuilder.Sort(advisoryList)

        assertNotNull(result)
        assert(!result?.isEmpty())
        assertEquals(result.size, 14)
        assertEquals(result[0].GetLevel(), AdvisoryModel.AdvisoryLevel.W)
        assertEquals(result[0].GetType(), AdvisoryModel.AdvisoryType.BS)
        assertEquals(result[1].GetLevel(), AdvisoryModel.AdvisoryLevel.W)
        assertEquals(result[1].GetType(), AdvisoryModel.AdvisoryType.AF)
        assertEquals(result[2].GetLevel(), AdvisoryModel.AdvisoryLevel.F)
        assertNull(result[2].GetType())
        assertEquals(result[3].GetLevel(), AdvisoryModel.AdvisoryLevel.A)
        assertEquals(result[3].GetType(), AdvisoryModel.AdvisoryType.LB)
        assertEquals(result[4].GetLevel(), AdvisoryModel.AdvisoryLevel.O)
        assertEquals(result[4].GetType(), AdvisoryModel.AdvisoryType.MA)
        assertEquals(result[5].GetLevel(), AdvisoryModel.AdvisoryLevel.O)
        assertNull(result[5].GetType())
        assertEquals(result[6].GetLevel(), AdvisoryModel.AdvisoryLevel.Y)
        assertEquals(result[6].GetType(), AdvisoryModel.AdvisoryType.LO)
        assertEquals(result[7].GetLevel(), AdvisoryModel.AdvisoryLevel.N)
        assertNull(result[7].GetType())
        assertNull(result[8].GetLevel())
        assertEquals(result[8].GetType(), AdvisoryModel.AdvisoryType.BS)
        assertNull(result[9].GetLevel())
        assertEquals(result[9].GetType(), AdvisoryModel.AdvisoryType.FZ)
        assertNull(result[10].GetLevel())
        assertEquals(result[10].GetType(), AdvisoryModel.AdvisoryType.MA)
        assertNull(result[11].GetLevel())
        assertNull(result[11].GetType())
        assertNull(result[12].GetLevel())
        assertNull(result[12].GetType())
        assertNull(result[13].GetLevel())
        assertNull(result[13].GetType())
    }



    @Test(timeout = 10000)
    fun BuildListNullListTest()
    {
        val listBuilder = AdvisoryDisplayListBuilder()
        val latch = CountDownLatch(1)

        listBuilder.BuildList(null, object: AdvisoryDisplayListBuilder.AdvisoryListListener{
            override fun ListComplete(inResult: MutableList<Pair<AdvisoryDisplayListBuilder.AdvisoryDataType, AdvisoryDisplayListBuilder.AdvisoryData>>) {
                assert(inResult.isEmpty())
                latch.countDown()
            }
        })

//        Shadows.shadowOf(listBuilder.mThreadHandler.mThreads["AdvisoryData0"]?.first.looper).runToEndOfTasks()

        latch.await()
    }


    @Test(timeout = 10000)
    fun BuildListEmptyListTest()
    {
        val listBuilder = AdvisoryDisplayListBuilder()
        val latch = CountDownLatch(1)

        listBuilder.BuildList(ArrayList(), object: AdvisoryDisplayListBuilder.AdvisoryListListener{
            override fun ListComplete(inResult: MutableList<Pair<AdvisoryDisplayListBuilder.AdvisoryDataType, AdvisoryDisplayListBuilder.AdvisoryData>>) {
                assert(inResult.isEmpty())
                latch.countDown()
            }
        })

//        Shadows.shadowOf(listBuilder.mThreadHandler.mThreads["AdvisoryData0"]?.first.looper).runToEndOfTasks()

        latch.await()
    }


    @Test//(timeout = 10000)
    fun BuildListGoodListTest()
    {
        val advisoryList = ArrayList<Advisory>()

        advisoryList.add(object: Advisory{
            override fun GetLevel(): AdvisoryModel.AdvisoryLevel? {
                return AdvisoryModel.AdvisoryLevel.O
            }

            override fun GetType(): AdvisoryModel.AdvisoryType? {
                return null
            }

            override fun GetFilePath(): String? {
                return null
            }

            override fun GetCounties(): List<String>? {
                return null
            }
        })

        advisoryList.add(object: Advisory{
            override fun GetLevel(): AdvisoryModel.AdvisoryLevel? {
                return AdvisoryModel.AdvisoryLevel.A
            }

            override fun GetType(): AdvisoryModel.AdvisoryType? {
                return AdvisoryModel.AdvisoryType.LB
            }

            override fun GetFilePath(): String? {
                return null
            }

            override fun GetCounties(): List<String>? {
                return null
            }

        })
        advisoryList.add(object: Advisory{
            override fun GetLevel(): AdvisoryModel.AdvisoryLevel? {
                return AdvisoryModel.AdvisoryLevel.F
            }

            override fun GetType(): AdvisoryModel.AdvisoryType? {
                return null
            }

            override fun GetFilePath(): String? {
                return null
            }

            override fun GetCounties(): List<String>? {
                return null
            }
        })

        advisoryList.add(object: Advisory{
            override fun GetLevel(): AdvisoryModel.AdvisoryLevel? {
                return AdvisoryModel.AdvisoryLevel.W
            }

            override fun GetType(): AdvisoryModel.AdvisoryType? {
                return AdvisoryModel.AdvisoryType.AF
            }

            override fun GetFilePath(): String? {
                return null
            }

            override fun GetCounties(): List<String>? {
                return null
            }

        })

        advisoryList.add(object: Advisory{
            override fun GetLevel(): AdvisoryModel.AdvisoryLevel? {
                return AdvisoryModel.AdvisoryLevel.O
            }

            override fun GetType(): AdvisoryModel.AdvisoryType? {
                return AdvisoryModel.AdvisoryType.MA
            }

            override fun GetFilePath(): String? {
                return null
            }

            override fun GetCounties(): List<String>? {
                return null
            }
        })

        advisoryList.add(object: Advisory{
            override fun GetLevel(): AdvisoryModel.AdvisoryLevel? {
                return null
            }

            override fun GetType(): AdvisoryModel.AdvisoryType? {
                return null
            }

            override fun GetFilePath(): String? {
                return null
            }

            override fun GetCounties(): List<String>? {
                return null
            }
        })

        advisoryList.add(object: Advisory{
            override fun GetLevel(): AdvisoryModel.AdvisoryLevel? {
                return AdvisoryModel.AdvisoryLevel.Y
            }

            override fun GetType(): AdvisoryModel.AdvisoryType? {
                return AdvisoryModel.AdvisoryType.LO
            }

            override fun GetFilePath(): String? {
                return null
            }

            override fun GetCounties(): List<String>? {
                return null
            }

        })

        advisoryList.add(object: Advisory{
            override fun GetLevel(): AdvisoryModel.AdvisoryLevel? {
                return AdvisoryModel.AdvisoryLevel.N
            }

            override fun GetType(): AdvisoryModel.AdvisoryType? {
                return null
            }

            override fun GetFilePath(): String? {
                return null
            }

            override fun GetCounties(): List<String>? {
                return null
            }
        })

        advisoryList.add(object: Advisory{
            override fun GetLevel(): AdvisoryModel.AdvisoryLevel? {
                return null
            }

            override fun GetType(): AdvisoryModel.AdvisoryType? {
                return null
            }

            override fun GetFilePath(): String? {
                return null
            }

            override fun GetCounties(): List<String>? {
                return null
            }
        })

        advisoryList.add(object: Advisory{
            override fun GetLevel(): AdvisoryModel.AdvisoryLevel? {
                return null
            }

            override fun GetType(): AdvisoryModel.AdvisoryType? {
                return AdvisoryModel.AdvisoryType.FZ
            }

            override fun GetFilePath(): String? {
                return "bad file path"
            }

            override fun GetCounties(): List<String>? {
                return null
            }
        })

        advisoryList.add(object: Advisory{
            override fun GetLevel(): AdvisoryModel.AdvisoryLevel? {
                return null
            }

            override fun GetType(): AdvisoryModel.AdvisoryType? {
                return AdvisoryModel.AdvisoryType.BS
            }

            override fun GetFilePath(): String? {
                return null
            }

            override fun GetCounties(): List<String>? {
                return null
            }
        })

        advisoryList.add(object: Advisory{
            override fun GetLevel(): AdvisoryModel.AdvisoryLevel? {
                return AdvisoryModel.AdvisoryLevel.W
            }

            override fun GetType(): AdvisoryModel.AdvisoryType? {
                return AdvisoryModel.AdvisoryType.BS
            }

            override fun GetFilePath(): String? {
                return null
            }

            override fun GetCounties(): List<String>? {
                return null
            }
        })

        advisoryList.add(object: Advisory{
            override fun GetLevel(): AdvisoryModel.AdvisoryLevel? {
                return null
            }

            override fun GetType(): AdvisoryModel.AdvisoryType? {
                return null
            }

            override fun GetFilePath(): String? {
                return null
            }

            override fun GetCounties(): List<String>? {
                return Arrays.asList("OKC003", "TX003", "bad county")
            }
        })

        advisoryList.add(object: Advisory{
            override fun GetLevel(): AdvisoryModel.AdvisoryLevel? {
                return null
            }

            override fun GetType(): AdvisoryModel.AdvisoryType? {
                return AdvisoryModel.AdvisoryType.MA
            }

            override fun GetFilePath(): String? {
                return null
            }

            override fun GetCounties(): List<String>? {
                return null
            }
        })

        val listBuilder = AdvisoryDisplayListBuilder()
        val latch = CountDownLatch(1)

        listBuilder.BuildList(advisoryList, object: AdvisoryDisplayListBuilder.AdvisoryListListener{
            override fun ListComplete(inResult: MutableList<Pair<AdvisoryDisplayListBuilder.AdvisoryDataType, AdvisoryDisplayListBuilder.AdvisoryData>>) {
                assert(!inResult.isEmpty())

                assertEquals(inResult.size, 26)

                assertEquals(inResult[0].first, AdvisoryDisplayListBuilder.AdvisoryDataType.kHeader)
                assertEquals(inResult[0].second.AdvisoryType(), "Blowing Snow Warning")
                assert(inResult[0].second.Counties().isEmpty())
                assertNull(inResult[0].second.Url())
                assertEquals(inResult[1].first, AdvisoryDisplayListBuilder.AdvisoryDataType.kContent)
                assertEquals(inResult[1].second.AdvisoryType(), "Blowing Snow Warning")
                assertEquals(inResult[1].second.Counties(), "Unknown Location")
                assertNull(inResult[1].second.Url())
                assertEquals(inResult[2].first, AdvisoryDisplayListBuilder.AdvisoryDataType.kHeader)
                assertEquals(inResult[2].second.AdvisoryType(), "Ashfall Warning")
                assert(inResult[2].second.Counties().isEmpty())
                assertNull(inResult[2].second.Url())
                assertEquals(inResult[3].first, AdvisoryDisplayListBuilder.AdvisoryDataType.kContent)
                assertEquals(inResult[3].second.AdvisoryType(), "Ashfall Warning")
                assertEquals(inResult[3].second.Counties(), "Unknown Location")
                assertNull(inResult[3].second.Url())
                assertEquals(inResult[4].first, AdvisoryDisplayListBuilder.AdvisoryDataType.kHeader)
                assertEquals(inResult[4].second.AdvisoryType(), "Forecast")
                assert(inResult[4].second.Counties().isEmpty())
                assertNull(inResult[4].second.Url())
                assertEquals(inResult[5].first, AdvisoryDisplayListBuilder.AdvisoryDataType.kContent)
                assertEquals(inResult[5].second.AdvisoryType(), "Forecast")
                assertEquals(inResult[5].second.Counties(), "Unknown Location")
                assertNull(inResult[5].second.Url())
                assertEquals(inResult[6].first, AdvisoryDisplayListBuilder.AdvisoryDataType.kHeader)
                assertEquals(inResult[6].second.AdvisoryType(), "Lake Effect Snow and Blowing Snow Watch")
                assert(inResult[6].second.Counties().isEmpty())
                assertNull(inResult[6].second.Url())
                assertEquals(inResult[7].first, AdvisoryDisplayListBuilder.AdvisoryDataType.kContent)
                assertEquals(inResult[7].second.AdvisoryType(), "Lake Effect Snow and Blowing Snow Watch")
                assertEquals(inResult[7].second.Counties(), "Unknown Location")
                assertNull(inResult[7].second.Url())
                assertEquals(inResult[8].first, AdvisoryDisplayListBuilder.AdvisoryDataType.kHeader)
                assertEquals(inResult[8].second.AdvisoryType(), "Marine Outlook")
                assert(inResult[8].second.Counties().isEmpty())
                assertNull(inResult[8].second.Url())
                assertEquals(inResult[9].first, AdvisoryDisplayListBuilder.AdvisoryDataType.kContent)
                assertEquals(inResult[9].second.AdvisoryType(), "Marine Outlook")
                assertEquals(inResult[9].second.Counties(), "Unknown Location")
                assertNull(inResult[9].second.Url())
                assertEquals(inResult[10].first, AdvisoryDisplayListBuilder.AdvisoryDataType.kHeader)
                assertEquals(inResult[10].second.AdvisoryType(), "Outlook")
                assert(inResult[10].second.Counties().isEmpty())
                assertNull(inResult[10].second.Url())
                assertEquals(inResult[11].first, AdvisoryDisplayListBuilder.AdvisoryDataType.kContent)
                assertEquals(inResult[11].second.AdvisoryType(), "Outlook")
                assertEquals(inResult[11].second.Counties(), "Unknown Location")
                assertNull(inResult[11].second.Url())
                assertEquals(inResult[12].first, AdvisoryDisplayListBuilder.AdvisoryDataType.kHeader)
                assertEquals(inResult[12].second.AdvisoryType(), "Low Water Advisory")
                assert(inResult[12].second.Counties().isEmpty())
                assertNull(inResult[12].second.Url())
                assertEquals(inResult[13].first, AdvisoryDisplayListBuilder.AdvisoryDataType.kContent)
                assertEquals(inResult[13].second.AdvisoryType(), "Low Water Advisory")
                assertEquals(inResult[13].second.Counties(), "Unknown Location")
                assertNull(inResult[13].second.Url())
                assertEquals(inResult[14].first, AdvisoryDisplayListBuilder.AdvisoryDataType.kHeader)
                assertEquals(inResult[14].second.AdvisoryType(), "Synopsis")
                assert(inResult[14].second.Counties().isEmpty())
                assertNull(inResult[14].second.Url())
                assertEquals(inResult[15].first, AdvisoryDisplayListBuilder.AdvisoryDataType.kContent)
                assertEquals(inResult[15].second.AdvisoryType(), "Synopsis")
                assertEquals(inResult[15].second.Counties(), "Unknown Location")
                assertNull(inResult[15].second.Url())
                assertEquals(inResult[16].first, AdvisoryDisplayListBuilder.AdvisoryDataType.kHeader)
                assertEquals(inResult[16].second.AdvisoryType(), "Blowing Snow")
                assert(inResult[16].second.Counties().isEmpty())
                assertNull(inResult[16].second.Url())
                assertEquals(inResult[17].first, AdvisoryDisplayListBuilder.AdvisoryDataType.kContent)
                assertEquals(inResult[17].second.AdvisoryType(), "Blowing Snow")
                assertEquals(inResult[17].second.Counties(), "Unknown Location")
                assertNull(inResult[17].second.Url())
                assertEquals(inResult[18].first, AdvisoryDisplayListBuilder.AdvisoryDataType.kHeader)
                assertEquals(inResult[18].second.AdvisoryType(), "Freeze")
                assert(inResult[18].second.Counties().isEmpty())
                assertNull(inResult[18].second.Url())
                assertEquals(inResult[19].first, AdvisoryDisplayListBuilder.AdvisoryDataType.kContent)
                assertEquals(inResult[19].second.AdvisoryType(), "Freeze")
                assertEquals(inResult[19].second.Counties(), "Unknown Location")
                assertEquals(inResult[19].second.Url(), "https://www.mesonet.org/data/public/noaa/text/archivebad file path")
                assertEquals(inResult[20].first, AdvisoryDisplayListBuilder.AdvisoryDataType.kHeader)
                assertEquals(inResult[20].second.AdvisoryType(), "Marine")
                assert(inResult[20].second.Counties().isEmpty())
                assertNull(inResult[20].second.Url())
                assertEquals(inResult[21].first, AdvisoryDisplayListBuilder.AdvisoryDataType.kContent)
                assertEquals(inResult[21].second.AdvisoryType(), "Marine")
                assertEquals(inResult[21].second.Counties(), "Unknown Location")
                assertNull(inResult[21].second.Url())
                assertEquals(inResult[22].first, AdvisoryDisplayListBuilder.AdvisoryDataType.kHeader)
                assertEquals(inResult[22].second.AdvisoryType(), "Other")
                assert(inResult[22].second.Counties().isEmpty())
                assertNull(inResult[22].second.Url())
                assertEquals(inResult[23].first, AdvisoryDisplayListBuilder.AdvisoryDataType.kContent)
                assertEquals(inResult[23].second.AdvisoryType(), "Other")
                assertEquals(inResult[23].second.Counties(), "Unknown Location")
                assertNull(inResult[23].second.Url())
                assertEquals(inResult[24].first, AdvisoryDisplayListBuilder.AdvisoryDataType.kContent)
                assertEquals(inResult[24].second.AdvisoryType(), "Other")
                assertEquals(inResult[24].second.Counties(), "Unknown Location")
                assertNull(inResult[24].second.Url())
                assertEquals(inResult[25].first, AdvisoryDisplayListBuilder.AdvisoryDataType.kContent)
                assertEquals(inResult[25].second.AdvisoryType(), "Other")
                assertEquals(inResult[25].second.Counties(), "Alfalfa, TX003, bad county")
                assertNull(inResult[25].second.Url())

                latch.countDown()
            }
        })

//        Shadows.shadowOf(listBuilder.mThreadHandler.mThreads["AdvisoryData0"]?.first.looper).runToEndOfTasks()

        latch.await()
    }
}