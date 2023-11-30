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

            servicoCadastradorepositorio.iniciar()
            servicoMonitoradorepositorio.iniciar()
            maquina_repositorio.iniciar()
            usuario_repositorio.iniciar()

            val sn = Scanner(System.`in`)

            println("Bem vindo a PowerTech Por favor realize o login para utilizar nosso sistema")

            println("Insira seu Cpf: ")
            var Cpf:String = sn.next()

            if (usuario_repositorio.autenticar(Cpf)){

                var funcionario:Usuario = usuario_repositorio.resgatarinfo(Cpf)

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
            repositorio.iniciar()

            var captura = Captura

            var dataRedeJanelas = LocalDateTime.now()


            var verificacaoSemanal:Int = 0
            var dia_semana:Int = 1
            var verificacaoMinutos:Int = 0

            var verificacaoJanela = false
            var verificacaoDiaria = 0


            while (true) {
                if (capturaJANELAS == 1) {
                    captura.pegarJanelas(maquinaEscolhida)
                }
                if (capturaCPU == 1) {
                    CodigoPythonConst.execpython(servicos)
                }
                Thread.sleep(5000)
            }
        }
    }
}