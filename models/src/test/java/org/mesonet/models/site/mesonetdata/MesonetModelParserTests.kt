package org.mesonet.models.site.mesonetdata

import junit.framework.TestCase.*
import org.junit.Test

class MesonetModelParserTests
{
    @Test
    fun EmptyDataMesonetParserTest()
    {
        val parser = MesonetModelParser()

        val model = parser.CreateMesonetData("")

        assertNotNull(model)
        assertNull(model?.GetTime())
        assertNull(model.GetRelH())
        assertNull(model.GetTAir())
        assertNull(model.GetWSpd())
        assertNull(model.GetWDir())
        assertNull(model.GetWMax())
        assertNull(model.GetPres())
        assertNull(model.GetRain24H())
        assertNull(model.GetStID())
    }


    @Test
    fun BadDataMesoentParserTest()
    {
        val parser = MesonetModelParser()

        val model = parser.CreateMesonetData("ERROR!!! ERROR!!! ERROR!!!")

        assertNotNull(model)
        assertNull(model?.GetTime())
        assertNull(model.GetRelH())
        assertNull(model.GetTAir())
        assertNull(model.GetWSpd())
        assertNull(model.GetWDir())
        assertNull(model.GetWMax())
        assertNull(model.GetPres())
        assertNull(model.GetRain24H())
        assertNull(model.GetStID())
    }



    @Test
    fun InterruptedDataMesoentParserTest()
    {
        val parser = MesonetModelParser()

        val model = parser.CreateMesonetData("STNM=121,TIME=1526133900,RELH=80,TAIR=22.4,WSPD=7.3,WVEC=7.1,WDIR=190,WDSD=12.4,WSSD=1.5,WMAX=11.6,RAIN=0.00,PRES=970.80,SRAD=464,TA9M=22.1,WS2M=5.6,TS10=20.9,ERROR!!! ERROR!!! ERROR!!!, TB10=22.0,TS05=20.9,TS25=20.5,TS60=18.4,TR05=2.3,TR25=1.5,TR60=1.5,RAIN_24H=1.23,RAIN_24=0.00,STID=NRMN,YEAR=2018,MONTH=05,DAY=12,HOUR=14,MINUTE=05,SECOND=0")

        assertNotNull(model)
        assertNotNull(model?.GetTime())
        assertEquals(model.GetTime(), 1526133900L)
        assertNotNull(model.GetRelH())
        assertEquals(model.GetRelH(), 80.0)
        assertNotNull(model.GetTAir())
        assertEquals(model.GetTAir(), 22.4)
        assertNotNull(model.GetWSpd())
        assertEquals(model.GetWSpd(), 7.3)
        assertNotNull(model.GetWDir())
        assertEquals(model.GetWDir(), 190.0)
        assertNotNull(model.GetWMax())
        assertEquals(model.GetWMax(), 11.6)
        assertNotNull(model.GetPres())
        assertEquals(model.GetPres(), 970.80)
        assertNotNull(model.GetRain24H())
        assertEquals(model.GetRain24H(), 1.23)
        assertNotNull(model.GetStID())
        assertEquals(model.GetStID(), "NRMN")
    }



    @Test
    fun EmptyFieldMesoentParserTest()
    {
        val parser = MesonetModelParser()

        val model = parser.CreateMesonetData("STNM=121,TIME=1526133900,RELH=80,TAIR=22.4,WSPD=7.3,WVEC=7.1,WDIR=190,WDSD=12.4,WSSD=1.5,WMAX=11.6,RAIN=0.00,PRES=,SRAD=464,TA9M=22.1,WS2M=5.6,TS10=20.9,ERROR!!! ERROR!!! ERROR!!!, TB10=22.0,TS05=20.9,TS25=20.5,TS60=18.4,TR05=2.3,TR25=1.5,TR60=1.5,RAIN_24H=1.23,RAIN_24=0.00,STID=NRMN,YEAR=2018,MONTH=05,DAY=12,HOUR=14,MINUTE=05,SECOND=0")

        assertNotNull(model)
        assertNotNull(model?.GetTime())
        assertEquals(model.GetTime(), 1526133900L)
        assertNotNull(model.GetRelH())
        assertEquals(model.GetRelH(), 80.0)
        assertNotNull(model.GetTAir())
        assertEquals(model.GetTAir(), 22.4)
        assertNotNull(model.GetWSpd())
        assertEquals(model.GetWSpd(), 7.3)
        assertNotNull(model.GetWDir())
        assertEquals(model.GetWDir(), 190.0)
        assertNotNull(model.GetWMax())
        assertEquals(model.GetWMax(), 11.6)
        assertNull(model.GetPres())
        assertNotNull(model.GetRain24H())
        assertEquals(model.GetRain24H(), 1.23)
        assertNotNull(model.GetStID())
        assertEquals(model.GetStID(), "NRMN")
    }


    @Test
    fun BadFieldMesoentParserTest()
    {
        val parser = MesonetModelParser()

        val model = parser.CreateMesonetData("STNM=121,TIME=1526133900,RELH=80,TAIR=22.4,WSPD=ERROR!!! ERROR!!! ERROR!!!,WVEC=7.1,WDIR=190,WDSD=12.4,WSSD=1.5,WMAX=11.6,RAIN=0.00,PRES=970.80,SRAD=464,TA9M=22.1,WS2M=5.6,TS10=20.9,TB10=22.0,TS05=20.9,TS25=20.5,TS60=18.4,TR05=2.3,TR25=1.5,TR60=1.5,RAIN_24H=1.23,RAIN_24=0.00,STID=NRMN,YEAR=2018,MONTH=05,DAY=12,HOUR=14,MINUTE=05,SECOND=0")

        assertNotNull(model)
        assertNotNull(model?.GetTime())
        assertEquals(model.GetTime(), 1526133900L)
        assertNotNull(model.GetRelH())
        assertEquals(model.GetRelH(), 80.0)
        assertNotNull(model.GetTAir())
        assertEquals(model.GetTAir(), 22.4)
        assertNull(model.GetWSpd())
        assertNotNull(model.GetWDir())
        assertEquals(model.GetWDir(), 190.0)
        assertNotNull(model.GetWMax())
        assertEquals(model.GetWMax(), 11.6)
        assertNotNull(model.GetPres())
        assertEquals(model.GetPres(), 970.80)
        assertNotNull(model.GetRain24H())
        assertEquals(model.GetRain24H(), 1.23)
        assertNotNull(model.GetStID())
        assertEquals(model.GetStID(), "NRMN")
    }


    @Test
    fun GoodDataMesoentParserTest()
    {
        val parser = MesonetModelParser()

        val model = parser.CreateMesonetData("STNM=121,TIME=1526133900,RELH=80,TAIR=22.4,WSPD=7.3,WVEC=7.1,WDIR=190,WDSD=12.4,WSSD=1.5,WMAX=11.6,RAIN=0.00,PRES=970.80,SRAD=464,TA9M=22.1,WS2M=5.6,TS10=20.9,TB10=22.0,TS05=20.9,TS25=20.5,TS60=18.4,TR05=2.3,TR25=1.5,TR60=1.5,RAIN_24H=1.23,RAIN_24=0.00,STID=NRMN,YEAR=2018,MONTH=05,DAY=12,HOUR=14,MINUTE=05,SECOND=0")

        assertNotNull(model)
        assertNotNull(model?.GetTime())
        assertEquals(model.GetTime(), 1526133900L)
        assertNotNull(model.GetRelH())
        assertEquals(model.GetRelH(), 80.0)
        assertNotNull(model.GetTAir())
        assertEquals(model.GetTAir(), 22.4)
        assertNotNull(model.GetWSpd())
        assertEquals(model.GetWSpd(), 7.3)
        assertNotNull(model.GetWDir())
        assertEquals(model.GetWDir(), 190.0)
        assertNotNull(model.GetWMax())
        assertEquals(model.GetWMax(), 11.6)
        assertNotNull(model.GetPres())
        assertEquals(model.GetPres(), 970.80)
        assertNotNull(model.GetRain24H())
        assertEquals(model.GetRain24H(), 1.23)
        assertNotNull(model.GetStID())
        assertEquals(model.GetStID(), "NRMN")
    }
}