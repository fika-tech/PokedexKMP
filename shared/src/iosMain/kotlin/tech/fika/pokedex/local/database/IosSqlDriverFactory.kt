package tech.fika.pokedex.local.database

import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import tech.fika.pokedex.local.database.SqlDriverFactory

class IosSqlDriverFactory : SqlDriverFactory {
    override fun create(schema: SqlSchema<QueryResult.Value<Unit>>, name: String): SqlDriver = NativeSqliteDriver(
        schema = schema,
        name = name,
    )
}
