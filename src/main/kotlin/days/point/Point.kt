package days.point

import kotlin.math.abs

interface Point<T> {
    fun addToPoint(other: T): T
    fun getPointVectorTo(other: T): T
    fun scaleBy(scaleFactor: Long): T
    fun manhattenDist(): Long
}

class Point2D(val x: Long, val y: Long) : Point<Point2D> {

    override fun addToPoint(other: Point2D): Point2D {
        return Point2D(x + other.x, y + other.y)
    }

    override fun getPointVectorTo(other: Point2D): Point2D {
        return Point2D(other.x - x, other.y - y)
    }

    override fun scaleBy(scaleFactor: Long): Point2D {
        return Point2D(x * scaleFactor, y * scaleFactor)
    }

    override fun manhattenDist(): Long {
        return abs(x) + abs(y)
    }

    fun toMinimalString(): String {
        return "$x,$y"
    }

    override fun toString(): String {
        return "(x: $x, y: $y)"
    }

    override fun equals(other: Any?): Boolean {
        return (other is Point2D) && (x == other.x) && (y == other.y)
    }

    override fun hashCode(): Int {
        return x.hashCode() + y.hashCode()
    }
}

class Point3D(val x: Long, val y: Long, val z: Long) : Point<Point3D> {

    override fun addToPoint(other: Point3D): Point3D {
        return Point3D(x + other.x, y + other.y, z + other.z)
    }

    override fun getPointVectorTo(other: Point3D): Point3D {
        return Point3D(other.x - x, other.y - y, other.z - z)
    }

    override fun scaleBy(scaleFactor: Long): Point3D {
        return Point3D(x * scaleFactor, y * scaleFactor, z * scaleFactor)
    }

    override fun manhattenDist(): Long {
        return abs(x) + abs(y) + abs(z)
    }

    fun toMinimalString(): String {
        return "$x,$y,$z"
    }

    override fun toString(): String {
        return "(x: $x, y: $y, z: $z)"
    }

    override fun equals(other: Any?): Boolean {
        return (other is Point3D) && (x == other.x) && (y == other.y) && (z == other.z)
    }

    override fun hashCode(): Int {
        return x.hashCode() + y.hashCode() + z.hashCode()
    }
}