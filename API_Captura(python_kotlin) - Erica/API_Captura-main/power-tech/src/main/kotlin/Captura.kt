object Captura {

    fun pegarJanelas(maquinaEscolhida: Int){

        var capturajanela = CapturaJanelas()
        capturajanela.iniciar()

        var inserts = capturajanela.captura(maquinaEscolhida)

        println("$inserts Registro(s) de atividade inseridos em janelas")

    }

}