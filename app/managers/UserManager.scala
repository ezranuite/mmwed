package managers

import anorm._ 
import play.api.db.DB
import play.api.Play.current
import models.User
import models.User.Row2User
import controllers.UserData

object UserManager {

  def findById(id: Long): User = {
    DB.withConnection { implicit c =>
      SQL("""SELECT id as user_id, password as user_password, first_name as user_first_name, last_name as user_last_name, email as user_email, phone as user_phone 
               FROM users 
              WHERE id = {id}"""
      ).on("id" -> id).apply().head
    }
  }

  def findAll(): List[User] = {
    DB.withConnection { implicit c =>
      SQL("""SELECT id as user_id, password as user_password, first_name as user_first_name, last_name as user_last_name, email as user_email, phone as user_phone
               FROM users"""
      )().toList
    }
  }

  def findByEmail(email: String): User = {
    DB.withConnection { implicit c =>
      SQL("SELECT id as user_id, password as user_password, first_name as user_first_name, last_name as user_last_name, email as user_email, phone as user_phone FROM users WHERE email = {email}").on("email" -> email).apply().head
    }
  }
       
  def checkEmailPass( email: String, password: String ) = {
    DB.withConnection { implicit c =>
      SQL("SELECT id as user_id, password as user_password, first_name as user_first_name, last_name as user_last_name, email as user_email, phone as user_phone FROM users WHERE email = {email} AND password = {password}").on("email" -> email).on("password" -> password).apply().size == 1
    }
  }

  def create( user: User ): Option[Long] = {
    DB.withConnection { implicit c =>
      SQL(
        """INSERT INTO users (first_name, last_name, email, password, phone)
                 VALUES      ({firstName}, {lastName}, {email}, {password}, {phone})"""
      ).on("firstName" -> user.firstName,
           "lastName" -> user.lastName,
           "email" -> user.email,
           "password" -> user.password,
           "phone" -> user.phone).executeInsert()
    }
  }
}
