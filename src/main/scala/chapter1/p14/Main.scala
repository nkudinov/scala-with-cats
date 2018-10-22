package chapter1.p14
import java.time.LocalDate
import java.util.Date

import cats._
import cats.implicits._

object Main extends App {
 val showInt = Show.apply[Int]
 println(showInt.show(10))

 val showString:Show[String] = Show.apply[String]
 println(showString.show("Hello"))
 import cats.syntax.show._
 println("hello".show)
 implicit val localDateShow:Show[LocalDate] = new Show[LocalDate] {
   override def show(t: LocalDate): String = s"year is ${t.getYear}"
 }
 println(LocalDate.of(2018,1,1).show)

  implicit val dateShow:Show[Date] =  Show.show[Date](d => s"${d.getTime}")

  val date = new Date()
  println(date.show)
}
