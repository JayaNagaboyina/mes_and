package org.mesonet.dataprocessing

interface PageStateInfo
{
    enum class PageState{ kLoading, kError, kData }

    fun GetPageState(): PageState
    fun GetErrorMessage(): String?
}