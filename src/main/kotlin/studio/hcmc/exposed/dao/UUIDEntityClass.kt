package studio.hcmc.exposed.dao

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.SizedIterable
import org.jetbrains.exposed.sql.SqlExpressionBuilder
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.and
import studio.hcmc.exposed.table.orderBy
import studio.hcmc.kotlin.protocol.SortOrder
import java.util.*

inline fun <E> UUIDEntityClass<E>.list(
    lastId: UUID,
    sortOrder: SortOrder,
    limit: Int,
    op: SqlExpressionBuilder.() -> Op<Boolean> = { Op.TRUE }
): SizedIterable<E> where E : UUIDEntity {
    return find((table.id eq lastId) and SqlExpressionBuilder.op())
        .orderBy(table.id to sortOrder)
        .limit(limit)
}

inline fun <E> UUIDEntityClass<E>.list(
    lastId: EntityID<UUID>,
    sortOrder: SortOrder,
    limit: Int,
    op: SqlExpressionBuilder.() -> Op<Boolean> = { Op.TRUE }
): SizedIterable<E> where E : UUIDEntity {
    return find((table.id eq lastId) and SqlExpressionBuilder.op())
        .orderBy(table.id to sortOrder)
        .limit(limit)
}