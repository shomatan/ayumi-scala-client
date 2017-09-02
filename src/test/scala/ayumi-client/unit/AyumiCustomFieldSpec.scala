package com.shoma.ayumi.client.unit

import org.scalatest._
import io.circe.parser.decode
import me.shoma.ayumi.client.Decoders._
import me.shoma.ayumi.model.CustomField

class AyumiCustomFieldSpec extends FlatSpec {

  "AyumiCustomField.listCustomFields" should "get specified post's custom fields" in {
    val actual = decode[List[CustomField]](json) match {
      case Right(json) => Right(json)
      case Left(error) => Left(error.getMessage)
    }

    assert(actual.right.get(0).key == "total_count")
    assert(actual.right.get(0).value == 123)
    assert(actual.right.get(1).key == "is_title")
    assert(actual.right.get(1).value == false)
  }

  val json =
    """
      |[
      |  {"postId":1,"key":"total_count","value":"123"},
      |  {"postId":1,"key":"is_title","value":false}
      |]
    """.stripMargin
}