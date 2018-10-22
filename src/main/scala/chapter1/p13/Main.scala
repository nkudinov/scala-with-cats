package chapter1.p13

trait Printable[A]{
  def format(value:A):String
}
final case class Cat(name: String, age: Int, color: String)
object PrintableInstances{
  implicit val printableString:Printable[String] = new Printable[String] {
    override def format(value: String): String = value
  }
  implicit val printableInt:Printable[Int] = new Printable[Int] {
    override def format(value: Int): String = value.toString
  }
  implicit val printableCat:Printable[Cat] = new Printable[Cat] {
    override def format(value: Cat): String = s"${value.name} is a ${value.age} year-old ${value.color} cat."
  }
}
object Printable{
  def format[A](value:A)(implicit ev:Printable[A]):String = ev.format(value)
  def print[A](value:A)(implicit ev:Printable[A]):Unit =
    println(ev.format(value))

}
object PrintableSyntax{
  implicit class PrintableOps[A](value:A){
    def format(implicit ev:Printable[A]): String ={
      ev.format(value)
    }
  }
}
object Main extends App {
 import PrintableInstances._
  println(Printable.format("hello"))
  println(Printable.format(1))
  Printable.print("hello,world")
  Printable.print(2)
  Printable.print(Cat("Kiss",1,"red"))
  import PrintableSyntax._
  println(Cat("Kiss2",1,"green").format)
}
