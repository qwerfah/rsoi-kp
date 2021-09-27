package com.qwerfah.reporting.repos

import java.time.LocalDateTime

import com.qwerfah.reporting.models._
import com.qwerfah.common.Uid
import com.qwerfah.common.http.HttpMethod

trait OperationRecordRepo[DB[_]] {
    def get: DB[Seq[OperationRecord]]
    def get(uid: Uid): DB[Option[OperationRecord]]
    def get(
      serviceId: Option[String],
      method: Option[HttpMethod],
      status: Option[Int],
      interval: Option[(LocalDateTime, LocalDateTime)]
    ): DB[Seq[OperationRecord]]
    def add(record: OperationRecord): DB[OperationRecord]
    def remove(uid: Uid): DB[Int]
    def remove(serviceId: String): DB[Int]
}
