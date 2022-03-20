package lesson7

import java.io.File

//t7 from https://github.com/vitaya-para/KotlinAsFirst2021

/**
 * Средняя (12 баллов)
 *
 * В русском языке, как правило, после букв Ж, Ч, Ш, Щ пишется И, А, У, а не Ы, Я, Ю.
 * Во входном файле с именем inputName содержится некоторый текст на русском языке.
 * Проверить текст во входном файле на соблюдение данного правила и вывести в выходной
 * файл outputName текст с исправленными ошибками.
 *
 * Регистр заменённых букв следует сохранять.
 *
 * Исключения (жюри, брошюра, парашют) в рамках данного задания обрабатывать не нужно
 *
 */
private fun String.syncCase(oldLine: String): String {
    val outLine = this.toMutableList()
    for (i in this.indices)
        if (oldLine[i].isUpperCase() && this[i].isLowerCase() || oldLine[i].isLowerCase() && this[i].isUpperCase())
            if (oldLine[i].isUpperCase())
                outLine[i] = outLine[i].uppercase()[0]
            else
                outLine[i] = outLine[i].lowercase()[0]
    return outLine.joinToString("")
}

fun sibilants(inputName: String, outputName: String) {
    val writer = File(outputName).bufferedWriter()
    for (line in File(inputName).readLines()) {
        writer.appendLine(
            line.lowercase().replace(Regex("""[жчшщ][ыяю]"""), transform = {
                it.value.replace('ы', 'и').replace('я', 'а').replace('ю', 'у')
            })
                .syncCase(line)
        )
    }
    writer.close()
}
