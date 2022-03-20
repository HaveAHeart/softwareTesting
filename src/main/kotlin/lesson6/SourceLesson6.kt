package lesson6

//t6 from https://github.com/vitaya-para/KotlinAsFirst2021

/**
 * Сложная (6 баллов)
 *
 * Перевести число roman, заданное в римской системе счисления,
 * в десятичную систему и вернуть как результат.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: XXIII = 23, XLIV = 44, C = 100
 *
 * Вернуть -1, если roman не является корректным римским числом
 */

fun roman(n: Int): String {
    val nums = listOf(1000, 500, 100, 50, 10, 5, 1)
    val assoc = mapOf<Int, String>(
        1 to "I",
        5 to "V",
        10 to "X",
        50 to "L",
        100 to "C",
        500 to "D",
        1000 to "M"
    )
    var cp = n
    return buildString {
        append(padEnd(cp / 1000, 'M'))
        cp %= 1000
        for (i in 2 until nums.size) {
            val occurrence = cp / nums[i - 1]
            val occurrenceNext = cp / nums[i]
            when {
                occurrenceNext == 9 -> {
                    append(assoc[nums[i]] + assoc[nums[i - 2]])
                    cp -= (nums[i - 2] - nums[i])
                }
                // в случае если occurrence будет 2, нам будет более "выгодно" заменить на символы, связанные с ним, нежели
                // с occurrenceNext (предполагается, что в последовательности MDCLXVI occurrence отвечает за более ранний символ,
                // а occurrenceNext за последующий (если occurrence ~ M, то occurrenceNext ~ D))
                occurrenceNext == 4 && occurrence != 2 -> {
                    append(assoc[nums[i]] + assoc[nums[i - 1]])
                    cp -= (nums[i - 1] - nums[i])
                }
                else -> when (occurrence) {
                    4 -> {
                        append(assoc[nums[i - 1]] + assoc[nums[i - 2]])
                        cp -= (nums[i - 2] - nums[i - 1])
                    }
                    else -> {
                        for (j in 0 until occurrence)
                            append(assoc[nums[i - 1]])
                        cp %= nums[i - 1]
                    }
                }
            }
        }
        for (i in 1..cp) {
            append(assoc[nums[6]])
        }
    }
}


fun fromRoman(roman: String): Int {
    if (Regex("""[^IVXLCDM]""").containsMatchIn(roman) || roman == "") return -1
    val assoc = mapOf<Char, Int>(
        'I' to 1,
        'V' to 5,
        'X' to 10,
        'L' to 50,
        'C' to 100,
        'D' to 500,
        'M' to 1000
    )
    if (roman.length == 1) return assoc[roman[0]]!!
    if (roman.isEmpty()) return 0
    var res = assoc[roman[1]]!!
    //проверяем наличие п.2 из цикла до его начала (XL)
    res += if (res > assoc[roman[0]]!!) -assoc[roman[0]]!! else assoc[roman[0]]!!
    for (i in 2 until roman.length) {
        //обработка двух цифр, идущих подряд, сумма которых меньше i-го символа (IIV)
        if (roman[i - 1] == roman[i - 2] && 2 * assoc[roman[i - 1]]!! < assoc[roman[i]]!!)
            res = res - 4 * assoc[roman[i - 1]]!! + assoc[roman[i]]!!
        else if (assoc[roman[i - 1]]!! < assoc[roman[i]]!!) //обработка одного числа, которое меньше i-го (IV) (п.2)
            res = res - 2 * assoc[roman[i - 1]]!! + assoc[roman[i]]!!
        else
            res += assoc[roman[i]]!! //это база
    }
    return if (roman(res) == roman) res else -1
}
