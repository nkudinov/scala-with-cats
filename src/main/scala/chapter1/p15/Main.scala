package chapter1.p15
import java.util.Date

import cats._
import cats.implicits._
object Main extends App {
  val eqInt:Eq[Int] = Eq.apply[Int]
  println( eqInt.eqv(10,10))
  println( eqInt.eqv(1,3))

  println( 1 === 1)
  println( 10 =!= 1)

  println( Option(1) === None )
  println( (None:Option[Int]) === None)
 println (1.some === none[Int] )

  val d1 = new Date()
  val d2 = new Date()

  implicit val eqDate:Eq[Date] = new Eq[Date]{
    override def eqv(x: Date, y: Date): Boolean = x.getTime === y.getTime
  }
  d1 === d2

  final case class Cat(name: String, age: Int, color: String)

  implicit val catEq:Eq[Cat] = new Eq[Cat]{
    override def eqv(x: Cat, y: Cat): Boolean = {
      x.age === y.age && x.color === y.color
    }
  }
  val c1 = Cat("John",1,"red")
  val c2 = Cat("Mur",1,"red")
  println( c1 === c2)

}
