object Captura {

    fun pegarJanelas(maquinaEscolhida: Int){

        var capturajanela = CapturaJanelas()
        capturajanela.iniciarMysql()
        capturajanela.iniciarSqlServer()

        var inserts = capturajanela.captura(maquinaEscolhida)

        println("$inserts Registro(s) de atividade inseridos em janelas")

    }

}