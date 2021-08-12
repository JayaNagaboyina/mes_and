package org.mesonet.models.advisories

import androidx.annotation.Keep

@Keep
class AdvisoryModel internal constructor() : Advisory {
    override fun compareTo(other: Advisory): Int {

        if(mAdvisoryType.mAdvisoryType != null && other.GetType() != null)
            return mAdvisoryType.mAdvisoryType?.CompareTo(other.GetType()) ?: 1
        else if (mAdvisoryType.mAdvisoryType != null)
            return -1
        else if(other.GetType() != null)
            return 1

        if (mAdvisoryType.mAdvisoryLevel != null && other.GetLevel() != null)
            return mAdvisoryType.mAdvisoryLevel?.CompareTo(other.GetLevel()) ?: 1
        else if (mAdvisoryType.mAdvisoryLevel != null)
            return -1
        else if(other.GetLevel() != null)
            return 1

        if (mFilePath != null && other.GetFilePath() != null)
            return mFilePath?.compareTo(other.GetFilePath() ?: "") ?: 1
        else if (mFilePath != null)
            return -1
        else if(other.GetFilePath() != null)
            return 1

        if (mCountyCodes != null && other.GetCounties() != null)
            return compareValuesBy(mCountyCodes, other.GetCounties())
        else if (mCountyCodes != null)
            return -1
        else if(other.GetCounties() != null)
            return 1

        return 0
    }

    internal var mAdvisoryType = AdvisoryTypeModel()
    internal var mFilePath: String? = null
    internal var mCountyCodes: ArrayList<String>? = null


    override fun GetLevel(): AdvisoryLevel? {
        return mAdvisoryType.mAdvisoryLevel
    }

    override fun GetType(): AdvisoryType? {
        return mAdvisoryType.mAdvisoryType
    }


    override fun GetFilePath(): String? {
        return mFilePath
    }

    override fun GetCounties(): ArrayList<String>? {
        return mCountyCodes
    }

    enum class AdvisoryLevel {
        S, N, Y, O, A, F, W;

        override fun toString(): String {
            return when (this) {
                S -> "Statement"
                N -> "Synopsis"
                Y -> "Advisory"
                O -> "Outlook"
                A -> "Watch"
                F -> "Forecast"
                W -> "Warning"
            }
        }


        fun CompareTo(other: AdvisoryLevel?): Int
        {
            if(other == null)
                return 1

            return compareTo(other)
        }
    }

    enum class
    AdvisoryType {
        RB, HF, LS, SW, BW, UP, TS, SU, TR, LO, HY,
        AS, HU, SM, TI, LE, SI, CF, MA, LB, SE, GL, SC, AF, TY, DS, ZF,
        LW, HI, SN, DU, SB, FR, FZ, FG, BS, HZ, SR, HT, EH, WC, EC, IP,
        WW, WS, HS, ZR, WI, HW, EW, FA, FL, FF, FW, IS, BZ, SV, TO;


        override fun toString(): String {

            when (this) {
                RB -> return "Small Craft for Rough Bar"
                HF -> return "Hurricane Force Wind"
                LS -> return "Lakeshore Flood"
                SW -> return "Small Craft for Hazardous Seas"
                BW -> return "Brisk Wind"
                UP -> return "Ice Accretion"
                TS -> return "Tsunami"
                SU -> return "High Surf"
                TR -> return "Tropical Storm"
                LO -> return "Low Water"
                HY -> return "Hydrologic"
                AS -> return "Air Stagnation"
                HU -> return "Hurricane"
                SM -> return "Dense Smoke"
                TI -> return "Inland Tropical Storm"
                LE -> return "Lake Effect Snow"
                SI -> return "Small Craft for Winds"
                CF -> return "Coastal Flood"
                MA -> return "Marine"
                LB -> return "Lake Effect Snow and Blowing Snow"
                SE -> return "Hazardous Seas"
                GL -> return "Gale"
                SC -> return "Small Craft"
                AF -> return "Ashfall"
                TY -> return "Typhoon"
                DS -> return "Dust Storm"
                ZF -> return "Freezing Fog"
                LW -> return "Lake Wind"
                HI -> return "Inland Hurricane"
                SN -> return "Snow"
                DU -> return "Blowing Dust"
                SB -> return "Snow and Blowing Snow"
                FR -> return "Frost"
                FZ -> return "Freeze"
                FG -> return "Dense Fog"
                BS -> return "Blowing Snow"
                HZ -> return "Hard Freeze"
                SR -> return "Storm"
                HT -> return "Heat"
                EH -> return "Excessive Heat"
                WC -> return "Wind Chill"
                EC -> return "Extreme Cold"
                IP -> return "Sleet"
                WW -> return "Winter Weather"
                WS -> return "Winter Storm"
                HS -> return "Heavy Snow"
                ZR -> return "Freezing Rain"
                WI -> return "Wind"
                HW -> return "High Wind"
                EW -> return "Extreme Wind"
                FA -> return "Areal Flood"
                FL -> return "Flood"
                FF -> return "Flash Flood"
                FW -> return "Fire Weather"
                IS -> return "Ice Storm"
                BZ -> return "Blizzard"
                SV -> return "Severe Thunderstorm"
                TO -> return "Tornado"
            }
        }


        fun CompareTo(other: AdvisoryType?): Int
        {
            if(other == null)
                return 1

            return compareTo(other)
        }
    }

    enum class County {
        OKC001, OKC003, OKC005, OKC007, OKC009, OKC011, OKC013, OKC015, OKC017, OKC019,
        OKC021, OKC023, OKC025, OKC027, OKC029, OKC031, OKC033, OKC035, OKC037, OKC039,
        OKC041, OKC043, OKC045, OKC047, OKC049, OKC051, OKC053, OKC055, OKC057, OKC059,
        OKC061, OKC063, OKC065, OKC067, OKC069, OKC071, OKC073, OKC075, OKC077, OKC079,
        OKC081, OKC083, OKC085, OKC087, OKC089, OKC091, OKC093, OKC095, OKC097, OKC099,
        OKC101, OKC103, OKC105, OKC107, OKC109, OKC111, OKC113, OKC115, OKC117, OKC119,
        OKC121, OKC123, OKC125, OKC127, OKC129, OKC131, OKC133, OKC135, OKC137, OKC139,
        OKC141, OKC143, OKC145, OKC147, OKC149, OKC151, OKC153, OKZ001, OKZ002, OKZ003,
        OKZ004, OKZ005, OKZ006, OKZ007, OKZ008, OKZ009, OKZ010, OKZ011, OKZ012, OKZ013,
        OKZ014, OKZ015, OKZ016, OKZ017, OKZ018, OKZ019, OKZ020, OKZ021, OKZ022, OKZ023,
        OKZ024, OKZ025, OKZ026, OKZ027, OKZ028, OKZ029, OKZ030, OKZ031, OKZ032, OKZ033,
        OKZ034, OKZ035, OKZ036, OKZ037, OKZ038, OKZ039, OKZ040, OKZ041, OKZ042, OKZ043,
        OKZ044, OKZ045, OKZ046, OKZ047, OKZ048, OKZ049, OKZ050, OKZ051, OKZ052, OKZ053,
        OKZ054, OKZ055, OKZ056, OKZ057, OKZ058, OKZ059, OKZ060, OKZ061, OKZ062, OKZ063,
        OKZ064, OKZ065, OKZ066, OKZ067, OKZ068, OKZ069, OKZ070, OKZ071, OKZ072, OKZ073,
        OKZ074, OKZ075, OKZ076, OKZ077;

        override fun toString(): String {
            when (this) {
                OKC001 -> return "Adair"
                OKC003 -> return "Alfalfa"
                OKC005 -> return "Atoka"
                OKC007 -> return "Beaver"
                OKC009 -> return "Beckham"
                OKC011 -> return "Blaine"
                OKC013 -> return "Bryan"
                OKC015 -> return "Caddo"
                OKC017 -> return "Canadian"
                OKC019 -> return "Carter"
                OKC021 -> return "Cherokee"
                OKC023 -> return "Choctaw"
                OKC025 -> return "Cimarron"
                OKC027 -> return "Cleveland"
                OKC029 -> return "Coal"
                OKC031 -> return "Comanche"
                OKC033 -> return "Cotton"
                OKC035 -> return "Craig"
                OKC037 -> return "Creek"
                OKC039 -> return "Custer"
                OKC041 -> return "Delaware"
                OKC043 -> return "Dewey"
                OKC045 -> return "Ellis"
                OKC047 -> return "Garfield"
                OKC049 -> return "Garvin"
                OKC051 -> return "Grady"
                OKC053 -> return "Grant"
                OKC055 -> return "Greer"
                OKC057 -> return "Harmon"
                OKC059 -> return "Harper"
                OKC061 -> return "Haskell"
                OKC063 -> return "Hughes"
                OKC065 -> return "Jackson"
                OKC067 -> return "Jefferson"
                OKC069 -> return "Johnston"
                OKC071 -> return "Kay"
                OKC073 -> return "Kingfisher"
                OKC075 -> return "Kiowa"
                OKC077 -> return "Latimer"
                OKC079 -> return "Le Flore"
                OKC081 -> return "Lincoln"
                OKC083 -> return "Logan"
                OKC085 -> return "Love"
                OKC087 -> return "McClain"
                OKC089 -> return "McCurtain"
                OKC091 -> return "McIntosh"
                OKC093 -> return "Major"
                OKC095 -> return "Marshall"
                OKC097 -> return "Mayes"
                OKC099 -> return "Murray"
                OKC101 -> return "Muskogee"
                OKC103 -> return "Noble"
                OKC105 -> return "Nowata"
                OKC107 -> return "Okfuskee"
                OKC109 -> return "Oklahoma"
                OKC111 -> return "Okmulgee"
                OKC113 -> return "Osage"
                OKC115 -> return "Ottawa"
                OKC117 -> return "Pawnee"
                OKC119 -> return "Payne"
                OKC121 -> return "Pittsburg"
                OKC123 -> return "Pontotoc"
                OKC125 -> return "Pottawatomie"
                OKC127 -> return "Pushmataha"
                OKC129 -> return "Roger Mills"
                OKC131 -> return "Rogers"
                OKC133 -> return "Seminole"
                OKC135 -> return "Sequoyah"
                OKC137 -> return "Stephens"
                OKC139 -> return "Texas"
                OKC141 -> return "Tillman"
                OKC143 -> return "Tulsa"
                OKC145 -> return "Wagoner"
                OKC147 -> return "Washington"
                OKC149 -> return "Washita"
                OKC151 -> return "Woods"
                OKC153 -> return "Woodward"

                OKZ001 -> return "Cimarron"
                OKZ002 -> return "Texas"
                OKZ003 -> return "Beaver"
                OKZ004 -> return "Harper"
                OKZ005 -> return "Woods"
                OKZ006 -> return "Alfalfa"
                OKZ007 -> return "Grant"
                OKZ008 -> return "Kay"
                OKZ009 -> return "Ellis"
                OKZ010 -> return "Woodward"
                OKZ011 -> return "Major"
                OKZ012 -> return "Garfield"
                OKZ013 -> return "Noble"
                OKZ014 -> return "Roger Mills"
                OKZ015 -> return "Dewey"
                OKZ016 -> return "Custer"
                OKZ017 -> return "Blaine"
                OKZ018 -> return "Kingfisher"
                OKZ019 -> return "Logan"
                OKZ020 -> return "Payne"
                OKZ021 -> return "Beckham"
                OKZ022 -> return "Washita"
                OKZ023 -> return "Caddo"
                OKZ024 -> return "Canadian"
                OKZ025 -> return "Oklahoma"
                OKZ026 -> return "Lincoln"
                OKZ027 -> return "Grady"
                OKZ028 -> return "McClain"
                OKZ029 -> return "Cleveland"
                OKZ030 -> return "Pottawatomie"
                OKZ031 -> return "Seminole"
                OKZ032 -> return "Hughes"
                OKZ033 -> return "Harmon"
                OKZ034 -> return "Greer"
                OKZ035 -> return "Kiowa"
                OKZ036 -> return "Jackson"
                OKZ037 -> return "Tillman"
                OKZ038 -> return "Comanche"
                OKZ039 -> return "Stephens"
                OKZ040 -> return "Garvin"
                OKZ041 -> return "Murray"
                OKZ042 -> return "Pontotoc"
                OKZ043 -> return "Coal"
                OKZ044 -> return "Cotton"
                OKZ045 -> return "Jefferson"
                OKZ046 -> return "Carter"
                OKZ047 -> return "Johnston"
                OKZ048 -> return "Atoka"
                OKZ049 -> return "Pushmataha"
                OKZ050 -> return "Love"
                OKZ051 -> return "Marshall"
                OKZ052 -> return "Bryan"
                OKZ053 -> return "Choctaw"
                OKZ054 -> return "Osage"
                OKZ055 -> return "Washington"
                OKZ056 -> return "Nowata"
                OKZ057 -> return "Craig"
                OKZ058 -> return "Ottawa"
                OKZ059 -> return "Pawnee"
                OKZ060 -> return "Tulsa"
                OKZ061 -> return "Rogers"
                OKZ062 -> return "Mayes"
                OKZ063 -> return "Delaware"
                OKZ064 -> return "Creek"
                OKZ065 -> return "Okfuskee"
                OKZ066 -> return "Okmulgee"
                OKZ067 -> return "Wagoner"
                OKZ068 -> return "Cherokee"
                OKZ069 -> return "Adair"
                OKZ070 -> return "Muskogee"
                OKZ071 -> return "McIntosh"
                OKZ072 -> return "Sequoyah"
                OKZ073 -> return "Pittsburg"
                OKZ074 -> return "Haskell"
                OKZ075 -> return "Latimer"
                OKZ076 -> return "Le Flore"
                OKZ077 -> return "McCurtain"
            }
        }
    }

    inner class AdvisoryTypeModel {
        internal var mAdvisoryLevel: AdvisoryLevel? = null
        internal var mAdvisoryType: AdvisoryType? = null
    }
}