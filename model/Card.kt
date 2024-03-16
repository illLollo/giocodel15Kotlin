package model

data class Card<T> (val x: Int, val y: Int, var value : T?)
{
    fun isEmpty() : Boolean = this.value === null;

    override fun equals(other: Any?): Boolean
    {
        if (other === null)
            return false
        if (other === this)
            return true

        if (other is Card<*>)
            return other.x == this.x && other.y == this.y && other.value == this.value
        return false;
    }

    override fun hashCode(): Int
    {
        var result = x
        result = 31 * result + y
        result = 31 * result + (value?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return value.toString()
    }

}