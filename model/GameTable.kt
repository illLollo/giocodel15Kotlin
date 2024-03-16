package model

class GameTable<T>(
    rows: Int,
    cols: Int,
) : Table<T>(rows, cols)
{
    var empty : Card<T?> = getCard(rows - 1, cols - 1)
        private set
    fun isVoid(x: Int, y: Int) = super.getCard(x,y).value === null
    fun isShiftable(x: Int, y: Int) : Boolean
    {
        if (isVoid(x, y))
            return false

        if (getCard(x,y) == empty)
            return false

        return getRow(x).contains(empty) || getCol(y).contains(empty)
    }
    fun shift(x: Int, y: Int)
    {
        if (!isShiftable(x, y))
            throw IllegalArgumentException("Cell not shiftable")

        if (getCol(y).contains(empty))
        {
            if (empty.x >= x)
            {
                for (i in empty.x downTo x + 1)
                    getCard(i, y).value = getCard(i - 1, y).value
            }
            else
            {
                for (i in empty.x + 1 .. x)
                    getCard(i - 1, y).value = getCard(i, y).value
            }

        }
        else
        {
            if (empty.y >= y)
            {
                for (i in empty.y downTo y + 1)
                    getCard(x, i).value = getCard(x, i - 1).value
            }
            else
            {
                for (i in empty.y + 1 .. y)
                    getCard(x, i - 1).value = getCard(x, i).value
            }


        }
            getCard(x, y).value = null
            empty = getCard(x, y)

    }
    fun forEach(func: (row: Int, col: Int) -> Unit)
    {
        for (i in 0 ..< rows)
            for (j in 0 ..< cols)
                func(i, j)
    }


}
