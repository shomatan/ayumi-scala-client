package me.shoma.ayumi.client

import me.shoma.ayumi.model.{Category, Post}
import me.shoma.ayumi.client.Decoders._

class AyumiPosts(endpoint: String) {

  def listPosts() = ???
}


class AyumiCategories(endpoint: String) {
  val client = new HttpClient
  def listCategories() = client.get[List[Category]](s"$endpoint/categories")
}