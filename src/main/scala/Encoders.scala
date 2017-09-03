package me.shoma.ayumi.client

import io.circe.{Encoder, Json}
import io.circe.syntax._
import io.circe.generic.auto._
import io.circe.java8.time._
import me.shoma.ayumi.model._

object Encoders {

  implicit val encodePost: Encoder[Post] = Encoder.instance { c =>
    Json.obj(
      ("id", c.id.asJson),
      ("title", c.title.asJson),
      ("content", c.content.asJson),
      ("categories", c.categories.asJson),
      ("tags", c.tags.asJson),
      ("customFields", c.customFields.asJson),
      ("createdAt", c.createdAt.asJson),
      ("updatedAt", c.updatedAt.asJson),
      ("postedAt", c.postedAt.asJson),
      ("deletedAt", c.deletedAt.asJson)
    )
  }

  implicit val encodeCategory: Encoder[Category] = Encoder.instance { c =>
    Json.obj(
      ("id", c.id.asJson),
      ("name", c.name.asJson)
    )
  }

  implicit val encodeTag: Encoder[Tag] = Encoder.instance { c =>
    Json.obj(
      ("id", c.id.asJson),
      ("name", c.name.asJson)
    )
  }

  implicit val encodeCustomField: Encoder[CustomField] = Encoder.instance { c =>
    Json.obj(
      ("postId", c.postId.asJson),
      ("key", c.key.asJson),
      ("value", c.value match {
        case v: Int => Json.fromInt(v)
        case v: Boolean => Json.fromBoolean(v)
        case v => Json.fromString(v.toString)
      })
    )
  }
}
