package models

import java.util.Date
import anorm._
import play.api.db.DB

case class User( id:Long, firstName:String, lastName:String, email:String, password:String, phone:String)

object User {
  implicit def Row2User( result: SqlRow ) = {
    User(result[Long]("user_id"), result[String]("user_first_name"), result[String]("user_last_name"), result[String]("user_email"), result[String]("user_password"), result[String]("user_phone"))
  }
}

case class Beer( id:Long, name:String, brewery:Option[String], style:Option[String], country:Option[String], state:Option[String] )

case class Food( id:Long, name:String)

case class Movie( id:Long, name:String, watched:Option[Date], user:User, releaseDate:Option[Date], imdbUrl:Option[String] )

object Movie {
  implicit def Row2Movie( result: SqlRow ): Movie = {
    Movie( result[Long]("movie_id"), result[String]("movie_name"), result[Option[Date]]("movie_watch_date"), result, result[Option[Date]]("movie_release_date"), result[Option[String]]("movie_imdb_url"))
  }
}

case class Meeting( id:Long, date:Date, movie:Option[Movie] )
object Meeting {
  implicit def Row2Meeting( result: SqlRow ):Meeting = { 
    ( result[Long]("meeting_id"), result[Date]("meeting_date"), result[Option[Long]]("movie_id")) match {
      case ( id:Long, date:Date, Some(movie:Long)) => Meeting( id, date, Some(result))
      case ( id:Long, date:Date, None) => Meeting( id, date, None) 
  }}
}

case class Attendance( id:Long, meeting:Meeting, user:User, assigned:Option[String], beer:Option[Beer], food:Option[Food], movie:Option[Movie], expense:Int )
