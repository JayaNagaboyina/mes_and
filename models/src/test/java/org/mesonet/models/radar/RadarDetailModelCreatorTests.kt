package org.mesonet.models.radar

import android.util.Xml
import junit.framework.TestCase.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.io.ByteArrayInputStream
import java.nio.charset.StandardCharsets


@RunWith(RobolectricTestRunner::class)
class RadarDetailModelCreatorTests
{
    @Test
    fun ParseEmptyRadarDetail()
    {
        val creator = RadarDetailModelCreatorFactory()
        val parser = Xml.newPullParser()
        parser.setInput(ByteArrayInputStream("".toByteArray(StandardCharsets.UTF_8)), null)

        val result = creator.ParseRadarDetail(parser)

        assertNull(result.GetName())
        assertNull(result.GetRange())
        assertNull(result.GetLatitude())
        assertNull(result.GetLongitude())
    }



    @Test
    fun ParseBadRadarDetail()
    {
        val creator = RadarDetailModelCreatorFactory()
        val parser = Xml.newPullParser()
        parser.setInput(ByteArrayInputStream("ERROR!!! ERROR!!! ERROR!!!".toByteArray(StandardCharsets.UTF_8)), null)

        val result = creator.ParseRadarDetail(parser)

        assertNull(result.GetName())
        assertNull(result.GetRange())
        assertNull(result.GetLatitude())
        assertNull(result.GetLongitude())
    }


    @Test
    fun ParseRadarDetailWithKeyPrefix()
    {
        val creator = RadarDetailModelCreatorFactory()
        val parser = Xml.newPullParser()
        parser.setInput(ByteArrayInputStream(("<key>KFDR</key>\n" +
                "        <dict>\n" +
                "            <key>state</key>\n" +
                "            <string>OK</string>\n" +
                "            <key>longitude</key>\n" +
                "            <real>-98.9764</real>\n" +
                "            <key>latitude</key>\n" +
                "            <real>34.3622</real>\n" +
                "            <key>name</key>\n" +
                "            <string>Frederick</string>\n" +
                "            <key>latDelta</key>\n" +
                "            <real>9.035</real>\n" +
                "            <key>lonDelta</key>\n" +
                "            <real>10.966</real>\n" +
                "            <key>range</key>\n" +
                "            <real>434320</real>\n" +
                "        </dict>").toByteArray(StandardCharsets.UTF_8)), null)

        val result = creator.ParseRadarDetail(parser)

        assertNotNull(result.GetName())
        assertEquals(result.GetName()?, "Frederick")
        assertNotNull(result.GetRange())
        assertEquals(result.GetRange(), 434320f)
        assertNotNull(result.GetLatitude())
        assertEquals(result.GetLatitude(), 34.3622f)
        assertNotNull(result.GetLongitude())
        assertEquals(result.GetLongitude(), -98.9764f)
    }



    fun ParseRadarDetailWithDictPrefix()
    {
        val creator = RadarDetailModelCreatorFactory()
        val parser = Xml.newPullParser()
        parser.setInput(ByteArrayInputStream(("<dict>KFDR</dict>\n" +
                "        <dict>\n" +
                "            <key>state</key>\n" +
                "            <string>OK</string>\n" +
                "            <key>longitude</key>\n" +
                "            <real>-98.9764</real>\n" +
                "            <key>latitude</key>\n" +
                "            <real>34.3622</real>\n" +
                "            <key>name</key>\n" +
                "            <string>Frederick</string>\n" +
                "            <key>latDelta</key>\n" +
                "            <real>9.035</real>\n" +
                "            <key>lonDelta</key>\n" +
                "            <real>10.966</real>\n" +
                "            <key>range</key>\n" +
                "            <real>434320</real>\n" +
                "        </dict>").toByteArray(StandardCharsets.UTF_8)), null)

        val result = creator.ParseRadarDetail(parser)

        assertNotNull(result.GetName())
        assertEquals(result.GetName()?, "Frederick")
        assertNotNull(result.GetRange())
        assertEquals(result.GetRange(), 434320f)
        assertNotNull(result.GetLatitude())
        assertEquals(result.GetLatitude(), 34.3622f)
        assertNotNull(result.GetLongitude())
        assertEquals(result.GetLongitude(), -98.9764f)
    }



    fun ParseRadarDetailWithBadEntry()
    {
        val creator = RadarDetailModelCreatorFactory()
        val parser = Xml.newPullParser()
        parser.setInput(ByteArrayInputStream(("        <dict>\n" +
                "            <key>state</key>\n" +
                "            <string>OK</string>\n" +
                "            <key>longitude</key>\n" +
                "            <real>-98.9764</real>\n" +
                "            <key>latitude</key>\n" +
                "            <real>34.3622</real>\n" +
                "            <badElement badAttribute=\"Bad Attribute\"/>" +
                "            <key>name</key>\n" +
                "            <string>Frederick</string>\n" +
                "        </dict>").toByteArray(StandardCharsets.UTF_8)), null)

        val result = creator.ParseRadarDetail(parser)

        assertNotNull(result.GetName())
        assertEquals(result.GetName()?, "Frederick")
        assertNull(result.GetRange())
        assertNotNull(result.GetLatitude())
        assertEquals(result.GetLatitude(), 34.3622f)
        assertNotNull(result.GetLongitude())
        assertEquals(result.GetLongitude(), -98.9764f)
    }


    @Test
    fun EmptyFileTests()
    {
        val creator = RadarDetailModelCreatorFactory()
        val parser = Xml.newPullParser()
        parser.setInput(ByteArrayInputStream("".toByteArray(StandardCharsets.UTF_8)), null)

        val result = creator.ParseRadarXml(parser)

        assert(result.isEmpty())
    }



    @Test
    fun BadXmlTests()
    {
        val creator = RadarDetailModelCreatorFactory()
        val parser = Xml.newPullParser()
        parser.setInput(ByteArrayInputStream("bad file bad file bad file".toByteArray(StandardCharsets.UTF_8)), null)

        val result = creator.ParseRadarXml(parser)

        assert(result.isEmpty())
    }



    @Test
    fun IncompatibleXmlTests()
    {
        val creator = RadarDetailModelCreatorFactory()
        val parser = Xml.newPullParser()
        parser.setInput(ByteArrayInputStream(("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<!DOCTYPE plist PUBLIC \"-//Apple//DTD PLIST 1.0//EN\" \"http://www.apple.com/DTDs/PropertyList-1.0.dtd\">\n" +
                "<plist version=\"1.0\"><entry><entry attribute=\"attribute\"/></entry></plist>").toByteArray(StandardCharsets.UTF_8)), null)

        val result = creator.ParseRadarXml(parser)

        assert(result.isEmpty())
    }



    @Test
    fun PartlyBadXmlTests()
    {
        val creator = RadarDetailModelCreatorFactory()
        val parser = Xml.newPullParser()
        parser.setInput(ByteArrayInputStream(("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<!DOCTYPE plist PUBLIC \"-//Apple//DTD PLIST 1.0//EN\" \"http://www.apple.com/DTDs/PropertyList-1.0.dtd\">\n" +
                "<plist version=\"1.0\">\n" +
                "    <dict>\n" +
                "        <key>KTLX</key>\n" +
                "        <dict>\n" +
                "            <key>state</key>\n" +
                "            <string>OK</string>\n" +
                "            <key>name</key>\n" +
                "            <string>Oklahoma City</string>\n" +
                "            <key>latitude</key>\n" +
                "            <real>35.3331</real>\n" +
                "            <key>longitude</key>\n" +
                "            <real>-97.2778</real>\n" +
                "            <key>latDelta</key>\n" +
                "            <real>8.92</real>\n" +
                "            <key>lonDelta</key>\n" +
                "            <real>10.966</real>\n" +
                "            <key>range</key>\n" +
                "            <real>434320</real>\n" +
                "        </dict>\n" +
                "        ERROR!!! ERROR!!! ERROR!!!" +
                "        <key>KINX</key>\n" +
                "        <dict>\n" +
                "            <key>state</key>\n" +
                "            <string>OK</string>\n" +
                "            <key>latitude</key>\n" +
                "            <real>36.175</real>\n" +
                "            <key>longitude</key>\n" +
                "            <real>-95.5647</real>\n" +
                "            <key>name</key>\n" +
                "            <string>Tulsa</string>\n" +
                "            <key>latDelta</key>\n" +
                "            <real>8.834</real>\n" +
                "            <key>lonDelta</key>\n" +
                "            <real>10.966</real>\n" +
                "            <key>range</key>\n" +
                "            <real>434320</real>\n" +
                "        </dict>\n" +
                "    </dict>\n" +
                "</plist>").toByteArray(StandardCharsets.UTF_8)), null)

        val result = creator.ParseRadarXml(parser)

        assert(!result.isEmpty())
        assert(result.size == 2)
        assertNotNull(result["KTLX"])
        assertEquals(result["KTLX"]?.GetName(), "Oklahoma City")
        assertEquals(result["KTLX"]?.GetLatitude(), 35.3331f)
        assertEquals(result["KTLX"]?.GetLongitude(), -97.2778f)
        assertEquals(result["KTLX"]?.GetRange(), 434320f)
        assertNotNull(result["KINX"])
        assertEquals(result["KINX"]?.GetName(), "Tulsa")
        assertEquals(result["KINX"]?.GetLatitude(), 36.175f)
        assertEquals(result["KINX"]?.GetLongitude(), -95.5647f)
        assertEquals(result["KINX"]?.GetRange(), 434320f)
    }



    @Test
    fun PartlyBadRadarInfoTests()
    {
        val creator = RadarDetailModelCreatorFactory()
        val parser = Xml.newPullParser()
        parser.setInput(ByteArrayInputStream(("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<!DOCTYPE plist PUBLIC \"-//Apple//DTD PLIST 1.0//EN\" \"http://www.apple.com/DTDs/PropertyList-1.0.dtd\">\n" +
                "<plist version=\"1.0\">\n" +
                "    <dict>\n" +
                "        <key>KTLX</key>\n" +
                "        <dict>\n" +
                "            <key>state</key>\n" +
                "            <string>OK</string>\n" +
                "            <key>name</key>\n" +
                "            <string>Oklahoma City</string>\n" +
                "            <key>latitude</key>\n" +
                "            <real>35.3331</real>\n" +
                "            <key>longitude</key>\n" +
                "            <real>-97.2778</real>\n" +
                "            <key>latDelta</key>\n" +
                "            <real>8.92</real>\n" +
                "            <key>lonDelta</key>\n" +
                "            <real>10.966</real>\n" +
                "            <key>range</key>\n" +
                "            <real>434320</real>\n" +
                "        </dict>\n" +
                "        <key>KFDR</key>\n" +
                "        <dict>\n" +
                "            <key>state</key>\n" +
                "            <string>OK</string>\n" +
                "            <key>longitude</key>\n" +
                "            <real>-98.9764</real>\n" +
                "            <key>latitude</key>\n" +
                "            <real>34.3622</real>\n" +
                "            <key>name</key>\n" +
                "            <badel attribute=\"bad attribute\">Bad element</badel>\n" +
                "            <badel>Bad element2</badel>\n" +
                "        </dict>" +
                "        <key>KINX</key>\n" +
                "        <dict>\n" +
                "            <key>state</key>\n" +
                "            <string>OK</string>\n" +
                "            <key>latitude</key>\n" +
                "            <real>36.175</real>\n" +
                "            <key>longitude</key>\n" +
                "            <real>-95.5647</real>\n" +
                "            <key>name</key>\n" +
                "            <string>Tulsa</string>\n" +
                "            <key>latDelta</key>\n" +
                "            <real>8.834</real>\n" +
                "            <key>lonDelta</key>\n" +
                "            <real>10.966</real>\n" +
                "            <key>range</key>\n" +
                "            <real>434320</real>\n" +
                "        </dict>\n" +
                "    </dict>\n" +
                "</plist>").toByteArray(StandardCharsets.UTF_8)), null)

        val result = creator.ParseRadarXml(parser)

        assert(!result.isEmpty())
        assert(result.size == 3)
        assertNotNull(result["KTLX"])
        assertEquals(result["KTLX"]?.GetName(), "Oklahoma City")
        assertEquals(result["KTLX"]?.GetLatitude(), 35.3331f)
        assertEquals(result["KTLX"]?.GetLongitude(), -97.2778f)
        assertEquals(result["KTLX"]?.GetRange(), 434320f)
        assertNotNull(result["KFDR"])
        assertNull(result["KFDR"]?.GetName())
        assertEquals(result["KFDR"]?.GetLatitude(), 34.3622f)
        assertEquals(result["KFDR"]?.GetLongitude(), -98.9764f)
        assertNull(result["KFDR"]?.GetRange())
        assertNotNull(result["KINX"])
        assertEquals(result["KINX"]?.GetName(), "Tulsa")
        assertEquals(result["KINX"]?.GetLatitude(), 36.175f)
        assertEquals(result["KINX"]?.GetLongitude(), -95.5647f)
        assertEquals(result["KINX"]?.GetRange(), 434320f)
    }
}