package managers

import anorm._ 
import play.api.db.DB
import play.api.Play.current
import models.User


object UserManager {

	def findById(id: Long) = {
		DB.withConnection { implicit c =>
	      val result = SQL("SELECT id, name FROM users WHERE id = {id}")
	      		.on("id" -> id).apply().head

	      new User(result[Long]("id"), result[String]("name"))
	    }
    }
}