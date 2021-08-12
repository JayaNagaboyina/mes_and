package org.mesonet.models.maps

import com.google.gson.Gson
import junit.framework.TestCase.*
import org.junit.Test
import java.util.*


class MapsModelTests
{
    @Test
    fun MainModelConstructorTests()
    {
        val model = MapsModel.MainModel()

        assertNull(model.GetTitle())
        assertNull(model.GetSections())
    }

    @Test
    fun MainModelNullTests()
    {
        val model = Gson().fromJson("{}", MapsModel.MainModel::class.java)

        assertNull(model.GetTitle())
        assertNull(model.GetSections())
    }

    @Test
    fun MainModelEmptyValueTests()
    {
        val model = Gson().fromJson("{\"title\":\"\",\"sections\":[]}", MapsModel.MainModel::class.java)

        assertNotNull(model.GetTitle())
        assert(model.GetTitle()?.isEmpty())
        assertNotNull(model.GetSections())
        assert(model.GetSections()?.isEmpty())
    }

    @Test
    fun MainModelFullValueTests()
    {
        val model = Gson().fromJson("{\"title\":\"test\",\"sections\":[\"test\",\"sections\",\"test\",\"sections\"]}", MapsModel.MainModel::class.java)

        val testList = Arrays.asList("test", "sections", "test", "sections")
        assertNotNull(model.GetTitle())
        assertEquals(model.GetTitle()?, "test")
        assertNotNull(model.GetSections())
        assertEquals(model.GetSections()?.size, 4)
        assertEquals(model.GetSections(), testList)
    }

    @Test
    fun ProductModelConstructorTests()
    {
        val model = MapsModel.ProductModel()

        assertNull(model.GetTitle())
        assertNull(model.GetUrl())
    }


    @Test
    fun ProductModelNullTests()
    {
        val model = Gson().fromJson("{}", MapsModel.ProductModel::class.java)

        assertNull(model.GetTitle())
        assertNull(model.GetUrl())
    }


    @Test
    fun ProductModelFullValueTests()
    {
        val model = Gson().fromJson("{\"title\":\"testTitle\",\"url\":\"https://www.google.com\"}", MapsModel.ProductModel::class.java)

        assertEquals(model.GetTitle(), "testTitle")
        assertEquals(model.GetUrl(), "https://www.google.com")
    }


    @Test
    fun MapsModelConstructorTests()
    {
        val model = MapsModel()

        assertNull(model.GetMain())
        assertNull(model.GetSections())
        assertNull(model.GetProducts())
    }


    @Test
    fun MapsModelNullTests()
    {
        val model = Gson().fromJson("{}", MapsModel::class.java)

        assertNull(model.GetMain())
        assertNull(model.GetSections())
        assertNull(model.GetProducts())
    }


    @Test
    fun MapsModelValueTests()
    {
        val model = Gson().fromJson("{\"main\":" +
                                                    "[{\"title\":\"mainTest1\"," +
                                                    "\"sections\":" +
                                                        "[\"section1\"," +
                                                         "\"test\"," +
                                                         "\"section1\"," +
                                                         "\"test\"]}," +
                                                      "{\"title\":\"mainTest2\"," +
                                                        "\"sections\":" +
                                                        "[\"section2\"," +
                                                            "\"test\"," +
                                                            "\"section2\"," +
                                                            "\"test\"," +
                                                            "\"section2\"," +
                                                            "\"test\"]}]," +
                                                        "\"sections\":{" +
                                                        "\"section1\":" +
                                                            "{\"title\":\"Section 1\"," +
                                                            "\"products\":[" +
                                                                "\"product1\"," +
                                                                "\"product2\"]}," +
                                                        "\"section2\":" +
                                                            "{\"title\":\"Section 2\"," +
                                                            "\"products\":[" +
                                                                "\"product3\"," +
                                                                "\"product4\"]}}," +
                                                    "\"products\":{" +
                                                        "\"product1\":{\"title\":\"Product 1\", \"url\":\"url\"}," +
                                                        "\"product2\":{\"title\":\"Product 2\",\"url\":\"url\"}}}", MapsModel::class.java)

        assertNotNull(model.GetMain())
        assertEquals(model.GetMain()?.size, 2)
        assertEquals(model.GetMain()?.get(0).GetTitle(), "mainTest1")
        assertNotNull(model.GetMain()?.get(0).GetSections())
        assertEquals(model.GetMain()?.get(0).GetSections()?.size, 4)
        assertEquals(model.GetMain()?.get(1).GetTitle(), "mainTest2")
        assertNotNull(model.GetMain()?.get(1).GetSections())
        assertEquals(model.GetMain()?.get(1).GetSections()?.size, 6)

        assertNotNull(model.GetSections())
        assertEquals(model.GetSections()?.size, 2)
        assertNotNull(model.GetSections()?["section1"])
        assertEquals(model.GetSections()?["section1"]?.GetTitle(), "Section 1")
        assertNotNull(model.GetSections()?["section1"]?.GetProducts())
        assertEquals(model.GetSections()?["section1"]?.GetProducts()?.size, 2)
        assertEquals(model.GetSections()?["section1"]?.GetProducts()?[0], "product1")
        assertEquals(model.GetSections()?["section1"]?.GetProducts()?[1], "product2")
        assertNotNull(model.GetSections()?["section2"])
        assertEquals(model.GetSections()?["section2"]?.GetTitle(), "Section 2")
        assertNotNull(model.GetSections()?["section2"]?.GetProducts())
        assertEquals(model.GetSections()?["section2"]?.GetProducts()?.size, 2)
        assertEquals(model.GetSections()?["section2"]?.GetProducts()?[0], "product3")
        assertEquals(model.GetSections()?["section2"]?.GetProducts()?[1], "product4")

        assertNotNull(model.GetProducts())
        assertEquals(model.GetProducts()?.size, 2)
        assertNotNull(model.GetProducts()?["product1"])
        assertEquals(model.GetProducts()?["product1"]?.GetTitle(), "Product 1")
        assertEquals(model.GetProducts()?["product1"]?.GetUrl(), "url")
        assertNotNull(model.GetProducts()?["product2"])
        assertEquals(model.GetProducts()?["product2"]?.GetTitle(), "Product 2")
        assertEquals(model.GetProducts()?["product2"]?.GetUrl(), "url")
    }
}