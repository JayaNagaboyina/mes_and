package org.mesonet.models.site.mesonetdata

import org.mesonet.core.DefaultUnits


class MesonetModel : MesonetData
{
    override fun compareTo(other: MesonetData): Int {
        var result = 0

        if(GetStID() != null && other.GetStID() == null)
            result = -1

        else if(GetStID() == null && other.GetStID() != null)
            result = 1

        else if(GetStID() != null && other.GetStID() != null)
            result = GetStID()?.compareTo(other.GetStID()?: "zzzzz") ?: 1

        if(result == 0)
        {
            if(GetTime() != null && other.GetTime() == null)
                result = -1

            else if(GetTime() == null && other.GetTime() != null)
                result = 1

            else if(GetTime() != null && other.GetTime() != null)
                result = GetTime()?.compareTo(other.GetTime()?: Long.MAX_VALUE) ?: 1
        }

        if(result == 0)
        {
            if(GetRelH() != null && other.GetRelH() == null)
                result = -1

            else if(GetRelH() == null && other.GetRelH() != null)
                result = 1

            else if(GetRelH() != null && other.GetRelH() != null)
                result = GetRelH()?.toDouble()?.compareTo(other.GetRelH()?.toDouble() ?: Double.MAX_VALUE)?: 1
        }

        if(result == 0)
        {
            if(GetTAir() != null && other.GetTAir() == null)
                result = -1

            else if(GetTAir() == null && other.GetTAir() != null)
                result = 1

            else if(GetTAir() != null && other.GetTAir() != null)
                result = GetTAir()?.toDouble()?.compareTo(other.GetTime()?.toDouble()?: Double.MAX_VALUE) ?: 1
        }

        if(result == 0)
        {
            if(GetWSpd() != null && other.GetWSpd() == null)
                result = -1

            else if(GetWSpd() == null && other.GetWSpd() != null)
                result = 1

            else if(GetWSpd() != null && other.GetWSpd() != null)
                result = GetWSpd()?.toDouble()?.compareTo(other.GetWSpd()?.toDouble()?: Double.MAX_VALUE)?: 1
        }

        if(result == 0)
        {
            if(GetWDir() != null && other.GetWDir() == null)
                result = -1

            else if(GetWDir() == null && other.GetWDir() != null)
                result = 1

            else if(GetWDir() != null && other.GetWDir() != null)
                result = GetWDir()?.toDouble()?.compareTo(other.GetWDir()?.toDouble()?: Double.MAX_VALUE) ?: 1
        }

        if(result == 0)
        {
            if(GetWMax() != null && other.GetWMax() == null)
                result = -1

            else if(GetWMax() == null && other.GetWMax() != null)
                result = 1

            else if(GetWMax() != null && other.GetWMax() != null)
                result = GetWMax()?.toDouble()?.compareTo(other.GetWMax()?.toDouble()?: 0.0)?: 1
        }

        if(result == 0)
        {
            if(GetPres() != null && other.GetPres() == null)
                result = -1

            else if(GetPres() == null && other.GetPres() != null)
                result = 1

            else if(GetPres() != null && other.GetPres() != null)
                result = GetPres()?.toDouble()?.compareTo(other.GetPres()?.toDouble()?: Double.MAX_VALUE) ?: 1
        }

        if(result == 0)
        {
            if(GetRain24H() != null && other.GetRain24H() == null)
                result = -1

            else if(GetRain24H() == null && other.GetRain24H() != null)
                result = 1

            else if(GetRain24H() != null && other.GetRain24H() != null)
                result = GetRain24H()?.toDouble()?.compareTo(other.GetRain24H()?.toDouble()?: Double.MAX_VALUE) ?: 1
        }

        return result
    }

    internal var TIME: Long? = null
    internal var RELH: Number? = null
    internal var TAIR: Number? = null
    internal var WSPD: Number? = null
    internal var WDIR: Number? = null
    internal var WMAX: Number? = null
    internal var PRES: Number? = null
    internal var RAIN_24H: Number? = null
    internal var STID: String? = null



    override fun GetTime(): Long? {
        return TIME
    }

    override fun GetRelH(): Number? {
        return RELH
    }

    override fun GetTAir(): Number? {
        return TAIR
    }

    override fun GetWSpd(): Number? {
        return WSPD
    }

    override fun GetWDir(): Number? {
        return WDIR
    }

    override fun GetWMax(): Number? {
        return WMAX
    }

    override fun GetPres(): Number? {
        return PRES
    }

    override fun GetRain24H(): Number? {
        return RAIN_24H
    }

    override fun GetStID(): String? {
        return STID
    }


    override fun GetDefaultTempUnit(): DefaultUnits.TempUnits {
        return DefaultUnits.TempUnits.kCelsius
    }


    override fun GetDefaultLengthUnit(): DefaultUnits.LengthUnits {
        return DefaultUnits.LengthUnits.kMm
    }


    override fun GetDefaultSpeedUnit(): DefaultUnits.SpeedUnits {
        return DefaultUnits.SpeedUnits.kMps
    }


    override fun GetDefaultPressureUnit(): DefaultUnits.PressureUnits {
        return DefaultUnits.PressureUnits.kMb
    }
}