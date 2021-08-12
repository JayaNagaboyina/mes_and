package org.mesonet.models.radar


import com.tickaroo.tikxml.annotation.Attribute
import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.ElementNameMatcher
import com.tickaroo.tikxml.annotation.Xml


@Xml(name = "list")
class RadarHistoryModel(@Attribute(name = "timeStampUTC") private val mTimeStampUTC: String = "",
                        @Attribute(name = "radar") private val mRadar: String = "",
                        @Element(name = "frame") private val mFrames: List<RadarFrameModel> = ArrayList()): RadarHistory
{
    fun getMTimeStampUTC(): String{
        return mTimeStampUTC
    }

    override fun GetTimeStampUTC(): String {
        return getMTimeStampUTC()
    }


    fun getMRadar(): String {
        return mRadar
    }


    override fun GetRadar(): String {
        return getMRadar()
    }


    fun getMFrames(): List<RadarFrameModel>
    {
        return mFrames
    }


    override fun GetFrames(): List<RadarHistory.RadarFrame> {
        return getMFrames()
    }


    override fun compareTo(other: RadarHistory): Int {
        var result = GetRadar().compareTo(other.GetRadar())

        if(result == 0)
            result = GetTimeStampUTC().compareTo(other.GetTimeStampUTC())

        return result
    }


    @Xml(name = "frame")
    class RadarFrameModel(@Attribute(name = "filename") private val mFilename: String = "",
                          @Attribute(name = "timestring") private val mTimestring: String = "",
                          @Attribute(name = "looptime") private val mLooptime: Long = 0): RadarHistory.RadarFrame {

        private var mFrameId: String? = null

        fun getMTimestring(): String{
            return mTimestring
        }

        override fun GetTimestring(): String {
            return getMTimestring()
        }

        fun getMFilename(): String {
            return mFilename
        }

        override fun GetFrameId(): String {
            if(mFrameId == null)
                mFrameId = getMFilename().split("/").last().removeSuffix(".gif")

            return mFrameId?: ""
        }

        fun getMLooptime(): Long {
            return mLooptime
        }


        override fun GetTime(): Long {
            return mLooptime
        }


        override fun compareTo(other: RadarHistory.RadarFrame): Int {
            var result = GetFrameId().compareTo(other.GetFrameId())
            if (result == 0)
                result = GetTimestring().compareTo(other.GetTimestring())

            return result
        }
    }


//
//
//    class Builder {
//        private val mResult = RadarImageModel()
//
//
//        internal fun SetValue(inKey: String, inValue: String) {
//            val field = FindField(inKey)
//
//            if (field != null) {
//                try {
//                    field.isAccessible = true
//                    if (field.type == String::class.java || field.type == String::class)
//                        field.set(mResult, inValue)
//                    else if (field.type == Long::class.javaPrimitiveType || field.type == Long::class)
//                        field.set(mResult, java.lang.Long.parseLong(inValue))
//                } catch (e: IllegalAccessException) {
//                    e.printStackTrace()
//                } catch (e: NumberFormatException) {
//                    e.printStackTrace()
//                }
//
//                field.isAccessible = false
//            }
//        }
//
//
//        internal fun FindField(inName: String): Field? {
//            val fields = RadarImageModel::class.java.declaredFields
//
//            if (fields != null) {
//                for (i in fields.indices) {
//                    if (fields[i].name == inName)
//                        return fields[i]
//                }
//            }
//
//            return null
//        }
//
//
//        internal fun Build(): RadarImageModel {
//            return mResult
//        }
//    }

}
