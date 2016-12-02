from elasticsearch import Elasticsearch
from datetime import datetime

es = Elasticsearch("http://127.0.0.1:9200")


def esIndex(index, type, id):
    try:
        print(es.index(index=index, doc_type=type, id=id, body={"fileContent": "", "timestamp": datetime.now()}))
    except:
        print("no need delete")


def esDelete(index, type, id):
    print(es.delete(index=index, doc_type=type, id=id))


if __name__ == '__main__':
    esDelete("test", "txt", "123456")
    esIndex("test", "txt", "123456")
