package me.shoma.ayumi.client

class Ayumi(endpoint: String) {

  lazy val posts = new AyumiPosts(endpoint)
  
}

object Ayumi {
  def apply(endpoint: String): Ayumi = new Ayumi(endpoint)
}