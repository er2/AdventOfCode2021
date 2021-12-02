import scala.io.Source

Source.fromResource("input.txt", getClass.getClassLoader)
  .getLines()
  .map(_.toInt)
  .sliding(2)
  .count {
    case Seq(x, y) => x < y
  }

Source.fromResource("input.txt", getClass.getClassLoader)
  .getLines()
  .map(_.toInt)
  .sliding(4)
  .count {
    case Seq(w, x, y, z) => w < z
  }