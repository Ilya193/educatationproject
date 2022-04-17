package com.xlwe.educatationproject.core

import org.junit.Assert.*
import org.junit.Test
import java.net.UnknownHostException

class AbstractTest {

    @Test
    fun test_success() {
        val dataObject = TestDataObject.Success("a", "b")
        val domainObject = dataObject.map(DataToDomainMapper.Base())
        assertTrue(domainObject is DomainObject.Success)
    }

    @Test
    fun test_fail() {
        val dataObject = TestDataObject.Fail(UnknownHostException())
        val domainObject = dataObject.map(DataToDomainMapper.Base())
        assertTrue(domainObject is DomainObject.Fail)
    }

    private sealed class TestDataObject : Abstract.Object<DomainObject, DataToDomainMapper>() {
        abstract override fun map(mapper: DataToDomainMapper): DomainObject

        class Success(private val textOne: String, private val textTwo: String) : TestDataObject() {
            override fun map(mapper: DataToDomainMapper): DomainObject {
                return mapper.map(textOne, textTwo)
            }
        }

        class Fail(private val exception: Exception) : TestDataObject() {
            override fun map(mapper: DataToDomainMapper): DomainObject {
                return mapper.map(exception)
            }
        }
    }

    private interface DataToDomainMapper : Abstract.Mapper {
        fun map(textOne: String, textTwo: String): DomainObject

        fun map(exception: Exception): DomainObject

        class Base : DataToDomainMapper {
            override fun map(textOne: String, textTwo: String): DomainObject {
                return DomainObject.Success("$textOne $textTwo")
            }

            override fun map(exception: Exception): DomainObject {
                return DomainObject.Fail()
            }
        }
    }

    private sealed class DomainObject : Abstract.Object<UiObject, DomainToUiMapper>() {
        class Success(private val textCombined: String) : DomainObject() {
            override fun map(mapper: DomainToUiMapper): UiObject {
                TODO()
            }
        }

        class Fail() : DomainObject() {
            override fun map(mapper: DomainToUiMapper): UiObject {
                TODO()
            }

        }
    }

    private interface DomainToUiMapper : Abstract.Mapper

    private sealed class UiObject : Abstract.Object<Unit, Abstract.Mapper.Empty>() {

    }
}