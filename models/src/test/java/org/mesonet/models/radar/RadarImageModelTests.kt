package org.mesonet.models.radar

import junit.framework.TestCase.*
import org.junit.Test


class RadarImageModelTests
{
    @Test
    fun BuilderFindFieldTests()
    {
        val builder = RadarImageModel.Builder()

        assertNotNull(builder)

        assertNull(builder.FindField(""))
        assertNull(builder.FindField("bad field name"))
        assertNotNull(builder.FindField("filename"))
        assertEquals(builder.FindField("filename")?.type, String::class.java)
        assertNotNull(builder.FindField("timestring"))
        assertEquals(builder.FindField("timestring")?.type, String::class.java)
    }

    @Test
    fun BuilderSetValueTests()
    {
        var builder = RadarImageModel.Builder()

        assertNotNull(builder)

        var model = builder.Build()

        assert(model.GetFilename().isEmpty())
        assert(model.GetTimeString().isEmpty())

        builder.SetValue("", "")

        model = builder.Build()

        assert(model.GetFilename().isEmpty())
        assert(model.GetTimeString().isEmpty())

        builder.SetValue("bad name", "value")

        model = builder.Build()

        assert(model.GetFilename().isEmpty())
        assert(model.GetTimeString().isEmpty())

        builder.SetValue("filename", "")

        model = builder.Build()

        assert(model.GetFilename().isEmpty())
        assert(model.GetTimeString().isEmpty())

        builder.SetValue("filename", "File name test")

        model = builder.Build()

        assert(!model.GetFilename().isEmpty())
        assertEquals(model.GetFilename(), "File name test")
        assert(model.GetTimeString().isEmpty())

        builder = RadarImageModel.Builder()

        builder.SetValue("timestring", "")

        model = builder.Build()

        assert(model.GetTimeString().isEmpty())
        assert(model.GetFilename().isEmpty())

        builder.SetValue("timestring", "Time string test")

        model = builder.Build()

        assert(!model.GetTimeString().isEmpty())
        assertEquals(model.GetTimeString(), "Time string test")
        assert(model.GetFilename().isEmpty())
    }
}