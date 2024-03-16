package model

import kotlin.random.Random

class CardGame (
    nCell : Int,
    val onVictory : () -> Unit
) : Game
{
    private val table: GameTable<Int> = GameTable(nCell, nCell)

    fun rows() = table.rows
    fun cols() = table.cols
    init {
        fillTable()

        val times = 2

        var counter = 0;

        while (counter < times)
        {
            val x = Random.nextInt(0, table.rows)
            val y = Random.nextInt(0, table.cols)

            if (table.isShiftable(x, y))
            {
                table.shift(x, y)
                counter++
            }
        }
    }
    var gameStatus : Status = Status.STOPPED
        private set
    private fun fillTable()
    {
        table.forEach { row, col ->
            if (row != table.rows - 1 || col != table.cols - 1)
                table.getCard(row, col).value = col + (row * table.cols)
        }
    }

    fun tryMove(x: Int, y: Int) : Boolean
    {
        return table.isShiftable(x, y)
    }
    fun makeMove(x: Int, y: Int)
    {
        table.shift(x, y)

        if (decreteVictory())
        {
            onVictory()
            gameStatus = Status.STOPPED
        }

    }

    private fun decreteVictory() : Boolean
    {
        for (i in 0..< table.rows)
            for (j in 0..< table.cols)
                if ((i != table.rows - 1 || j != table.cols - 1) && table.getCard(i, j).value != (j + (i * table.cols)))
                    return false
        return true
    }

    override fun start()
    {
        if (gameStatus === Status.STOPPED)
            gameStatus = Status.STARTED
    }

    override fun stop()
    {
        if (gameStatus === Status.STARTED)
            gameStatus = Status.STOPPED

    }
    override fun toString() : String
    {
        val sb : StringBuilder = StringBuilder()
        table.forEach { row, col ->
            if (table.getCard(row, col).value === null)
                sb.append("X")
            else
                sb.append(table.getCard(row, col))

            sb.append(" ")
            if (col == table.cols - 1)
                sb.append("\n")
        }
        return sb.toString()
    }



}