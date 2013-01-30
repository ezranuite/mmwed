package models

import java.util.Date
import anorm._
import play.api.db.DB

case class User( id:Long, firstName:String, lastName:String, email:String, password:String, phone:String)

object User {
  implicit def Row2User( result: SqlRow ) = {
    User(result[Long]("id"), result[String]("first_name"), result[String]("last_name"), result[String]("email"), result[String]("password"), result[String]("phone"))
  }
}

case class Beer( id:Long, name:String, brewery:Option[String], style:Option[String], country:Option[String], state:Option[String] )

case class Food( id:Long, name:String)

case class Movie( id:Long, name:String, watched:Option[Date], user:User, releaseDate:Option[Date], imdbUrl:Option[String] )

case class Meeting( id:Long, date:Date, movie:Movie )

case class Attendance( id:Long, meeting:Meeting, user:User, assigned:Option[String], beer:Option[Beer], food:Option[Food], movie:Option[Movie], expense:Int )
