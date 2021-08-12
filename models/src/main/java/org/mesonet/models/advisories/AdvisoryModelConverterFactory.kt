package org.mesonet.models.advisories

import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type
import kotlin.collections.ArrayList



class AdvisoryModelConverterFactory: Converter.Factory() {

    override fun responseBodyConverter(type: Type, annotations: Array<Annotation>, retrofit: Retrofit): Converter<ResponseBody, List<Advisory>>
    {
        if(annotations.any { it is AdvisoryConverter })
            return AdvisoryModelConverter()

        return retrofit.nextResponseBodyConverter(this, type, annotations)
    }

    class AdvisoryModelConverter: Converter<ResponseBody, List<Advisory>> {
        override fun convert(inValue: ResponseBody?): List<Advisory> {
            val result = Advisory.AdvisoryList()

            try {
                if (inValue != null) {
                    val splitList = String(inValue.bytes()).split(";".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

                    var i = 0
                    while (i + 5 < splitList.size) {
                        val advisory = AdvisoryModel()

                        val splitType = splitList[i].substring(splitList[i].length - 4).split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

                        try {
                            advisory.mAdvisoryType.mAdvisoryType = AdvisoryModel.AdvisoryType.valueOf(splitType[0])
                            advisory.mAdvisoryType.mAdvisoryLevel = AdvisoryModel.AdvisoryLevel.valueOf(splitType[1])
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }

                        i += 4

                        advisory.mFilePath = splitList[i++]

                        val splitCounties = splitList[i].split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

                        advisory.mCountyCodes = ArrayList()
                        advisory.mCountyCodes?.addAll(splitCounties)

                        result.add(advisory)
                        i++
                    }
                }
            }
            finally {
                inValue?.close()
            }

            return result
        }
    }
}