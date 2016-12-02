from celery import task
from .FileAutoInput import FileAutoInput
from core.models import FileInfo
from .constants import *

GLOBAL_THREAD_NUM = 5


@task(name='oneFileTasks')
def oneFileTasks(result):
    downloadThread = FileAutoInput(result)
    downloadThread.start()


@task(name='cronFileTask')
def cronFileTask():
    "定时任务"
    print("--------------------cronfiletasks----------------------")
    results = FileInfo.objects.filter(fileStatus=False)[0:GLOBAL_THREAD_NUM]
    print(results)
    for result in results:
        print(result.id)
        downloadThread = FileAutoInput(result)
        downloadThread.start()


def imdtlyHandleFile(fileIndex, fileType, fileId, fileMD5):
    "立即执行文件插入工作"
    try:
        result = FileInfo.objects.get(fileIndex=fileIndex, fileType=fileType, fileId=fileId, fileMD5=fileMD5)
        if not result.fileStatus:
            oneFileTasks.delay(result)
            #oneFileTasks(result)
            return True
            #oneFileTasks(result)  # for test
        else:
            return FILE_ALREADY_DONE
    except FileInfo.DoesNotExist:
        return FILE_NOT_EXIST


def newFileTask(fileIndex, fileType, fileId, fileMd5, fileRemotePath):
    "插入一个新的文件"
    obj, result = FileInfo.objects.get_or_create(fileIndex=fileIndex, fileType=fileType, fileId=fileId,
                                                 fileMD5=fileMd5,
                                                 defaults={'fileRemotePath': fileRemotePath,
                                                           'fileStatus': False})
    print("object : %s \nresult : %s", obj, result)
    if result:
        return HANDLER_SUCCESS
    else:
        return FILE_EXIST


@task(name='testTask')
def testTask():
    print("here is for test")
