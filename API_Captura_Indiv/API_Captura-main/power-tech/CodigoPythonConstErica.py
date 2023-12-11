
import psutil
import time
import mysql.connector
import pyodbc
        
UsoCpu = psutil.cpu_percent(interval=1)
        
conn_mssql = pyodbc.connect(
    'DRIVER=ODBC DRIVER 17 for SQL server;'
    'Server=ec2-34-194-127-191.compute-1.amazonaws.com;'
    'UID=sa;'
    'PWD=myLOVEisthe0506;'
    'Database=PowerTechSolutions;'
)
cursor_mssql = conn_mssql.cursor()    
componenteCPU = 1
dado = (round(UsoCpu, 2), componenteCPU)
sql_insert_CPU = f"INSERT INTO Monitoramento_RAW (Uso,FKComponente_Monitorado) VALUES ({round(UsoCpu, 2)},{componenteCPU})"
    
cursor_mssql.execute(sql_insert_CPU)
conn_mssql.commit()
