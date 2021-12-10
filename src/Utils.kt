import java.io.File
import java.lang.Integer.parseInt
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String): List<String> = File("resources", "$name.txt").readLines()

fun readInputToInt(name: String): List<Int> = File("resources", "$name.txt").readLines().map { parseInt(it) }

/**
 * Converts string to md5 hash.
 */
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)
