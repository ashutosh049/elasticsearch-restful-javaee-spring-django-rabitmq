from datetime import datetime
from elasticsearch import Elasticsearch


# print(es.delete(index="my-index", doc_type="test-type", id=42))
# print(es.index(index="my-index", doc_type="test-type", id=42, body=｛'doc':{"any": "data", "timestamp": datetime.now()}｝))
# print(es.update(index="my-index", doc_type="test-type", id=41,
#                 body={"doc": {"any": "data", "timestamp": datetime.now()}}))
# print(es.get(index="my-index", doc_type="test-type", id=42)['_source'])

class ElasticEntity:
    "用于最终更新ES内容"
    es = Elasticsearch("http://127.0.0.1:9200")

    def esUpdateFileContent(self, index, type, id, fileContent):
        self.es.update(index=index, doc_type=type, id=id,
                       body={"doc": {"fileContent": fileContent}})

    def esIndex(self, index, type, id):
        self.es.index(index=index, doc_type=type, id=id, body={"any": "data", "timestamp": datetime.now()})
        {u'_id': id, u'_index': index, u'_type': type, u'fileContent': u'', u'_version': 1, u'ok': True}
