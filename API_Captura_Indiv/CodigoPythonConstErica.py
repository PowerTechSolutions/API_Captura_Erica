
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
        dado = (round(UsoCpu, 2), 1)
        
        mycursor.execute(sql_query_CPU, dado)
        mydb.commit()
except mysql.connector.Error as err:
    print(f"Error: {err}")
finally:
    if mydb.is_connected():
        mycursor.close()
        mydb.close()

