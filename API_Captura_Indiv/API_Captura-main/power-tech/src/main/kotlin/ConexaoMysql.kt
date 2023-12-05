
import org.apache.commons.dbcp2.BasicDataSource
import org.springframework.jdbc.core.JdbcTemplate

object ConexaoMysql {

    var jdbcTemplate: JdbcTemplate? = null
        get() {
            if (field == null){

                val dataSource = BasicDataSource()

                dataSource.driverClassName = "com.mysql.cj.jdbc.Driver"
                dataSource.url = "jdbc:mysql://localhost/PowerTechSolutions"
                dataSource.username = "aluno"
                dataSource.password = "sptech"
                val novojdbcTmeplate = JdbcTemplate(dataSource)

                field = novojdbcTmeplate

            }

            return field

        }

    fun iniciarMysqlServer(){
        jdbcTemplate = jdbcTemplate!!
    }

}

