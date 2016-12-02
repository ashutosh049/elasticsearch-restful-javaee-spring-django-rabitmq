from http import client
import json


filePojo = {}
userPojo = {}
configParam = {}
commonParam = {}


userPojo['userId'] = 'liwenbo@iscas.ac.cn'
userPojo['userLevel'] = 6
configParam['todoAction'] = 4353
commonParam['filePojo'] = filePojo
commonParam['userPojo'] = userPojo
commonParam['configParam'] = configParam


def sendhttp(fileContent, type, category):

    conn = client.HTTPConnection('localhost', 8080)

    filePojo['fileContent'] = fileContent
    subUrls = '/securitysearch/webresources/{type}/{category}'.format(type=type, category=category)
    paramJson = json.dumps(commonParam)
    headers = {"content-type": "application/json"}

    conn.request('POST', subUrls, paramJson, headers)
    httpres = conn.getresponse()
    data = httpres.read()
    return data
