package com.qwerfah.monitoring.json

import io.circe.literal._
import io.circe.schema.Schema

/** Provide json schemas for different model resources. */
object JsonSchemas {
    val addMonitorRequestSchema: Schema = Schema.load(
      json"""
            {
                "type": "object",
                "required": ["instanceUid", "name"],
                "properties": {
                    "instanceUid": {
                        "type": "string",
                        "pattern": "[a-f0-9]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89aAbB][a-f0-9]{3}-[a-f0-9]{12}"
                    },
                    "name": {
                        "type": "string",
                        "minLength": 1,
                        "maxLength": 100,
                        "pattern": "^(?!\\s*$$).+"
                    },
                    "description": {
                        "type": "string",
                        "minLength": 1,
                        "maxLength": 300,
                        "pattern": "^(?!\\s*$$).+"
                    }
                }
            }
            """
    )

    val updateMonitorRequestSchema: Schema = Schema.load(
      json"""
            {
                "type": "object",
                "required": ["name"],
                "properties": {
                    "name": {
                        "type": "string",
                        "minLength": 1,
                        "maxLength": 100,
                        "pattern": "^(?!\\s*$$).+"
                    },
                    "description": {
                        "type": "string",
                        "minLength": 1,
                        "maxLength": 300,
                        "pattern": "^(?!\\s*$$).+"
                    }
                }
            }
            """
    )

    val monitorResponseSchema: Schema = Schema.load(
      json"""
            {
                "type": "object",
                "required": ["uid", "instanceUid", "name"],
                "properties": {
                    "uid": {
                        "type": "string",
                        "pattern": "[a-f0-9]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89aAbB][a-f0-9]{3}-[a-f0-9]{12}"
                    },
                    "instanceUid": {
                        "type": "string",
                        "pattern": "[a-f0-9]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89aAbB][a-f0-9]{3}-[a-f0-9]{12}"
                    },
                    "name": {
                        "type": "string",
                        "minLength": 1,
                        "maxLength": 100,
                        "pattern": "^(?!\\s*$$).+"
                    },
                    "description": {
                        "type": "string",
                        "minLength": 1,
                        "maxLength": 300,
                        "pattern": "^(?!\\s*$$).+"
                    }
                }
            }
            """
    )

    val monitorParamRequestSchema: Schema = Schema.load(
      json"""
            {
                "type": "object",
                "required": ["paramUid"],
                "properties": {
                    "paramUid": {
                        "type": "string",
                        "pattern": "[a-f0-9]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89aAbB][a-f0-9]{3}-[a-f0-9]{12}"
                    }
                }
            }
            """
    )

    val monitorParamResponseSchema: Schema = Schema.load(
      json"""
            {
                "type": "object",
                "required": ["monitorUid", "paramUid"],
                "properties": {
                    "monitorUid": {
                        "type": "string",
                        "pattern": "[a-f0-9]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89aAbB][a-f0-9]{3}-[a-f0-9]{12}"
                    },
                    "paramUid": {
                        "type": "string",
                        "pattern": "[a-f0-9]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89aAbB][a-f0-9]{3}-[a-f0-9]{12}"
                    }
                }
            }
            """
    )
}
