from django.db import models


# Create your models here.
class FileInfo(models.Model):
    fileIndex = models.CharField(max_length=128)
    fileType = models.CharField(max_length=128)
    fileId = models.CharField(max_length=128)
    fileRemotePath = models.CharField(max_length=256)
    fileMD5 = models.CharField(max_length=1024, blank=True)
    fileStatus = models.BooleanField(default=False)

    def __str__(self):
        return self.fileRemotePath

