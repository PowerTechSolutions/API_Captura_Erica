object Captura {

    fun pegarJanelas(maquinaEscolhida: Int){

        var capturajanela = CapturaJanelas()
        capturajanela.iniciarMysql()
        var inserts = capturajanela.captura(maquinaEscolhida)

        capturajanela.iniciarSql()

        var inserts2 = capturajanela.captura(maquinaEscolhida)

        println("$inserts Registro(s) de atividade inseridos em janelas")

    }

}