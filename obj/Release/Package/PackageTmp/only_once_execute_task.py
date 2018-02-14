import pyodbc
import statistics

def insert_variance_into_db(pi,cursor=None):
    if cursor is None:
        cursor=getCursor()
    hpdays=getHpdays(pi, cursor)

    if len(hpdays)>=3:
        hpday1=hpdays[0]
        pains1=getPains(pi, hpday1, cursor)
        variance1=getVariance(pains1)

        hpday2=hpdays[1]
        pains2=getPains(pi, hpday2, cursor)
        variance2=getVariance(pains2)

        hpday3=hpdays[2]
        pains3=getPains(pi, hpday3, cursor)
        variance3=getVariance(pains3)
     
        cursor.execute("insert into Var_Table(PI, var_cycle_1, var_cycle_2, var_cycle_3) values({},{},{},{})".format(pi, variance1, variance2, variance3))

    elif len(hpdays)==2:
        hpday1=hpdays[0]
        pains1=getPains(pi, hpday1, cursor)
        variance1=getVariance(pains1)
        
        hpday2=hpdays[1]
        pains2=getPains(pi, hpday2, cursor)
        variance2=getVariance(pains2)

        cursor.execute("insert into Var_Table(PI, var_cycle_1, var_cycle_2) values({},{},{})".format(pi, variance1, variance2))

    elif len(hpdays)==1:
        hpday1=hpdays[0]
        pains1=getPains(pi, hpday1, cursor)
        variance1=getVariance(pains1)

        cursor.execute("insert into Var_Table(PI, var_cycle_1) values({},{})".format(pi, variance1))

    else:
        pass

def getHpdays(pi, cursor=None):
    if cursor is None:
        cursor=getCursor()

    hpdays=[i[0] for i in cursor.execute('SELECT DISTINCT Hpday FROM Time where PI={} order by Hpday'.format(pi))]
    return hpdays

def getPains(pi, hpday, cursor=None):
    if cursor is None:
        cursor=getCursor()
    pains=[]
    for pain in cursor.execute("SELECT Pain From Time where PI={} AND Hpday='{}'".format(pi, hpday)):
        try:
            pains.append(int(pain[0]))
        except ValueError:
            continue
    return pains

def getVariance(pains):
    try:
        return statistics.variance(pains)
    except :
        return 'null'

def getCursor():
    cursor=pyodbc.connect('DRIVER={ODBC Driver 11 for SQL Server};SERVER=tcp:severancebigcon.database.windows.net;DATABASE=severancebigcon;UID=sbigcon05;PWD=P@ssw0rd;', autocommit=True, timeout=900).cursor()
    return cursor

cnxn = pyodbc.connect('DRIVER={ODBC Driver 11 for SQL Server};SERVER=tcp:severancebigcon.database.windows.net;DATABASE=severancebigcon;UID=sbigcon05;PWD=P@ssw0rd;', autocommit=True, timeout=900)
cursor = cnxn.cursor()
pis=[i[0] for i in cursor.execute('select distinct PI from Time order by PI')]

for pi in pis:
    insert_variance_into_db(pi, cursor)
