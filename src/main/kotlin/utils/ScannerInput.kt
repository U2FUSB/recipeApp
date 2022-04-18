package utils

import java.util.*

/**
 * @author Siobhan Drohan
 * @author Mairead Meagher
 * @since V 0
 * @constructor Utility ScannerInput
 * */
object ScannerInput {
    @JvmStatic
    fun readNextInt(prompt: String?):Int {
        do {
            try {
                print(prompt)
                return Scanner(System.`in`).next().toInt()
            } catch (e: NumberFormatException) {
                System.err.println("\tEnter a number please.")
            }
        } while(true)
    }
    @JvmStatic
    fun readNextDouble(prompt: String?):Double {
        do {
            try {
                print(prompt)
                return Scanner(System.`in`).next().toDouble()
            } catch (e: NumberFormatException) {
                System.err.println("\tEnter a number please.")
            }
        } while(true)
    }

    @JvmStatic
    fun readNextLine(prompt: String?): String {
        print(prompt)
        return Scanner(System.`in`).nextLine()
    }

    @JvmStatic
    fun readAllNextLines(prompt: String?): String {
        println(prompt)
        val input: StringBuilder = java.lang.StringBuilder()
        val finalizer = readNextLine("but first, enter the LAST LINE you put in. It will finalise your input:\n")
        println("Now you can enter what you wanted to enter. \n!!Remember: Last LINE must be \"${finalizer}\"!!\n")
        var nextLine: String
        val scanner = Scanner(System.`in`)
        do  {
            nextLine = scanner.nextLine()
            input.append("${nextLine}\n")
        } while (nextLine != finalizer)
        return input.toString().replace(finalizer, "")
    }

    @JvmStatic
    fun readNextChar(prompt: String?): Char {
        print(prompt)
        return Scanner(System.`in`).next()[0]
    }
}