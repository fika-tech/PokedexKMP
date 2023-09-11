package tech.fika.pokedex.local.database

import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import tech.fika.pokedex.application.Config
import tech.fika.pokedex.local.database.SqlDriverFactory

class AndroidSqlDriverFactory(private val config: Config) : SqlDriverFactory {
    override fun create(schema: SqlSchema<QueryResult.Value<Unit>>, name: String): SqlDriver = AndroidSqliteDriver(
        schema = schema,
        context = config.platform.context,
        name = name,
    )
}
