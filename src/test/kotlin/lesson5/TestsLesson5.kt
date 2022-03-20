package lesson5

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class TestsLesson5 {

    @Test
    fun extractRepeats() {
        //пустой лист
        var listIn = listOf<String>()
        assertEquals(mapOf(), extractRepeats(listIn))

        //массив из одного элемента
        listIn = listOf("a")
        assertEquals(mapOf(), extractRepeats(listIn))

        //массив из нескольких элементов
        listIn = listOf("a", "b", "c")
        assertEquals(mapOf(), extractRepeats(listIn))

        //простейший случай ненулевого возврата
        listIn = listOf("a", "a")
        assertEquals(mapOf("a" to 2), extractRepeats(listIn))

        //Case-sensitivity
        listIn = listOf("a", "A", "a", "A")
        assertEquals(mapOf("a" to 2, "A" to 2), extractRepeats(listIn))

        //элементы длиннее одного символа
        listIn = listOf("lorem", "ipsum", "lorem", "amogus")
        assertEquals(mapOf("lorem" to 2), extractRepeats(listIn))

        //Один элемент - подэлемент другого
        listIn = listOf("lorem ipsum", "lorem", "ipsum")
        assertEquals(mapOf(), extractRepeats(listIn))

    }

}