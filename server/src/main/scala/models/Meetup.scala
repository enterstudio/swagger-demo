package models

import models.Tag
import scala.reflect.BeanProperty

case class Meetup (
  title: String,
  tags: List[Tag],
  active: Boolean)

