package chapter1.p12

import chapter1.p11._
//implicitly method
final case class Animal(name:String)
final case object Animal{
 implicit val animalWriter:JsonWriter[Animal] = new JsonWriter[Animal] {
   override def write(value: Animal): Json = JString(value.name)
 }
}
object Main extends App {
   import chapter1.p11.JsonWriterInstances._
   val stringWriter = implicitly[JsonWriter[String]]
   println(stringWriter.write("Hello"))

   implicit val intWriter:JsonWriter[Int] = new JsonWriter[Int] {
     override def write(value: Int): Json = JNumber(value)
   }
   // Implicit Scope
   // The compiler searches for candidate instances in the implicit scope at the call
   // site, which roughly consists of:
   // 1) local
   // 2) imported definitions;
   // 3)definitions in the companion object of the type class or the parameter type

  println(Json.toJson(1))
  println(Json.toJson(Animal("Lion")))
  //Recursive Implicit Resoluঞon

  implicit def optionWriter[A](implicit writer:JsonWriter[A]):JsonWriter[Option[A]] = new JsonWriter[Option[A]] {
    override def write(value: Option[A]): Json = {
      value match {
        case Some(v) => writer.write(v)
        case None    => JNull
      }
    }
  }
 // val i1= implicitly[JsonWriter[Option[String]]]
  println(Json.toJson(Option(0.0))(optionWriter(JsonWriterInstances.doubleWrite)))
  println(Json.toJson(Option(Animal("Lion"))))
}
