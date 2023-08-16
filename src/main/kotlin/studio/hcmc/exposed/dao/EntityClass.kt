package studio.hcmc.exposed.dao

import org.jetbrains.exposed.dao.*
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.SqlExpressionBuilder
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.and
import studio.hcmc.exposed.table.orderBy
import studio.hcmc.kotlin.protocol.SortOrder
import java.util.*

inline fun <ID : Comparable<ID>, T : Entity<ID>> EntityClass<ID, T>.findByIdOrThrow(
    id: ID,
    throwable: () -> Throwable
): T {
    return findById(id) ?: throw throwable()
}

inline fun <ID : Comparable<ID>, T : Entity<ID>> EntityClass<ID, T>.findByIdOrThrow(
    id: EntityID<ID>,
    throwable: () -> Throwable
): T {
    return findById(id) ?: throw throwable()
}

inline fun <E : IntEntity> IntEntityClass<E>.list(
    lastId: Int,
    sortOrder: SortOrder,
    limit: Int,
    op: SqlExpressionBuilder.() -> Op<Boolean> = { Op.TRUE }
) {
    find((table.id eq lastId) and SqlExpressionBuilder.op())
        .orderBy(table.id to sortOrder)
        .limit(limit)
}

inline fun <E : IntEntity> IntEntityClass<E>.list(
    lastId: EntityID<Int>,
    sortOrder: SortOrder,
    limit: Int,
    op: SqlExpressionBuilder.() -> Op<Boolean> = { Op.TRUE }
) {
    find((table.id eq lastId) and SqlExpressionBuilder.op())
        .orderBy(table.id to sortOrder)
        .limit(limit)
}

inline fun <E : LongEntity> LongEntityClass<E>.list(
    lastId: Long,
    sortOrder: SortOrder,
    limit: Int,
    op: SqlExpressionBuilder.() -> Op<Boolean> = { Op.TRUE }
) {
    find((table.id eq lastId) and SqlExpressionBuilder.op())
        .orderBy(table.id to sortOrder)
        .limit(limit)
}

inline fun <E : LongEntity> LongEntityClass<E>.list(
    lastId: EntityID<Long>,
    sortOrder: SortOrder,
    limit: Int,
    op: SqlExpressionBuilder.() -> Op<Boolean> = { Op.TRUE }
) {
    find((table.id eq lastId) and SqlExpressionBuilder.op())
        .orderBy(table.id to sortOrder)
        .limit(limit)
}

inline fun <E : UUIDEntity> UUIDEntityClass<E>.list(
    lastId: UUID,
    sortOrder: SortOrder,
    limit: Int,
    op: SqlExpressionBuilder.() -> Op<Boolean> = { Op.TRUE }
) {
    find((table.id eq lastId) and SqlExpressionBuilder.op())
        .orderBy(table.id to sortOrder)
        .limit(limit)
}

inline fun <E : UUIDEntity> UUIDEntityClass<E>.list(
    lastId: EntityID<UUID>,
    sortOrder: SortOrder,
    limit: Int,
    op: SqlExpressionBuilder.() -> Op<Boolean> = { Op.TRUE }
) {
    find((table.id eq lastId) and SqlExpressionBuilder.op())
        .orderBy(table.id to sortOrder)
        .limit(limit)
}