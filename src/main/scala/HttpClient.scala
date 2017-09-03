package me.shoma.ayumi.client

import java.nio.charset.Charset

import me.shoma.ayumi.client.AyumiResponses._
import io.circe.{Decoder, Encoder}
import io.circe.parser.decode
import io.circe.syntax._
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

  def post[A](endpoint: String, data: A)(implicit E: Encoder[A], D: Decoder[A]): Future[AyumiResponse[A]] = {
    val request = url(endpoint)
      .POST
      .setBody(data.asJson.noSpaces)
      .setContentType("application/json", Charset.defaultCharset())
    Http.default(request).either.map {
      case Right(response) => {
        response.getStatusCode match {
          case 200 => {
            decode[A](response.getResponseBody) match {
              case Right(json) => Right(new AyumiResult(json))
              case Left(error) => Left(JsonParsingException(error.getMessage, response.getResponseBody))
            }
          }
          case _ => Left(UnexpectedException(response.getResponseBody))
        }
      }
      case Left(error) => Left(UnexpectedException(error.getMessage))
    }
  }
}