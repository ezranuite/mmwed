package controllers

import play.api.mvc._
import managers.UserManager
import play.api.data._
import play.api.data.Forms._
import models.User

case class UserData( firstName: String, lastName: String, email: String, phone: String)
object UserData {
  def create( user: User ) = {
    UserData( user.firstName, user.lastName, user.email, user.phone match { case Some(phone) => phone; case None => ""})
  }
}

object Users extends Controller with Secured {

  val userForm = Form(
    mapping(
      "firstName" -> nonEmptyText,
      "lastName" -> nonEmptyText,
      "email" -> email,
      "phone" -> text
    )(UserData.apply)(UserData.unapply)
  )

	def get (id:Long) = withAuth { username => _ =>
		Ok(views.html.users.view(UserManager.findById(id)))
	}

  def list() = withAuth { username => _ =>
    Ok(views.html.users.list(UserManager.findAll()))
  }

  def form() = withAuth { username => implicit request =>
    Ok(views.html.users.form(userForm))
  }

  def form(id:Long) = withAuth { username => implicit request =>
    Ok(views.html.users.form(userForm.fill(UserData.create(UserManager.findById(id)))))
  }

  def submit() = withAuth { username => implicit request =>
    userForm.bindFromRequest.fold(
      formWithErrors => {
        BadRequest(views.html.users.form(formWithErrors))
      },
      userData => {
        //Need to generate password and send email for the new user
        val newUser = User(None, userData.firstName, userData.lastName, userData.email, "toby123", Some(userData.phone))
        val id: Option[Long] = UserManager.create(newUser)

        id match {
          case Some(userId:Long) => Redirect(routes.Users.get(userId))
          case None => BadRequest(views.html.users.form(userForm))
        }
      }
    )
  }
}
