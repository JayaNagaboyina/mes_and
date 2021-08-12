package org.mesonet.models.radar

import com.tickaroo.tikxml.TikXml
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import okhttp3.ResponseBody
import org.mesonet.models.advisories.Advisory
import org.mesonet.models.advisories.AdvisoryConverter
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type


class RadarHistoryConverterFactory: Converter.Factory()
{
    val mTickarooConverterFactory = TikXmlConverterFactory.create(TikXml.Builder().exceptionOnUnreadXml(false).build())


    override fun responseBodyConverter(type: Type, annotations: Array<Annotation>, retrofit: Retrofit): Converter<ResponseBody, RadarHistoryModel>
    {
        if(annotations.any { it is RadarHistoryConverter }) {
            val converter = mTickarooConverterFactory.responseBodyConverter(type, annotations, retrofit)
            if(converter != null) {
                try {
                    return (converter as Converter<ResponseBody, RadarHistoryModel>)
                }
                catch (e: ClassCastException)
                {
                    e.printStackTrace()
                }
                catch (e: TypeCastException)
                {
                    e.printStackTrace()
                }
            }
        }

        return retrofit.nextResponseBodyConverter(this, type, annotations)
    }
}