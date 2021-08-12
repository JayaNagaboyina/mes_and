package org.mesonet.models.site


import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import org.junit.Test
import org.mesonet.models.site.MesonetSiteListModel


class MesonetSiteListModelTests
{
    @Test fun NoValueSetTests()
    {
        val model = MesonetSiteListModel.MesonetSiteModel()

        val stnm = model.GetStnm()
        val name = model.GetName()
        val lat = model.GetLat()
        val lon = model.GetLon()
        val elev = model.GetElev()
        val datd = model.GetDatd()

        assertEquals(stnm, model.mStnm)
        assertEquals(name, model.mName)
        assertEquals(lat, model.mLat)
        assertEquals(lon, model.mLon)
        assertEquals(elev, model.mElev)
        assertEquals(datd, model.mDatd)
    }


    @Test fun NullSetTests()
    {
        val model = MesonetSiteListModel.MesonetSiteModel()

        model.mStnm = null
        model.mName = null
        model.mLat = null
        model.mLon = null
        model.mElev = null
        model.mDatd = null

        val stnm = model.GetStnm()
        val name = model.GetName()
        val lat = model.GetLat()
        val lon = model.GetLon()
        val elev = model.GetElev()
        val datd = model.GetDatd()

        assertEquals(stnm, model.mStnm)
        assertEquals(name, model.mName)
        assertEquals(lat, model.mLat)
        assertEquals(lon, model.mLon)
        assertEquals(elev, model.mElev)
        assertEquals(datd, model.mDatd)
    }



    @Test fun ValueSetTests()
    {
        val model = MesonetSiteListModel.MesonetSiteModel()

        model.mStnm = "nrmn"
        model.mName = "Norman"
        model.mLat = "35.2349"
        model.mLon = "-97.4643"
        model.mElev = "15"
        model.mDatd = "1234567890"

        val stnm = model.GetStnm()
        val name = model.GetName()
        val lat = model.GetLat()
        val lon = model.GetLon()
        val elev = model.GetElev()
        val datd = model.GetDatd()

        assertEquals(stnm, model.mStnm)
        assertEquals(name, model.mName)
        assertEquals(lat, model.mLat)
        assertEquals(lon, model.mLon)
        assertEquals(elev, model.mElev)
        assertEquals(datd, model.mDatd)

        assertEquals(stnm, "nrmn")
        assertEquals(name, "Norman")
        assertEquals(lat, "35.2349")
        assertEquals(lon, "-97.4643")
        assertEquals(elev, "15")
        assertEquals(datd, "1234567890")
    }



    @Test fun MethodSetNullTests()
    {
        val model = MesonetSiteListModel.MesonetSiteModel()

        model.SetStnm(null)
        model.SetName(null)
        model.SetLat(null)
        model.SetLon(null)
        model.SetElev(null)

        val stnm = model.GetStnm()
        val name = model.GetName()
        val lat = model.GetLat()
        val lon = model.GetLon()
        val elev = model.GetElev()

        assertEquals(stnm, model.mStnm)
        assertEquals(name, model.mName)
        assertEquals(lat, model.mLat)
        assertEquals(lon, model.mLon)
        assertEquals(elev, model.mElev)

        assertNull(stnm)
        assertNull(name)
        assertNull(lat)
        assertNull(lon)
        assertNull(elev)
    }



    @Test fun MethodSetTests()
    {
        val model = MesonetSiteListModel.MesonetSiteModel()

        model.SetStnm("nrmn")
        model.SetName("Norman")
        model.SetLat("35.2349")
        model.SetLon("-97.4643")
        model.SetElev("15")

        val stnm = model.GetStnm()
        val name = model.GetName()
        val lat = model.GetLat()
        val lon = model.GetLon()
        val elev = model.GetElev()

        assertEquals(stnm, model.mStnm)
        assertEquals(name, model.mName)
        assertEquals(lat, model.mLat)
        assertEquals(lon, model.mLon)
        assertEquals(elev, model.mElev)
    }
}