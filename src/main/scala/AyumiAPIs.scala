package me.shoma.ayumi.client

import me.shoma.ayumi.model.{Category, CustomField, Post, Tag}
import me.shoma.ayumi.client.Decoders._

trait AyumiAPI {
  val client = new HttpClient
}

class AyumiPosts(endpoint: String) extends AyumiAPI {

  def listPosts(page: Int = 1, perPage: Int = 10) =
    client.get[List[Post]](s"$endpoint/posts?page=$page&perPage=$perPage")
}

class AyumiCategories(endpoint: String) extends AyumiAPI {

  def listCategories() = client.get[List[Category]](s"$endpoint/categories")
}

class AyumiTags(endpoint: String) extends AyumiAPI {

  def listTags() = client.get[List[Tag]](s"$endpoint/tags")
}

class AyumiCustomField(endpoint: String) extends AyumiAPI {

  def listCustomFields(postId: Long) = client.get[List[CustomField]](s"$endpoint/customFields/$postId")
}