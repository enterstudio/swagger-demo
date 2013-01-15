package com.wordnik.client.model

import com.wordnik.client.model.Tag
case class Meetup (
  title: String,
  tags: List[Tag],
  active: Boolean)

