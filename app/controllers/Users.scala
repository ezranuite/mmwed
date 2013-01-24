package controllers

import play.api._
import play.api.mvc._
import managers.UserManager
import models.User

object Users extends Controller {

	def get (id:Long) = Action {
		Ok(views.html.users.view(UserManager.findById(id).firstName))
	}

}
