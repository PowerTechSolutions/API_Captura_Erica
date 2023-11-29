import psutil
import time
import mysql.connector

UsoCpu = psutil.cpu_percent(interval=1)
try:
    mydb = mysql.connector.connect(host = '3.234.2.175', user = 'root',password = 'urubu100',database = 'PowerTechSolutions')
    if mydb.is_connected():
        db_info = mydb.get_server_info()
        mycursor = mydb.cursor()
        sql_querryCPU = 'INSERT INTO Monitoramento_RAW VALUES (NULL, CURRENT_TIMESTAMP(),NULL, NULL, %s, NULL, 1)'
        dado = (round(UsoCpu,2),)
        mycursor.execute(sql_querryCPU, dado)
        mydb.commit()
finally:
    if mydb.is_connected():
        mycursor.close()
        mydb.close()
