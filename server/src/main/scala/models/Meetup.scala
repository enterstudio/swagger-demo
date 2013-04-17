package models

import models.Tag
case class Meetup (
  title: String,

  tags: List[Tag],

  active: Boolean

  )

