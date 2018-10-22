package chapter1.p11

sealed trait Json
final case class JObject(get:Map[String,Json]) extends Json
final case class JString(get:String) extends Json
final case class JNumber(get:Double) extends Json
case object JNull extends Json
final case class Person(name: String, email: String)

trait JsonWriter[A]{
   def write(value:A):Json
}
object JsonWriterInstances {
  implicit val stringWrite:JsonWriter[String] = new JsonWriter[String] {
    override def write(value: String): Json = JString(value)
  }
  implicit val doubleWrite:JsonWriter[Double] = new JsonWriter[Double] {
    override def write(value: Double): Json = JNumber(value)
  }
  implicit val personWrite:JsonWriter[Person] = new JsonWriter[Person] {
    override def write(value: Person): Json = JObject(Map("name" -> JString(value.name),"email" -> JString(value.email)))
  }
}
object Json {
  def toJson[A](value: A)(implicit w: JsonWriter[A]): Json =
    w.write(value)
}
object JsonSyntax {
  implicit class  JsonWriterOps[A](value:A){
    def toJson(implicit w: JsonWriter[A]): Json =
      w.write(value)
  }
}
object Main extends App {
  import Json._
  import JsonSyntax._
  import JsonWriterInstances._
  println(toJson("Hello"))
  println(toJson(1.0))
  println("Hello".toJson)
  println(1.0.toJson)
  println(Person("John","@mail.ru").toJson)
}
