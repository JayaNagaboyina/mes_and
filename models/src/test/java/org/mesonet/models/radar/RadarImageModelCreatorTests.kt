package org.mesonet.models.radar

import android.util.Xml
import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.io.ByteArrayInputStream
import java.nio.charset.StandardCharsets


@RunWith(RobolectricTestRunner::class)
class RadarImageModelCreatorTests
{
    @Test
    fun EmptyFileTests()
    {
        val creator = RadarImageModelConverterFactory()
        val parser = Xml.newPullParser()
        parser.setInput(ByteArrayInputStream("".toByteArray(StandardCharsets.UTF_8)), null)

        val result = creator.ParseRadarImagesXml(parser, 12)

        assert(result.isEmpty())
    }


    @Test
    fun BadXmlTests()
    {
        val creator = RadarImageModelConverterFactory()
        val parser = Xml.newPullParser()
        parser.setInput(ByteArrayInputStream("bad file bad file bad file".toByteArray(StandardCharsets.UTF_8)), null)

        val result = creator.ParseRadarImagesXml(parser, 12)

        assert(result.isEmpty())
    }


    @Test
    fun IncompatibleXmlTests()
    {
        val creator = RadarImageModelConverterFactory()
        val parser = Xml.newPullParser()
        parser.setInput(ByteArrayInputStream(("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<!DOCTYPE plist PUBLIC \"-//Apple//DTD PLIST 1.0//EN\" \"http://www.apple.com/DTDs/PropertyList-1.0.dtd\">\n" +
                "<plist version=\"1.0\"><entry><entry attribute=\"attribute\"/></entry></plist>").toByteArray(StandardCharsets.UTF_8)), null)

        val result = creator.ParseRadarImagesXml(parser, 12)

        assert(result.isEmpty())
    }


    @Test
    fun PartlyBadXmlTests()
    {
        val creator = RadarImageModelConverterFactory()
        val parser = Xml.newPullParser()
        parser.setInput(ByteArrayInputStream(("<list timeStampUTC=\"2018-05-11 02:06:10\" radar=\"KTLX\" product=\"N0Q\">\n" +
                "<frame filename=\"/data/nids/maps/realtime/storage/KTLX/N0Q/201805110202.gif\" timestring=\" 9:02 PM May 10, 2018 CDT\" datatime=\"1526004120\" looptime=\"1526004120\"/>\n" +
                "ERROR!!! ERROR!!! ERROR!!!" +
                "<frame filename=\"/data/nids/maps/realtime/storage/KTLX/N0Q/201805110153.gif\" timestring=\" 8:53 PM May 10, 2018 CDT\" datatime=\"1526003580\" looptime=\"1526003580\"/>\n" +
                "</list>").toByteArray(StandardCharsets.UTF_8)), null)

        val result = creator.ParseRadarImagesXml(parser, 12)

        assert(!result.isEmpty())
        assert(result.size == 2)
        assertEquals(result[0].GetFilename(), "/data/nids/maps/realtime/storage/KTLX/N0Q/201805110202.gif")
        assertEquals(result[0].GetTimeString(), " 9:02 PM May 10, 2018 CDT")
        assertEquals(result[1].GetFilename(), "/data/nids/maps/realtime/storage/KTLX/N0Q/201805110153.gif")
        assertEquals(result[1].GetTimeString(), " 8:53 PM May 10, 2018 CDT")
    }



    @Test
    fun PartlyIncompatibleXmlTests()
    {
        val creator = RadarImageModelConverterFactory()
        val parser = Xml.newPullParser()
        parser.setInput(ByteArrayInputStream(("<list timeStampUTC=\"2018-05-11 02:06:10\" radar=\"KTLX\" product=\"N0Q\">\n" +
                "<frame filename=\"/data/nids/maps/realtime/storage/KTLX/N0Q/201805110202.gif\" timestring=\" 9:02 PM May 10, 2018 CDT\" datatime=\"1526004120\" looptime=\"1526004120\"/>\n" +
                "<element attribute=\"attr\">Data</element>" +
                "<frame filename=\"/data/nids/maps/realtime/storage/KTLX/N0Q/201805110153.gif\" timestring=\" 8:53 PM May 10, 2018 CDT\" datatime=\"1526003580\" looptime=\"1526003580\"/>\n" +
                "</list>").toByteArray(StandardCharsets.UTF_8)), null)

        val result = creator.ParseRadarImagesXml(parser, 12)

        assert(!result.isEmpty())
        assert(result.size == 2)
        assertEquals(result[0].GetFilename(), "/data/nids/maps/realtime/storage/KTLX/N0Q/201805110202.gif")
        assertEquals(result[0].GetTimeString(), " 9:02 PM May 10, 2018 CDT")
        assertEquals(result[1].GetFilename(), "/data/nids/maps/realtime/storage/KTLX/N0Q/201805110153.gif")
        assertEquals(result[1].GetTimeString(), " 8:53 PM May 10, 2018 CDT")
    }



    @Test
    fun IncompatibleFrameXmlTests()
    {
        val creator = RadarImageModelConverterFactory()
        val parser = Xml.newPullParser()
        parser.setInput(ByteArrayInputStream(("<list timeStampUTC=\"2018-05-11 02:06:10\" radar=\"KTLX\" product=\"N0Q\">\n" +
                "<frame filename=\"/data/nids/maps/realtime/storage/KTLX/N0Q/201805110202.gif\" timestring=\" 9:02 PM May 10, 2018 CDT\" datatime=\"1526004120\" looptime=\"1526004120\"/>\n" +
                "<frame data=\"bad\"/>\n" +
                "<frame filename=\"/data/nids/maps/realtime/storage/KTLX/N0Q/201805110153.gif\" timestring=\" 8:53 PM May 10, 2018 CDT\" datatime=\"1526003580\" looptime=\"1526003580\"/>\n" +
                "</list>").toByteArray(StandardCharsets.UTF_8)), null)

        val result = creator.ParseRadarImagesXml(parser, 12)

        assert(!result.isEmpty())
        assert(result.size == 3)
        assertEquals(result[0].GetFilename(), "/data/nids/maps/realtime/storage/KTLX/N0Q/201805110202.gif")
        assertEquals(result[0].GetTimeString(), " 9:02 PM May 10, 2018 CDT")
        assertEquals(result[1].GetFilename(), "")
        assertEquals(result[1].GetTimeString(), "")
        assertEquals(result[2].GetFilename(), "/data/nids/maps/realtime/storage/KTLX/N0Q/201805110153.gif")
        assertEquals(result[2].GetTimeString(), " 8:53 PM May 10, 2018 CDT")
    }



    @Test
    fun FrameLimitHitXmlTests()
    {
        val creator = RadarImageModelConverterFactory()
        val parser = Xml.newPullParser()
        parser.setInput(ByteArrayInputStream(("<list timeStampUTC=\"2018-05-11 02:06:10\" radar=\"KTLX\" product=\"N0Q\">\n" +
                "<frame filename=\"/data/nids/maps/realtime/storage/KTLX/N0Q/201805110202.gif\" timestring=\" 9:02 PM May 10, 2018 CDT\" datatime=\"1526004120\" looptime=\"1526004120\"/>\n" +
                "<frame data=\"bad\"/>\n" +
                "<frame filename=\"/data/nids/maps/realtime/storage/KTLX/N0Q/201805110153.gif\" timestring=\" 8:53 PM May 10, 2018 CDT\" datatime=\"1526003580\" looptime=\"1526003580\"/>\n" +
                "</list>").toByteArray(StandardCharsets.UTF_8)), null)

        val result = creator.ParseRadarImagesXml(parser, 2)

        assert(!result.isEmpty())
        assert(result.size == 2)
        assertEquals(result[0].GetFilename(), "/data/nids/maps/realtime/storage/KTLX/N0Q/201805110202.gif")
        assertEquals(result[0].GetTimeString(), " 9:02 PM May 10, 2018 CDT")
        assertEquals(result[1].GetFilename(), "")
        assertEquals(result[1].GetTimeString(), "")
    }
}