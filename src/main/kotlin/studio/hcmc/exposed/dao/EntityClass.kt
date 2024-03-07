package studio.hcmc.exposed.dao

import org.jetbrains.exposed.dao.DaoEntityID
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.vendors.ForUpdateOption

fun <ID : Comparable<ID>, T : Entity<ID>> EntityClass<ID, T>.findByIdForUpdate(
    id: ID,
    option: ForUpdateOption = ForUpdateOption.ForUpdate
): T? {
    return findByIdForUpdate(DaoEntityID(id, table), option)
}

fun <ID : Comparable<ID>, T : Entity<ID>> EntityClass<ID, T>.findByIdForUpdate(
    id: EntityID<ID>,
    option: ForUpdateOption = ForUpdateOption.ForUpdate
): T? {
    val present = testCache(id)
    if (present != null) {
        removeFromCache(present)
    }

    return find(table.id.eq(id))
        .forUpdate(option)
        .firstOrNull()
}