import java.io.File

object CodigoPythonConst {

    fun execpython(servicos:MutableList<ServicosMonitorados>) {

        val servicoCadastradorepositorio = ServicoCadastradoRepositorio()
        servicoCadastradorepositorio.iniciar()

        var CPU = 0
        var componenteCPU = 0

        for (servico in servicos){

            var apelido = servicoCadastradorepositorio.buscarComponente(servico.FKComponente_cadastrado)

            when(apelido){
                "CPU" -> {
                    CPU += 1
                    componenteCPU = servico.IDComponente_monitorado
                }

            }

        }


        var codigoPython ="""
import psutil
import time
import mysql.connector

UsoCpu = psutil.cpu_percent(interval=1)

try:
    mydb = mysql.connector.connect(host='localhost', user='root', password='@Icecubes123', database='PowerTechSolutions')
    if mydb.is_connected():
        db_info = mydb.get_server_info()
        mycursor = mydb.cursor()
        
        # Use %s as a placeholder for the variable componenteCPU
        sql_query_CPU = 'INSERT INTO Monitoramento_RAW VALUES (NULL, CURRENT_TIMESTAMP(), NULL, NULL, NULL, %s, %s)'
        componenteCPU = 1  # Replace this with the actual value for componenteCPU
        dado = (round(UsoCpu, 2), $componenteCPU)
        
        mycursor.execute(sql_query_CPU, dado)
        mydb.commit()
except mysql.connector.Error as err:
    print(f"Error: {err}")
finally:
    if mydb.is_connected():
        mycursor.close()
        mydb.close()

"""

        val nomeArquivoPyDefault = "CodigoPythonConstErica.py"

        File(nomeArquivoPyDefault).writeText(codigoPython)
        Runtime.getRuntime().exec("py $nomeArquivoPyDefault")

        println("Python executado para CPU")

    }

}