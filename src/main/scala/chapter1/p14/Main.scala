package chapter1.p14
import cats._
import cats.implicits._

object Main extends App {
 val showInt = Show.apply[Int]
 println(showInt.show(10))

 val showString:Show[String] = Show.apply[String]
 println(showString.show("Hello"))
 import cats.syntax.show._
 println("hello".show)
}
