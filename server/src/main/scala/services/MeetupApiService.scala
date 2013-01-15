package services

import models._

object MeetupApiService {
	var meetups = List(
		Meetup("another scala startup", List(Tag("software"), Tag("programming")), true),
		Meetup("cooking meetup", List(Tag("cooking"), Tag("drinking"), Tag("dancing")), true)
	)

	def addMeetup(meetup: Meetup) = {
		meetups = meetups -- meetups.filter(_.title == meetup.title) ++ List(meetup)
	}

	def removeMeetup(name: String) = {
		meetups = meetups -- meetups.filter(_.title == name)
	}

	def findMeetups(title:Option[String], tags: Option[String], active: Option[Boolean]) = {
		val o = ((title match {
			case Some(s) => meetups.filter(_.title.startsWith(s)).toList
			case _ => List.empty
		}) ++ (tags match {
			case Some(tag) => {
				meetups.filter(m => {
					m.tags.map(_.name).toSet.contains(tag)
				})
			}
			case _ => List.empty
		})).toList
		
		active match {
			case Some(a) => o.filter(_.active == a)
			case _ => o
		}
	}
}
