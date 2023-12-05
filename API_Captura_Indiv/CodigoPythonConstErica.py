
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
                user='aluno',
                password='sptech',
                database='PowerTechSolutions'
            )
            mycursor = mydb.cursor()
            
            if mydb.is_connected():
                db_info = mydb.get_server_info()
                
                sql_query_CPU = 'INSERT INTO Monitoramento_RAW VALUES (NULL, CURRENT_TIMESTAMP(), NULL, NULL, NULL, %s, %s)'
                componenteCPU = 1
                dado = (round(UsoCpu, 2), 1)
                
                mycursor.execute(sql_query_CPU, dado)
                mydb.commit()
        
                sql_insert_CPU = "INSERT INTO Monitoramento_RAW VALUES (null, CURRENT_TIMESTAMP(), NULL, NULL, NULL, %s, %s)"
                componenteCPU = 1
                dado = (round(UsoCpu, 2), 1)
        
                cursor_mssql.execute(sql_insert_CPU, dado)
                conn_mssql.commit()
                
        except mysql.connector.Error as err:
            print(f"Error: {err}")
        finally:
                mycursor.close()
                mydb.close()
        
