package model

abstract class Table<T> protected constructor(
    val rows: Int,
    val cols: Int,
)
{
    var matrix: Array<Array<Card<T?>>> = Array(rows) { row -> Array(cols) { col -> Card(row, col, null)} }

    fun getCard(x: Int, y: Int) : Card<T?>
    {
        if (x < 0 || x >= rows || y < 0 || y >= cols)
            throw IllegalArgumentException("Parameters out of bounds")

        return matrix[x][y]
    }
    fun getCol(index: Int) : List<Card<T?>>
    {
        if (index < 0 || index >= cols)
            throw IllegalArgumentException("Parameters out of bounds")

        val temp : ArrayList<Card<T?>> = ArrayList()

        for (i in 0..<rows)
            temp.add(getCard(i, index))

        return temp
    }

    fun getRow(index: Int) : List<Card<T?>>
    {
        if (index < 0 || index >= rows)
            throw IllegalArgumentException("Parameters out of bounds")

        return matrix[index].toList()
    }
    fun getColValues(index: Int) : List<Card<T?>>
    {
        if (index < 0 || index >= cols)
            throw IllegalArgumentException("Parameters out of bounds")

        val values : ArrayList<Card<T?>> = ArrayList()

        val col = getCol(index)

        for (element in col)
            values.add(element)

        return values
    }
    fun getRowValues(index: Int) : List<Card<T?>>
    {
        if (index < 0 || index >= rows)
            throw IllegalArgumentException("Parameters out of bounds")
        val values : ArrayList<Card<T?>> = ArrayList()

        val row = getRow(index)

        for (element in row)
            values.add(element)
        return values
    }

    override fun toString(): String
    {
        val sb : StringBuilder = StringBuilder()
        for (i in 0 ..< rows)
            sb.append(getRow(i))
        return sb.toString()
    }
}