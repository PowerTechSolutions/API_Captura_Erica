import psutil
import mysql.connector
import time
while(True):
    UsoCpu = psutil.cpu_percent(interval=1)

    conn = mysql.connector.connect(user='aluno', password='sptech', host='localhost', database='monitoramento')

    cursor = conn.cursor()
    mysql_insert = "INSERT INTO MonitoramentoCPU VALUES (NULL, %s, CURRENT_TIMESTAMP())"
    dado = (round(UsoCpu,2),)
    try:    
        cursor.execute(mysql_insert, dado)
        conn.commit()
        print("dados inseridos no banco com sucesso")
    except:
        conn.rollback()
        print("erro ao inserir dados no banco")
        conn.close()
time.sleep(600)
