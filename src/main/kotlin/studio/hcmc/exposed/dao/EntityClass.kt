package studio.hcmc.exposed.dao

import org.jetbrains.exposed.dao.*
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.SizedIterable
import org.jetbrains.exposed.sql.SqlExpressionBuilder
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.and
import studio.hcmc.exposed.table.orderBy
import studio.hcmc.kotlin.protocol.Deletable
import studio.hcmc.kotlin.protocol.SortOrder
import java.util.*

inline fun <ID : Comparable<ID>, T> EntityClass<ID, T>.findByIdOrThrow(
    id: ID,
    throwable: () -> Throwable
): T where T : Entity<ID> {
    return findById(id) ?: throw throwable()
}

inline fun <ID : Comparable<ID>, T> EntityClass<ID, T>.findByIdOrThrow(
    id: EntityID<ID>,
    throwable: () -> Throwable
): T where T : Entity<ID> {
    return findById(id) ?: throw throwable()
}

inline fun <ID : Comparable<ID>, T> EntityClass<ID, T>.findVisibleByIdOrThrow(
    id: ID,
    throwable: () -> Throwable
): T where T : Entity<ID>, T : Deletable {
    val entity = findById(id)
    if (entity == null || entity.isDeleted) {
        throw throwable()
    }

    return entity
}

inline fun <ID : Comparable<ID>, T> EntityClass<ID, T>.findVisibleByIdOrThrow(
    id: EntityID<ID>,
    throwable: () -> Throwable
): T where T : Entity<ID>, T : Deletable {
    val entity = findById(id)
    if (entity == null || entity.isDeleted) {
        throw throwable()
    }

    return entity
}