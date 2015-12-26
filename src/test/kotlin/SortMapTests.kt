import org.junit.Assert.assertEquals
import kotlin.collections.mapOf
import kotlin.collections.toSortedMap
import org.junit.Test as test

class SortMapTests {

    val original = mapOf("a" to 4, "c" to 6, "b" to 2)

    @test fun toSortMap() {
        val expected = mapOf("a" to 4, "b" to 2, "c" to 6 )
        assertEquals(expected, original.toSortedMap())
    }

    @test fun toSortMapDescending() {
        val expected = mapOf("c" to 6, "b" to 2, "a" to 4)
        assertEquals(expected, original.toSortedMap(compareByDescending { it }))
    }

    @test fun toSortMap_comparator() {
        val expected = mapOf("c" to 6, "a" to 4, "b" to 2)
        assertEquals(expected, original.toSortedMap(comparator { x, y -> (original[x] as Int).compareTo((original[y] as Int)) }))
    }
}