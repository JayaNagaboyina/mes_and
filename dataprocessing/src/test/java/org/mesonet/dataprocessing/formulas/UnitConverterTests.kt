package org.mesonet.dataprocessing.formulas

import junit.framework.Assert
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import org.junit.Test
import org.mesonet.core.DefaultUnits

class UnitConverterTests
{
    @Test
    fun CelsiusToFahrenheitTests()
    {
        val converter = UnitConverter()
        assertEquals(converter.CelsiusToFahrenheit(null), null)
        assertEquals(converter.CelsiusToFahrenheit(0.0), 32.0)
        assertEquals(converter.CelsiusToFahrenheit(-17.77777777777778), 0.0)
        assertEquals(converter.CelsiusToFahrenheit(-40.0), -40.0)
        assertEquals(converter.CelsiusToFahrenheit(37.77777777777778), 100.0)
    }



    @Test
    fun FahrenheitToCelsiusTests()
    {
        val converter = UnitConverter()
        assertEquals(converter.FahrenheitToCelsius(null), null)
        assertEquals(converter.FahrenheitToCelsius(32.0), 0.0)
        assertEquals(converter.FahrenheitToCelsius(0.0), -17.77777777777778)
        assertEquals(converter.FahrenheitToCelsius(-40), -40.0)
        assertEquals(converter.FahrenheitToCelsius(100.0), 37.77777777777778)
    }



    @Test
    fun MpsToMphTests()
    {
        val converter = UnitConverter()
        assertEquals(converter.MpsToMph(null), null)
        assertEquals(converter.MpsToMph(0.0), 0.0)
        assertEquals(converter.MpsToMph(100.0), 223.6936292054402)
        assertEquals(converter.MpsToMph(44.70400000000001), 100.0)
        assertEquals(converter.MpsToMph(-100.0), -223.6936292054402)
        assertEquals(converter.MpsToMph(-44.70400000000001), -100.0)
    }


    @Test
    fun MphToMpsTests()
    {
        val converter = UnitConverter()
        assertEquals(converter.MphToMps(null), null)
        assertEquals(converter.MphToMps(0.0), 0.0)
        assertEquals(converter.MphToMps(223.6936292054402), 100.0)
        assertEquals(converter.MphToMps(100.0), 44.70400000000001)
        assertEquals(converter.MphToMps(-223.6936292054402), -100.0)
        assertEquals(converter.MphToMps(-100.0), -44.70400000000001)
    }



    @Test
    fun MpsToKmphTests()
    {
        val converter = UnitConverter()
        assertEquals(converter.MpsToKmph(null), null)
        assertEquals(converter.MpsToKmph(0.0), 0.0)
        assertEquals(converter.MpsToKmph(100.0), 360.0)
        assertEquals(converter.MpsToKmph(27.777777777777778), 100.0)
        assertEquals(converter.MpsToKmph(-100.0), -360.0)
        assertEquals(converter.MpsToKmph(-27.777777777777778), -100.0)
    }



    @Test
    fun KmphToMpsTests()
    {
        val converter = UnitConverter()
        assertEquals(converter.KmphToMps(null), null)
        assertEquals(converter.KmphToMps(0.0), 0.0)
        assertEquals(converter.KmphToMps(360.0), 100.0)
        assertEquals(converter.KmphToMps(100.0), 27.777777777777778)
        assertEquals(converter.KmphToMps(-360.0), -100.0)
        assertEquals(converter.KmphToMps(-100.0), -27.777777777777778)
    }


    @Test
    fun MphToKmphTests()
    {
        val converter = UnitConverter()
        assertEquals(converter.MphToKmph(null), null)
        assertEquals(converter.MphToKmph(0.0), 0.0)
        assertEquals(converter.MphToKmph(62.13711922373339), 100.0)
        assertEquals(converter.MphToKmph(100.0), 160.9344)
        assertEquals(converter.MphToKmph(-62.13711922373339), -100.0)
        assertEquals(converter.MphToKmph(-100.0), -160.9344)
    }


    @Test
    fun KmphToMphTests()
    {
        val converter = UnitConverter()
        assertEquals(converter.KmphToMph(null), null)
        assertEquals(converter.KmphToMph(0.0), 0.0)
        assertEquals(converter.KmphToMph(100.0), 62.13711922373339)
        assertEquals(converter.KmphToMph(160.9344), 100.0)
        assertEquals(converter.KmphToMph(-100.0), -62.13711922373339)
        assertEquals(converter.KmphToMph(-160.9344), -100.0)
    }



    @Test
    fun InToMmTests()
    {
        val converter = UnitConverter()
        assertEquals(converter.InToMm(null), null)
        assertEquals(converter.InToMm(0.0), 0.0)
        assertEquals(converter.InToMm(100.0), 2539.9999999999986)
        assertEquals(converter.InToMm(3.93700787401575), 100.0)
        assertEquals(converter.InToMm(-100.0), -2539.9999999999986)
        assertEquals(converter.InToMm(-3.93700787401575), -100.0)
    }


    @Test
    fun MmToInTests()
    {
        val converter = UnitConverter()
        assertEquals(converter.MmToIn(null), null)
        assertEquals(converter.MmToIn(0.0), 0.0)
        assertEquals(converter.MmToIn(2539.9999999999986), 100.0)
        assertEquals(converter.MmToIn(100.0), 3.93700787401575)
        assertEquals(converter.MmToIn(-2539.9999999999986), -100.0)
        assertEquals(converter.MmToIn(-100.0), -3.93700787401575)
    }



    @Test
    fun InHgToMbTests()
    {
        val converter = UnitConverter()
        assertEquals(converter.InHgToMb(null), null)
        assertEquals(converter.InHgToMb(0.0), 0.0)
        assertEquals(converter.InHgToMb(100.0), 3386.3900000000003)
        assertEquals(converter.InHgToMb(2.952997144451761), 100.0)
        assertEquals(converter.InHgToMb(-100.0), -3386.3900000000003)
        assertEquals(converter.InHgToMb(-2.952997144451761), -100.0)
        assertEquals(converter.InHgToMb(29.411851558739543), 996.0)
        assertEquals(converter.InHgToMb(30.0), 1015.917)
        assertEquals(converter.InHgToMb(29.92124356615747), 1013.25)
    }



    @Test
    fun MbToInHgTests()
    {
        val converter = UnitConverter()
        assertEquals(converter.MbToInHg(null), null)
        assertEquals(converter.MbToInHg(0.0), 0.0)
        assertEquals(converter.MbToInHg(3386.3900000000003), 100.0)
        assertEquals(converter.MbToInHg(100.0), 2.952997144451761)
        assertEquals(converter.MbToInHg(-3386.3900000000003), -100.0)
        assertEquals(converter.MbToInHg(-100.0), -2.952997144451761)
        assertEquals(converter.MbToInHg(996.0), 29.411851558739543)
        assertEquals(converter.MbToInHg(1015.917), 30.0)
        assertEquals(converter.MbToInHg(1013.25), 29.92124356615747)
    }



    @Test
    fun GetTempInPreferredUnitsCelsiusTests()
    {
        var defaultTempUnit: DefaultUnits.TempUnits? = null

        val defaultUnits = object: DefaultUnits
        {
            override fun GetDefaultTempUnit(): DefaultUnits.TempUnits? {
                return defaultTempUnit
            }

            override fun GetDefaultLengthUnit(): DefaultUnits.LengthUnits? {
                return null
            }

            override fun GetDefaultSpeedUnit(): DefaultUnits.SpeedUnits? {
                return null
            }

            override fun GetDefaultPressureUnit(): DefaultUnits.PressureUnits? {
                return null
            }

        }

        val converter = UnitConverter()
        assertEquals(converter.GetTempInPreferredUnits(null, null, null), null)
        assertEquals(converter.GetTempInPreferredUnits(null, null, DefaultUnits.TempUnits.kCelsius), null)
        assertEquals(converter.GetTempInPreferredUnits(null, defaultUnits, null), null)
        assertEquals(converter.GetTempInPreferredUnits(null, defaultUnits, DefaultUnits.TempUnits.kCelsius), null)
        assertEquals(converter.GetTempInPreferredUnits(0.0, null, null), null)
        assertEquals(converter.GetTempInPreferredUnits(0.0, null, DefaultUnits.TempUnits.kCelsius), null)
        assertEquals(converter.GetTempInPreferredUnits(0.0, defaultUnits, null), null)
        assertEquals(converter.GetTempInPreferredUnits(0.0, defaultUnits, DefaultUnits.TempUnits.kCelsius), null)

        defaultTempUnit = DefaultUnits.TempUnits.kCelsius

        assertEquals(converter.GetTempInPreferredUnits(0.0, defaultUnits, DefaultUnits.TempUnits.kCelsius), 0.0)
        assertEquals(converter.GetTempInPreferredUnits(-17.77777777777778, defaultUnits, DefaultUnits.TempUnits.kCelsius), -17.77777777777778)
        assertEquals(converter.GetTempInPreferredUnits(32.0, defaultUnits, DefaultUnits.TempUnits.kCelsius), 32.0)
        assertEquals(converter.GetTempInPreferredUnits(-40.0, defaultUnits, DefaultUnits.TempUnits.kCelsius), -40.0)
        assertEquals(converter.GetTempInPreferredUnits(37.77777777777778, defaultUnits, DefaultUnits.TempUnits.kCelsius), 37.77777777777778)
        assertEquals(converter.GetTempInPreferredUnits(100.0, defaultUnits, DefaultUnits.TempUnits.kCelsius), 100.0)

        defaultTempUnit = DefaultUnits.TempUnits.kFahrenheit

        assertEquals(converter.GetTempInPreferredUnits(0.0, defaultUnits, DefaultUnits.TempUnits.kCelsius), -17.77777777777778)
        assertEquals(converter.GetTempInPreferredUnits(-17.77777777777778, defaultUnits, DefaultUnits.TempUnits.kCelsius), -27.65432098765432)
        assertEquals(converter.GetTempInPreferredUnits(32.0, defaultUnits, DefaultUnits.TempUnits.kCelsius), 0.0)
        assertEquals(converter.GetTempInPreferredUnits(-40.0, defaultUnits, DefaultUnits.TempUnits.kCelsius), -40.0)
        assertEquals(converter.GetTempInPreferredUnits(37.77777777777778, defaultUnits, DefaultUnits.TempUnits.kCelsius), 3.209876543209877)
        assertEquals(converter.GetTempInPreferredUnits(100.0, defaultUnits, DefaultUnits.TempUnits.kCelsius), 37.77777777777778)
    }


    @Test
    fun GetTempInPreferredUnitsFahrenheitTests()
    {
        var defaultTempUnit: DefaultUnits.TempUnits? = null

        val defaultUnits = object: DefaultUnits
        {
            override fun GetDefaultTempUnit(): DefaultUnits.TempUnits? {
                return defaultTempUnit
            }

            override fun GetDefaultLengthUnit(): DefaultUnits.LengthUnits? {
                return null
            }

            override fun GetDefaultSpeedUnit(): DefaultUnits.SpeedUnits? {
                return null
            }

            override fun GetDefaultPressureUnit(): DefaultUnits.PressureUnits? {
                return null
            }

        }

        val converter = UnitConverter()
        assertEquals(converter.GetTempInPreferredUnits(null, null, null), null)
        assertEquals(converter.GetTempInPreferredUnits(null, null, DefaultUnits.TempUnits.kFahrenheit), null)
        assertEquals(converter.GetTempInPreferredUnits(null, defaultUnits, null), null)
        assertEquals(converter.GetTempInPreferredUnits(null, defaultUnits, DefaultUnits.TempUnits.kFahrenheit), null)
        assertEquals(converter.GetTempInPreferredUnits(0.0, null, null), null)
        assertEquals(converter.GetTempInPreferredUnits(0.0, null, DefaultUnits.TempUnits.kFahrenheit), null)
        assertEquals(converter.GetTempInPreferredUnits(0.0, defaultUnits, null), null)
        assertEquals(converter.GetTempInPreferredUnits(0.0, defaultUnits, DefaultUnits.TempUnits.kFahrenheit), null)

        defaultTempUnit = DefaultUnits.TempUnits.kFahrenheit

        assertEquals(converter.GetTempInPreferredUnits(0.0, defaultUnits, DefaultUnits.TempUnits.kFahrenheit), 0.0)
        assertEquals(converter.GetTempInPreferredUnits(-17.77777777777778, defaultUnits, DefaultUnits.TempUnits.kFahrenheit), -17.77777777777778)
        assertEquals(converter.GetTempInPreferredUnits(32.0, defaultUnits, DefaultUnits.TempUnits.kFahrenheit), 32.0)
        assertEquals(converter.GetTempInPreferredUnits(-40.0, defaultUnits, DefaultUnits.TempUnits.kFahrenheit), -40.0)
        assertEquals(converter.GetTempInPreferredUnits(37.77777777777778, defaultUnits, DefaultUnits.TempUnits.kFahrenheit), 37.77777777777778)
        assertEquals(converter.GetTempInPreferredUnits(100.0, defaultUnits, DefaultUnits.TempUnits.kFahrenheit), 100.0)

        defaultTempUnit = DefaultUnits.TempUnits.kCelsius

        assertEquals(converter.GetTempInPreferredUnits(0.0, defaultUnits, DefaultUnits.TempUnits.kFahrenheit), 32.0)
        assertEquals(converter.GetTempInPreferredUnits(-17.77777777777778, defaultUnits, DefaultUnits.TempUnits.kFahrenheit), 0.0)
        assertEquals(converter.GetTempInPreferredUnits(32.0, defaultUnits, DefaultUnits.TempUnits.kFahrenheit), 89.6)
        assertEquals(converter.GetTempInPreferredUnits(-40.0, defaultUnits, DefaultUnits.TempUnits.kFahrenheit), -40.0)
        assertEquals(converter.GetTempInPreferredUnits(37.77777777777778, defaultUnits, DefaultUnits.TempUnits.kFahrenheit), 100.0)
        assertEquals(converter.GetTempInPreferredUnits(100.0, defaultUnits, DefaultUnits.TempUnits.kFahrenheit), 212.0)
    }



    @Test
    fun GetSpeedInPreferredUnitsMpsTests()
    {
        var defaultSpeedUnit: DefaultUnits.SpeedUnits? = null

        val defaultUnits = object: DefaultUnits
        {
            override fun GetDefaultTempUnit(): DefaultUnits.TempUnits? {
                return null
            }

            override fun GetDefaultLengthUnit(): DefaultUnits.LengthUnits? {
                return null
            }

            override fun GetDefaultSpeedUnit(): DefaultUnits.SpeedUnits? {
                return defaultSpeedUnit
            }

            override fun GetDefaultPressureUnit(): DefaultUnits.PressureUnits? {
                return null
            }

        }

        val converter = UnitConverter()
        assertEquals(converter.GetSpeedInPreferredUnits(null, null, null), null)
        assertEquals(converter.GetSpeedInPreferredUnits(null, null, DefaultUnits.SpeedUnits.kMps), null)
        assertEquals(converter.GetSpeedInPreferredUnits(null, defaultUnits, null), null)
        assertEquals(converter.GetSpeedInPreferredUnits(null, defaultUnits, DefaultUnits.SpeedUnits.kMps), null)
        assertEquals(converter.GetSpeedInPreferredUnits(0.0, null, null), null)
        assertEquals(converter.GetSpeedInPreferredUnits(0.0, null, DefaultUnits.SpeedUnits.kMps), null)
        assertEquals(converter.GetSpeedInPreferredUnits(0.0, defaultUnits, null), null)
        assertEquals(converter.GetSpeedInPreferredUnits(32.0, defaultUnits, DefaultUnits.SpeedUnits.kMps), null)

        defaultSpeedUnit = DefaultUnits.SpeedUnits.kMps

        assertEquals(converter.GetSpeedInPreferredUnits(0.0, defaultUnits, DefaultUnits.SpeedUnits.kMps), 0.0)
        assertEquals(converter.GetSpeedInPreferredUnits(100.0, defaultUnits, DefaultUnits.SpeedUnits.kMps), 100.0)
        assertEquals(converter.GetSpeedInPreferredUnits(44.70400000000001, defaultUnits, DefaultUnits.SpeedUnits.kMps), 44.70400000000001)
        assertEquals(converter.GetSpeedInPreferredUnits(-100.0, defaultUnits, DefaultUnits.SpeedUnits.kMps), -100.0)
        assertEquals(converter.GetSpeedInPreferredUnits(-44.70400000000001, defaultUnits, DefaultUnits.SpeedUnits.kMps), -44.70400000000001)

        defaultSpeedUnit = DefaultUnits.SpeedUnits.kKmph

        assertEquals(converter.GetSpeedInPreferredUnits(0.0, defaultUnits, DefaultUnits.SpeedUnits.kMps), 0.0)
        assertEquals(converter.GetSpeedInPreferredUnits(100.0, defaultUnits, DefaultUnits.SpeedUnits.kMps), 27.77777777777778)
        assertEquals(converter.GetSpeedInPreferredUnits(44.70400000000001, defaultUnits, DefaultUnits.SpeedUnits.kMps), 12.417777777777781)
        assertEquals(converter.GetSpeedInPreferredUnits(-100.0, defaultUnits, DefaultUnits.SpeedUnits.kMps), -27.77777777777778)
        assertEquals(converter.GetSpeedInPreferredUnits(-44.70400000000001, defaultUnits, DefaultUnits.SpeedUnits.kMps), -12.417777777777781)

        defaultSpeedUnit = DefaultUnits.SpeedUnits.kMph

        assertEquals(converter.GetSpeedInPreferredUnits(0.0, defaultUnits, DefaultUnits.SpeedUnits.kMps), 0.0)
        assertEquals(converter.GetSpeedInPreferredUnits(100.0, defaultUnits, DefaultUnits.SpeedUnits.kMps), 44.70400000000001)
        assertEquals(converter.GetSpeedInPreferredUnits(44.70400000000001, defaultUnits, DefaultUnits.SpeedUnits.kMps), 19.984476160000007)
        assertEquals(converter.GetSpeedInPreferredUnits(-100.0, defaultUnits, DefaultUnits.SpeedUnits.kMps), -44.70400000000001)
        assertEquals(converter.GetSpeedInPreferredUnits(-44.70400000000001, defaultUnits, DefaultUnits.SpeedUnits.kMps), -19.984476160000007)
    }



    @Test
    fun GetSpeedInPreferredUnitsKmphTests()
    {
        var defaultSpeedUnit: DefaultUnits.SpeedUnits? = null

        val defaultUnits = object: DefaultUnits
        {
            override fun GetDefaultTempUnit(): DefaultUnits.TempUnits? {
                return null
            }

            override fun GetDefaultLengthUnit(): DefaultUnits.LengthUnits? {
                return null
            }

            override fun GetDefaultSpeedUnit(): DefaultUnits.SpeedUnits? {
                return defaultSpeedUnit
            }

            override fun GetDefaultPressureUnit(): DefaultUnits.PressureUnits? {
                return null
            }

        }

        val converter = UnitConverter()
        assertEquals(converter.GetSpeedInPreferredUnits(null, null, null), null)
        assertEquals(converter.GetSpeedInPreferredUnits(null, null, DefaultUnits.SpeedUnits.kKmph), null)
        assertEquals(converter.GetSpeedInPreferredUnits(null, defaultUnits, null), null)
        assertEquals(converter.GetSpeedInPreferredUnits(null, defaultUnits, DefaultUnits.SpeedUnits.kKmph), null)
        assertEquals(converter.GetSpeedInPreferredUnits(0.0, null, null), null)
        assertEquals(converter.GetSpeedInPreferredUnits(0.0, null, DefaultUnits.SpeedUnits.kKmph), null)
        assertEquals(converter.GetSpeedInPreferredUnits(0.0, defaultUnits, null), null)
        assertEquals(converter.GetSpeedInPreferredUnits(32.0, defaultUnits, DefaultUnits.SpeedUnits.kKmph), null)

        defaultSpeedUnit = DefaultUnits.SpeedUnits.kMps

        assertEquals(converter.GetSpeedInPreferredUnits(0.0, defaultUnits, DefaultUnits.SpeedUnits.kKmph), 0.0)
        assertEquals(converter.GetSpeedInPreferredUnits(100.0, defaultUnits, DefaultUnits.SpeedUnits.kKmph), 360.0)
        assertEquals(converter.GetSpeedInPreferredUnits(44.70400000000001, defaultUnits, DefaultUnits.SpeedUnits.kKmph), 160.93440000000004)
        assertEquals(converter.GetSpeedInPreferredUnits(-100.0, defaultUnits, DefaultUnits.SpeedUnits.kKmph), -360.0)
        assertEquals(converter.GetSpeedInPreferredUnits(-44.70400000000001, defaultUnits, DefaultUnits.SpeedUnits.kKmph), -160.93440000000004)

        defaultSpeedUnit = DefaultUnits.SpeedUnits.kKmph

        assertEquals(converter.GetSpeedInPreferredUnits(0.0, defaultUnits, DefaultUnits.SpeedUnits.kKmph), 0.0)
        assertEquals(converter.GetSpeedInPreferredUnits(100.0, defaultUnits, DefaultUnits.SpeedUnits.kKmph), 100.0)
        assertEquals(converter.GetSpeedInPreferredUnits(44.70400000000001, defaultUnits, DefaultUnits.SpeedUnits.kKmph), 44.70400000000001)
        assertEquals(converter.GetSpeedInPreferredUnits(-100.0, defaultUnits, DefaultUnits.SpeedUnits.kKmph), -100.0)
        assertEquals(converter.GetSpeedInPreferredUnits(-44.70400000000001, defaultUnits, DefaultUnits.SpeedUnits.kKmph), -44.70400000000001)

        defaultSpeedUnit = DefaultUnits.SpeedUnits.kMph

        assertEquals(converter.GetSpeedInPreferredUnits(0.0, defaultUnits, DefaultUnits.SpeedUnits.kKmph), 0.0)
        assertEquals(converter.GetSpeedInPreferredUnits(100.0, defaultUnits, DefaultUnits.SpeedUnits.kKmph), 160.9344)
        assertEquals(converter.GetSpeedInPreferredUnits(44.70400000000001, defaultUnits, DefaultUnits.SpeedUnits.kKmph), 71.94411417600001)
        assertEquals(converter.GetSpeedInPreferredUnits(-100.0, defaultUnits, DefaultUnits.SpeedUnits.kKmph), -160.9344)
        assertEquals(converter.GetSpeedInPreferredUnits(-44.70400000000001, defaultUnits, DefaultUnits.SpeedUnits.kKmph), -71.94411417600001)
    }



    @Test
    fun GetSpeedInPreferredUnitsMphTests()
    {
        var defaultSpeedUnit: DefaultUnits.SpeedUnits? = null

        val defaultUnits = object: DefaultUnits
        {
            override fun GetDefaultTempUnit(): DefaultUnits.TempUnits? {
                return null
            }

            override fun GetDefaultLengthUnit(): DefaultUnits.LengthUnits? {
                return null
            }

            override fun GetDefaultSpeedUnit(): DefaultUnits.SpeedUnits? {
                return defaultSpeedUnit
            }

            override fun GetDefaultPressureUnit(): DefaultUnits.PressureUnits? {
                return null
            }

        }

        val converter = UnitConverter()
        assertEquals(converter.GetSpeedInPreferredUnits(null, null, null), null)
        assertEquals(converter.GetSpeedInPreferredUnits(null, null, DefaultUnits.SpeedUnits.kMph), null)
        assertEquals(converter.GetSpeedInPreferredUnits(null, defaultUnits, null), null)
        assertEquals(converter.GetSpeedInPreferredUnits(null, defaultUnits, DefaultUnits.SpeedUnits.kMph), null)
        assertEquals(converter.GetSpeedInPreferredUnits(0.0, null, null), null)
        assertEquals(converter.GetSpeedInPreferredUnits(0.0, null, DefaultUnits.SpeedUnits.kMph), null)
        assertEquals(converter.GetSpeedInPreferredUnits(0.0, defaultUnits, null), null)
        assertEquals(converter.GetSpeedInPreferredUnits(32.0, defaultUnits, DefaultUnits.SpeedUnits.kMph), null)

        defaultSpeedUnit = DefaultUnits.SpeedUnits.kMps

        assertEquals(converter.GetSpeedInPreferredUnits(0.0, defaultUnits, DefaultUnits.SpeedUnits.kMph), 0.0)
        assertEquals(converter.GetSpeedInPreferredUnits(100.0, defaultUnits, DefaultUnits.SpeedUnits.kMph), 223.6936292054402)
        assertEquals(converter.GetSpeedInPreferredUnits(44.70400000000001, defaultUnits, DefaultUnits.SpeedUnits.kMph), 100.0)
        assertEquals(converter.GetSpeedInPreferredUnits(-100.0, defaultUnits, DefaultUnits.SpeedUnits.kMph), -223.6936292054402)
        assertEquals(converter.GetSpeedInPreferredUnits(-44.70400000000001, defaultUnits, DefaultUnits.SpeedUnits.kMph), -100.0)


        defaultSpeedUnit = DefaultUnits.SpeedUnits.kKmph

        assertEquals(converter.GetSpeedInPreferredUnits(0.0, defaultUnits, DefaultUnits.SpeedUnits.kMph), 0.0)
        assertEquals(converter.GetSpeedInPreferredUnits(100.0, defaultUnits, DefaultUnits.SpeedUnits.kMph), 62.13711922373339)
        assertEquals(converter.GetSpeedInPreferredUnits(44.70400000000001, defaultUnits, DefaultUnits.SpeedUnits.kMph), 27.777777777777782)
        assertEquals(converter.GetSpeedInPreferredUnits(-100.0, defaultUnits, DefaultUnits.SpeedUnits.kMph), -62.13711922373339)
        assertEquals(converter.GetSpeedInPreferredUnits(-44.70400000000001, defaultUnits, DefaultUnits.SpeedUnits.kMph), -27.777777777777782)

        defaultSpeedUnit = DefaultUnits.SpeedUnits.kMph

        assertEquals(converter.GetSpeedInPreferredUnits(0.0, defaultUnits, DefaultUnits.SpeedUnits.kMph), 0.0)
        assertEquals(converter.GetSpeedInPreferredUnits(100.0, defaultUnits, DefaultUnits.SpeedUnits.kMph), 100.0)
        assertEquals(converter.GetSpeedInPreferredUnits(44.70400000000001, defaultUnits, DefaultUnits.SpeedUnits.kMph), 44.70400000000001)
        assertEquals(converter.GetSpeedInPreferredUnits(-100.0, defaultUnits, DefaultUnits.SpeedUnits.kMph), -100.0)
        assertEquals(converter.GetSpeedInPreferredUnits(-44.70400000000001, defaultUnits, DefaultUnits.SpeedUnits.kMph), -44.70400000000001)
    }



    @Test
    fun GetLengthInPreferredUnitsInTests()
    {
        var defaultLengthUnit: DefaultUnits.LengthUnits? = null

        val defaultUnits = object: DefaultUnits
        {
            override fun GetDefaultTempUnit(): DefaultUnits.TempUnits? {
                return null
            }

            override fun GetDefaultLengthUnit(): DefaultUnits.LengthUnits? {
                return defaultLengthUnit
            }

            override fun GetDefaultSpeedUnit(): DefaultUnits.SpeedUnits? {
                return null
            }

            override fun GetDefaultPressureUnit(): DefaultUnits.PressureUnits? {
                return null
            }

        }

        val converter = UnitConverter()
        assertEquals(converter.GetLengthInPreferredUnits(null, null, null), null)
        assertEquals(converter.GetLengthInPreferredUnits(null, null, DefaultUnits.LengthUnits.kIn), null)
        assertEquals(converter.GetLengthInPreferredUnits(null, defaultUnits, null), null)
        assertEquals(converter.GetLengthInPreferredUnits(null, defaultUnits, DefaultUnits.LengthUnits.kIn), null)
        assertEquals(converter.GetLengthInPreferredUnits(0.0, null, null), null)
        assertEquals(converter.GetLengthInPreferredUnits(0.0, null, DefaultUnits.LengthUnits.kIn), null)
        assertEquals(converter.GetLengthInPreferredUnits(0.0, defaultUnits, null), null)
        assertEquals(converter.GetLengthInPreferredUnits(0.0, defaultUnits, DefaultUnits.LengthUnits.kIn), null)

        defaultLengthUnit = DefaultUnits.LengthUnits.kIn

        assertEquals(converter.GetLengthInPreferredUnits(0.0, defaultUnits, DefaultUnits.LengthUnits.kIn), 0.0)
        assertEquals(converter.GetLengthInPreferredUnits(100.0, defaultUnits, DefaultUnits.LengthUnits.kIn), 100.0)
        assertEquals(converter.GetLengthInPreferredUnits(3.93700787401575, defaultUnits, DefaultUnits.LengthUnits.kIn), 3.93700787401575)
        assertEquals(converter.GetLengthInPreferredUnits(-100.0, defaultUnits, DefaultUnits.LengthUnits.kIn), -100.0)
        assertEquals(converter.GetLengthInPreferredUnits(-3.93700787401575, defaultUnits, DefaultUnits.LengthUnits.kIn), -3.93700787401575)
        assertEquals(converter.GetLengthInPreferredUnits(2539.9999999999986, defaultUnits, DefaultUnits.LengthUnits.kIn), 2539.9999999999986)
        assertEquals(converter.GetLengthInPreferredUnits(-2539.9999999999986, defaultUnits, DefaultUnits.LengthUnits.kIn), -2539.9999999999986)

        defaultLengthUnit = DefaultUnits.LengthUnits.kMm

        assertEquals(converter.GetLengthInPreferredUnits(0.0, defaultUnits, DefaultUnits.LengthUnits.kIn), 0.0)
        assertEquals(converter.GetLengthInPreferredUnits(100.0, defaultUnits, DefaultUnits.LengthUnits.kIn), 3.93700787401575)
        assertEquals(converter.GetLengthInPreferredUnits(3.93700787401575, defaultUnits, DefaultUnits.LengthUnits.kIn), 0.15500031000062015)
        assertEquals(converter.GetLengthInPreferredUnits(-100.0, defaultUnits, DefaultUnits.LengthUnits.kIn), -3.93700787401575)
        assertEquals(converter.GetLengthInPreferredUnits(-3.93700787401575, defaultUnits, DefaultUnits.LengthUnits.kIn), -0.15500031000062015)
        assertEquals(converter.GetLengthInPreferredUnits(2539.9999999999986, defaultUnits, DefaultUnits.LengthUnits.kIn), 100.0)
        assertEquals(converter.GetLengthInPreferredUnits(-2539.9999999999986, defaultUnits, DefaultUnits.LengthUnits.kIn), -100.0)
    }



    @Test
    fun GetLengthInPreferredUnitsMmTests()
    {
        var defaultLengthUnit: DefaultUnits.LengthUnits? = null

        val defaultUnits = object: DefaultUnits
        {
            override fun GetDefaultTempUnit(): DefaultUnits.TempUnits? {
                return null
            }

            override fun GetDefaultLengthUnit(): DefaultUnits.LengthUnits? {
                return defaultLengthUnit
            }

            override fun GetDefaultSpeedUnit(): DefaultUnits.SpeedUnits? {
                return null
            }

            override fun GetDefaultPressureUnit(): DefaultUnits.PressureUnits? {
                return null
            }

        }

        val converter = UnitConverter()
        assertEquals(converter.GetLengthInPreferredUnits(null, null, null), null)
        assertEquals(converter.GetLengthInPreferredUnits(null, null, DefaultUnits.LengthUnits.kMm), null)
        assertEquals(converter.GetLengthInPreferredUnits(null, defaultUnits, null), null)
        assertEquals(converter.GetLengthInPreferredUnits(null, defaultUnits, DefaultUnits.LengthUnits.kMm), null)
        assertEquals(converter.GetLengthInPreferredUnits(0.0, null, null), null)
        assertEquals(converter.GetLengthInPreferredUnits(0.0, null, DefaultUnits.LengthUnits.kMm), null)
        assertEquals(converter.GetLengthInPreferredUnits(0.0, defaultUnits, null), null)
        assertEquals(converter.GetLengthInPreferredUnits(0.0, defaultUnits, DefaultUnits.LengthUnits.kMm), null)

        defaultLengthUnit = DefaultUnits.LengthUnits.kIn

        assertEquals(converter.GetLengthInPreferredUnits(0.0, defaultUnits, DefaultUnits.LengthUnits.kMm), 0.0)
        assertEquals(converter.GetLengthInPreferredUnits(100.0, defaultUnits, DefaultUnits.LengthUnits.kMm), 2539.9999999999986)
        assertEquals(converter.GetLengthInPreferredUnits(3.93700787401575, defaultUnits, DefaultUnits.LengthUnits.kMm), 100.0)
        assertEquals(converter.GetLengthInPreferredUnits(-100.0, defaultUnits, DefaultUnits.LengthUnits.kMm), -2539.9999999999986)
        assertEquals(converter.GetLengthInPreferredUnits(-3.93700787401575, defaultUnits, DefaultUnits.LengthUnits.kMm), -100.0)
        assertEquals(converter.GetLengthInPreferredUnits(2539.9999999999986, defaultUnits, DefaultUnits.LengthUnits.kMm), 64515.999999999935)
        assertEquals(converter.GetLengthInPreferredUnits(-2539.9999999999986, defaultUnits, DefaultUnits.LengthUnits.kMm), -64515.999999999935)

        defaultLengthUnit = DefaultUnits.LengthUnits.kMm

        assertEquals(converter.GetLengthInPreferredUnits(0.0, defaultUnits, DefaultUnits.LengthUnits.kMm), 0.0)
        assertEquals(converter.GetLengthInPreferredUnits(100.0, defaultUnits, DefaultUnits.LengthUnits.kMm), 100.0)
        assertEquals(converter.GetLengthInPreferredUnits(3.93700787401575, defaultUnits, DefaultUnits.LengthUnits.kMm), 3.93700787401575)
        assertEquals(converter.GetLengthInPreferredUnits(-100.0, defaultUnits, DefaultUnits.LengthUnits.kMm), -100.0)
        assertEquals(converter.GetLengthInPreferredUnits(-3.93700787401575, defaultUnits, DefaultUnits.LengthUnits.kMm), -3.93700787401575)
        assertEquals(converter.GetLengthInPreferredUnits(2539.9999999999986, defaultUnits, DefaultUnits.LengthUnits.kMm), 2539.9999999999986)
        assertEquals(converter.GetLengthInPreferredUnits(-2539.9999999999986, defaultUnits, DefaultUnits.LengthUnits.kMm), -2539.9999999999986)
    }



    @Test
    fun GetPressureInPreferredUnitsInHgTests()
    {
        var defaultPressureUnit: DefaultUnits.PressureUnits? = null

        val defaultUnits = object: DefaultUnits
        {
            override fun GetDefaultTempUnit(): DefaultUnits.TempUnits? {
                return null
            }

            override fun GetDefaultLengthUnit(): DefaultUnits.LengthUnits? {
                return null
            }

            override fun GetDefaultSpeedUnit(): DefaultUnits.SpeedUnits? {
                return null
            }

            override fun GetDefaultPressureUnit(): DefaultUnits.PressureUnits? {
                return defaultPressureUnit
            }

        }

        val converter = UnitConverter()
        assertEquals(converter.GetPressureInPreferredUnits(null, null, null), null)
        assertEquals(converter.GetPressureInPreferredUnits(null, null, DefaultUnits.PressureUnits.kInHg), null)
        assertEquals(converter.GetPressureInPreferredUnits(null, defaultUnits, null), null)
        assertEquals(converter.GetPressureInPreferredUnits(null, defaultUnits, DefaultUnits.PressureUnits.kInHg), null)
        assertEquals(converter.GetPressureInPreferredUnits(0.0, null, null), null)
        assertEquals(converter.GetPressureInPreferredUnits(0.0, null, DefaultUnits.PressureUnits.kInHg), null)
        assertEquals(converter.GetPressureInPreferredUnits(0.0, defaultUnits, null), null)
        assertEquals(converter.GetPressureInPreferredUnits(0.0, defaultUnits, DefaultUnits.PressureUnits.kInHg), null)

        defaultPressureUnit = DefaultUnits.PressureUnits.kInHg

        assertEquals(converter.GetPressureInPreferredUnits(0.0, defaultUnits, DefaultUnits.PressureUnits.kInHg), 0.0)
        assertEquals(converter.GetPressureInPreferredUnits(3386.3900000000003, defaultUnits, DefaultUnits.PressureUnits.kInHg), 3386.3900000000003)
        assertEquals(converter.GetPressureInPreferredUnits(100.0, defaultUnits, DefaultUnits.PressureUnits.kInHg), 100.0)
        assertEquals(converter.GetPressureInPreferredUnits(2.952997144451761, defaultUnits, DefaultUnits.PressureUnits.kInHg), 2.952997144451761)
        assertEquals(converter.GetPressureInPreferredUnits(-3386.3900000000003, defaultUnits, DefaultUnits.PressureUnits.kInHg), -3386.3900000000003)
        assertEquals(converter.GetPressureInPreferredUnits(-100.0, defaultUnits, DefaultUnits.PressureUnits.kInHg), -100.0)
        assertEquals(converter.GetPressureInPreferredUnits(-2.952997144451761, defaultUnits, DefaultUnits.PressureUnits.kInHg), -2.952997144451761)
        assertEquals(converter.GetPressureInPreferredUnits(29.411851558739543, defaultUnits, DefaultUnits.PressureUnits.kInHg), 29.411851558739543)
        assertEquals(converter.GetPressureInPreferredUnits(996.0, defaultUnits, DefaultUnits.PressureUnits.kInHg), 996.0)
        assertEquals(converter.GetPressureInPreferredUnits(30.0, defaultUnits, DefaultUnits.PressureUnits.kInHg), 30.0)
        assertEquals(converter.GetPressureInPreferredUnits(1015.917, defaultUnits, DefaultUnits.PressureUnits.kInHg), 1015.917)
        assertEquals(converter.GetPressureInPreferredUnits(29.92124356615747, defaultUnits, DefaultUnits.PressureUnits.kInHg), 29.92124356615747)
        assertEquals(converter.GetPressureInPreferredUnits(1013.25, defaultUnits, DefaultUnits.PressureUnits.kInHg), 1013.25)

        defaultPressureUnit = DefaultUnits.PressureUnits.kMb

        assertEquals(converter.GetPressureInPreferredUnits(0.0, defaultUnits, DefaultUnits.PressureUnits.kInHg), 0.0)
        assertEquals(converter.GetPressureInPreferredUnits(3386.3900000000003, defaultUnits, DefaultUnits.PressureUnits.kInHg), 100.0)
        assertEquals(converter.GetPressureInPreferredUnits(100.0, defaultUnits, DefaultUnits.PressureUnits.kInHg), 2.952997144451761)
        assertEquals(converter.GetPressureInPreferredUnits(2.952997144451761, defaultUnits, DefaultUnits.PressureUnits.kInHg), 0.08720192135140256)
        assertEquals(converter.GetPressureInPreferredUnits(-3386.3900000000003, defaultUnits, DefaultUnits.PressureUnits.kInHg), -100.0)
        assertEquals(converter.GetPressureInPreferredUnits(-100.0, defaultUnits, DefaultUnits.PressureUnits.kInHg), -2.952997144451761)
        assertEquals(converter.GetPressureInPreferredUnits(-2.952997144451761, defaultUnits, DefaultUnits.PressureUnits.kInHg), -0.08720192135140256)
        assertEquals(converter.GetPressureInPreferredUnits(29.411851558739543, defaultUnits, DefaultUnits.PressureUnits.kInHg), 0.8685311366599695)
        assertEquals(converter.GetPressureInPreferredUnits(996.0, defaultUnits, DefaultUnits.PressureUnits.kInHg), 29.411851558739543)
        assertEquals(converter.GetPressureInPreferredUnits(30.0, defaultUnits, DefaultUnits.PressureUnits.kInHg), 0.8858991433355283)
        assertEquals(converter.GetPressureInPreferredUnits(1015.917, defaultUnits, DefaultUnits.PressureUnits.kInHg), 30.0)
        assertEquals(converter.GetPressureInPreferredUnits(29.92124356615747, defaultUnits, DefaultUnits.PressureUnits.kInHg), 0.8835734680930865)
        assertEquals(converter.GetPressureInPreferredUnits(1013.25, defaultUnits, DefaultUnits.PressureUnits.kInHg), 29.92124356615747)
    }


    @Test
    fun GetPressureInPreferredUnitsMbTests()
    {
        var defaultPressureUnit: DefaultUnits.PressureUnits? = null

        val defaultUnits = object: DefaultUnits
        {
            override fun GetDefaultTempUnit(): DefaultUnits.TempUnits? {
                return null
            }

            override fun GetDefaultLengthUnit(): DefaultUnits.LengthUnits? {
                return null
            }

            override fun GetDefaultSpeedUnit(): DefaultUnits.SpeedUnits? {
                return null
            }

            override fun GetDefaultPressureUnit(): DefaultUnits.PressureUnits? {
                return defaultPressureUnit
            }

        }

        val converter = UnitConverter()
        assertEquals(converter.GetPressureInPreferredUnits(null, null, null), null)
        assertEquals(converter.GetPressureInPreferredUnits(null, null, DefaultUnits.PressureUnits.kMb), null)
        assertEquals(converter.GetPressureInPreferredUnits(null, defaultUnits, null), null)
        assertEquals(converter.GetPressureInPreferredUnits(null, defaultUnits, DefaultUnits.PressureUnits.kMb), null)
        assertEquals(converter.GetPressureInPreferredUnits(0.0, null, null), null)
        assertEquals(converter.GetPressureInPreferredUnits(0.0, null, DefaultUnits.PressureUnits.kMb), null)
        assertEquals(converter.GetPressureInPreferredUnits(0.0, defaultUnits, null), null)
        assertEquals(converter.GetPressureInPreferredUnits(0.0, defaultUnits, DefaultUnits.PressureUnits.kMb), null)

        defaultPressureUnit = DefaultUnits.PressureUnits.kInHg


        assertEquals(converter.GetPressureInPreferredUnits(0.0, defaultUnits, DefaultUnits.PressureUnits.kMb), 0.0)
        assertEquals(converter.GetPressureInPreferredUnits(100.0, defaultUnits, DefaultUnits.PressureUnits.kMb), 3386.3900000000003)
        assertEquals(converter.GetPressureInPreferredUnits(2.952997144451761, defaultUnits, DefaultUnits.PressureUnits.kMb), 100.0)
        assertEquals(converter.GetPressureInPreferredUnits(-100.0, defaultUnits, DefaultUnits.PressureUnits.kMb), -3386.3900000000003)
        assertEquals(converter.GetPressureInPreferredUnits(-2.952997144451761, defaultUnits, DefaultUnits.PressureUnits.kMb), -100.0)
        assertEquals(converter.GetPressureInPreferredUnits(29.411851558739543, defaultUnits, DefaultUnits.PressureUnits.kMb), 996.0)
        assertEquals(converter.GetPressureInPreferredUnits(996.0, defaultUnits, DefaultUnits.PressureUnits.kMb), 33728.4444)
        assertEquals(converter.GetPressureInPreferredUnits(30.0, defaultUnits, DefaultUnits.PressureUnits.kMb), 1015.917)
        assertEquals(converter.GetPressureInPreferredUnits(1015.917, defaultUnits, DefaultUnits.PressureUnits.kMb), 34402.911696300005)
        assertEquals(converter.GetPressureInPreferredUnits(29.92124356615747, defaultUnits, DefaultUnits.PressureUnits.kMb), 1013.25)
        assertEquals(converter.GetPressureInPreferredUnits(1013.25, defaultUnits, DefaultUnits.PressureUnits.kMb), 34312.596675)

        defaultPressureUnit = DefaultUnits.PressureUnits.kMb

        assertEquals(converter.GetPressureInPreferredUnits(0.0, defaultUnits, DefaultUnits.PressureUnits.kMb), 0.0)
        assertEquals(converter.GetPressureInPreferredUnits(100.0, defaultUnits, DefaultUnits.PressureUnits.kMb), 100.0)
        assertEquals(converter.GetPressureInPreferredUnits(2.952997144451761, defaultUnits, DefaultUnits.PressureUnits.kMb), 2.952997144451761)
        assertEquals(converter.GetPressureInPreferredUnits(-100.0, defaultUnits, DefaultUnits.PressureUnits.kMb), -100.0)
        assertEquals(converter.GetPressureInPreferredUnits(-2.952997144451761, defaultUnits, DefaultUnits.PressureUnits.kMb), -2.952997144451761)
        assertEquals(converter.GetPressureInPreferredUnits(29.411851558739543, defaultUnits, DefaultUnits.PressureUnits.kMb), 29.411851558739543)
        assertEquals(converter.GetPressureInPreferredUnits(996.0, defaultUnits, DefaultUnits.PressureUnits.kMb), 996.0)
        assertEquals(converter.GetPressureInPreferredUnits(30.0, defaultUnits, DefaultUnits.PressureUnits.kMb), 30.0)
        assertEquals(converter.GetPressureInPreferredUnits(1015.917, defaultUnits, DefaultUnits.PressureUnits.kMb), 1015.917)
        assertEquals(converter.GetPressureInPreferredUnits(29.92124356615747, defaultUnits, DefaultUnits.PressureUnits.kMb), 29.92124356615747)
        assertEquals(converter.GetPressureInPreferredUnits(1013.25, defaultUnits, DefaultUnits.PressureUnits.kMb), 1013.25)
    }



    @Test
    fun GetCompassDirectionsTests()
    {
        val converter = UnitConverter()

        assertNull(converter.GetCompassDirection(null))
        assertEquals(converter.GetCompassDirection(0.0), DefaultUnits.CompassDirections.N)
        assertEquals(converter.GetCompassDirection(22.5), DefaultUnits.CompassDirections.NNE)
        assertEquals(converter.GetCompassDirection(45.0), DefaultUnits.CompassDirections.NE)
        assertEquals(converter.GetCompassDirection(67.5), DefaultUnits.CompassDirections.ENE)
        assertEquals(converter.GetCompassDirection(90.0), DefaultUnits.CompassDirections.E)
        assertEquals(converter.GetCompassDirection(112.5), DefaultUnits.CompassDirections.ESE)
        assertEquals(converter.GetCompassDirection(135.0), DefaultUnits.CompassDirections.SE)
        assertEquals(converter.GetCompassDirection(157.5), DefaultUnits.CompassDirections.SSE)
        assertEquals(converter.GetCompassDirection(180.0), DefaultUnits.CompassDirections.S)
        assertEquals(converter.GetCompassDirection(202.5), DefaultUnits.CompassDirections.SSW)
        assertEquals(converter.GetCompassDirection(225.0), DefaultUnits.CompassDirections.SW)
        assertEquals(converter.GetCompassDirection(247.5), DefaultUnits.CompassDirections.WSW)
        assertEquals(converter.GetCompassDirection(270.0), DefaultUnits.CompassDirections.W)
        assertEquals(converter.GetCompassDirection(292.5), DefaultUnits.CompassDirections.WNW)
        assertEquals(converter.GetCompassDirection(315.0), DefaultUnits.CompassDirections.NW)
        assertEquals(converter.GetCompassDirection(337.5), DefaultUnits.CompassDirections.NNW)
        assertEquals(converter.GetCompassDirection(360.0), DefaultUnits.CompassDirections.N)

        assertEquals(converter.GetCompassDirection(-150.0), DefaultUnits.CompassDirections.SSW)
        assertEquals(converter.GetCompassDirection(1000.0), DefaultUnits.CompassDirections.W)
    }
}