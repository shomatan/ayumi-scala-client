package me.shoma.ayumi.client

import me.shoma.ayumi.client.AyumiResponses._
import io.circe.Decoder
import io.circe.parser.decode
import dispatch._
import Defaults._

object HttpClient {

}

class HttpClient {
  def get[A](endpoint: String)(implicit D: Decoder[A]): Future[AyumiResponse[A]] = {
    Http.default(url(endpoint).GET).either.map {
      case Right(response) => {
        decode[A](response.getResponseBody) match {
          case Right(json) => Right(new AyumiResult(json))
          case Left(error) => Left(JsonParsingException(error.getMessage, response.getResponseBody))
        }
      }
      case Left(error) => Left(UnexpectedException(error.getMessage))
    }
  }
}