from .http import HttpError

import json


class Endpoint:

    def __init__(self):
        self.content_type = None
        self.params = {}
        self.data = None
        self.raw_data = None

    def _parse_content_type(self):
        if ';' in self.content_type:
            ct, params = self.content_type.split(';', 1)
            try:
                params = dict(param.split('=') for param in params.split())
            except:
                params = {}
        else:
            ct = self.content_type
            params = {}
        return ct, params

    def _parse_body(self, request):
        if request.method not in ['POST', 'PUT', 'PATCH']:
            return

        ct, ct_params = self._parse_content_type()
        if ct == 'application/json':
            charset = ct_params.get('charset', 'utf-8')
            try:
                data = request.body.decode(charset)
                self.data = json.loads(data)
            except Exception as ex:
                raise HttpError(400, 'invalid JSON payload: %s' % ex)
        elif ((ct == 'application/x-www-form-urlencoded') or
                (ct.startswith('multipart/form-data'))):
            self.data = dict((k, v) for (k, v) in request.POST.items())
        else:
            self.data = request.body

    def parse_request(self, request):
        self.content_type = request.META.get('CONTENT_TYPE', 'text/plain')
        self.params = dict((k, v) for (k, v) in request.GET.items())
        self.data = None
        self.raw_data = request.body

        self._parse_body(request)

    def get_parsed_request(self, request):
        self.parse_request(request)
        return self.data

    def get_data(self):
        return self.get_data()
