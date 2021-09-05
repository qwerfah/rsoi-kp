package services.default

import scalaz.Monad

import repos._
import models._
import services._

class DefaultEquipmentModelService[F[_]: Monad, DB[_]: Monad](
  repo: EquipmentModelRepo[DB],
  dbManager: DbManager[F, DB]
) extends EquipmentModelService[F] {
    override def getById(id: Int): F[Option[EquipmentModel]] =
        dbManager.execute(repo.getById(id))
}
