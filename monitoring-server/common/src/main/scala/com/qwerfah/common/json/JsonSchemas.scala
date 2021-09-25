package com.qwerfah.common.json

import io.circe.literal._
import io.circe.schema.Schema

object JsonSchemas {
    val credentialsSchema: Schema = Schema.load(
      json""" {
        "type": "object",
        "required": ["login", "password"],
        "properties": {
            "login": {
                "type": "string",
                "minLength": 1,
                "maxLength": 100,
                "pattern": "^(?!\\s*$$).+"
            },
            "password": {
                "type": "string",
                "minLength": 1,
                "maxLength": 100,
                "pattern": "^(?!\\s*$$).+"
            }
        }  
    }"""
    )
}
