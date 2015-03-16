package controllers

import play.api._
import play.api.mvc._
import scala.math.BigInt
import java.math.BigInteger

object Application extends Controller {
  import Logger._

  var state = ElevatorState()

  def call = Action { implicit request =>
    val floor = readParam("atFloor").toInt
    val to = readParam("to")
    debug(s"call floor=$floor to=$to")
    state = state.call(floor, to == "UP")
    debug(state.toString)
    Ok("")
  }

  def go = Action { implicit request =>
    val floor = readParam("floorToGo").toInt
    debug(s"go floor=$floor")
    state = state.go(floor)
    debug(state.toString)
    Ok("")
  }

  def userHasEntered = Action {
    debug("enters")
    state = state.enters
    debug(state.toString)
    Ok("")
  }

  def userHasExited = Action {
    debug("exits")
    state = state.exits
    debug(state.toString)
    Ok("")
  }

  def reset = Action { implicit request =>
    val cause = readParam("cause")
    debug(s"reset cause=$cause")
    state = ElevatorState()
    debug(state.toString)
    Ok("")
  }

  def nextCommand = Action { implicit request =>
    val (cmd, nextState) = state.next
    debug(cmd)
    state = nextState
    debug(state.toString)
    Ok(cmd)
  }

  def readParam(n: String)(implicit r: Request[AnyContent]) =
    r.queryString(n)(0)

}
