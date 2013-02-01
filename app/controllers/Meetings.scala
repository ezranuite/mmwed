package controllers

import play.api._
import play.api.mvc._
import managers.MeetingManager

object Meetings extends Controller with Secured {

	def get (id:Long) = withAuth { username => _ => 
		Ok(views.html.meetings.view(MeetingManager.findById(id)))
	}

}
