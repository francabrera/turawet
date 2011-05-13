import logging
from django.conf import settings



def getlogger(logname):
    logger = logging.getLogger(logname)
    hdlr = logging.FileHandler(settings.LOGS[logname]['file'])
    formatter = logging.Formatter('[%(asctime)s]%(levelname)-8s"%(message)s"','%Y-%m-%d %a %H:%M:%S') 
    
    hdlr.setFormatter(formatter)
    logger.addHandler(hdlr)
    logger.setLevel(settings.LOGS[logname]['level'])

    return logger

def debug(msg):
    logger = getlogger()
    logger.debug(msg)