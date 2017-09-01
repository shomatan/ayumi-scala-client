package me.shoma.ayumi.client

object AyumiResponses {

  type AyumiResponse[A] = Either[AyumiException, AyumiResult[A]]

  case class AyumiResult[A](result: A)

  sealed abstract class AyumiException(msg: String, cause: Option[Throwable] = None)
      extends Throwable(msg) {
    cause foreach initCause
  }

  case class JsonParsingException(msg: String, json: String)
      extends AyumiException(msg)

  case class UnexpectedException(msg: String) extends AyumiException(msg)
}
