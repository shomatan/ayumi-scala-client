package me.shoma.ayumi.client

import me.shoma.ayumi.model.{Category, Post, Tag}
import me.shoma.ayumi.client.Decoders._

trait AyumiAPI {
  val client = new HttpClient
}

class AyumiPosts(endpoint: String) extends AyumiAPI {

  def listPosts() = ???
}

class AyumiCategories(endpoint: String) extends AyumiAPI {

  def listCategories() = client.get[List[Category]](s"$endpoint/categories")
}

class AyumiTags(endpoint: String) extends AyumiAPI {

  def listTags() = client.get[List[Tag]](s"$endpoint/tags")
}