import com.github.britooo.looca.api.core.Looca
import org.springframework.jdbc.core.JdbcTemplate

class CapturaJanelas {
    lateinit var jdbcTemplate: JdbcTemplate

    fun iniciarSql() {
        jdbcTemplate = ConexaoSqlServer.jdbcTemplate!!
    }

    fun iniciarMysql(){
        jdbcTemplate = ConexaoMysql.jdbcTemplate!!
    }

    var looca: Looca = Looca()
    var JanelasVisiveis = looca.grupoDeJanelas.janelasVisiveis
    var qntdVisiveis = looca.grupoDeJanelas.totalJanelasVisiveis

    fun captura(FKMaquina:Int) {
        var contador = 0
        var inserts = 0

        while(contador < qntdVisiveis){
            var idJanela = JanelasVisiveis[contador].janelaId
            var pidJanela = JanelasVisiveis[contador].pid
            var nomeJanela = JanelasVisiveis[contador].titulo

            inserts += jdbcTemplate.update(
                "INSERT INTO Janelas_Abertas (IDJanela,PIDJanelas,Nome_Janelas,FKMaquina) VALUES (${idJanela},${pidJanela},'${nomeJanela}',${FKMaquina})"
            )

            contador+=1
        }
    }

}