package org.mesonet.models.advisories

interface Advisory: Comparable<Advisory>
{
    fun GetLevel(): AdvisoryModel.AdvisoryLevel?
    fun GetType(): AdvisoryModel.AdvisoryType?
    fun GetFilePath(): String?
    fun GetCounties(): ArrayList<String>?


    class AdvisoryList: ArrayList<Advisory>(), Comparable<List<Advisory>>
    {
        override fun compareTo(other: List<Advisory>): Int {
            var result = size.compareTo(other.size)

            var index = 0
            while(result == 0 && index < size)
            {
                result = get(index).compareTo(other[index])

                index++
            }

            return result
        }

    }
}