package lesson5.fuzzing

import com.code_intelligence.jazzer.api.FuzzedDataProvider
import kotlin.random.Random

class L5fuzzing {
    companion object {
        @JvmStatic
        fun fuzzerTestOneInput(data: FuzzedDataProvider) {
                val arr = mutableListOf<String>()
                for (j in 0..Random.nextInt(1000)) {
                    arr.add(data.consumeAsciiString(10))
                }
                lesson5.extractRepeats(arr)
        }
    }
}