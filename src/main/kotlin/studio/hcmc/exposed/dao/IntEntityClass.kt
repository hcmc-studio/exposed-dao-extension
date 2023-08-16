package studio.hcmc.exposed.dao

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.SizedIterable
import org.jetbrains.exposed.sql.SqlExpressionBuilder
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.and
import studio.hcmc.exposed.table.orderBy
import studio.hcmc.kotlin.protocol.SortOrder

inline fun <E> IntEntityClass<E>.list(
    lastId: Int,
    sortOrder: SortOrder,
    limit: Int,
    op: SqlExpressionBuilder.() -> Op<Boolean> = { Op.TRUE }
): SizedIterable<E> where E : IntEntity {
    return find((table.id eq lastId) and SqlExpressionBuilder.op())
        .orderBy(table.id to sortOrder)
        .limit(limit)
}

inline fun <E> IntEntityClass<E>.list(
    lastId: EntityID<Int>,
    sortOrder: SortOrder,
    limit: Int,
    op: SqlExpressionBuilder.() -> Op<Boolean> = { Op.TRUE }
): SizedIterable<E> where E : IntEntity {
    return find((table.id eq lastId) and SqlExpressionBuilder.op())
        .orderBy(table.id to sortOrder)
        .limit(limit)
}