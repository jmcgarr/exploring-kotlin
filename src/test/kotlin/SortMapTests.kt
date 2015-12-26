import org.junit.Assert.assertEquals
import kotlin.collections.*
import org.junit.Test

class SortMapTests {

    val original = mapOf("a" to 4, "c" to 6, "b" to 2, "d" to 0)

    fun assertOrder( expected: Map<String, Int>, actual: Map<String, Int>) {
        val expectedKeys = expected.keys.toArrayList()
        val actualKeys = actual.keys.toArrayList()
        for(i in expectedKeys.indices) {
            assertEquals( expectedKeys[i], actualKeys[i] )
        }
    }

    @Test fun toSortedMap() {
        val expected = mapOf("a" to 4, "b" to 2, "c" to 6, "d" to 0 )
        val actual = original.toSortedMap()
        assertOrder( expected, actual )
    }

    @Test fun toSortedMapDescending() {
        val expected = mapOf("d" to 0, "c" to 6, "b" to 2, "a" to 4)
        val actual = original.toSortedMap(compareByDescending { it })
        assertOrder(expected, actual)
    }

    /**
     * Question: Why does this implementation require a cast to Int in order to compare?
     */
    @Test fun toSortedMap_comparator() {
        val expected = mapOf("c" to 6, "a" to 4, "b" to 2, "d" to 0)
        val actual = original.toSortedMap(comparator { x, y -> (original[y] as Int).compareTo((original[x] as Int)) })
        assertOrder(expected, actual)
    }

    @Test fun filter_thenSortByValue() {
        val expected = mapOf("c" to 6, "a" to 4, "b" to 2)
        val actual = original.filter { it.value > 0 }
                             .toSortedMap(comparator { x, y -> (original[y] as Int).compareTo((original[x] as Int)) })
        assertOrder(expected, actual)
    }
}