import time
import threading
from .models import FileInfo
from .esHandler import *


class FileAutoInput(threading.Thread):
    def __init__(self, result):
        threading.Thread.__init__(self)
        self.result = result  # 当前存储的搜索结果

    def run(self):
        try:
            print("-------------processing-------------")
            FileInfo.objects.filter(id=self.result.id).update(fileStatus=True)
            print("%d is start", self.result.id)
            result, fileContent = self.remoteFileDownload
            if result and self.updateElasticLabel(fileContent):
                pass
            else:
                FileInfo.objects.filter(id=self.result.id).update(fileStatus=False)
        except:
            FileInfo.objects.filter(id=self.result.id).update(fileStatus=False)

    @property
    def remoteFileDownload(self):
        "远程文件获取，由于远程服务器文件获取形式尚未确定，暂时使用读取本地文件进行测试"
        print("here is remote FileDownload from %s" % self.result.fileRemotePath)
        file_object = open(self.result.fileRemotePath, encoding='UTF-8')
        try:
            fileContent = file_object.read()
            print(fileContent)
            result = True
        except IOError as err:
            result = False
            print(err)
            return result
        finally:
            file_object.close()

        return result, fileContent

    def updateElasticLabel(self, fileContent):
        "更新es条目信息"
        try:
            es = ElasticEntity()
            es.esUpdateFileContent(self.result.fileIndex, self.result.fileType, self.result.fileId, fileContent)
        except:
            return False
        return True
