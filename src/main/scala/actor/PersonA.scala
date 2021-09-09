package actor

import akka.actor.Actor
import scala.math.random

class PersonA(sayZunRatio: Double, message: List[String]) extends Actor {
  def receive = {
    case Message(zun) => {
      val sayZun = random < sayZunRatio
      if (sayZun) println(message(0)) else println(message(1))
      (sayZun, zun) match {
        case (true, _) => sender ! Message(zun + 1)
        case (false, 4) => sender ! Kiyoshi
        case (false, _) => sender ! Message(0)
      }
    }
    case Kiyoshi => {
      println(message(2))
      Runtime.getRuntime().halt(0)
    }
  }
}
