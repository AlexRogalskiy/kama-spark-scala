package scala

object Intro extends App {
  println(args.length)

  val intExample: Int = 5

  val ifExample: String = if (1 < 5) "1 less 5" else "5 less 1"

  lazy val UnitExample = println(ifExample)

  def getNewName(): String = "TestName"

  val getNewName2: () => String = () => "TestName"

  def changeUpdateNameInDB(name: String): Unit = {
    println("Log here")
    println("update table value($name)")
  }

  val changeUpdateNameInDBLambda: String => Unit = (name: String) => {
    println("Log here")
    println("update table value($name)")
  }


  class Example {
    def f(x: Int): Int = x + 1
  }

  object Example {
    val Const = 777

    def g(x: Int): Int = x - 1
  }

  //  println((new Example).f(5))
  //
  //  println(Example.g(5))


  def f5(x: Int, y: Int): Int = {
    def sqr(x: Int): Int =
      if (x > 0) sqr(x - 1)
      else 1

    sqr(x) + sqr(y)
  }

  class Animal

  class Cat extends Animal

  trait EatAble {
    def eat(food: String)
  }

  class Dog extends Animal with EatAble {
    override def eat(food: String): Unit = println("Dog eating")
  }

  trait Dog2 extends Animal with EatAble {
    override def eat(food: String): Unit = println("Dog2 eating")
  }

  class BulDog extends Dog with Dog2 {
  }

  val bulDog = new BulDog


  // pattern matching
  val unknown = -99


  private val matchingExample: String = unknown match {
    case 1 => "first"
    case 2 => "second"
    case x if x > 2 => "more than 2"
    case _ => "other"
  }


  case class Car(year: Int, model: String)

  val classInstanceCar = Car(-99, "Unknown")

  //  classInstanceCar match {
  //    case Car(666, _) => println("Diablo tuning")
  //    case c @ Car(_, _) if c.year > 0  => println(s"Year is ${c.model}")
  //    case Car(_, _) => println(s"Incorrect year")
  //  }

  val partFunction: PartialFunction[Int, String] = {
    case 1 => "101"
    case 2 => "102"
    case _ => "777"
  }


  val ints: Unit = Seq(1, 3, 5, 8).foreach{
    case x if x > 3 => println(300)
    case _ => println(100)
  }

  println(ints)

  val inclusive: Range.Inclusive = 1 to 10000

  val list: List[Int] = inclusive.toList

  val stringToInt: Map[String, Int] = Map("a" -> 444, "b" -> 777)

  val tuple = stringToInt.+("b" -> 777)

  case class Item(country:String, ipAddress: String)
  val listOfItem = List(1,2,3)

  val mapOfIp: Map[String, String] = ???



}
