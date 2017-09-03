package me.shoma.ayumi.client

import me.shoma.ayumi.model._

trait Pagination {
  val page: Int
  val perPage: Int
}

case class PostResult(
    posts: List[Post],
    override val page: Int,
    override val perPage: Int
  ) extends Pagination

case class CategoryResult(categories: List[Category])

case class TagResult(tags: List[Tag])

case class CustomFieldResult(customFields: List[CustomField])