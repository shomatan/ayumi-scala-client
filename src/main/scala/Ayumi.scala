package me.shoma.ayumi.client

class Ayumi(endpoint: String) {

  lazy val posts = new AyumiPosts(endpoint)
  lazy val categories = new AyumiCategories(endpoint)
  lazy val tags = new AyumiTags(endpoint)
  lazy val customFields = new AyumiCustomFields(endpoint)
  
}

object Ayumi {
  def apply(endpoint: String): Ayumi = new Ayumi(endpoint)
}