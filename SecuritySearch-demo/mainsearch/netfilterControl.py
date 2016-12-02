import socket


def addressCommand(command):
    if command == 'add':
        return addNetfilter()
    else:
        return delNetfilter()


def addNetfilter():
    s = socket.socket()
    s.connect(('192.168.150.128', 8887))  # 与服务器程序ip地址和端口号相同
    senddate = 'add'
    s.send(senddate.encode())
    data = s.recv(512)
    s.close()
    print('the data received is', data)
    return data


def delNetfilter():
    s = socket.socket()
    s.connect(('192.168.150.128', 8887))  # 与服务器程序ip地址和端口号相同
    senddate = 'del'
    s.send(senddate.encode())
    data = s.recv(512)
    s.close()
    print('the data received is', data)
    return data
