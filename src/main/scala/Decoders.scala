package me.shoma.ayumi.client

import java.time.ZonedDateTime

import io.circe.Decoder.Result
import io.circe._
import io.circe.generic.auto._
import io.circe.parser._
import io.circe.syntax._
import io.circe.java8.time._
import me.shoma.ayumi.model.{Category, CustomField, Post, Tag}

object Decoders {

  implicit val decodePosts: Decoder[PostResult] = Decoder.instance { c =>
    for {
      posts <- c.downField("posts").as[List[Post]]
      page <- c.downField("page").as[Int]
      perPage <- c.downField("perPage").as[Int]
    } yield PostResult(posts, page, perPage)
  }

  implicit val decodePost: Decoder[Post] = Decoder.instance { c =>
    for {
      id <- c.downField("id").as[Long]
      title <- c.downField("title").as[String]
      content <- c.downField("content").as[String]
      categories <- c.downField("categories").as[Seq[Category]]
      tags <- c.downField("tags").as[Seq[Tag]]
      customFields <- c.downField("customFields").as[Seq[CustomField]]
      createdAt <- c.downField("createdAt").as[ZonedDateTime]
      updatedAt <- c.downField("updatedAt").as[ZonedDateTime]
      postedAt <- c.downField("postedAt").as[ZonedDateTime]
      deletedAt <- c.downField("deletedAt").as[Option[Long]]
    } yield Post(id, title, content, categories, tags, customFields, createdAt, updatedAt, postedAt, deletedAt)
  }

  implicit val decodeCategories: Decoder[CategoryResult] = Decoder.instance { c =>
    for {
      categories <- c.downField("categories").as[List[Category]]
    } yield CategoryResult(categories)
  }

  implicit val decodeCategory: Decoder[Category] = Decoder.instance { c =>
    for {
      id <- c.downField("id").as[Option[Long]]
      name <- c.downField("name").as[String]
    } yield Category(id = id, name = name)
  }

  implicit val decodeTags: Decoder[TagResult] = Decoder.instance { c =>
    for {
      tags <- c.downField("tags").as[List[Tag]]
    } yield TagResult(tags)
  }

  implicit val decodeTag: Decoder[Tag] = Decoder.instance { c =>
    for {
      id <- c.downField("id").as[Option[Long]]
      name <- c.downField("name").as[String]
    } yield Tag(id = id, name = name)
  }

  implicit val decodeCustomFields: Decoder[CustomFieldResult] = Decoder.instance { c =>
    for {
      customFields <- c.downField("customFields").as[List[CustomField]]
    } yield CustomFieldResult(customFields)
  }

  implicit val decodeCustomField: Decoder[CustomField] = Decoder.instance { c =>

    val aa = c.downField("value") match {
      case a if a.as[Int].isRight =>  a.as[Int]
      case a if a.as[Boolean].isRight => a.as[Boolean]
      case a => a.as[String]
    }
    for {
      postId <- c.downField("postId").as[Long]
      key <- c.downField("key").as[String]
      value <- aa
    } yield CustomField(postId = postId, key = key, value = value)
  }
}
