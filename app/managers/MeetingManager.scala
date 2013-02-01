package managers

import anorm._ 
import play.api.db.DB
import play.api.Play.current
import models.Meeting
import models.Meeting.Row2Meeting
object MeetingManager {
  def findById(id: Long): Meeting = {
    DB.withConnection { implicit c =>
      SQL("""SELECT mt.id as meeting_id, mt.date as meeting_date, mt.movie_id, mv.name as movie_name, mv.watch_date as movie_watch_date, mv.brought_by_user_id as user_id, mv.release_date as movie_release_date, mv.imdb_url as movie_imdb_url, u.first_name as user_first_name, u.last_name as user_last_name, u.password as user_password, u.email as user_email, u.phone as user_phone
               FROM meetings mt
          LEFT JOIN movies mv ON mt.movie_id = mv.id    
          LEFT JOIN users u ON mv.brought_by_user_id = u.id
              WHERE mt.id = {id}"""
         ).on("id" -> id).apply().head
    }
  }
}
