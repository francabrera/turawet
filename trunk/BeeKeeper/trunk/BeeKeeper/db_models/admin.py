from django.contrib import admin
from BeeKeeper.db_models.models import *

# Try to register one
admin.site.register(Form)
admin.site.register(Section)
admin.site.register(FieldGroup)
admin.site.register(FormField)
admin.site.register(FieldOption)
admin.site.register(Property)
admin.site.register(Instance)
admin.site.register(InstanceField)
admin.site.register(TextField)
admin.site.register(TextAreaField)
admin.site.register(DateField)
admin.site.register(RadioField)
admin.site.register(CheckField)
admin.site.register(ComboField)
admin.site.register(ImageField)
