package studio.hcmc.exposed.dao

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.SizedIterable
import org.jetbrains.exposed.sql.SqlExpressionBuilder
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.and
import studio.hcmc.exposed.table.DeletableTable
import studio.hcmc.exposed.table.orderBy
import studio.hcmc.kotlin.protocol.Deletable
import studio.hcmc.kotlin.protocol.SortOrder

inline fun <E> LongEntityClass<E>.list(
    lastId: Long,
    sortOrder: SortOrder,
    limit: Int,
    op: SqlExpressionBuilder.() -> Op<Boolean> = { Op.TRUE }
): SizedIterable<E> where E : LongEntity {
    return find((table.id eq lastId) and SqlExpressionBuilder.op())
        .orderBy(table.id to sortOrder)
        .limit(limit)
}

inline fun <E> LongEntityClass<E>.list(
    lastId: EntityID<Long>,
    sortOrder: SortOrder,
    limit: Int,
    op: SqlExpressionBuilder.() -> Op<Boolean> = { Op.TRUE }
): SizedIterable<E> where E : LongEntity {
    return find((table.id eq lastId) and SqlExpressionBuilder.op())
        .orderBy(table.id to sortOrder)
        .limit(limit)
}

inline fun <E> LongEntityClass<E>.listNotDeleted(
    lastId: Long,
    sortOrder: SortOrder,
    limit: Int,
    op: SqlExpressionBuilder.() -> Op<Boolean> = { Op.TRUE }
): SizedIterable<E> where E : LongEntity, E : Deletable {
    val table = table
    if (table !is DeletableTable) {
        return list(lastId, sortOrder, limit, op)
    }

    return find((table.id eq lastId) and (table.deletedAt eq null) and SqlExpressionBuilder.op())
        .orderBy(table.id to sortOrder)
        .limit(limit)
}

inline fun <E> LongEntityClass<E>.listNotDeleted(
    lastId: EntityID<Long>,
    sortOrder: SortOrder,
    limit: Int,
    op: SqlExpressionBuilder.() -> Op<Boolean> = { Op.TRUE }
): SizedIterable<E> where E : LongEntity, E : Deletable {
    val table = table
    if (table !is DeletableTable) {
        return list(lastId, sortOrder, limit, op)
    }

    return find((table.id eq lastId) and (table.deletedAt eq null) and SqlExpressionBuilder.op())
        .orderBy(table.id to sortOrder)
        .limit(limit)
}