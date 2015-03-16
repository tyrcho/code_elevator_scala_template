package controllers

import play.api._
import play.api.mvc._
import scala.math.BigInt
import java.math.BigInteger

object Application extends Controller {
  import Logger._

  def readParam(n: String)(implicit r: Request[AnyContent]) =
    r.queryString(n)(0)

  def call = Action { implicit request =>
    val floor = readParam("atFloor").toInt
    val to = readParam("to")
    debug(s"call floor=$floor to=$to")
    Ok("")
  }

  def go = Action { implicit request =>
    val floor = readParam("floorToGo").toInt
    debug(s"go floor=$floor")
    Ok("")
  }

  def userHasEntered = Action {
    Ok("")
  }

  def userHasExited = Action {
    Ok("")
  }

  def reset = Action { implicit request =>
    val cause = readParam("cause")
    debug(s"reset cause=$cause")
    Ok("")
  }

  def nextCommand = Action { implicit request =>
    val res = "NOTHING"
    debug(res)
    Ok(res)
  }
}
