package org.mesonet.models.site.forecast

import org.mesonet.core.DefaultUnits

class ForecastModel : Forecast
{
    override fun compareTo(other: Forecast): Int {
        if(GetTime() != null && other.GetTime() == null)
            return -1
        else if(GetTime() == null && other.GetTime() != null)
            return 1
        else if( GetTime() != null && other.GetTime() != null)
            return GetTime()?.compareTo(other.GetTime()?: "") ?: 1

        if(GetIconUrl() != null && other.GetIconUrl() == null)
            return -1
        else if(GetIconUrl() == null && other.GetIconUrl() != null)
            return 1
        else if( GetIconUrl() != null && other.GetIconUrl() != null)
            return GetIconUrl()?.compareTo(other.GetIconUrl()?: "") ?: 1

        if(GetStatus() != null && other.GetStatus() == null)
            return -1
        else if(GetStatus() == null && other.GetStatus() != null)
            return 1
        else if( GetStatus() != null && other.GetStatus() != null)
            return GetStatus()?.compareTo(other.GetStatus()?: "") ?: 1

        if(GetHighOrLow() != null && other.GetHighOrLow() == null)
            return -1
        else if(GetHighOrLow() == null && other.GetHighOrLow() != null)
            return 1
        else if( GetHighOrLow() != null && other.GetHighOrLow() != null)
            return GetHighOrLow()?.CompareTo(other.GetHighOrLow()) ?: 1

        if(GetTemp() != null && other.GetTemp() == null)
            return -1
        else if(GetTemp() == null && other.GetTemp() != null)
            return 1
        else if(GetTemp() != null && other.GetTemp() != null)
            return GetTemp()?.toDouble()?.compareTo(other.GetTemp()?.toDouble() ?: Double.MAX_VALUE) ?: 1

        if(GetWindDirectionStart() != null && other.GetWindDirectionStart() == null)
            return -1
        else if(GetWindDirectionStart() == null && other.GetWindDirectionStart() != null)
            return 1
        else if( GetWindDirectionStart() != null && other.GetWindDirectionStart() != null)
            return GetWindDirectionStart()?.CompareTo(other.GetWindDirectionStart())?: 1

        if(GetWindDirectionEnd() != null && other.GetWindDirectionEnd() == null)
            return -1
        else if(GetWindDirectionEnd() == null && other.GetWindDirectionEnd() != null)
            return 1
        else if( GetWindDirectionEnd() != null && other.GetWindDirectionEnd() != null)
            return GetWindDirectionEnd()?.CompareTo(other.GetWindDirectionEnd())?: 1

        if(GetWindMin() != null && other.GetWindMin() == null)
            return -1
        else if(GetWindMin() == null && other.GetWindMin() != null)
            return 1
        else if(GetWindMin() != null && other.GetWindMin() != null)
            return GetWindMin()?.toDouble()?.compareTo(other.GetWindMin()?.toDouble()?: Double.MAX_VALUE) ?: 1

        if(GetWindMax() != null && other.GetWindMax() == null)
            return -1
        else if(GetWindMax() == null && other.GetWindMax() != null)
            return 1
        else if(GetWindMax() != null && other.GetWindMax() != null)
            return GetWindMax()?.toDouble()?.compareTo(other.GetWindMax()?.toDouble() ?: Double.MAX_VALUE) ?: 1

        return 0
    }

    internal var mTime: String? = null
    internal var mIconUrl: String? = null
    internal var mStatus: String? = null
    internal var mHighOrLow: Forecast.HighOrLow? = null
    internal var mTemp: Number? = null
    internal var mWindDirectionStart: DefaultUnits.CompassDirections? = null
    internal var mWindDirectionEnd: DefaultUnits.CompassDirections? = null
    internal var mWindMin: Number? = null
    internal var mWindMax: Number? = null

    override fun GetTime(): String? {
        return mTime
    }

    override fun GetTemp(): Number? {
        return mTemp
    }

    override fun GetIconUrl(): String? {
        return mIconUrl
    }

    override fun GetStatus(): String? {
        return mStatus
    }

    override fun GetHighOrLow(): Forecast.HighOrLow? {
        return mHighOrLow
    }

    override fun GetWindMin(): Number? {
        return mWindMin
    }

    override fun GetWindMax(): Number? {
        return mWindMax
    }

    override fun GetWindDirectionStart(): DefaultUnits.CompassDirections? {
        return mWindDirectionStart
    }

    override fun GetWindDirectionEnd(): DefaultUnits.CompassDirections? {
        return mWindDirectionEnd
    }

    override fun GetDefaultTempUnit(): DefaultUnits.TempUnits {
        return DefaultUnits.TempUnits.kFahrenheit
    }

    override fun GetDefaultLengthUnit(): DefaultUnits.LengthUnits? {
        return null
    }

    override fun GetDefaultSpeedUnit(): DefaultUnits.SpeedUnits {
        return DefaultUnits.SpeedUnits.kMph
    }

    override fun GetDefaultPressureUnit(): DefaultUnits.PressureUnits? {
        return null
    }

}
