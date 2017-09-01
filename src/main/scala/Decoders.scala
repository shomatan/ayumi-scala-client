package me.shoma.ayumi.client

import io.circe._
import io.circe.generic.auto._
import me.shoma.ayumi.model.{Category, Tag}

object Decoders {

  implicit val decodeCategory: Decoder[Category] = Decoder.instance { c =>
    for {
      id <- c.downField("id").as[Option[Long]]
      name <- c.downField("name").as[String]
    } yield Category(id = id, name = name)
  }

  implicit val decodeTag: Decoder[Tag] = Decoder.instance { c =>
    for {
      id <- c.downField("id").as[Option[Long]]
      name <- c.downField("name").as[String]
    } yield Tag(id = id, name = name)
  }
}
