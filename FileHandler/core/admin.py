from django.contrib import admin
from .models import FileInfo

# Register your models here.
admin.site.register(FileInfo, list_display=('id', 'fileIndex', 'fileRemotePath', 'fileMD5', 'fileStatus'))
