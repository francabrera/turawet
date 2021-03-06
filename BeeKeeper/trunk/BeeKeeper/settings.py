# Django settings for BeeKeeper project.
import os.path
import logging
import django

DJANGO_ROOT = os.path.dirname(os.path.realpath(django.__file__))
PROJECT_DIR = os.path.dirname(os.path.realpath(__file__))

# CSS paths
CSS_BEEKEEPER_ROOT = os.path.join(PROJECT_DIR, 'beekeeper', 'css')

# Images paths
IMAGES_BEEKEEPER_ROOT = os.path.join(PROJECT_DIR, 'beekeeper',
                                    'templates', 'images')

# JS paths
JS_BEEKEEPER_ROOT = os.path.join(PROJECT_DIR, 'beekeeper', 'js')



DEBUG = True
TEMPLATE_DEBUG = DEBUG


#logging.basicConfig(level=logging.DEBUG,
#     format='%(asctime)s %(levelname)s %(message)s',
#     filename=os.path.join(PARENT_DIR, 'django.log'),
#     filemode='a+')


LOGS = {
    'FormXmldbParser': {
        'file': PROJECT_DIR+'/logs/FormXmldbParser.log',
        'level': 'ERROR',
    },
    'InstanceXmldbParser': {
        'file': PROJECT_DIR+'/logs/InstanceXmldbParser.log',
        'level': 'ERROR',
    },
}


ADMINS = (
    # ('Your Name', 'your_email@domain.com'),
)

MANAGERS = ADMINS

DATABASES = {
    'default': {
        'ENGINE':   'django.db.backends.mysql',
        'NAME':     'turawetbbdd',
        'USER':     'ubuntu',
        'PASSWORD': 'D474B453P455W0RD',
        'HOST':     'localhost',
        #'PORT':     '',
    },
}

"""
DATABASES = {
    'default': {
        'ENGINE': 'django.db.backends.sqlite3', # Add 'postgresql_psycopg2', 'postgresql', 'mysql', 'sqlite3' or 'oracle'.
        'NAME': os.path.join(PROJECT_DIR, 'sqlite.db'), # Or path to database file if using sqlite3.
        'USER': '', # Not used with sqlite3.
        'PASSWORD': '', # Not used with sqlite3.
        'HOST': '', # Set to empty string for localhost. Not used with sqlite3.
        'PORT': '', # Set to empty string for default. Not used with sqlite3.
    }
}
"""
# Local time zone for this installation. Choices can be found here:
# http://en.wikipedia.org/wiki/List_of_tz_zones_by_name
# although not all choices may be available on all operating systems.
# On Unix systems, a value of None will cause Django to use the same
# timezone as the operating system.
# If running in a Windows environment this must be set to the same as your
# system time zone.
TIME_ZONE = 'Atlantic/Canary'

# Language code for this installation. All choices can be found here:
# http://www.i18nguy.com/unicode/language-identifiers.html
LANGUAGE_CODE = 'es-ES'

SITE_ID = 1

# If you set this to False, Django will make some optimizations so as not
# to load the internationalization machinery.
USE_I18N = True

# If you set this to False, Django will not format dates, numbers and
# calendars according to the current locale
USE_L10N = True

# Absolute path to the directory that holds media.
# Example: "/home/media/media.lawrence.com/"
MEDIA_ROOT = os.path.join(PROJECT_DIR, 'media')

# URL that handles the media served from MEDIA_ROOT. Make sure to use a
# trailing slash if there is a path component (optional in other cases).
# Examples: "http://media.lawrence.com", "http://example.com/media/"
MEDIA_URL = ''

# URL prefix for admin media -- CSS, JavaScript and images. Make sure to use a
# trailing slash.
# Examples: "http://foo.com/media/", "/media/".
ADMIN_MEDIA_PREFIX = '/media/'

# Make this unique, and don't share it with anybody.
SECRET_KEY = 'nim&krjty7h91=ymv54k%o(7pzijjj2lesbrur!wj(kfyr1e8)'

# List of callables that know how to import templates from various sources.
TEMPLATE_LOADERS = (
    'django.template.loaders.filesystem.Loader',
    'django.template.loaders.app_directories.Loader',
#     'django.template.loaders.eggs.Loader',
)
"""
MIDDLEWARE_CLASSES = (
    'django.middleware.common.CommonMiddleware',
    'django.contrib.sessions.middleware.SessionMiddleware',
    'django.middleware.csrf.CsrfViewMiddleware',
    'django.contrib.auth.middleware.AuthenticationMiddleware',
    'django.contrib.messages.middleware.MessageMiddleware',

)
"""
ROOT_URLCONF = 'BeeKeeper.urls'


TEMPLATE_DIRS = (
    os.path.join(PROJECT_DIR, 'form_xml2db_parser/templates'),
    os.path.join(PROJECT_DIR, 'beekeeper/templates'),
    # here you can add another templates directory if you wish.
)

MIDDLEWARE_CLASSES = (
    'django.middleware.common.CommonMiddleware',
    'django.contrib.sessions.middleware.SessionMiddleware',
    'django.middleware.csrf.CsrfViewMiddleware',
    'django.contrib.auth.middleware.AuthenticationMiddleware',
    'django.contrib.messages.middleware.MessageMiddleware',
    'BeeKeeper.beekeeper.middleware.SiteLogin',
)


INSTALLED_APPS = (
    'django.contrib.auth',
    'django.contrib.contenttypes',
    'django.contrib.sessions',
    'django.contrib.sites',
    'django.contrib.messages',
    # Uncomment the next line to enable the admin:
    'django.contrib.admin',
    # Uncomment the next line to enable admin documentation:
    'django.contrib.admindocs',
    'django_extensions',
    'django.contrib.admindocs',
    'BeeKeeper.db_models',
    'BeeKeeper.user_models',
    'BeeKeeper.ws_server',
    'BeeKeeper.form_xml2db_parser',
    'BeeKeeper.instance_xml2db_parser',
    'BeeKeeper.beekeeper',
)
