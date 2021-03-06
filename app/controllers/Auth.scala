package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import managers.UserManager

object Auth extends Controller {
  def loginForm = Form (
    tuple( 
      "email" -> text,
      "password" -> text
    ) verifying ("Invalid email or password", result => result match {
      case (email, password) => UserManager.checkEmailPass(email, password) 
    })  
  )
  
  def login = Action { implicit request =>
    Ok(views.html.login(loginForm))
  }
 
  def authenticate = Action { implicit request =>
    loginForm.bindFromRequest.fold( 
      formWithErrors => BadRequest(views.html.index("ERROR")),
      user => Redirect(routes.Application.home).withSession(Security.username -> user._1)
    )
  }  
  
  def logout = Action {
    Redirect(routes.Auth.login).withNewSession.flashing(
      "success" -> "You are now logged out."
    )
  }
} 


trait Secured { 
  
  def username(request: RequestHeader) = request.session.get(Security.username)
  
  def onUnauthorized(request: RequestHeader) = Results.Redirect(routes.Auth.login)
  
  def withAuth( f: => String => Request[AnyContent] => Result) = {
    Security.Authenticated(username, onUnauthorized) { user =>
      Action(request => f(user)(request))
    }
  }
}
