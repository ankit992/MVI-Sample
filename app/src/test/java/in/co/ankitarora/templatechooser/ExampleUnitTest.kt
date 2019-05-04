package `in`.co.ankitarora.templatechooser

import org.junit.Test

import org.junit.Assert.*
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
    fun main() {
        assertEquals(4, 2 + 2)
        assertEquals(testtheGame(arrayOf("1", "6", "112 243 512 343 90 478", "500 789 234 400 452 150")), "WIN")
    }


fun testtheGame(args: Array<String>) {
    val lines= mutableListOf<String>()
    val scan = Scanner(System.`in`)

    val numberOfLines = scan.nextLine()!!.toInt().times(3)
    for(i in 0 until numberOfLines){
        lines.add(scan.nextLine()!!)
    }
    var numberOfHeroes = true
    var heroArray = listOf<String>()
    var villianArray = listOf<String>()
    lines.forEachIndexed { index, s ->
            if (heroArray.isEmpty() && villianArray.isEmpty() && numberOfHeroes) {
                numberOfHeroes = false
            } else if (villianArray.isEmpty() && !numberOfHeroes) {
                villianArray = s.split(" ")
            } else if (villianArray.isNotEmpty() && !numberOfHeroes) {
                heroArray = s.split(" ")
                var won = true
                val heroIntList = heroArray.map { x -> x.toInt() }.sorted()
                val villianIntList = villianArray.map { x -> x.toInt() }.sorted()
                for (i in 0 until heroArray.size) {
                    if (heroIntList[i] <= villianIntList[i]) {
                        println("LOSE")
                        won = false
                        break
                    }
                }
                if (won)
                    println("WIN")
                numberOfHeroes = true
            }
    }
}

