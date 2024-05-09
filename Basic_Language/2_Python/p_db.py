#pip install cs_Oracle
import cx_Oracle as DBConnection
import os
def getConnection():
    os.putenv("NLS_LANG",".UTF8")

    conn = DBConnection.connect("hr","hr","localhost:1521/XE")
    return conn

def select():
    conn = getConnection()
    cs = conn.cursor()
    sql = "SELECT SYSDATE FROM DUAL"
    cs.execute(sql)

    for row in cs:
        print(row)

    cs.close()
    conn.close()

def insert():
    conn = getConnection()
    cs = conn.cursor()
    sql = "INSERT INTO TEST VALUES(:1,:2)"
    cursor.execute(sql,("A",10))
    cursor.close()
    conn.commit()
    conn.close()
