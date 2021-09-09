import actor.{Message, PersonA, PersonB}
import akka.actor._
import com.typesafe.config.ConfigFactory
import scala.collection.JavaConverters._

object Main extends App {
  val conf = ConfigFactory.load()
  val messages = conf.getStringList("system.messages").asScala.toList

  val system = ActorSystem(conf.getString("system.name"))
  val personA = system.actorOf(Props(new PersonA(conf.getDouble("personA.sayZunRatio"), messages)), name = "personA")
  val personB = system.actorOf(Props(new PersonB(personA, conf.getDouble("personB.sayZunRatio"), messages)), name = "personB")

  personB ! Message(0)
}