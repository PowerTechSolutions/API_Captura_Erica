import java.io.File

object CodigoPythonConst {

    fun execpython(servicos:MutableList<ServicosMonitorados>) {

        val servicoCadastradorepositorio = ServicoCadastradoRepositorio()
        servicoCadastradorepositorio.iniciarSql()
        servicoCadastradorepositorio.iniciarMysql()

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
        import pymssql
        
        UsoCpu = psutil.cpu_percent(interval=1)
        
        try:
        
            conn_mssql = pymssql.connect(
                server='34.194.127.191',
                user='sa',
                password='myLOVEisthe0506',
                database='PowerTechSolutions'
            )
            cursor_mssql = conn_mssql.cursor()
            
            mydb = mysql.connector.connect(
                host='localhost',
                user='root',
                password='urubu100',
                database='PowerTechSolutions'
            )
            mycursor = mydb.cursor()
            
            if mydb.is_connected():
                db_info = mydb.get_server_info()
                
                sql_query_CPU = 'INSERT INTO Monitoramento_RAW VALUES (NULL, CURRENT_TIMESTAMP(), NULL, NULL, NULL, %s, %s)'
                componenteCPU = 1
                dado = (round(UsoCpu, 2), componenteCPU)
                
                mycursor.execute(sql_query_CPU, dado)
                mydb.commit()
        
                sql_insert_CPU = "INSERT INTO Monitoramento_RAW VALUES (null, CURRENT_TIMESTAMP(), NULL, NULL, NULL, %s, %s)"
                componenteCPU = 1
                dado = (round(UsoCpu, 2), componenteCPU)
        
                cursor_mssql.execute(sql_insert_CPU, dado)
                conn_mssql.commit()
                
        except mysql.connector.Error as err:
            print(f"Error: {err}")
        finally:
                mycursor.close()
                mydb.close()
        
"""

        val nomeArquivoPyDefault = "CodigoPythonConstErica.py"

        File(nomeArquivoPyDefault).writeText(codigoPython)
        Runtime.getRuntime().exec("py $nomeArquivoPyDefault")

        println("Python executado para CPU")

    }

}