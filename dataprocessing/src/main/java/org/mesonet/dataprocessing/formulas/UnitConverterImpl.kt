package org.mesonet.dataprocessing.formulas


import org.mesonet.core.DefaultUnits
import javax.inject.Inject

class UnitConverterImpl @Inject
constructor(): UnitConverter {
    override fun CelsiusToFahrenheit(inCelsius: Number?): Number? {
        return if (inCelsius == null) null else inCelsius.toDouble() * (9.0 / 5.0) + 32.0
    }


    override fun FahrenheitToCelsius(inFahrenheit: Number?): Number? {
        return if (inFahrenheit == null) null else (inFahrenheit.toDouble() - 32.0) / (9.0 / 5.0)
    }


    override fun MpsToMph(inMps: Number?): Number? {
        return if (inMps == null) null else inMps.toDouble() * 2.236936292054402
    }


    override fun MphToMps(inMph: Number?): Number? {
        return if (inMph == null) null else inMph.toDouble() * (1.0 / (MpsToMph(1)?.toDouble()?: 0.0))
    }


    override fun MpsToKmph(inMps: Number?): Number? {
        return if (inMps == null) null else inMps.toDouble() * 3.6

    }


    override fun KmphToMps(inKmph: Number?): Number? {
        return if (inKmph == null) null else inKmph.toDouble() * (1.0 / (MpsToKmph(1)?.toDouble() ?: 0.0))
    }


    override fun MphToKmph(inMph: Number?): Number? {
        return if (inMph == null) null else inMph.toDouble() * 1.609344
    }


    override fun KmphToMph(inMph: Number?): Number? {
        return if (inMph == null) null else inMph.toDouble() * (1.0 / (MphToKmph(1)?.toDouble() ?: 0.0))
    }


    override fun MmToIn(inMm: Number?): Number? {
        return if (inMm == null) null else inMm.toDouble() * 0.0393700787401575
    }


    override fun InToMm(inIn: Number?): Number? {
        return if (inIn == null) null else inIn.toDouble() * (1.0 / (MmToIn(1)?.toDouble() ?: 0.0))
    }


    override fun InHgToMb(inIn: Number?): Number? {
        return if (inIn == null) null else inIn.toDouble() * 33.8639
    }


    override fun MbToInHg(inIn: Number?): Number? {
        return if (inIn == null) null else inIn.toDouble() / 33.8639
    }


    override fun GetTempInPreferredUnits(inBaseValue: Number?, inDefaultUnits: DefaultUnits?, inPreference: DefaultUnits.TempUnits?): Number? {
        if (inBaseValue == null || inDefaultUnits == null || inDefaultUnits.GetDefaultTempUnit() == null || inPreference == null)
            return null

        if (inPreference == inDefaultUnits.GetDefaultTempUnit())
            return inBaseValue

        when (inDefaultUnits.GetDefaultTempUnit()) {
            DefaultUnits.TempUnits.kFahrenheit -> if (inPreference == DefaultUnits.TempUnits.kCelsius)
                return FahrenheitToCelsius(inBaseValue)
            DefaultUnits.TempUnits.kCelsius -> if (inPreference == DefaultUnits.TempUnits.kFahrenheit)
                return CelsiusToFahrenheit(inBaseValue)
        }

        return null
    }


    override fun GetSpeedInPreferredUnits(inBaseValue: Number?, inDefaultUnits: DefaultUnits?, inPreference: DefaultUnits.SpeedUnits?): Number? {
        if (inBaseValue == null || inDefaultUnits == null || inDefaultUnits.GetDefaultSpeedUnit() == null || inPreference == null)
            return null

        if (inPreference == inDefaultUnits.GetDefaultSpeedUnit())
            return inBaseValue

        when (inDefaultUnits.GetDefaultSpeedUnit()) {
            DefaultUnits.SpeedUnits.kMps -> {
                if (inPreference == DefaultUnits.SpeedUnits.kMph)
                    return MpsToMph(inBaseValue)
                if (inPreference == DefaultUnits.SpeedUnits.kKmph)
                    return MpsToKmph(inBaseValue)
            }
            DefaultUnits.SpeedUnits.kMph -> {
                if (inPreference == DefaultUnits.SpeedUnits.kMps)
                    return MphToMps(inBaseValue)
                if (inPreference == DefaultUnits.SpeedUnits.kKmph)
                    return MphToKmph(inBaseValue)
            }
            DefaultUnits.SpeedUnits.kKmph -> {
                if (inPreference == DefaultUnits.SpeedUnits.kMps)
                    return KmphToMps(inBaseValue)
                if (inPreference == DefaultUnits.SpeedUnits.kMph)
                    return KmphToMph(inBaseValue)
            }
        }

        return null
    }


    override fun GetLengthInPreferredUnits(inBaseValue: Number?, inDefaultUnits: DefaultUnits?, inPreference: DefaultUnits.LengthUnits?): Number? {
        if (inBaseValue == null || inDefaultUnits == null || inDefaultUnits.GetDefaultLengthUnit() == null || inPreference == null)
            return null

        if (inPreference == inDefaultUnits.GetDefaultLengthUnit())
            return inBaseValue

        when (inDefaultUnits.GetDefaultLengthUnit()) {
            DefaultUnits.LengthUnits.kIn -> if (inPreference == DefaultUnits.LengthUnits.kMm)
                return InToMm(inBaseValue)
            DefaultUnits.LengthUnits.kMm -> if (inPreference == DefaultUnits.LengthUnits.kIn)
                return MmToIn(inBaseValue)
        }

        return null
    }


    override fun GetPressureInPreferredUnits(inBaseValue: Number?, inDefaultUnits: DefaultUnits?, inPreference: DefaultUnits.PressureUnits?): Number? {
        if (inBaseValue == null || inDefaultUnits == null || inDefaultUnits.GetDefaultPressureUnit() == null || inPreference == null)
            return null

        if (inPreference == inDefaultUnits.GetDefaultPressureUnit())
            return inBaseValue

        when (inDefaultUnits.GetDefaultPressureUnit()) {
            DefaultUnits.PressureUnits.kInHg -> if (inPreference == DefaultUnits.PressureUnits.kMb)
                return InHgToMb(inBaseValue)
            DefaultUnits.PressureUnits.kMb -> if (inPreference == DefaultUnits.PressureUnits.kInHg)
                return MbToInHg(inBaseValue)
        }

        return null
    }


    override fun GetCompassDirection(inDirection: Number?): DefaultUnits.CompassDirections? {
        if (inDirection == null)
            return null

        var direction = inDirection.toDouble()

        while (direction < 0)
            direction += 360.0

        while (direction >= 360)
            direction -= 360.0

        if (direction >= 0 && direction <= 11.25)
            return DefaultUnits.CompassDirections.N
        if (direction > 11.25 && direction <= 33.75)
            return DefaultUnits.CompassDirections.NNE
        if (direction > 33.75 && direction <= 56.25)
            return DefaultUnits.CompassDirections.NE
        if (direction > 56.25 && direction <= 78.75)
            return DefaultUnits.CompassDirections.ENE
        if (direction > 78.25 && direction <= 101.25)
            return DefaultUnits.CompassDirections.E
        if (direction > 101.25 && direction <= 123.75)
            return DefaultUnits.CompassDirections.ESE
        if (direction > 123.75 && direction <= 146.25)
            return DefaultUnits.CompassDirections.SE
        if (direction > 146.25 && direction <= 168.75)
            return DefaultUnits.CompassDirections.SSE
        if (direction > 168.75 && direction <= 191.25)
            return DefaultUnits.CompassDirections.S
        if (direction > 191.25 && direction <= 213.75)
            return DefaultUnits.CompassDirections.SSW
        if (direction > 213.75 && direction <= 236.25)
            return DefaultUnits.CompassDirections.SW
        if (direction > 236.25 && direction <= 258.75)
            return DefaultUnits.CompassDirections.WSW
        if (direction > 258.75 && direction <= 281.25)
            return DefaultUnits.CompassDirections.W
        if (direction > 281.25 && direction <= 303.75)
            return DefaultUnits.CompassDirections.WNW
        if (direction > 303.75 && direction <= 326.25)
            return DefaultUnits.CompassDirections.NW
        if (direction > 326.25 && direction <= 348.75)
            return DefaultUnits.CompassDirections.NNW
        return if (direction > 348.75 && direction <= 360) DefaultUnits.CompassDirections.N else null
    }
}