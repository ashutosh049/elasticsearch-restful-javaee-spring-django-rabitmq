from django.http import HttpResponse
from .tasks import *
from .EndPoint import *
from django.utils.decorators import method_decorator
from django.views.decorators.csrf import csrf_exempt


@method_decorator(csrf_exempt)
def hello(request):
    testTask.delay()
    return HttpResponse("hello world")


@method_decorator(csrf_exempt)
def newFile(request):
    endPoint = Endpoint()
    data = endPoint.get_parsed_request(request)
    result = newFileTask(data[0].get('fileIndex'), data[0].get('fileType'), data[0].get('fileId'), data[0].get('fileMd5'),
                         data[0].get('fileRemotePath'))
    return HttpResponse(result)


@method_decorator(csrf_exempt)
def shortcut(request):
    "快速完成一个文档的下载和内容的添加"
    endPoint = Endpoint()
    data = endPoint.get_parsed_request(request)
    result = imdtlyHandleFile(data.get('fileIndex'), data.get('fileType'), data.get('fileId'),
                              data.get('fileMd5'))
    return HttpResponse(result)


def systemSetting(request):
    return HttpResponse("here is system setting")
