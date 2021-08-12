package org.mesonet.models.radar

import junit.framework.TestCase
import junit.framework.TestCase.*
import org.junit.Test

class RadarModelTests
{
    @Test
    fun BuilderFindFieldTests()
    {
        val builder = RadarModel.Builder()

        assertNotNull(builder)

        assertNull(builder.FindField(""))
        assertNull(builder.FindField("bad field name"))
        assertNotNull(builder.FindField("latitude"))
        Assert.assertEquals(builder.FindField("latitude")?.type, Float::class.javaObjectType)
        assertNotNull(builder.FindField("longitude"))
        Assert.assertEquals(builder.FindField("longitude")?.type, Float::class.javaObjectType)
        assertNotNull(builder.FindField("range"))
        Assert.assertEquals(builder.FindField("range")?.type, Float::class.javaObjectType)
        assertNotNull(builder.FindField("name"))
        Assert.assertEquals(builder.FindField("name")?.type, String::class.java)
    }



    @Test
    fun BuilderSetValueTests()
    {
        var builder = RadarModel.Builder()

        assertNotNull(builder)

        var model = builder.Build()

        assertNull(model.GetLatitude())
        assertNull(model.GetLongitude())
        assertNull(model.GetRange())
        assertNull(model.GetName())

        builder.SetValue("", "", "")

        model = builder.Build()

        assertNull(model.GetLatitude())
        assertNull(model.GetLongitude())
        assertNull(model.GetRange())
        assertNull(model.GetName())

        builder.SetValue("bad name", "bad type", "value")

        model = builder.Build()

        assertNull(model.GetLatitude())
        assertNull(model.GetLongitude())
        assertNull(model.GetRange())
        assertNull(model.GetName())

        builder.SetValue("latitude", "bad type", "value")

        assertNull(model.GetLatitude())
        assertNull(model.GetLongitude())
        assertNull(model.GetRange())
        assertNull(model.GetName())

        builder.SetValue("latitude", "string", "value")

        assertNull(model.GetLatitude())
        assertNull(model.GetLongitude())
        assertNull(model.GetRange())
        assertNull(model.GetName())

        builder.SetValue("latitude", "real", "value")

        assertNull(model.GetLatitude())
        assertNull(model.GetLongitude())
        assertNull(model.GetRange())
        assertNull(model.GetName())

        builder.SetValue("latitude", "real", "35.1234")

        assertNotNull(model.GetLatitude())
        assertEquals(model.GetLatitude()?, 35.1234f)
        assertNull(model.GetLongitude())
        assertNull(model.GetRange())
        assertNull(model.GetName())

        builder = RadarModel.Builder()
        model = builder.Build()

        builder.SetValue("longitude", "bad type", "value")

        assertNull(model.GetLatitude())
        assertNull(model.GetLongitude())
        assertNull(model.GetRange())
        assertNull(model.GetName())

        builder.SetValue("longitude", "string", "value")

        assertNull(model.GetLatitude())
        assertNull(model.GetLongitude())
        assertNull(model.GetRange())
        assertNull(model.GetName())

        builder.SetValue("longitude", "real", "value")

        assertNull(model.GetLatitude())
        assertNull(model.GetLongitude())
        assertNull(model.GetRange())
        assertNull(model.GetName())

        builder.SetValue("longitude", "real", "-95.1234")

        assertNull(model.GetLatitude())
        assertNotNull(model.GetLongitude())
        assertEquals(model.GetLongitude()?, -95.1234f)
        assertNull(model.GetRange())
        assertNull(model.GetName())



        builder = RadarModel.Builder()
        model = builder.Build()

        builder.SetValue("range", "bad type", "value")

        assertNull(model.GetLatitude())
        assertNull(model.GetLongitude())
        assertNull(model.GetRange())
        assertNull(model.GetName())

        builder.SetValue("range", "string", "value")

        assertNull(model.GetLatitude())
        assertNull(model.GetLongitude())
        assertNull(model.GetRange())
        assertNull(model.GetName())

        builder.SetValue("range", "real", "value")

        assertNull(model.GetLatitude())
        assertNull(model.GetLongitude())
        assertNull(model.GetRange())
        assertNull(model.GetName())

        builder.SetValue("range", "real", "123.456")

        assertNull(model.GetLatitude())
        assertNull(model.GetLongitude())
        assertNotNull(model.GetRange())
        assertEquals(model.GetRange()?, 123.456f)
        assertNull(model.GetName())


        builder = RadarModel.Builder()
        model = builder.Build()

        builder.SetValue("name", "bad type", "value")

        assertNull(model.GetLatitude())
        assertNull(model.GetLongitude())
        assertNull(model.GetRange())
        assertNull(model.GetName())

        builder.SetValue("name", "real", "value")

        assertNull(model.GetLatitude())
        assertNull(model.GetLongitude())
        assertNull(model.GetRange())
        assertNull(model.GetName())

        builder.SetValue("name", "string", "value")

        assertNull(model.GetLatitude())
        assertNull(model.GetLongitude())
        assertNull(model.GetRange())
        assertNotNull(model.GetName())
        assertEquals(model.GetName(), "value")
    }
}