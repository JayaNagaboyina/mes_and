package org.mesonet.models.site.forecast

import junit.framework.TestCase.*
import org.junit.Test
import org.mesonet.core.DefaultUnits


class ForecastModelTests
{
    @Test
    fun NullValueTests()
    {
        val model = ForecastModel()

        assertNull(model.GetTime())
        assertNull(model.GetTemp())
        assertNull(model.GetIconId())
        assertNull(model.GetStatus())
        assertNull(model.GetHighOrLow())
        assertNull(model.GetWindDirectionStart())
        assertNull(model.GetWindDirectionEnd())
        assertNull(model.GetWindMin())
        assertNull(model.GetWindMax())
        assertEquals(model.GetDefaultTempUnit(), DefaultUnits.TempUnits.kFahrenheit)
        assertEquals(model.GetDefaultSpeedUnit(), DefaultUnits.SpeedUnits.kMph)
    }



    @Test
    fun GoodValueTests()
    {
        val model = ForecastModel()

        model.mTime = "Time"
        model.mTemp = 86.7
        model.mIconUrl = "Icon url"
        model.mStatus = "Status"
        model.mHighOrLow = Forecast.HighOrLow.Low
        model.mWindDirectionStart = DefaultUnits.CompassDirections.S
        model.mWindDirectionEnd = DefaultUnits.CompassDirections.W
        model.mWindMin = 12
        model.mWindMax = 15

        assertNotNull(model.GetTime())
        assertEquals(model.GetTime(), "Time")
        assertNotNull(model.GetTemp())
        assertEquals(model.GetTemp(), 86.7)
        assertNotNull(model.GetIconId())
        assertEquals(model.GetIconId(), "Icon url")
        assertNotNull(model.GetStatus())
        assertEquals(model.GetStatus(), "Status")
        assertNotNull(model.GetHighOrLow())
        assertEquals(model.GetHighOrLow(), Forecast.HighOrLow.Low)
        assertNotNull(model.GetWindDirectionStart())
        assertEquals(model.GetWindDirectionStart(), DefaultUnits.CompassDirections.S)
        assertNotNull(model.GetWindDirectionEnd())
        assertEquals(model.GetWindDirectionEnd(), DefaultUnits.CompassDirections.W)
        assertNotNull(model.GetWindMin())
        assertEquals(model.GetWindMin(),12)
        assertNotNull(model.GetWindMax())
        assertEquals(model.GetWindMax(), 15)
        assertEquals(model.GetDefaultTempUnit(), DefaultUnits.TempUnits.kFahrenheit)
        assertEquals(model.GetDefaultSpeedUnit(), DefaultUnits.SpeedUnits.kMph)

        model.mWindDirectionStart = DefaultUnits.CompassDirections.N
        model.mWindDirectionEnd = DefaultUnits.CompassDirections.E

        assertEquals(model.GetWindDirectionStart(), DefaultUnits.CompassDirections.N)
        assertEquals(model.GetWindDirectionEnd(), DefaultUnits.CompassDirections.E)

        model.mWindDirectionStart = DefaultUnits.CompassDirections.SE
        model.mWindDirectionEnd = DefaultUnits.CompassDirections.SW

        assertEquals(model.GetWindDirectionStart(), DefaultUnits.CompassDirections.SE)
        assertEquals(model.GetWindDirectionEnd(), DefaultUnits.CompassDirections.SW)

        model.mWindDirectionStart = DefaultUnits.CompassDirections.NE
        model.mWindDirectionEnd = DefaultUnits.CompassDirections.NW

        assertEquals(model.GetWindDirectionStart(), DefaultUnits.CompassDirections.NE)
        assertEquals(model.GetWindDirectionEnd(), DefaultUnits.CompassDirections.NW)

        model.mWindDirectionStart = DefaultUnits.CompassDirections.SSE
        model.mWindDirectionEnd = DefaultUnits.CompassDirections.SSW

        assertEquals(model.GetWindDirectionStart(), DefaultUnits.CompassDirections.SSE)
        assertEquals(model.GetWindDirectionEnd(), DefaultUnits.CompassDirections.SSW)

        model.mWindDirectionStart = DefaultUnits.CompassDirections.NNE
        model.mWindDirectionEnd = DefaultUnits.CompassDirections.NNW

        assertEquals(model.GetWindDirectionStart(), DefaultUnits.CompassDirections.NNE)
        assertEquals(model.GetWindDirectionEnd(), DefaultUnits.CompassDirections.NNW)

        model.mWindDirectionStart = DefaultUnits.CompassDirections.ESE
        model.mWindDirectionEnd = DefaultUnits.CompassDirections.WSW

        assertEquals(model.GetWindDirectionStart(), DefaultUnits.CompassDirections.ESE)
        assertEquals(model.GetWindDirectionEnd(), DefaultUnits.CompassDirections.WSW)

        model.mWindDirectionStart = DefaultUnits.CompassDirections.ENE
        model.mWindDirectionEnd = DefaultUnits.CompassDirections.WNW

        assertEquals(model.GetWindDirectionStart(), DefaultUnits.CompassDirections.ENE)
        assertEquals(model.GetWindDirectionEnd(), DefaultUnits.CompassDirections.WNW)
    }
}