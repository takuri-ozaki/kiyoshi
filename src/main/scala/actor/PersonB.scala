package actor

import akka.actor.{Actor, ActorRef}
import scala.math.random

class PersonB(personA: ActorRef, sayZunRatio: Double, message: List[String]) extends Actor {
  def receive = {
    case Message(zun) => {
      val sayZun = random < sayZunRatio
      if (sayZun) println(message(0)) else println(message(1))
      (sayZun, zun) match {
        case (true, _) => personA ! Message(zun + 1)
        case (false, 4) => personA ! Kiyoshi
        case (false, _) => personA ! Message(0)
      }
    }
    case Kiyoshi => {
      println(message(2))
      Runtime.getRuntime().halt(0)
    }
  }
}