import scala.io.Source

val numbers = Source.fromResource("day1.txt", getClass.getClassLoader)
  .getLines()
  .map(_.toInt)
  .toSeq

numbers
  .sliding(2)
  .count {
    case Seq(x, y) => x < y
  }

numbers
  .sliding(4)
  .count {
    case Seq(w, x, y, z) => w < z
  }