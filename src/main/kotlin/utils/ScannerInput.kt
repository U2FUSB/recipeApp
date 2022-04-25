package utils

import java.util.*

/** Allows input management with [Scanner]
 *
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

    /** Reads every line and concatenates to one String.
     * First it asks for a [finalizer] to know where to stop.
     *
     * @author Niklas7U
     * @since V 1
     * */
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