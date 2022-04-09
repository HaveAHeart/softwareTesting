package lesson6.fuzzing

import com.code_intelligence.jazzer.api.FuzzedDataProvider
import kotlin.random.Random

class L6fuzzing {
    companion object {
        @JvmStatic
        fun fuzzerTestOneInput(data: FuzzedDataProvider) {
            val inStr = data.consumeAsciiString(100)
            lesson6.fromRoman(inStr)
        }
    }
}