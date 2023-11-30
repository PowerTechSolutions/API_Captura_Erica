object Captura {

    fun pegarJanelas(maquinaEscolhida: Int){

        var capturajanela = CapturaJanelas()
        capturajanela.iniciarMysql()

        var inserts = capturajanela.captura(maquinaEscolhida)

        println("$inserts Registro(s) de atividade inseridos em janelas")

        capturajanela.iniciarSqlServer()
        var inserts2 = capturajanela.captura(maquinaEscolhida)

        println("$inserts2 Registro(s) de atividade inseridos em janelas")



    }

}