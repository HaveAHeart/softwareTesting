package lesson6

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class TestsLesson6 {

    @Test
    fun fromRoman() {
        //пустой ввод
        var romanIn = ""
        assertEquals(-1, fromRoman(romanIn))

        //простейшее корректное одиночное число
        romanIn = "X"
        assertEquals(10, fromRoman(romanIn))

        //простейшее корректное повторение символа
        romanIn = "III"
        assertEquals(3, fromRoman(romanIn))

        //Число, задействующее все символы
        romanIn = "MDCLXVI"
        assertEquals(1666, fromRoman(romanIn))

        //некорректное число - 8 - VIII, а не IIX
        romanIn = "IIX"
        assertEquals(-1, fromRoman(romanIn))

        //некорректное число с разницей между символами в 2 раза - e.g. VX -> V
        var romansInDoubleRepeat = listOf("VX", "LC", "DM")
        romansInDoubleRepeat.forEach { assertEquals(-1, fromRoman(it)) }

        //некорректное число - кейс с 2 повторениями (VV -> X, LL-> C, DD -> M)
        romansInDoubleRepeat = listOf("VV", "LL", "DD")
        romansInDoubleRepeat.forEach{ assertEquals(-1, fromRoman(it)) }

        //некорректное число - кейс с 4 повторениями
        romanIn = "IIII"
        assertEquals(-1, fromRoman(romanIn))

        //некорректное число - кейс с 5 повторениями (IIIII -> V, XXXXX -> L, CCCCC-> D)
        val romansInPentRepeat = listOf("IIIII", "XXXXX", "CCCCC")
        romansInPentRepeat.forEach { assertEquals(-1, fromRoman(it)) }

        //корректное число - повторяем M сколько угодно раз
        romanIn = "MMMMMMMMMMMMMMM"
        assertEquals(15000, fromRoman(romanIn))

        //пример, в котором используется как сложение, так и вычитание (XCV = 100 - 10 + 5 = 95)
        romanIn = "XCV"
        assertEquals(95, fromRoman(romanIn))
    }

}
