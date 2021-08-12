package org.mesonet.dataprocessing.maps

import com.google.gson.Gson
import junit.framework.TestCase.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mesonet.models.maps.MapsModel
import org.powermock.core.classloader.annotations.PrepareForTest
import org.robolectric.RobolectricTestRunner


@RunWith(RobolectricTestRunner::class)
@PrepareForTest(Pair::class)
class MapsDataProviderTests
{
    @Test
    fun LoadMapsNullList()
    {
        val dataProvider = MapsDataProvider(ThreadHandler())

        var result = dataProvider.LoadMapsList(null, null)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, null)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, null)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, null)
        assertNull(result)
    }



    @Test
    fun LoadMapsEmptyModel()
    {
        val dataProvider = MapsDataProvider(ThreadHandler())

        val model = MapsModel()

        var result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)
    }


    @Test
    fun LoadMapsEmptyList()
    {
        val dataProvider = MapsDataProvider(ThreadHandler())
        val model = Gson().fromJson("", MapsModel::class.java)

        var result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)
    }



    @Test
    fun LoadMapsNullJson()
    {
        val dataProvider = MapsDataProvider(ThreadHandler())
        val model = Gson().fromJson("null", MapsModel::class.java)

        var result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)
    }



    @Test
    fun LoadMapsEmptyObject()
    {
        val dataProvider = MapsDataProvider(ThreadHandler())
        val model = Gson().fromJson("{}", MapsModel::class.java)

        var result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)
    }



    @Test
    fun LoadMapsMainOnlyTests()
    {
        val dataProvider = MapsDataProvider(ThreadHandler())
        var model = Gson().fromJson("{\"main\":null}", MapsModel::class.java)

        var result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[]}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{}]}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)
    }


    @Test
    fun LoadMapsMainOnlyTitleOnlyTests()
    {
        val dataProvider = MapsDataProvider(ThreadHandler())
        var model = Gson().fromJson("{\"main\":[{\"title\":null}]}", MapsModel::class.java)

        var result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\"}]}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Test Title\"}]}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)
    }



    @Test
    fun LoadMapsMainOnlySectionsOnlyTests()
    {
        val dataProvider = MapsDataProvider(ThreadHandler())
        var model = Gson().fromJson("{\"main\":[{\"sections\":null}]}", MapsModel::class.java)

        var result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[]}]}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[null]}]}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[\"Section 1\",\"Section 2\"]}]}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)
    }



    @Test
    fun LoadMapsMainOnlySectionsTitleTests()
    {
        val dataProvider = MapsDataProvider(ThreadHandler())
        var model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":null}]}", MapsModel::class.java)

        var result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":null}]}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":null}]}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[]}]}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[]}]}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[]}]}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[null]}]}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[null]}]}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[null]}]}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[\"Section 1\",\"Section 2\"]}]}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[\"Section 1\",\"Section 2\"]}]}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[\"Section 1\",\"Section 2\"]}]}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)
    }



    @Test
    fun LoadMapsSectionsOnlyTests()
    {
        val dataProvider = MapsDataProvider(ThreadHandler())
        var model = Gson().fromJson("{\"sections\":null}", MapsModel::class.java)

        var result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"sections\":{}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"sections\":{\"Section 1\":null}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)
    }



    @Test
    fun LoadMapsSectionsOnlyEmptyTests()
    {
        val dataProvider = MapsDataProvider(ThreadHandler())
        var model = Gson().fromJson("{\"sections\":{\"\":null}}", MapsModel::class.java)

        var result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"sections\":{\"Section 1\":null}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"sections\":{\"Section 1\":{}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)
    }



    @Test
    fun LoadMapsSectionsOnlyTitleOnlyTests()
    {
        val dataProvider = MapsDataProvider(ThreadHandler())
        var model = Gson().fromJson("{\"sections\":{\"Section 1\":{\"title\":null}}}", MapsModel::class.java)

        var result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"sections\":{\"Section 1\":{\"title\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"sections\":{\"Section 1\":{\"title\":\"Section 1\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)
    }



    @Test
    fun LoadMapsSectionsOnlyProductOnlyTests()
    {
        val dataProvider = MapsDataProvider(ThreadHandler())
        var model = Gson().fromJson("{\"sections\":{\"Section 1\":{\"products\":null}}}", MapsModel::class.java)

        var result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"sections\":{\"Section 1\":{\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"sections\":{\"Section 1\":{\"products\":[\"Product 1\",\"Product 2\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)
    }



    @Test
    fun LoadMapsSectionsOnlyAllDataTests()
    {
        val dataProvider = MapsDataProvider(ThreadHandler())
        var model = Gson().fromJson("{\"sections\":{\"Section 1\":{\"title\":null,\"products\":null}}}", MapsModel::class.java)

        var result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"sections\":{\"Section 1\":{\"title\":\"\",\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"sections\":{\"Section 1\":{\"title\":\"Section 1\",\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"sections\":{\"Section 1\":{\"title\":null,\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"sections\":{\"Section 1\":{\"title\":\"\",\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"sections\":{\"Section 1\":{\"title\":\"Section 1\",\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"sections\":{\"Section 1\":{\"title\":null,\"products\":[\"Product 1\",\"Product 2\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"sections\":{\"Section 1\":{\"title\":\"\",\"products\":[\"Product 1\",\"Product 2\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"sections\":{\"Section 1\":{\"title\":\"Section 1\",\"products\":[\"Product 1\",\"Product 2\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)
    }



    @Test
    fun LoadMapsProductsOnlyTests()
    {
        val dataProvider = MapsDataProvider(ThreadHandler())
        var model = Gson().fromJson("{\"products\":null}", MapsModel::class.java)

        var result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"products\":{}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"products\":{\"Product 1\":null}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"products\":{\"Product 1\":{}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)
    }



    @Test
    fun LoadMapsProductsOnlyTitleOnlyTests()
    {
        val dataProvider = MapsDataProvider(ThreadHandler())
        var model = Gson().fromJson("{\"products\":{\"Product 1\":{\"title\":null}}}", MapsModel::class.java)

        var result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"products\":{\"Product 1\":{\"title\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"products\":{\"Product 1\":{\"title\":\"Product 1\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)
    }



    @Test
    fun LoadMapsProductsOnlyUrlOnlyTests()
    {
        val dataProvider = MapsDataProvider(ThreadHandler())
        var model = Gson().fromJson("{\"products\":{\"Product 1\":{\"url\":null}}}", MapsModel::class.java)

        var result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"products\":{\"Product 1\":{\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"products\":{\"Product 1\":{\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)
    }



    @Test
    fun LoadMapsProductsOnlyAllDataTests()
    {
        val dataProvider = MapsDataProvider(ThreadHandler())
        var model = Gson().fromJson("{\"products\":{\"Product 1\":{\"title\":null,\"url\":null}}}", MapsModel::class.java)

        var result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"products\":{\"Product 1\":{\"title\":\"\",\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"products\":{\"Product 1\":{\"title\":\"Product 1\",\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"products\":{\"Product 1\":{\"title\":null,\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"products\":{\"Product 1\":{\"title\":\"\",\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"products\":{\"Product 1\":{\"title\":\"Product 1\",\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"products\":{\"Product 1\":{\"title\":null,\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"products\":{\"Product 1\":{\"title\":\"\",\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"products\":{\"Product 1\":{\"title\":\"Product 1\",\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)
    }


    @Test
    fun LoadMapsMainAndProductsOnlyNullAndEmptyTests()
    {
        val dataProvider = MapsDataProvider(ThreadHandler())
        var model = Gson().fromJson("{\"main\":null,\"products\":null}", MapsModel::class.java)

        var result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":null,\"products\":{}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":null,\"products\":{\"Product 1\":null}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":null,\"products\":{\"Product 1\":{}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[],\"products\":null}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[],\"products\":null}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[],\"products\":{}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[],\"products\":{\"Product 1\":{}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{}],\"products\":null}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{}],\"products\":{}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{}],\"products\":{\"Product 1\":{}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)
    }



    @Test
    fun LoadMapsMainAndProductsOnlyNullAndEmptyMainTests()
    {
        val dataProvider = MapsDataProvider(ThreadHandler())
        var model = Gson().fromJson("{\"main\":null,\"products\":{\"Product 1\":{}}}", MapsModel::class.java)

        var result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":null,\"products\":{\"Product 1\":{\"title\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":null,\"products\":{\"Product 1\":{\"title\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":null,\"products\":{\"Product 1\":{\"title\":\"Product 1\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":null,\"products\":{\"Product 1\":{\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":null,\"products\":{\"Product 1\":{\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":null,\"products\":{\"Product 1\":{\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":null,\"products\":{\"Product 1\":{\"title\":null,\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":null,\"products\":{\"Product 1\":{\"title\":null,\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":null,\"products\":{\"Product 1\":{\"title\":null,\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":null,\"products\":{\"Product 1\":{\"title\":\"\",\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":null,\"products\":{\"Product 1\":{\"title\":\"\",\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":null,\"products\":{\"Product 1\":{\"title\":\"\",\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":null,\"products\":{\"Product 1\":{\"title\":\"Product 1\",\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":null,\"products\":{\"Product 1\":{\"title\":\"Product 1\",\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":null,\"products\":{\"Product 1\":{\"title\":\"Product 1\",\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[],\"products\":{\"Product 1\":{}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[],\"products\":{\"Product 1\":{\"title\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[],\"products\":{\"Product 1\":{\"title\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[],\"products\":{\"Product 1\":{\"title\":\"Product 1\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[],\"products\":{\"Product 1\":{\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[],\"products\":{\"Product 1\":{\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[],\"products\":{\"Product 1\":{\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[],\"products\":{\"Product 1\":{\"title\":null,\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[],\"products\":{\"Product 1\":{\"title\":null,\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[],\"products\":{\"Product 1\":{\"title\":null,\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[],\"products\":{\"Product 1\":{\"title\":\"\",\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[],\"products\":{\"Product 1\":{\"title\":\"\",\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[],\"products\":{\"Product 1\":{\"title\":\"\",\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[],\"products\":{\"Product 1\":{\"title\":\"Product 1\",\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[],\"products\":{\"Product 1\":{\"title\":\"Product 1\",\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[],\"products\":{\"Product 1\":{\"title\":\"Product 1\",\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{}],\"products\":{\"Product 1\":{}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{}],\"products\":{\"Product 1\":{\"title\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{}],\"products\":{\"Product 1\":{\"title\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{}],\"products\":{\"Product 1\":{\"title\":\"Product 1\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{}],\"products\":{\"Product 1\":{\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{}],\"products\":{\"Product 1\":{\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{}],\"products\":{\"Product 1\":{\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{}],\"products\":{\"Product 1\":{\"title\":null,\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{}],\"products\":{\"Product 1\":{\"title\":null,\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{}],\"products\":{\"Product 1\":{\"title\":null,\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{}],\"products\":{\"Product 1\":{\"title\":\"\",\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{}],\"products\":{\"Product 1\":{\"title\":\"\",\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{}],\"products\":{\"Product 1\":{\"title\":\"\",\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{}],\"products\":{\"Product 1\":{\"title\":\"Product 1\",\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{}],\"products\":{\"Product 1\":{\"title\":\"Product 1\",\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{}],\"products\":{\"Product 1\":{\"title\":\"Product 1\",\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)
    }


    @Test
    fun LoadMapsMainAndProductTitleOnlyTests()
    {
        val dataProvider = MapsDataProvider(ThreadHandler())
        var model = Gson().fromJson("{\"main\":[{\"title\":null}],\"products\":{\"Product 1\":{}}}", MapsModel::class.java)

        var result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null}],\"products\":{\"Product 1\":{\"title\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null}],\"products\":{\"Product 1\":{\"title\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null}],\"products\":{\"Product 1\":{\"title\":\"Product 1\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null}],\"products\":{\"Product 1\":{\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null}],\"products\":{\"Product 1\":{\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null}],\"products\":{\"Product 1\":{\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null}],\"products\":{\"Product 1\":{\"title\":null,\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null}],\"products\":{\"Product 1\":{\"title\":null,\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null}],\"products\":{\"Product 1\":{\"title\":null,\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null}],\"products\":{\"Product 1\":{\"title\":\"\",\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null}],\"products\":{\"Product 1\":{\"title\":\"\",\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null}],\"products\":{\"Product 1\":{\"title\":\"\",\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null}],\"products\":{\"Product 1\":{\"title\":\"Product 1\",\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null}],\"products\":{\"Product 1\":{\"title\":\"Product 1\",\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null}],\"products\":{\"Product 1\":{\"title\":\"Product 1\",\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\"}],\"products\":{\"Product 1\":{}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\"}],\"products\":{\"Product 1\":{\"title\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\"}],\"products\":{\"Product 1\":{\"title\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\"}],\"products\":{\"Product 1\":{\"title\":\"Product 1\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\"}],\"products\":{\"Product 1\":{\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\"}],\"products\":{\"Product 1\":{\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\"}],\"products\":{\"Product 1\":{\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\"}],\"products\":{\"Product 1\":{\"title\":null,\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\"}],\"products\":{\"Product 1\":{\"title\":null,\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\"}],\"products\":{\"Product 1\":{\"title\":null,\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\"}],\"products\":{\"Product 1\":{\"title\":\"\",\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\"}],\"products\":{\"Product 1\":{\"title\":\"\",\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\"}],\"products\":{\"Product 1\":{\"title\":\"\",\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\"}],\"products\":{\"Product 1\":{\"title\":\"Product 1\",\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\"}],\"products\":{\"Product 1\":{\"title\":\"Product 1\",\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\"}],\"products\":{\"Product 1\":{\"title\":\"Product 1\",\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Test Title\"}],\"products\":{\"Product 1\":{}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Test Title\"}],\"products\":{\"Product 1\":{\"title\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Test Title\"}],\"products\":{\"Product 1\":{\"title\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Test Title\"}],\"products\":{\"Product 1\":{\"title\":\"Product 1\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Test Title\"}],\"products\":{\"Product 1\":{\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Test Title\"}],\"products\":{\"Product 1\":{\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Test Title\"}],\"products\":{\"Product 1\":{\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Test Title\"}],\"products\":{\"Product 1\":{\"title\":null,\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Test Title\"}],\"products\":{\"Product 1\":{\"title\":null,\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Test Title\"}],\"products\":{\"Product 1\":{\"title\":null,\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Test Title\"}],\"products\":{\"Product 1\":{\"title\":\"\",\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Test Title\"}],\"products\":{\"Product 1\":{\"title\":\"\",\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Test Title\"}],\"products\":{\"Product 1\":{\"title\":\"\",\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Test Title\"}],\"products\":{\"Product 1\":{\"title\":\"Product 1\",\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Test Title\"}],\"products\":{\"Product 1\":{\"title\":\"Product 1\",\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Test Title\"}],\"products\":{\"Product 1\":{\"title\":\"Product 1\",\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)
    }



    @Test
    fun LoadMapsMainAndProductsOnlySectionsOnlyTests()
    {
        val dataProvider = MapsDataProvider(ThreadHandler())

        var model = Gson().fromJson("{\"main\":[{\"sections\":null}],\"products\":{\"Product 1\":{}}}", MapsModel::class.java)

        var result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":null}],\"products\":{\"Product 1\":{\"title\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":null}],\"products\":{\"Product 1\":{\"title\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":null}],\"products\":{\"Product 1\":{\"title\":\"Product 1\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":null}],\"products\":{\"Product 1\":{\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":null}],\"products\":{\"Product 1\":{\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":null}],\"products\":{\"Product 1\":{\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":null}],\"products\":{\"Product 1\":{\"title\":null,\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":null}],\"products\":{\"Product 1\":{\"title\":null,\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":null}],\"products\":{\"Product 1\":{\"title\":null,\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":null}],\"products\":{\"Product 1\":{\"title\":\"\",\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":null}],\"products\":{\"Product 1\":{\"title\":\"\",\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":null}],\"products\":{\"Product 1\":{\"title\":\"\",\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":null}],\"products\":{\"Product 1\":{\"title\":\"Product 1\",\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":null}],\"products\":{\"Product 1\":{\"title\":\"Product 1\",\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":null}],\"products\":{\"Product 1\":{\"title\":\"Product 1\",\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[]}],\"products\":{\"Product 1\":{}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[]}],\"products\":{\"Product 1\":{\"title\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[]}],\"products\":{\"Product 1\":{\"title\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[]}],\"products\":{\"Product 1\":{\"title\":\"Product 1\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[]}],\"products\":{\"Product 1\":{\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[]}],\"products\":{\"Product 1\":{\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[]}],\"products\":{\"Product 1\":{\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[]}],\"products\":{\"Product 1\":{\"title\":null,\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[]}],\"products\":{\"Product 1\":{\"title\":null,\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[]}],\"products\":{\"Product 1\":{\"title\":null,\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[]}],\"products\":{\"Product 1\":{\"title\":\"\",\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[]}],\"products\":{\"Product 1\":{\"title\":\"\",\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[]}],\"products\":{\"Product 1\":{\"title\":\"\",\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[]}],\"products\":{\"Product 1\":{\"title\":\"Product 1\",\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[]}],\"products\":{\"Product 1\":{\"title\":\"Product 1\",\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[]}],\"products\":{\"Product 1\":{\"title\":\"Product 1\",\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[null]}],\"products\":{\"Product 1\":{}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[null]}],\"products\":{\"Product 1\":{\"title\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[null]}],\"products\":{\"Product 1\":{\"title\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[null]}],\"products\":{\"Product 1\":{\"title\":\"Product 1\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[null]}],\"products\":{\"Product 1\":{\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[null]}],\"products\":{\"Product 1\":{\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[null]}],\"products\":{\"Product 1\":{\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[null]}],\"products\":{\"Product 1\":{\"title\":null,\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[null]}],\"products\":{\"Product 1\":{\"title\":null,\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[null]}],\"products\":{\"Product 1\":{\"title\":null,\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[null]}],\"products\":{\"Product 1\":{\"title\":\"\",\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[null]}],\"products\":{\"Product 1\":{\"title\":\"\",\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[null]}],\"products\":{\"Product 1\":{\"title\":\"\",\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[null]}],\"products\":{\"Product 1\":{\"title\":\"Product 1\",\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[null]}],\"products\":{\"Product 1\":{\"title\":\"Product 1\",\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[null]}],\"products\":{\"Product 1\":{\"title\":\"Product 1\",\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[\"Section 1\",\"Section 2\"]}],\"products\":{\"Product 1\":{}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[\"Section 1\",\"Section 2\"]}],\"products\":{\"Product 1\":{\"title\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[\"Section 1\",\"Section 2\"]}],\"products\":{\"Product 1\":{\"title\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[\"Section 1\",\"Section 2\"]}],\"products\":{\"Product 1\":{\"title\":\"Product 1\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[\"Section 1\",\"Section 2\"]}],\"products\":{\"Product 1\":{\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[\"Section 1\",\"Section 2\"]}],\"products\":{\"Product 1\":{\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[\"Section 1\",\"Section 2\"]}],\"products\":{\"Product 1\":{\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[\"Section 1\",\"Section 2\"]}],\"products\":{\"Product 1\":{\"title\":null,\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[\"Section 1\",\"Section 2\"]}],\"products\":{\"Product 1\":{\"title\":null,\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[\"Section 1\",\"Section 2\"]}],\"products\":{\"Product 1\":{\"title\":null,\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[\"Section 1\",\"Section 2\"]}],\"products\":{\"Product 1\":{\"title\":\"\",\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[\"Section 1\",\"Section 2\"]}],\"products\":{\"Product 1\":{\"title\":\"\",\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[\"Section 1\",\"Section 2\"]}],\"products\":{\"Product 1\":{\"title\":\"\",\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[\"Section 1\",\"Section 2\"]}],\"products\":{\"Product 1\":{\"title\":\"Product 1\",\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[\"Section 1\",\"Section 2\"]}],\"products\":{\"Product 1\":{\"title\":\"Product 1\",\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[\"Section 1\",\"Section 2\"]}],\"products\":{\"Product 1\":{\"title\":\"Product 1\",\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)
    }



    @Test
    fun LoadMapsMainAndProductsSectionsTitleTests()
    {
        val dataProvider = MapsDataProvider(ThreadHandler())
        var model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":null}],\"products\":{\"Product 1\":{}}}", MapsModel::class.java)

        var result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":null}],\"products\":{\"Product 1\":{\"title\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":null}],\"products\":{\"Product 1\":{\"title\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":null}],\"products\":{\"Product 1\":{\"title\":\"Product 1\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":null}],\"products\":{\"Product 1\":{\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":null}],\"products\":{\"Product 1\":{\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":null}],\"products\":{\"Product 1\":{\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":null}],\"products\":{\"Product 1\":{\"title\":null,\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":null}],\"products\":{\"Product 1\":{\"title\":null,\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":null}],\"products\":{\"Product 1\":{\"title\":null,\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":null}],\"products\":{\"Product 1\":{\"title\":\"\",\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":null}],\"products\":{\"Product 1\":{\"title\":\"\",\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":null}],\"products\":{\"Product 1\":{\"title\":\"\",\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":null}],\"products\":{\"Product 1\":{\"title\":\"Product 1\",\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":null}],\"products\":{\"Product 1\":{\"title\":\"Product 1\",\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":null}],\"products\":{\"Product 1\":{\"title\":\"Product 1\",\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":null}],\"products\":{\"Product 1\":{}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":null}],\"products\":{\"Product 1\":{\"title\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":null}],\"products\":{\"Product 1\":{\"title\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":null}],\"products\":{\"Product 1\":{\"title\":\"Product 1\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":null}],\"products\":{\"Product 1\":{\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":null}],\"products\":{\"Product 1\":{\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":null}],\"products\":{\"Product 1\":{\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":null}],\"products\":{\"Product 1\":{\"title\":null,\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":null}],\"products\":{\"Product 1\":{\"title\":null,\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":null}],\"products\":{\"Product 1\":{\"title\":null,\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":null}],\"products\":{\"Product 1\":{\"title\":\"\",\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":null}],\"products\":{\"Product 1\":{\"title\":\"\",\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":null}],\"products\":{\"Product 1\":{\"title\":\"\",\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":null}],\"products\":{\"Product 1\":{\"title\":\"Product 1\",\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":null}],\"products\":{\"Product 1\":{\"title\":\"Product 1\",\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":null}],\"products\":{\"Product 1\":{\"title\":\"Product 1\",\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":null}],\"products\":{\"Product 1\":{}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":null}],\"products\":{\"Product 1\":{\"title\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":null}],\"products\":{\"Product 1\":{\"title\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":null}],\"products\":{\"Product 1\":{\"title\":\"Product 1\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":null}],\"products\":{\"Product 1\":{\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":null}],\"products\":{\"Product 1\":{\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":null}],\"products\":{\"Product 1\":{\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":null}],\"products\":{\"Product 1\":{\"title\":null,\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":null}],\"products\":{\"Product 1\":{\"title\":null,\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":null}],\"products\":{\"Product 1\":{\"title\":null,\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":null}],\"products\":{\"Product 1\":{\"title\":\"\",\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":null}],\"products\":{\"Product 1\":{\"title\":\"\",\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":null}],\"products\":{\"Product 1\":{\"title\":\"\",\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":null}],\"products\":{\"Product 1\":{\"title\":\"Product 1\",\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":null}],\"products\":{\"Product 1\":{\"title\":\"Product 1\",\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":null}],\"products\":{\"Product 1\":{\"title\":\"Product 1\",\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[]}],\"products\":{\"Product 1\":{}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[]}],\"products\":{\"Product 1\":{\"title\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[]}],\"products\":{\"Product 1\":{\"title\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[]}],\"products\":{\"Product 1\":{\"title\":\"Product 1\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[]}],\"products\":{\"Product 1\":{\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[]}],\"products\":{\"Product 1\":{\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[]}],\"products\":{\"Product 1\":{\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[]}],\"products\":{\"Product 1\":{\"title\":null,\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[]}],\"products\":{\"Product 1\":{\"title\":null,\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[]}],\"products\":{\"Product 1\":{\"title\":null,\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[]}],\"products\":{\"Product 1\":{\"title\":\"\",\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[]}],\"products\":{\"Product 1\":{\"title\":\"\",\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[]}],\"products\":{\"Product 1\":{\"title\":\"\",\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[]}],\"products\":{\"Product 1\":{\"title\":\"Product 1\",\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[]}],\"products\":{\"Product 1\":{\"title\":\"Product 1\",\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[]}],\"products\":{\"Product 1\":{\"title\":\"Product 1\",\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[]}],\"products\":{\"Product 1\":{}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[]}],\"products\":{\"Product 1\":{\"title\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[]}],\"products\":{\"Product 1\":{\"title\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[]}],\"products\":{\"Product 1\":{\"title\":\"Product 1\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[]}],\"products\":{\"Product 1\":{\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[]}],\"products\":{\"Product 1\":{\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[]}],\"products\":{\"Product 1\":{\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[]}],\"products\":{\"Product 1\":{\"title\":null,\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[]}],\"products\":{\"Product 1\":{\"title\":null,\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[]}],\"products\":{\"Product 1\":{\"title\":null,\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[]}],\"products\":{\"Product 1\":{\"title\":\"\",\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[]}],\"products\":{\"Product 1\":{\"title\":\"\",\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[]}],\"products\":{\"Product 1\":{\"title\":\"\",\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[]}],\"products\":{\"Product 1\":{\"title\":\"Product 1\",\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[]}],\"products\":{\"Product 1\":{\"title\":\"Product 1\",\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[]}],\"products\":{\"Product 1\":{\"title\":\"Product 1\",\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)

        
        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[]}],\"products\":{\"Product 1\":{}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[]}],\"products\":{\"Product 1\":{\"title\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[]}],\"products\":{\"Product 1\":{\"title\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[]}],\"products\":{\"Product 1\":{\"title\":\"Product 1\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[]}],\"products\":{\"Product 1\":{\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[]}],\"products\":{\"Product 1\":{\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[]}],\"products\":{\"Product 1\":{\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[]}],\"products\":{\"Product 1\":{\"title\":null,\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[]}],\"products\":{\"Product 1\":{\"title\":null,\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[]}],\"products\":{\"Product 1\":{\"title\":null,\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[]}],\"products\":{\"Product 1\":{\"title\":\"\",\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[]}],\"products\":{\"Product 1\":{\"title\":\"\",\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[]}],\"products\":{\"Product 1\":{\"title\":\"\",\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[]}],\"products\":{\"Product 1\":{\"title\":\"Product 1\",\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[]}],\"products\":{\"Product 1\":{\"title\":\"Product 1\",\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[]}],\"products\":{\"Product 1\":{\"title\":\"Product 1\",\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[null]}],\"products\":{\"Product 1\":{}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[null]}],\"products\":{\"Product 1\":{\"title\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[null]}],\"products\":{\"Product 1\":{\"title\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[null]}],\"products\":{\"Product 1\":{\"title\":\"Product 1\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[null]}],\"products\":{\"Product 1\":{\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[null]}],\"products\":{\"Product 1\":{\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[null]}],\"products\":{\"Product 1\":{\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[null]}],\"products\":{\"Product 1\":{\"title\":null,\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[null]}],\"products\":{\"Product 1\":{\"title\":null,\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[null]}],\"products\":{\"Product 1\":{\"title\":null,\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[null]}],\"products\":{\"Product 1\":{\"title\":\"\",\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[null]}],\"products\":{\"Product 1\":{\"title\":\"\",\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[null]}],\"products\":{\"Product 1\":{\"title\":\"\",\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[null]}],\"products\":{\"Product 1\":{\"title\":\"Product 1\",\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[null]}],\"products\":{\"Product 1\":{\"title\":\"Product 1\",\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[null]}],\"products\":{\"Product 1\":{\"title\":\"Product 1\",\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[null]}],\"products\":{\"Product 1\":{}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[null]}],\"products\":{\"Product 1\":{\"title\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[null]}],\"products\":{\"Product 1\":{\"title\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[null]}],\"products\":{\"Product 1\":{\"title\":\"Product 1\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[null]}],\"products\":{\"Product 1\":{\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[null]}],\"products\":{\"Product 1\":{\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[null]}],\"products\":{\"Product 1\":{\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[null]}],\"products\":{\"Product 1\":{\"title\":null,\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[null]}],\"products\":{\"Product 1\":{\"title\":null,\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[null]}],\"products\":{\"Product 1\":{\"title\":null,\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[null]}],\"products\":{\"Product 1\":{\"title\":\"\",\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[null]}],\"products\":{\"Product 1\":{\"title\":\"\",\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[null]}],\"products\":{\"Product 1\":{\"title\":\"\",\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[null]}],\"products\":{\"Product 1\":{\"title\":\"Product 1\",\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[null]}],\"products\":{\"Product 1\":{\"title\":\"Product 1\",\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[null]}],\"products\":{\"Product 1\":{\"title\":\"Product 1\",\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[null]}],\"products\":{\"Product 1\":{}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[null]}],\"products\":{\"Product 1\":{\"title\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[null]}],\"products\":{\"Product 1\":{\"title\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[null]}],\"products\":{\"Product 1\":{\"title\":\"Product 1\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[null]}],\"products\":{\"Product 1\":{\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[null]}],\"products\":{\"Product 1\":{\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[null]}],\"products\":{\"Product 1\":{\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[null]}],\"products\":{\"Product 1\":{\"title\":null,\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[null]}],\"products\":{\"Product 1\":{\"title\":null,\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[null]}],\"products\":{\"Product 1\":{\"title\":null,\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[null]}],\"products\":{\"Product 1\":{\"title\":\"\",\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[null]}],\"products\":{\"Product 1\":{\"title\":\"\",\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[null]}],\"products\":{\"Product 1\":{\"title\":\"\",\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[null]}],\"products\":{\"Product 1\":{\"title\":\"Product 1\",\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[null]}],\"products\":{\"Product 1\":{\"title\":\"Product 1\",\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[null]}],\"products\":{\"Product 1\":{\"title\":\"Product 1\",\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[\"Section 1\",\"Section 2\"]}],\"products\":{\"Product 1\":{}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[\"Section 1\",\"Section 2\"]}],\"products\":{\"Product 1\":{\"title\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[\"Section 1\",\"Section 2\"]}],\"products\":{\"Product 1\":{\"title\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[\"Section 1\",\"Section 2\"]}],\"products\":{\"Product 1\":{\"title\":\"Product 1\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[\"Section 1\",\"Section 2\"]}],\"products\":{\"Product 1\":{\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[\"Section 1\",\"Section 2\"]}],\"products\":{\"Product 1\":{\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[\"Section 1\",\"Section 2\"]}],\"products\":{\"Product 1\":{\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[\"Section 1\",\"Section 2\"]}],\"products\":{\"Product 1\":{\"title\":null,\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[\"Section 1\",\"Section 2\"]}],\"products\":{\"Product 1\":{\"title\":null,\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[\"Section 1\",\"Section 2\"]}],\"products\":{\"Product 1\":{\"title\":null,\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[\"Section 1\",\"Section 2\"]}],\"products\":{\"Product 1\":{\"title\":\"\",\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[\"Section 1\",\"Section 2\"]}],\"products\":{\"Product 1\":{\"title\":\"\",\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[\"Section 1\",\"Section 2\"]}],\"products\":{\"Product 1\":{\"title\":\"\",\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[\"Section 1\",\"Section 2\"]}],\"products\":{\"Product 1\":{\"title\":\"Product 1\",\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[\"Section 1\",\"Section 2\"]}],\"products\":{\"Product 1\":{\"title\":\"Product 1\",\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[\"Section 1\",\"Section 2\"]}],\"products\":{\"Product 1\":{\"title\":\"Product 1\",\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[\"Section 1\",\"Section 2\"]}],\"products\":{\"Product 1\":{}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[\"Section 1\",\"Section 2\"]}],\"products\":{\"Product 1\":{\"title\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[\"Section 1\",\"Section 2\"]}],\"products\":{\"Product 1\":{\"title\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[\"Section 1\",\"Section 2\"]}],\"products\":{\"Product 1\":{\"title\":\"Product 1\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[\"Section 1\",\"Section 2\"]}],\"products\":{\"Product 1\":{\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[\"Section 1\",\"Section 2\"]}],\"products\":{\"Product 1\":{\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[\"Section 1\",\"Section 2\"]}],\"products\":{\"Product 1\":{\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[\"Section 1\",\"Section 2\"]}],\"products\":{\"Product 1\":{\"title\":null,\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[\"Section 1\",\"Section 2\"]}],\"products\":{\"Product 1\":{\"title\":null,\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[\"Section 1\",\"Section 2\"]}],\"products\":{\"Product 1\":{\"title\":null,\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[\"Section 1\",\"Section 2\"]}],\"products\":{\"Product 1\":{\"title\":\"\",\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[\"Section 1\",\"Section 2\"]}],\"products\":{\"Product 1\":{\"title\":\"\",\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[\"Section 1\",\"Section 2\"]}],\"products\":{\"Product 1\":{\"title\":\"\",\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[\"Section 1\",\"Section 2\"]}],\"products\":{\"Product 1\":{\"title\":\"Product 1\",\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[\"Section 1\",\"Section 2\"]}],\"products\":{\"Product 1\":{\"title\":\"Product 1\",\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[\"Section 1\",\"Section 2\"]}],\"products\":{\"Product 1\":{\"title\":\"Product 1\",\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[\"Section 1\",\"Section 2\"]}],\"products\":{\"Product 1\":{}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[\"Section 1\",\"Section 2\"]}],\"products\":{\"Product 1\":{\"title\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[\"Section 1\",\"Section 2\"]}],\"products\":{\"Product 1\":{\"title\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[\"Section 1\",\"Section 2\"]}],\"products\":{\"Product 1\":{\"title\":\"Product 1\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[\"Section 1\",\"Section 2\"]}],\"products\":{\"Product 1\":{\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[\"Section 1\",\"Section 2\"]}],\"products\":{\"Product 1\":{\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[\"Section 1\",\"Section 2\"]}],\"products\":{\"Product 1\":{\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[\"Section 1\",\"Section 2\"]}],\"products\":{\"Product 1\":{\"title\":null,\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[\"Section 1\",\"Section 2\"]}],\"products\":{\"Product 1\":{\"title\":null,\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[\"Section 1\",\"Section 2\"]}],\"products\":{\"Product 1\":{\"title\":null,\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[\"Section 1\",\"Section 2\"]}],\"products\":{\"Product 1\":{\"title\":\"\",\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[\"Section 1\",\"Section 2\"]}],\"products\":{\"Product 1\":{\"title\":\"\",\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[\"Section 1\",\"Section 2\"]}],\"products\":{\"Product 1\":{\"title\":\"\",\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[\"Section 1\",\"Section 2\"]}],\"products\":{\"Product 1\":{\"title\":\"Product 1\",\"url\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[\"Section 1\",\"Section 2\"]}],\"products\":{\"Product 1\":{\"title\":\"Product 1\",\"url\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[\"Section 1\",\"Section 2\"]}],\"products\":{\"Product 1\":{\"title\":\"Product 1\",\"url\":\"url\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)
    }



    @Test
    fun LoadMapsMainAndSectionsOnlyNullAndEmptyTests()
    {
        val dataProvider = MapsDataProvider(ThreadHandler())
        var model = Gson().fromJson("{\"main\":null,\"sections\":null}", MapsModel::class.java)

        var result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":null,\"products\":{}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":null,\"sections\":{\"Section 1\":null}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":null,\"sections\":{\"Section 1\":{}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[],\"sections\":null}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[],\"sections\":null}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[],\"sections\":{}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[],\"sections\":{\"Section 1\":{}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{}],\"sections\":null}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{}],\"sections\":{}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{}],\"sections\":{\"Section 1\":{}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)
    }



    @Test
    fun LoadMapsMainAndSectionsOnlyNullAndEmptyMainTests()
    {
        val dataProvider = MapsDataProvider(ThreadHandler())
        var model = Gson().fromJson("{\"main\":null,\"sections\":{\"Section 1\":{}}}", MapsModel::class.java)

        var result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":null,\"sections\":{\"Section 1\":{\"title\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":null,\"sections\":{\"Section 1\":{\"title\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":null,\"sections\":{\"Section 1\":{\"title\":\"Section 1\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":null,\"sections\":{\"Section 1\":{\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":null,\"sections\":{\"Section 1\":{\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":null,\"sections\":{\"Section 1\":{\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":null,\"sections\":{\"Section 1\":{\"title\":null,\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":null,\"sections\":{\"Section 1\":{\"title\":null,\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":null,\"sections\":{\"Section 1\":{\"title\":null,\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":null,\"sections\":{\"Section 1\":{\"title\":null,\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":null,\"sections\":{\"Section 1\":{\"title\":null,\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":null,\"sections\":{\"Section 1\":{\"title\":\"\",\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":null,\"sections\":{\"Section 1\":{\"title\":\"\",\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":null,\"sections\":{\"Section 1\":{\"title\":\"\",\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":null,\"sections\":{\"Section 1\":{\"title\":\"Section 1\",\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":null,\"sections\":{\"Section 1\":{\"title\":\"Section 1\",\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":null,\"sections\":{\"Section 1\":{\"title\":\"Section 1\",\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[],\"sections\":{\"Section 1\":{}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[],\"sections\":{\"Section 1\":{\"title\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[],\"sections\":{\"Section 1\":{\"title\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[],\"sections\":{\"Section 1\":{\"title\":\"Section 1\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[],\"sections\":{\"Section 1\":{\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[],\"sections\":{\"Section 1\":{\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[],\"sections\":{\"Section 1\":{\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[],\"sections\":{\"Section 1\":{\"title\":null,\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[],\"sections\":{\"Section 1\":{\"title\":null,\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[],\"sections\":{\"Section 1\":{\"title\":null,\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[],\"sections\":{\"Section 1\":{\"title\":\"\",\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[],\"sections\":{\"Section 1\":{\"title\":\"\",\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[],\"sections\":{\"Section 1\":{\"title\":\"\",\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[],\"sections\":{\"Section 1\":{\"title\":\"Section 1\",\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[],\"sections\":{\"Section 1\":{\"title\":\"Section 1\",\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[],\"sections\":{\"Section 1\":{\"title\":\"Section 1\",\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{}],\"sections\":{\"Section 1\":{}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{}],\"sections\":{\"Section 1\":{\"title\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{}],\"sections\":{\"Section 1\":{\"title\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{}],\"sections\":{\"Section 1\":{\"title\":\"Section 1\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{}],\"sections\":{\"Section 1\":{\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{}],\"sections\":{\"Section 1\":{\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{}],\"sections\":{\"Section 1\":{\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{}],\"sections\":{\"Section 1\":{\"title\":null,\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{}],\"sections\":{\"Section 1\":{\"title\":null,\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{}],\"sections\":{\"Section 1\":{\"title\":null,\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{}],\"sections\":{\"Section 1\":{\"title\":\"\",\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{}],\"sections\":{\"Section 1\":{\"title\":\"\",\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{}],\"sections\":{\"Section 1\":{\"title\":\"\",\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{}],\"sections\":{\"Section 1\":{\"title\":\"Section 1\",\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{}],\"sections\":{\"Section 1\":{\"title\":\"Section 1\",\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{}],\"sections\":{\"Section 1\":{\"title\":\"Section 1\",\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)
    }


    @Test
    fun LoadMapsMainAndSectionTitleOnlyTests()
    {
        val dataProvider = MapsDataProvider(ThreadHandler())
        var model = Gson().fromJson("{\"main\":[{\"title\":null}],\"sections\":{\"Section 1\":{}}}", MapsModel::class.java)

        var result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null}],\"sections\":{\"Section 1\":{\"title\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null}],\"sections\":{\"Section 1\":{\"title\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null}],\"sections\":{\"Section 1\":{\"title\":\"Section 1\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null}],\"sections\":{\"Section 1\":{\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null}],\"sections\":{\"Section 1\":{\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null}],\"sections\":{\"Section 1\":{\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null}],\"sections\":{\"Section 1\":{\"title\":null,\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null}],\"sections\":{\"Section 1\":{\"title\":null,\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null}],\"sections\":{\"Section 1\":{\"title\":null,\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null}],\"sections\":{\"Section 1\":{\"title\":\"\",\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null}],\"sections\":{\"Section 1\":{\"title\":\"\",\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null}],\"sections\":{\"Section 1\":{\"title\":\"\",\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null}],\"sections\":{\"Section 1\":{\"title\":\"Section 1\",\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null}],\"sections\":{\"Section 1\":{\"title\":\"Section 1\",\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null}],\"sections\":{\"Section 1\":{\"title\":\"Section 1\",\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\"}],\"sections\":{\"Section 1\":{}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\"}],\"sections\":{\"Section 1\":{\"title\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\"}],\"sections\":{\"Section 1\":{\"title\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\"}],\"sections\":{\"Section 1\":{\"title\":\"Section 1\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\"}],\"sections\":{\"Section 1\":{\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\"}],\"sections\":{\"Section 1\":{\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\"}],\"sections\":{\"Section 1\":{\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\"}],\"sections\":{\"Section 1\":{\"title\":null,\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\"}],\"sections\":{\"Section 1\":{\"title\":null,\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\"}],\"sections\":{\"Section 1\":{\"title\":null,\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\"}],\"sections\":{\"Section 1\":{\"title\":\"\",\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\"}],\"sections\":{\"Section 1\":{\"title\":\"\",\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\"}],\"sections\":{\"Section 1\":{\"title\":\"\",\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\"}],\"sections\":{\"Section 1\":{\"title\":\"Section 1\",\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\"}],\"sections\":{\"Section 1\":{\"title\":\"Section 1\",\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\"}],\"sections\":{\"Section 1\":{\"title\":\"Section 1\",\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Test Title\"}],\"sections\":{\"Section 1\":{}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Test Title\"}],\"sections\":{\"Section 1\":{\"title\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Test Title\"}],\"sections\":{\"Section 1\":{\"title\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Test Title\"}],\"sections\":{\"Section 1\":{\"title\":\"Section 1\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Test Title\"}],\"sections\":{\"Section 1\":{\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Test Title\"}],\"sections\":{\"Section 1\":{\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Test Title\"}],\"sections\":{\"Section 1\":{\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Test Title\"}],\"sections\":{\"Section 1\":{\"title\":null,\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Test Title\"}],\"sections\":{\"Section 1\":{\"title\":null,\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Test Title\"}],\"sections\":{\"Section 1\":{\"title\":null,\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Test Title\"}],\"sections\":{\"Section 1\":{\"title\":\"\",\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Test Title\"}],\"sections\":{\"Section 1\":{\"title\":\"\",\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Test Title\"}],\"sections\":{\"Section 1\":{\"title\":\"\",\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Test Title\"}],\"sections\":{\"Section 1\":{\"title\":\"Section 1\",\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Test Title\"}],\"sections\":{\"Section 1\":{\"title\":\"Section 1\",\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Test Title\"}],\"sections\":{\"Section 1\":{\"title\":\"Section 1\",\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)
    }



    @Test
    fun LoadMapsMainAndSectionsOnlySectionsOnlyTests()
    {
        val dataProvider = MapsDataProvider(ThreadHandler())

        var model = Gson().fromJson("{\"main\":[{\"sections\":null}],\"sections\":{\"Section 1\":{}}}", MapsModel::class.java)

        var result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":null}],\"sections\":{\"Section 1\":{\"title\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":null}],\"sections\":{\"Section 1\":{\"title\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":null}],\"sections\":{\"Section 1\":{\"title\":\"Section 1\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":null}],\"sections\":{\"Section 1\":{\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":null}],\"sections\":{\"Section 1\":{\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":null}],\"sections\":{\"Section 1\":{\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":null}],\"sections\":{\"Section 1\":{\"title\":null,\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":null}],\"sections\":{\"Section 1\":{\"title\":null,\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":null}],\"sections\":{\"Section 1\":{\"title\":null,\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":null}],\"sections\":{\"Section 1\":{\"title\":\"\",\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":null}],\"sections\":{\"Section 1\":{\"title\":\"\",\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":null}],\"sections\":{\"Section 1\":{\"title\":\"\",\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":null}],\"sections\":{\"Section 1\":{\"title\":\"Section 1\",\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":null}],\"sections\":{\"Section 1\":{\"title\":\"Section 1\",\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":null}],\"sections\":{\"Section 1\":{\"title\":\"Section 1\",\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[]}],\"sections\":{\"Section 1\":{}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[]}],\"sections\":{\"Section 1\":{\"title\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[]}],\"sections\":{\"Section 1\":{\"title\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[]}],\"sections\":{\"Section 1\":{\"title\":\"Section 1\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[]}],\"sections\":{\"Section 1\":{\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[]}],\"sections\":{\"Section 1\":{\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[]}],\"sections\":{\"Section 1\":{\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[]}],\"sections\":{\"Section 1\":{\"title\":null,\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[]}],\"sections\":{\"Section 1\":{\"title\":null,\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[]}],\"sections\":{\"Section 1\":{\"title\":null,\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[]}],\"sections\":{\"Section 1\":{\"title\":\"\",\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[]}],\"sections\":{\"Section 1\":{\"title\":\"\",\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[]}],\"sections\":{\"Section 1\":{\"title\":\"\",\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[]}],\"sections\":{\"Section 1\":{\"title\":\"Section 1\",\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[]}],\"sections\":{\"Section 1\":{\"title\":\"Section 1\",\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[]}],\"sections\":{\"Section 1\":{\"title\":\"Section 1\",\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[null]}],\"sections\":{\"Section 1\":{}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[null]}],\"sections\":{\"Section 1\":{\"title\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[null]}],\"sections\":{\"Section 1\":{\"title\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[null]}],\"sections\":{\"Section 1\":{\"title\":\"Section 1\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[null]}],\"sections\":{\"Section 1\":{\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[null]}],\"sections\":{\"Section 1\":{\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[null]}],\"sections\":{\"Section 1\":{\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[null]}],\"sections\":{\"Section 1\":{\"title\":null,\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[null]}],\"sections\":{\"Section 1\":{\"title\":null,\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[null]}],\"sections\":{\"Section 1\":{\"title\":null,\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[null]}],\"sections\":{\"Section 1\":{\"title\":\"\",\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[null]}],\"sections\":{\"Section 1\":{\"title\":\"\",\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[null]}],\"sections\":{\"Section 1\":{\"title\":\"\",\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[null]}],\"sections\":{\"Section 1\":{\"title\":\"Section 1\",\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[null]}],\"sections\":{\"Section 1\":{\"title\":\"Section 1\",\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[null]}],\"sections\":{\"Section 1\":{\"title\":\"Section 1\",\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[\"Section 1\",\"Section 2\"]}],\"sections\":{\"Section 1\":{}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[\"Section 1\",\"Section 2\"]}],\"sections\":{\"Section 1\":{\"title\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[\"Section 1\",\"Section 2\"]}],\"sections\":{\"Section 1\":{\"title\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[\"Section 1\",\"Section 2\"]}],\"sections\":{\"Section 1\":{\"title\":\"Section 1\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[\"Section 1\",\"Section 2\"]}],\"sections\":{\"Section 1\":{\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[\"Section 1\",\"Section 2\"]}],\"sections\":{\"Section 1\":{\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[\"Section 1\",\"Section 2\"]}],\"sections\":{\"Section 1\":{\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[\"Section 1\",\"Section 2\"]}],\"sections\":{\"Section 1\":{\"title\":null,\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[\"Section 1\",\"Section 2\"]}],\"sections\":{\"Section 1\":{\"title\":null,\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[\"Section 1\",\"Section 2\"]}],\"sections\":{\"Section 1\":{\"title\":null,\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[\"Section 1\",\"Section 2\"]}],\"sections\":{\"Section 1\":{\"title\":\"\",\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[\"Section 1\",\"Section 2\"]}],\"sections\":{\"Section 1\":{\"title\":\"\",\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[\"Section 1\",\"Section 2\"]}],\"sections\":{\"Section 1\":{\"title\":\"\",\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[\"Section 1\",\"Section 2\"]}],\"sections\":{\"Section 1\":{\"title\":\"Section 1\",\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[\"Section 1\",\"Section 2\"]}],\"sections\":{\"Section 1\":{\"title\":\"Section 1\",\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"sections\":[\"Section 1\",\"Section 2\"]}],\"sections\":{\"Section 1\":{\"title\":\"Section 1\",\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)
    }



    @Test
    fun LoadMapsMainAndSectionsSectionsTitleTests()
    {
        val dataProvider = MapsDataProvider(ThreadHandler())
        var model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":null}],\"sections\":{\"Section 1\":{}}}", MapsModel::class.java)

        var result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":null}],\"sections\":{\"Section 1\":{\"title\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":null}],\"sections\":{\"Section 1\":{\"title\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":null}],\"sections\":{\"Section 1\":{\"title\":\"Section 1\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":null}],\"sections\":{\"Section 1\":{\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":null}],\"sections\":{\"Section 1\":{\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":null}],\"sections\":{\"Section 1\":{\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":null}],\"sections\":{\"Section 1\":{\"title\":null,\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":null}],\"sections\":{\"Section 1\":{\"title\":null,\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":null}],\"sections\":{\"Section 1\":{\"title\":null,\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":null}],\"sections\":{\"Section 1\":{\"title\":\"\",\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":null}],\"sections\":{\"Section 1\":{\"title\":\"\",\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":null}],\"sections\":{\"Section 1\":{\"title\":\"\",\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":null}],\"sections\":{\"Section 1\":{\"title\":\"Section 1\",\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":null}],\"sections\":{\"Section 1\":{\"title\":\"Section 1\",\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":null}],\"sections\":{\"Section 1\":{\"title\":\"Section 1\",\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":null}],\"sections\":{\"Section 1\":{}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":null}],\"sections\":{\"Section 1\":{\"title\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":null}],\"sections\":{\"Section 1\":{\"title\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":null}],\"sections\":{\"Section 1\":{\"title\":\"Section 1\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":null}],\"sections\":{\"Section 1\":{\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":null}],\"sections\":{\"Section 1\":{\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":null}],\"sections\":{\"Section 1\":{\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":null}],\"sections\":{\"Section 1\":{\"title\":null,\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":null}],\"sections\":{\"Section 1\":{\"title\":null,\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":null}],\"sections\":{\"Section 1\":{\"title\":null,\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":null}],\"sections\":{\"Section 1\":{\"title\":\"\",\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":null}],\"sections\":{\"Section 1\":{\"title\":\"\",\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":null}],\"sections\":{\"Section 1\":{\"title\":\"\",\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":null}],\"sections\":{\"Section 1\":{\"title\":\"Section 1\",\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":null}],\"sections\":{\"Section 1\":{\"title\":\"Section 1\",\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":null}],\"sections\":{\"Section 1\":{\"title\":\"Section 1\",\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":null}],\"sections\":{\"Section 1\":{}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":null}],\"sections\":{\"Section 1\":{\"title\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":null}],\"sections\":{\"Section 1\":{\"title\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":null}],\"sections\":{\"Section 1\":{\"title\":\"Section 1\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":null}],\"sections\":{\"Section 1\":{\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":null}],\"sections\":{\"Section 1\":{\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":null}],\"sections\":{\"Section 1\":{\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":null}],\"sections\":{\"Section 1\":{\"title\":null,\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":null}],\"sections\":{\"Section 1\":{\"title\":null,\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":null}],\"sections\":{\"Section 1\":{\"title\":null,\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":null}],\"sections\":{\"Section 1\":{\"title\":\"\",\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":null}],\"sections\":{\"Section 1\":{\"title\":\"\",\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":null}],\"sections\":{\"Section 1\":{\"title\":\"\",\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":null}],\"sections\":{\"Section 1\":{\"title\":\"Section 1\",\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":null}],\"sections\":{\"Section 1\":{\"title\":\"Section 1\",\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":null}],\"sections\":{\"Section 1\":{\"title\":\"Section 1\",\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[]}],\"sections\":{\"Section 1\":{}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[]}],\"sections\":{\"Section 1\":{\"title\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[]}],\"sections\":{\"Section 1\":{\"title\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[]}],\"sections\":{\"Section 1\":{\"title\":\"Section 1\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[]}],\"sections\":{\"Section 1\":{\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[]}],\"sections\":{\"Section 1\":{\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[]}],\"sections\":{\"Section 1\":{\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[]}],\"sections\":{\"Section 1\":{\"title\":null,\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[]}],\"sections\":{\"Section 1\":{\"title\":null,\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[]}],\"sections\":{\"Section 1\":{\"title\":null,\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[]}],\"sections\":{\"Section 1\":{\"title\":\"\",\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[]}],\"sections\":{\"Section 1\":{\"title\":\"\",\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[]}],\"sections\":{\"Section 1\":{\"title\":\"\",\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[]}],\"sections\":{\"Section 1\":{\"title\":\"Section 1\",\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[]}],\"sections\":{\"Section 1\":{\"title\":\"Section 1\",\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[]}],\"sections\":{\"Section 1\":{\"title\":\"Section 1\",\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[]}],\"sections\":{\"Section 1\":{}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[]}],\"sections\":{\"Section 1\":{\"title\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[]}],\"sections\":{\"Section 1\":{\"title\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[]}],\"sections\":{\"Section 1\":{\"title\":\"Section 1\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[]}],\"sections\":{\"Section 1\":{\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[]}],\"sections\":{\"Section 1\":{\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[]}],\"sections\":{\"Section 1\":{\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[]}],\"sections\":{\"Section 1\":{\"title\":null,\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[]}],\"sections\":{\"Section 1\":{\"title\":null,\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[]}],\"sections\":{\"Section 1\":{\"title\":null,\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[]}],\"sections\":{\"Section 1\":{\"title\":\"\",\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[]}],\"sections\":{\"Section 1\":{\"title\":\"\",\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[]}],\"sections\":{\"Section 1\":{\"title\":\"\",\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[]}],\"sections\":{\"Section 1\":{\"title\":\"Section 1\",\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[]}],\"sections\":{\"Section 1\":{\"title\":\"Section 1\",\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[]}],\"sections\":{\"Section 1\":{\"title\":\"Section 1\",\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[]}],\"sections\":{\"Section 1\":{}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[]}],\"sections\":{\"Section 1\":{\"title\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[]}],\"sections\":{\"Section 1\":{\"title\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[]}],\"sections\":{\"Section 1\":{\"title\":\"Section 1\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[]}],\"sections\":{\"Section 1\":{\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[]}],\"sections\":{\"Section 1\":{\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[]}],\"sections\":{\"Section 1\":{\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[]}],\"sections\":{\"Section 1\":{\"title\":null,\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[]}],\"sections\":{\"Section 1\":{\"title\":null,\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[]}],\"sections\":{\"Section 1\":{\"title\":null,\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[]}],\"sections\":{\"Section 1\":{\"title\":\"\",\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[]}],\"sections\":{\"Section 1\":{\"title\":\"\",\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[]}],\"sections\":{\"Section 1\":{\"title\":\"\",\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[]}],\"sections\":{\"Section 1\":{\"title\":\"Section 1\",\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[]}],\"sections\":{\"Section 1\":{\"title\":\"Section 1\",\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[]}],\"sections\":{\"Section 1\":{\"title\":\"Section 1\",\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[null]}],\"sections\":{\"Section 1\":{}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[null]}],\"sections\":{\"Section 1\":{\"title\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[null]}],\"sections\":{\"Section 1\":{\"title\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[null]}],\"sections\":{\"Section 1\":{\"title\":\"Section 1\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[null]}],\"sections\":{\"Section 1\":{\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[null]}],\"sections\":{\"Section 1\":{\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[null]}],\"sections\":{\"Section 1\":{\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[null]}],\"sections\":{\"Section 1\":{\"title\":null,\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[null]}],\"sections\":{\"Section 1\":{\"title\":null,\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[null]}],\"sections\":{\"Section 1\":{\"title\":null,\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[null]}],\"sections\":{\"Section 1\":{\"title\":\"\",\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[null]}],\"sections\":{\"Section 1\":{\"title\":\"\",\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[null]}],\"sections\":{\"Section 1\":{\"title\":\"\",\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[null]}],\"sections\":{\"Section 1\":{\"title\":\"Section 1\",\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[null]}],\"sections\":{\"Section 1\":{\"title\":\"Section 1\",\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[null]}],\"sections\":{\"Section 1\":{\"title\":\"Section 1\",\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[null]}],\"sections\":{\"Section 1\":{}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[null]}],\"sections\":{\"Section 1\":{\"title\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[null]}],\"sections\":{\"Section 1\":{\"title\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[null]}],\"sections\":{\"Section 1\":{\"title\":\"Section 1\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[null]}],\"sections\":{\"Section 1\":{\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[null]}],\"sections\":{\"Section 1\":{\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[null]}],\"sections\":{\"Section 1\":{\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[null]}],\"sections\":{\"Section 1\":{\"title\":null,\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[null]}],\"sections\":{\"Section 1\":{\"title\":null,\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[null]}],\"sections\":{\"Section 1\":{\"title\":null,\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[null]}],\"sections\":{\"Section 1\":{\"title\":\"\",\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[null]}],\"sections\":{\"Section 1\":{\"title\":\"\",\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[null]}],\"sections\":{\"Section 1\":{\"title\":\"\",\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[null]}],\"sections\":{\"Section 1\":{\"title\":\"Section 1\",\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[null]}],\"sections\":{\"Section 1\":{\"title\":\"Section 1\",\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[null]}],\"sections\":{\"Section 1\":{\"title\":\"Section 1\",\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[null]}],\"sections\":{\"Section 1\":{}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[null]}],\"sections\":{\"Section 1\":{\"title\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[null]}],\"sections\":{\"Section 1\":{\"title\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[null]}],\"sections\":{\"Section 1\":{\"title\":\"Section 1\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[null]}],\"sections\":{\"Section 1\":{\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[null]}],\"sections\":{\"Section 1\":{\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[null]}],\"sections\":{\"Section 1\":{\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[null]}],\"sections\":{\"Section 1\":{\"title\":null,\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[null]}],\"sections\":{\"Section 1\":{\"title\":null,\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[null]}],\"sections\":{\"Section 1\":{\"title\":null,\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[null]}],\"sections\":{\"Section 1\":{\"title\":\"\",\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[null]}],\"sections\":{\"Section 1\":{\"title\":\"\",\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[null]}],\"sections\":{\"Section 1\":{\"title\":\"\",\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[null]}],\"sections\":{\"Section 1\":{\"title\":\"Section 1\",\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[null]}],\"sections\":{\"Section 1\":{\"title\":\"Section 1\",\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[null]}],\"sections\":{\"Section 1\":{\"title\":\"Section 1\",\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[\"Section 1\",\"Section 2\"]}],\"sections\":{\"Section 1\":{}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[\"Section 1\",\"Section 2\"]}],\"sections\":{\"Section 1\":{\"title\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[\"Section 1\",\"Section 2\"]}],\"sections\":{\"Section 1\":{\"title\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[\"Section 1\",\"Section 2\"]}],\"sections\":{\"Section 1\":{\"title\":\"Section 1\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[\"Section 1\",\"Section 2\"]}],\"sections\":{\"Section 1\":{\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[\"Section 1\",\"Section 2\"]}],\"sections\":{\"Section 1\":{\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[\"Section 1\",\"Section 2\"]}],\"sections\":{\"Section 1\":{\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[\"Section 1\",\"Section 2\"]}],\"sections\":{\"Section 1\":{\"title\":null,\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[\"Section 1\",\"Section 2\"]}],\"sections\":{\"Section 1\":{\"title\":null,\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[\"Section 1\",\"Section 2\"]}],\"sections\":{\"Section 1\":{\"title\":null,\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[\"Section 1\",\"Section 2\"]}],\"sections\":{\"Section 1\":{\"title\":\"\",\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[\"Section 1\",\"Section 2\"]}],\"sections\":{\"Section 1\":{\"title\":\"\",\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[\"Section 1\",\"Section 2\"]}],\"sections\":{\"Section 1\":{\"title\":\"\",\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[\"Section 1\",\"Section 2\"]}],\"sections\":{\"Section 1\":{\"title\":\"Section 1\",\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[\"Section 1\",\"Section 2\"]}],\"sections\":{\"Section 1\":{\"title\":\"Section 1\",\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":null,\"sections\":[\"Section 1\",\"Section 2\"]}],\"sections\":{\"Section 1\":{\"title\":\"Section 1\",\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[\"Section 1\",\"Section 2\"]}],\"sections\":{\"Section 1\":{}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[\"Section 1\",\"Section 2\"]}],\"sections\":{\"Section 1\":{\"title\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[\"Section 1\",\"Section 2\"]}],\"sections\":{\"Section 1\":{\"title\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[\"Section 1\",\"Section 2\"]}],\"sections\":{\"Section 1\":{\"title\":\"Section 1\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[\"Section 1\",\"Section 2\"]}],\"sections\":{\"Section 1\":{\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[\"Section 1\",\"Section 2\"]}],\"sections\":{\"Section 1\":{\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[\"Section 1\",\"Section 2\"]}],\"sections\":{\"Section 1\":{\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[\"Section 1\",\"Section 2\"]}],\"sections\":{\"Section 1\":{\"title\":null,\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[\"Section 1\",\"Section 2\"]}],\"sections\":{\"Section 1\":{\"title\":null,\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[\"Section 1\",\"Section 2\"]}],\"sections\":{\"Section 1\":{\"title\":null,\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[\"Section 1\",\"Section 2\"]}],\"sections\":{\"Section 1\":{\"title\":\"\",\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[\"Section 1\",\"Section 2\"]}],\"sections\":{\"Section 1\":{\"title\":\"\",\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[\"Section 1\",\"Section 2\"]}],\"sections\":{\"Section 1\":{\"title\":\"\",\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[\"Section 1\",\"Section 2\"]}],\"sections\":{\"Section 1\":{\"title\":\"Section 1\",\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[\"Section 1\",\"Section 2\"]}],\"sections\":{\"Section 1\":{\"title\":\"Section 1\",\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"\",\"sections\":[\"Section 1\",\"Section 2\"]}],\"sections\":{\"Section 1\":{\"title\":\"Section 1\",\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[\"Section 1\",\"Section 2\"]}],\"sections\":{\"Section 1\":{}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[\"Section 1\",\"Section 2\"]}],\"sections\":{\"Section 1\":{\"title\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[\"Section 1\",\"Section 2\"]}],\"sections\":{\"Section 1\":{\"title\":\"\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[\"Section 1\",\"Section 2\"]}],\"sections\":{\"Section 1\":{\"title\":\"Section 1\"}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[\"Section 1\",\"Section 2\"]}],\"sections\":{\"Section 1\":{\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[\"Section 1\",\"Section 2\"]}],\"sections\":{\"Section 1\":{\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[\"Section 1\",\"Section 2\"]}],\"sections\":{\"Section 1\":{\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[\"Section 1\",\"Section 2\"]}],\"sections\":{\"Section 1\":{\"title\":null,\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[\"Section 1\",\"Section 2\"]}],\"sections\":{\"Section 1\":{\"title\":null,\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[\"Section 1\",\"Section 2\"]}],\"sections\":{\"Section 1\":{\"title\":null,\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[\"Section 1\",\"Section 2\"]}],\"sections\":{\"Section 1\":{\"title\":\"\",\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[\"Section 1\",\"Section 2\"]}],\"sections\":{\"Section 1\":{\"title\":\"\",\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[\"Section 1\",\"Section 2\"]}],\"sections\":{\"Section 1\":{\"title\":\"\",\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[\"Section 1\",\"Section 2\"]}],\"sections\":{\"Section 1\":{\"title\":\"Product 1\",\"products\":null}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[\"Section 1\",\"Section 2\"]}],\"sections\":{\"Section 1\":{\"title\":\"Product 1\",\"products\":[]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)


        model = Gson().fromJson("{\"main\":[{\"title\":\"Section 1\",\"sections\":[\"Section 1\",\"Section 2\"]}],\"sections\":{\"Section 1\":{\"title\":\"Product 1\",\"products\":[\"Product 1\"]}}}", MapsModel::class.java)

        result = dataProvider.LoadMapsList(null, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(0, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(3, model)
        assertNull(result)

        result = dataProvider.LoadMapsList(-1, model)
        assertNull(result)
    }
}