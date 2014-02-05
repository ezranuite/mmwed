package controllers

import play.api._
import play.api.mvc._
import managers.UserManager
import models.User

object Users extends Controller with Secured {

	def get (id:Long) = withAuth { username => _ =>
		Ok(views.html.users.view(UserManager.findById(id)))
	}

/*  def list() = { implicit request =>
    Ok(views.html.users.list(UserManager.findAll()))
  }*/
}
