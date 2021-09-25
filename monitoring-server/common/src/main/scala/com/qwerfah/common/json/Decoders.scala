package com.qwerfah.common.json

import io.circe.{Decoder, HCursor}

import cats.data.Validated.{Valid, Invalid}

import com.qwerfah.common.exceptions._
import com.qwerfah.common.Uid
import com.qwerfah.common.resources._

object Decoders {
    import JsonSchemas._

    implicit val decodeCredentials: Decoder[Credentials] =
        (c: HCursor) => {
            credentialsSchema.validate(c.value) match {
                case Valid(()) =>
                    for {
                        login <- c.downField("login").as[String]
                        password <- c.downField("password").as[String]
                    } yield Credentials(login, password)
                case Invalid(errors) =>
                    throw InvalidJsonBodyException(errors)
            }
        }

}
