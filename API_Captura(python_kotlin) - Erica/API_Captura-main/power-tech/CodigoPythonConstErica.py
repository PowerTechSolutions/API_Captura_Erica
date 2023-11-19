
import psutil
import time
import mysql.connector

UsoCpu = psutil.cpu_percent(interval=1)
try:
    mydb = mysql.connector.connect(host='localhost', user='root', password='@Icecubes123', database='PowerTechSolutions')
    if mydb.is_connected():
        db_info = mydb.get_server_info()
        mycursor = mydb.cursor()
        sql_querryCPU = 'INSERT INTO Monitoramento_RAW VALUES (NULL, CURRENT_TIMESTAMP(), NULL, NULL, NULL, %s, 1)'
        dado = (round(UsoCpu, 2),)
        mycursor.execute(sql_querryCPU, dado)
        mydb.commit()
finally:
    if mydb.is_connected():
        mycursor.close()
        mydb.close()
