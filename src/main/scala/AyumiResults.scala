package me.shoma.ayumi.client

import me.shoma.ayumi.model.Post

case class PostResult(posts: List[Post], page: Int, perPage: Int)