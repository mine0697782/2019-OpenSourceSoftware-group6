from wsgiref.simple_server import  make_server
from urlparse import parse_qs
from cgi import escape
import json
import pymysql


def application(environ, start_response):
    d = parse_qs(environ['QUERY_STRING'])
    taskstr = escape(d.get('task', [''])[0])
    response_body = json.dumps({'login' :1})
    if taskstr == 'login':
        idstr = escape(d.get('id', [''])[0])
        passwordstr = escape(d.get('password', [''])[0])
        response_body = loginm(idstr,passwordstr)
    elif taskstr == 'signup':
        idstr = escape(d.get('id', [''])[0])
        passwordstr = escape(d.get('password', [''])[0])
        namestr = escape(d.get('name', [''])[0])
        emailstr = escape(d.get('email', [''])[0])
        nickstr = escape(d.get('nick', [''])[0])
        response_body = signUpm(idstr, passwordstr, namestr, emailstr, nickstr)
    elif taskstr == 'templateup':
        tnostr = escape(d.get('tno', [''])[0])
        uidstr = escape(d.get('uid', [''])[0])
        datestr = escape(d.get('date', [''])[0])
        response_body = templateUpm(tnostr, uidstr, datestr)
    elif taskstr == 'templatecontentsup':
        tnostr = escape(d.get('tno', [''])[0])
        titlestr = escape(d.get('title', [''])[0])
        commentstr = escape(d.get('comment', [''])[0])
        response_body = templateContentsUpm(tnostr, titlestr, commentstr)
    elif taskstr == 'templateelementup':
        tnostr = escape(d.get('tno', [''])[0])
        elementstr = escape(d.get('element', [''])[0])
        unitstr = escape(d.get('unit', [''])[0])
        enostr = escape(d.get('eno', [''])[0])
        response_body = templateElementUpm(tnostr, elementstr, unitstr, enostr)
    elif taskstr == 'templatepartup':
        tnostr = escape(d.get('tno', [''])[0])
        enostr = escape(d.get('eno', [''])[0])
        valuestr = escape(d.get('value', [''])[0])
        uidstr = escape(d.get('uid', [''])[0])
        response_body = templatePartUpm(tnostr, enostr, valuestr, uidstr)
    elif taskstr == 'communityup':
        cidstr = escape(d.get('cid', [''])[0])
        uidstr = escape(d.get('uid', [''])[0])
        titlestr = escape(d.get('title', [''])[0])
        contentsstr = escape(d.get('contents', [''])[0])
        response_body = communityUpm(cidstr, uidstr, titlestr, contentsstr)
    elif taskstr == 'tagup':
        idstr = escape(d.get('id', [''])[0])
        tagstr = escape(d.get('tag', [''])[0])
        response_body = tagUpm(idstr, tagstr)
    elif taskstr == 'reset':
        alldel()
    elif taskstr == 'templateselect':
        tnostr = escape(d.get('tno', [''])[0])
        response_body = templateSelectm(tnostr)
    elif taskstr == 'templatepartselect':
        tnostr = escape(d.get('tno', [''])[0])
        response_body = templatePartSelectm(tnostr)
 #   elif taskstr == 'communityselect':
 #       cnostr = escape(d.get('cno',[''])[0])
 #       response_body = communitySelectm(cnostr)


    status = '200 OK'
    response_headers = [
        ('Content-Type', 'application/json'),
        ('Content-Length', str(len(response_body)))
    ]

    start_response(status, response_headers)
    return [response_body]

#login
def loginm(id, password):
    loginsq = "select password from user where id=%s"
    curs.execute(loginsq,(id))
    row = curs.fetchall()
    if row['password'] == password:
        result=1
    else:
        result=0
    return json.dumps({'login' : result})

#signup
def signUpm(id, password, name, email, nick):
    signUpsq = """insert into user(uid,name,password,email,nick) values (%s, %s, %s, %s, %s)"""
    curs.execute(signUpsq,(id,name,password,email,nick))
    conn.commit()
    result =1
    return json.dumps({'login' : result})

#templateup
def templateUpm(tno, uid, date):
    templateUpsq = """insert into template(tno,uid,date) values (%s, %s, %s)"""
    tnoint=int(tno)
    curs.execute(templateUpsq,(tnoint,uid,date))
    conn.commit()
    result =1
    return json.dumps({'login' : result})

#templateContentsup
def templateContentsUpm(tno, title, comment):
    templateContentsUpsq = """insert into template_contents(tno,title,comment) values (%s, %s, %s)"""
    tnoint=int(tno)
    curs.execute(templateContentsUpsq,(tnoint,title,comment))
    conn.commit()
    result =1
    return json.dumps({'login' : result})

#templateElementUp
def templateElementUpm(tno, element, unit, eno):
    templateElementUpsq = """insert into template_element(tno,element,unit,eno) values (%s, %s, %s, %s)"""
    tnoint=int(tno)
    curs.execute(templateElementUpsq,(tnoint,element,unit,eno))
    conn.commit()
    result =1
    return json.dumps({'login' : result})

#templatePartUp
def templatePartUpm(tno, eno, value, uid):
    templatePartUpsq = """insert into template_element(tno,eno,value,uid) values (%s, %s, %s, %s)"""
    tnoint = int(tno)
    enoint = int(eno)
    curs.execute(templatePartUpsq,(tnoint,enoint,value,uid))
    conn.commit()
    result =1
    return json.dumps({'login' : result})

#communityUp
def communityUpm(uid, title, contents):
    communityUpsq = """insert into template_element(uid, title, contents) values (%s, %s, %s)"""
    curs.execute(communityUpsq,(uid,title,contents))
    conn.commit()
    result =1
    return json.dumps({'login' : result})

#tagUp
def tagUpm(id, tag):
    tagUpsq = """insert into template_element(id, tag) values (%s, %s)"""
    curs.execute(tagUpsq,(id,tag))
    conn.commit()
    result =1
    return json.dumps({'login' : result})

def templateSelectm(tno):
    uid = ''
    uname = ''
    nick = ''
    title = ''
    comment = ''
    element = ['']
    unit = ['']
    date = ''

    templateSelectsq1 = "select * from template where tno=%s"
    curs.execute(templateSelectsq1,(tno))
    rows = curs.fetchall()
    for row in rows:
        uid = row['uid']
        date = row['date']

    templateSelectsq2 = "select * from template_contents where tno=%s"
    curs.execute(templateSelectsq2, (tno))
    rows = curs.fetchall()
    for row in rows:
        title = row['title']
        comment = row['comment']

    templateSelectsq3 = "select * from template_element where tno=%s"
    curs.execute(templateSelectsq3, (tno))
    rows = curs.fetchall()
    for row in rows:
        element.append(row['element'])
        unit.append(row['unit'])
    element.pop(0)
    unit.pop(0)

    templateSelectsq4 = "select * from user where tno=%s"
    curs.execute(templateSelectsq4, (uid))
    rows = curs.fetchall()
    for row in rows:
        uname = row['uname']
        nick = row['nick']

    return json.dumps({'uid' : uid, 'date' : date, 'title' : title, 'comment' : comment, 'element' : element, 'unit' : unit, 'uname' : uname, 'nick' : nick})

def templatePartSelectm(tno):
    uid = ['']
    uname = ''
    nick = ''
    title = ''
    comment = ''
    element = ['']
    unit = ['']
    date = ''
    eno = ['']
    value = ['']

    templatePartSelectsq1 = "select * from template_element where tno=%s"
    curs.execute(templatePartSelectsq1,(tno))
    rows = curs.fetchall()
    for row in rows:
        element.append(row['element'])
        unit.append(row['element'])
    element.pop(0)
    unit.pop(0)
    result = element
    result.append(unit)
    result.pop(0)


    templatePartSelectsq2 = "select * from template_part where tno=%s and element=0"
    curs.execute(templatePartSelectsq2, (tno))
    rows = curs.fetchall()
    for row in rows:
        uid.append(row['uid'])
    uid.pop(0)

    templatePartSelectsq3 = "select * from template_part where tno=%s and uid=%s"
    for user in uid:
        curs.execut(templatePartSelectsq3,(tno, user))
        rows = curs.fetchall()
        for row in rows:
            value.append(row['element'])
        value.pop(0)
        result.append(value)
        value = ''

    return json.dumps({'part' : result})


#def communitySelectm(cno):
#    uid = ['']
#    uname = ''
#    nick = ''
#    title = ''
#    comment = ''
#    element = ['']
#    unit = ['']
#    date = ''
#    eno = ['']
#    value = ['']
#
#    communitySelectsq1 = "select * from community where cno=%s"
#    curs.execute(communitySelectsq1, (cno))
#    rows = curs.fetchall()
#    for row in rows:
#
#    templatePartSelectsq2 = "select * from template_part where tno=%s and element=0"
#    curs.execute(templatePartSelectsq2, (tno))
#    rows = curs.fetchall()
#    for row in rows:
#        uid.append(row['uid'])
#    uid.pop(0)
#
#    templatePartSelectsq3 = "select * from template_part where tno=%s and uid=%s"
#    for user in uid:
#        curs.execut(templatePartSelectsq3, (tno, user))
#        rows = curs.fetchall()
#        for row in rows:
#            value.append(row['element'])
#        value.pop(0)
#        result.append(value)
#        value = ''
#
#    return json.dumps({'part': result})
#
#reset
def alldel():
    sq1= "delete from user"
    sq2= "delete from template"
    sq3= "delete from template_element"
    sq4= "delete from template_contents"
    sq5= "delete from template_part"
    curs.execute(sq1)
    conn.commit()
    curs.execute(sq2)
    conn.commit()
    curs.execute(sq3)
    conn.commit()
    curs.execute(sq4)
    conn.commit()
    curs.execute(sq5)
    conn.commit()
    result = 1
    return json.dumps({'login': result})

#데이터베이스 기본 셋팅
conn = pymysql.connect(host='localhost', user='root', password='ths85658', db='recipedb', charset='utf8')
curs = conn.cursor()

httpd = make_server('192.168.0.6', 8051, application)
httpd.serve_forever()
conn.close()