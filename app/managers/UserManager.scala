package managers

import anorm._ 
import play.api.db.DB
import play.api.Play.current
import models.User


object UserManager {

	def findById(id: Long) = {
		DB.withConnection { implicit c =>
	      val result = SQL("SELECT * FROM users WHERE id = {id}")
	      		.on("id" -> id).apply().head

	      new User(result[Long]("id"), result[String]("first_name"), result[String]("last_name"), result[String]("email"), result[String]("password"), result[String]("phone"))
	    }
    }
}
