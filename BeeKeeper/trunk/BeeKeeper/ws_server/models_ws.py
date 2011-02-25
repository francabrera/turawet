'''
Created on 10/02/2011

@author: nicopernas
'''
from soaplib.serializers.primitive import *
from soaplib.serializers.clazz import ClassSerializer

class ModelsSerializer(ClassSerializer):
    '''
    Helper class to convert django model into soaplib model
    
    Constructor read from django model, custom dict, 
    and permit to override parameters using kwargs.

    
    Example:

    #modelsws.py
    
        from contrib.soaplib.serializer import BaseClassSerializer
        from soaplib.serializers.primitive import *
        
        class WsComment(BaseClassSerializer):
            class types:
                content = String 
                rating = Integer
                ratingText = String
    
        class WsBlog(BaseClassSerializer):
            class types:
                title = String
                subtitle = String
                pub_date = DateTime
                replay_to = String 
                content = String
                comments = Array(WsComment)
            
            
    #views.py
    
        from contrib.soaplib.handler import DjangoSoapApp, soapmethod, soap_types
        from foo.models import Blog
        from foo.wsmodels import WsBlog
        from foo.wsmodels import WsComment
        
        class BlogService(DjangoSoapApp):
    
            __tns__ = 'http://ws.javapress.org/blog/'
        
            @soapmethod(_returns=soap_types.Array(WsBlog))
            def get_blogs(self):
                blogs = Blog.objects.all()
                results = []
                for blog in blogs:
                    comments = []
                    for comment in blog.comment_set.all():
                        comments.append(WsComment(comment, ratingText=comment.ratingText()))
                    b = WsBlog(blog, comments=comments)
                    results.append(b)
                return results
        
        blog_service = BlogService()
        
    #urls.py
        urlpatterns = patterns('',
            (r'^blog/', 'foo.views.blog_service'),
            (r'^blog/service.wsdl', 'foo.views.blog_service'),
        )
    
    '''
    def __init__(self, *args, **kwargs):
        super(ModelsSerializer, self).__init__()

        # for each args read attributes and update wsobjcet
        for source in args:
            if isinstance(source, dict):
                self.__dict__.update(source)
            else:
                # if arg is not an dict, take his dict
                self.__dict__.update(source.__dict__)

        # update object also with kwargs
        self.__dict__.update(kwargs)

class WsFormPreview(ModelsSerializer):
    class types:
        name = String
        version = String

