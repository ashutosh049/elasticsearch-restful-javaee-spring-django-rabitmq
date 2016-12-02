from django import forms


class AddForm(forms.Form):
    searchField = forms.CharField()
    selectType = forms.ChoiceField(choices=(('file', 'file'), ('msg', 'msg'), ('code', 'code')))
    selectCategory = forms.ChoiceField(choices=(('lib', 'lib'),))

class AddNetfilterForm(forms.Form):
    command = forms.ChoiceField(choices=(('add', 'add'), ('delete', 'delete')))
