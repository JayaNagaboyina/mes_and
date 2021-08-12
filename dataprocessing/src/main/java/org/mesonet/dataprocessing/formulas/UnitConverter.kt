package org.mesonet.dataprocessing.formulas

import org.mesonet.core.DefaultUnits

interface UnitConverter
{
    fun CelsiusToFahrenheit(inCelsius: Number?): Number?
    fun FahrenheitToCelsius(inFahrenheit: Number?): Number?
    fun MpsToMph(inMps: Number?): Number?
    fun MphToMps(inMph: Number?): Number?
    fun MpsToKmph(inMps: Number?): Number?
    fun KmphToMps(inKmph: Number?): Number?
    fun MphToKmph(inMph: Number?): Number?
    fun KmphToMph(inMph: Number?): Number?
    fun MmToIn(inMm: Number?): Number?
    fun InToMm(inIn: Number?): Number?
    fun InHgToMb(inIn: Number?): Number?
    fun MbToInHg(inIn: Number?): Number?
    fun GetTempInPreferredUnits(inBaseValue: Number?, inDefaultUnits: DefaultUnits?, inPreference: DefaultUnits.TempUnits?): Number?
    fun GetSpeedInPreferredUnits(inBaseValue: Number?, inDefaultUnits: DefaultUnits?, inPreference: DefaultUnits.SpeedUnits?): Number?
    fun GetLengthInPreferredUnits(inBaseValue: Number?, inDefaultUnits: DefaultUnits?, inPreference: DefaultUnits.LengthUnits?): Number?fun GetPressureInPreferredUnits(inBaseValue: Number?, inDefaultUnits: DefaultUnits?, inPreference: DefaultUnits.PressureUnits?): Number?
    fun GetCompassDirection(inDirection: Number?): DefaultUnits.CompassDirections?
}