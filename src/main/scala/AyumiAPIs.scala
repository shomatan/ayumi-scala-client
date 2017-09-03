package me.shoma.ayumi.client

import me.shoma.ayumi.model.{Category, CustomField, Post, Tag}
import me.shoma.ayumi.client.Encoders._
import me.shoma.ayumi.client.Decoders._

trait AyumiAPI {
  val client = new HttpClient
}

class AyumiPosts(endpoint: String) extends AyumiAPI {

  def listPosts(page: Int = 1, perPage: Int = 10) =
    client.get[PostResult](s"$endpoint/posts?page=$page&perPage=$perPage")

  def get(postId: Long) = client.get[Post](s"$endpoint/posts/$postId")

  def save(post: Post) = client.post[Post](s"$endpoint/posts", post)
}

class AyumiCategories(endpoint: String) extends AyumiAPI {

  def listCategories() = client.get[CategoryResult](s"$endpoint/categories")
}

class AyumiTags(endpoint: String) extends AyumiAPI {

  def listTags() = client.get[TagResult](s"$endpoint/tags")
}

class AyumiCustomFields(endpoint: String) extends AyumiAPI {

  def listCustomFields(postId: Long) = client.get[CustomFieldResult](s"$endpoint/customFields/$postId")
}