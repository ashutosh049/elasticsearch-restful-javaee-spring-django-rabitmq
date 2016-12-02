from django.shortcuts import render
from django.http import HttpResponse
from .forms import *
from .mainsearch import sendhttp
from .netfilterControl import *


def hello_world(request):
    return render(request, 'mainsearch/search.html', {})


def index(request):
    # 当提交表单时
    if request.method == 'POST':
        # form 包含提交的数据
        form = AddForm(request.POST)
        # 如果提交的数据合法
        if form.is_valid():
            searchfield = form.cleaned_data['searchField']
            selecttype = form.cleaned_data['selectType']
            selectcategory = form.cleaned_data['selectCategory']
            msg = sendhttp(searchfield, selecttype, selectcategory)
            return HttpResponse(msg)

    else:
        form = AddForm()

    return render(request, 'mainsearch/index.html', {'form': form})


def netfilter(request):
    # 当提交表单时
    if request.method == 'POST':
        # form 包含提交的数据
        form = AddNetfilterForm(request.POST)
        # 如果提交的数据合法
        if form.is_valid():
            command = form.cleaned_data['command']
            msg = addressCommand(command)
            return HttpResponse(msg)
    else:
        form = AddNetfilterForm()

    return render(request, 'netfilter/index.html', {'form': form})

