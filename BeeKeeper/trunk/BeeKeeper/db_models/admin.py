from django.contrib import admin
from BeeKeeper.db_models.models import *

# Try to register one
admin.site.register(Form)
admin.site.register(Section)
admin.site.register(FieldGroup)
admin.site.register(FormField)
admin.site.register(Instance)
admin.site.register(InstanceField)
admin.site.register(ImageField)
admin.site.register(TextField)
