package org.mesonet.models.site.mesonetdata

import junit.framework.TestCase.*
import org.junit.Test
import org.mesonet.core.DefaultUnits

class MesonetModelTests {
    @Test
    fun NullModelTests()
    {
        val model = MesonetModel()

        assertNull(model.GetTime())
        assertNull(model.GetRelH())
        assertNull(model.GetTAir())
        assertNull(model.GetWSpd())
        assertNull(model.GetWDir())
        assertNull(model.GetWMax())
        assertNull(model.GetPres())
        assertNull(model.GetRain24H())
        assertNull(model.GetStID())

        assertEquals(model.GetDefaultTempUnit(), DefaultUnits.TempUnits.kCelsius)
        assertEquals(model.GetDefaultLengthUnit(), DefaultUnits.LengthUnits.kMm)
        assertEquals(model.GetDefaultSpeedUnit(), DefaultUnits.SpeedUnits.kMps)
        assertEquals(model.GetDefaultPressureUnit(), DefaultUnits.PressureUnits.kMb)
    }



    @Test
    fun SetFieldModelTests()
    {
        val model = MesonetModel()

        model.TIME = 1234567890987654321
        model.RELH = 987654321
        model.TAIR = 1111111111
        model.WSPD = 2222222222
        model.WDIR = 3333333333
        model.WMAX = 4444444444
        model.PRES = 5555555555
        model.RAIN_24H = 6666666666
        model.STID = "STID"

        assertNotNull(model.GetTime())
        assertEquals(model.GetTime(), 1234567890987654321)
        assertNotNull(model.GetRelH())
        assertEquals(model.GetRelH(), 987654321)
        assertNotNull(model.GetTAir())
        assertEquals(model.GetTAir(), 1111111111)
        assertNotNull(model.GetWSpd())
        assertEquals(model.GetWSpd(), 2222222222)
        assertNotNull(model.GetWDir())
        assertEquals(model.GetWDir(), 3333333333)
        assertNotNull(model.GetWMax())
        assertEquals(model.GetWMax(), 4444444444)
        assertNotNull(model.GetPres())
        assertEquals(model.GetPres(), 5555555555)
        assertNotNull(model.GetRain24H())
        assertEquals(model.GetRain24H(), 6666666666)
        assertNotNull(model.GetStID())
        assertEquals(model.GetStID(), "STID")

        assertEquals(model.GetDefaultTempUnit(), DefaultUnits.TempUnits.kCelsius)
        assertEquals(model.GetDefaultLengthUnit(), DefaultUnits.LengthUnits.kMm)
        assertEquals(model.GetDefaultSpeedUnit(), DefaultUnits.SpeedUnits.kMps)
        assertEquals(model.GetDefaultPressureUnit(), DefaultUnits.PressureUnits.kMb)
    }
}