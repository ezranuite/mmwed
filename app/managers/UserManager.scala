package managers

import anorm._ 
import play.api.db.DB
import play.api.Play.current
import models.User
import models.User.Row2User

object UserManager {

  def findById(id: Long): User = {
    DB.withConnection { implicit c =>
      SQL("SELECT * FROM users WHERE id = {id}").on("id" -> id).apply().head
    }
  }

  def findByEmail(email: String): User = {
    DB.withConnection { implicit c =>
      SQL("SELECT * FROM users WHERE email = {email}").on("email" -> email).apply().head
    }
  }
       
  def checkEmailPass( email: String, password: String ) = { DB.withConnection { implicit c => 
    SQL("SELECT * FROM users WHERE email = {email} AND password = {password}").on("email" -> email).on("password" -> password).apply().size == 1
  }}
}
