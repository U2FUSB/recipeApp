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
    fun readNextChar(prompt: String?): Char {
        print(prompt)
        return Scanner(System.`in`).next()[0]
    }
}