import scala.io.Source

enum Direction(val distance: Int):
  case Forward(override val distance: Int) extends Direction(distance)
  case Down(override val distance: Int) extends Direction(distance)
  case Up(override val distance: Int) extends Direction(distance)

case class Submarine(forward: Int, depth: Int):
  def go(step: Direction): Submarine =
    step match
      case Direction.Up(u) => Submarine(forward, depth - u)
      case Direction.Forward(f) => Submarine(forward + f, depth)
      case Direction.Down(d) => Submarine(forward, depth + d)

//val result = """forward 5
//    |down 5
//    |forward 8
//    |up 3
//    |down 8
//    |forward 2""".stripMargin
//    .split(raw"\v")
val parsedInput = Source.fromResource("day2.txt", getClass.getClassLoader)
  .getLines()
  .map(_.split(raw"\s"))
  .collect {
    case Array("forward", x) => Direction.Forward(x.toInt)
    case Array("down", x) => Direction.Down(x.toInt)
    case Array("up", x) => Direction.Up(x.toInt)
  }
  .toSeq

val result1 = parsedInput
  .foldLeft(Submarine(0, 0))((runner, dir) => runner.go(dir))

result1.forward * result1.depth

case class AimingSubmarine(forward: Int, depth: Int, aim: Int):
  def go(step: Direction): AimingSubmarine =
    step match
      case Direction.Forward(f) => AimingSubmarine(forward + f, depth + (aim * f), aim)
      case Direction.Up(u) => AimingSubmarine(forward, depth, aim - u)
      case Direction.Down(d) => AimingSubmarine(forward, depth, aim + d)

val result2 = parsedInput
  .foldLeft(AimingSubmarine(0, 0, 0))((sub, dir) => sub.go(dir))

result2.forward * result2.depth