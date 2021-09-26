package com.qwerfah.common.util

import scala.concurrent.{Future => ScalaFuture}
import scala.concurrent.ExecutionContext
import scala.util.{Success, Failure}

import com.twitter.finagle.http.{Method => TwitterMethod}
import com.twitter.util.{Future => TwitterFuture, Promise => TwitterPromise}

import com.qwerfah.common.http._
import com.qwerfah.common.services.response._
import com.qwerfah.common.exceptions._

/** Provide implicit classes for different type conversions. */
object Conversions {

    /** Scala to twitter future convertor.
      * @param sf
      *   Scala future instance.
      */
    implicit class ScalaToTwitterFuture[A](sf: ScalaFuture[A]) {

        /** Convert scala future too twitter future.
          * @param e
          *   Future execution context.
          * @return
          *   Twitter future instance associated with the same task.
          */
        def asTwitter(implicit e: ExecutionContext): TwitterFuture[A] = {
            val promise: TwitterPromise[A] = new TwitterPromise[A]()
            sf onComplete {
                case Success(value)     => promise.setValue(value)
                case Failure(exception) => promise.setException(exception)
            }
            promise
        }
    }

    /** Implicit conversion of custom http client method enum to the Twitter
      * http method enum.
      * @param m
      *   Custom http method enum instance.
      */
    implicit class methodToTwitterMethod(m: Method) {
        def asTwitter = m match {
            case Get    => TwitterMethod.Get
            case Post   => TwitterMethod.Post
            case Patch  => TwitterMethod.Patch
            case Delete => TwitterMethod.Delete
        }
    }

    /** Object conversion to the successfull service response with payload.
      * @param obj
      *   Service method result instance.
      */
    implicit class objectToServiceResponse[A](obj: A) {
        def as200 = ObjectResponse(obj)
    }

    /** Error message conversions to the corresponding error responses.
      * @param msg
      *   Error message instance.
      */
    implicit class errorToServiceResponse(msg: ErrorMessage) {
        def as401 = BadAuthResponse(msg)
        def as404 = NotFoundResponse(msg)
        def as409 = ConflictResponse(msg)
        def as422 = UnprocessableResponse(msg)
        def as500 = InternalErrorResponse(msg)
        def as502 = BadGatewayResponse(msg)
        def as520 = UnknownErrorResponse(msg)
    }
}
