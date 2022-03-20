package lesson7

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.io.path.Path
import kotlin.test.assertEquals

class TestsLesson7 {
    val pathIn = Path("src", "test", "resources", "l7", "in")
    val pathOut = Path("src", "test", "resources", "l7", "out")
    val pathTmp = Path("src", "test", "resources", "l7", "tmp", "tmp.txt")

    fun sibilants(fileName: String) {
        File(pathIn.toUri()).walkBottomUp().filter { it.name.equals(fileName) }.forEach {
            File(pathTmp.toUri()).createNewFile()
            sibilants(it.canonicalPath, File(pathTmp.toUri()).canonicalPath)
            val idealOutput = File(pathOut.toUri())
                .walk()
                .filter { idealOut -> idealOut.name.equals(it.name) }
                .first()
                .readLines()
            val receivedOutput = File(pathTmp.toUri()).readLines()
            File(pathTmp.toUri()).delete()
            assertEquals(idealOutput, receivedOutput)
        }
    }

    @Test
    fun emptyTest() { sibilants("empty") }

    @Test
    fun correctSpellingTest() { sibilants("simpleCorrect") }

    @Test
    fun incorrectSpellingTest() { sibilants("simpleIncorrect") }

    @Test
    fun caseSensitiveTest() { sibilants("caseSensitivity") }

}