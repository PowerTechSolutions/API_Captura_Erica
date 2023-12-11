package app

import Captura
import CodigoPythonConst
import MaquinasRepositorio
import Monitoramento_RAWRepositorio
import ServicoCadastradoRepositorio
import ServicoMonitoradoRepositorio
import ServicosMonitorados
import Usuario
import UsuarioRepositorio
import java.time.Duration
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.LocalTime.*
import java.util.*


open class Main {
    companion object {
        @JvmStatic fun main(args: Array<String>) {

            var dataAtual = LocalDateTime.now()

            val usuario_repositorio = UsuarioRepositorio()
            val maquina_repositorio = MaquinasRepositorio()
            val servicoMonitoradorepositorio = ServicoMonitoradoRepositorio()
            val servicoCadastradorepositorio = ServicoCadastradoRepositorio()

            servicoCadastradorepositorio.iniciarSql()
            servicoMonitoradorepositorio.iniciarSql()
            maquina_repositorio.iniciarSql()
            usuario_repositorio.iniciarSql()

            val sn = Scanner(System.`in`)

            println("Bem vindo a PowerTech Por favor realize o login para utilizar nosso sistema")

            println("Insira seu email: ")
            var email:String = sn.next()

            println("Insira sua senha: ")
            var senha:String = sn.next()

            if (usuario_repositorio.autenticar(email,senha)){

                var funcionario:Usuario = usuario_repositorio.resgatarinfo(email,senha)

                var maquinas:String = maquina_repositorio.pegarMaquinas(funcionario.IDUsuario)
                println("Qual a numeração da maquina e está que está instalando o serviço? $maquinas")

                var maquinaEscolhida = sn.next().toInt()

                var servicos:MutableList<ServicosMonitorados> = servicoMonitoradorepositorio.buscarComponentes(maquinaEscolhida)

                var funcoes:MutableList<String> = mutableListOf()

                for (servico in servicos){

                    var apelido:String = servicoCadastradorepositorio.buscarComponente(servico.FKComponente_cadastrado)

                    when(apelido){

                        "JANELAS" -> funcoes.add("J")
                        "CPU" -> funcoes.add("C")
                        else -> {
                            funcoes.add("CR")
                        }

                    }

                }

                Captura(funcoes,servicos,maquinaEscolhida,dataAtual)

            }

        }

        fun Captura(funcoes:MutableList<String>,servicos: MutableList<ServicosMonitorados>, maquinaEscolhida: Int, dataAtual: LocalDateTime){

            var capturaJANELAS = 0
            var capturaCPU = 0

            for (servico in funcoes){
                when(servico){
                    "J" -> capturaJANELAS = 1
                    "C" -> capturaCPU = 1
                }
            }

            var repositorio = Monitoramento_RAWRepositorio()
            repositorio.iniciarSql()

            var captura = Captura


            while (true) {
                if (capturaJANELAS == 1) {
                    captura.pegarJanelas(maquinaEscolhida)
                }
                if (capturaCPU == 1) {
                    CodigoPythonConst.execpython(servicos)
                }
                Thread.sleep(60000)
            }
        }
    }
}