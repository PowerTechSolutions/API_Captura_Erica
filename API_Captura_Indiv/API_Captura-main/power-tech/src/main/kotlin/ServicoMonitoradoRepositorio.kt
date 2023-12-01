import org.springframework.jdbc.core.BeanPropertyRowMapper
import org.springframework.jdbc.core.JdbcTemplate

class ServicoMonitoradoRepositorio {

    lateinit var jdbcTemplate: JdbcTemplate

    fun iniciarMysql(){
        jdbcTemplate = ConexaoMysql.jdbcTemplate!!
    }

    fun iniciarSql(){
        jdbcTemplate = ConexaoSqlServer.jdbcTemplate!!
    }


    fun buscarComponentes(FKMaquina:Int):MutableList<ServicosMonitorados>{
        var componentes = jdbcTemplate.query("""
           SELECT * FROM Componentes_monitorados WHERE FKMaquina = $FKMaquina
        """,BeanPropertyRowMapper(ServicosMonitorados::class.java))

        return componentes

    }

}