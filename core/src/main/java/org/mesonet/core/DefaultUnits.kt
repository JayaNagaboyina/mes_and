package org.mesonet.core


interface DefaultUnits {
    fun GetDefaultTempUnit(): TempUnits?
    fun GetDefaultLengthUnit(): LengthUnits?
    fun GetDefaultSpeedUnit(): SpeedUnits?
    fun GetDefaultPressureUnit(): PressureUnits?


    enum class TempUnits {
        kCelsius, kFahrenheit
    }

    enum class SpeedUnits {
        kMps, kKmph, kMph
    }

    enum class LengthUnits {
        kMm, kIn
    }

    enum class PressureUnits {
        kMb, kInHg
    }

    enum class CompassDirections {
        N, NNE, NE, ENE, E, ESE, SE, SSE, S, SSW, SW, WSW, W, WNW, NW, NNW;

        fun CompareTo(other: CompassDirections?): Int
        {
            if(other == null)
                return 1

            return compareTo(other)
        }
    }
}