"""*Database Models* definition module. 
   :author: Francisco Jose Cabrera Hernandez,
            Nicolas Pernas Maradei,
            Romen Rodriguez Gil
   :version: 0.2"""

#Dump data

from xml.etree.ElementTree import ElementTree, tostring, XML
from BeeKeeper.db_models.models import Instance, Section, FormField, Form, TextField, InstanceField, DateField,\
    RadioField, CheckField, ComboField, TextAreaField, NumericField
from BeeKeeper.logger import *

import datetime
import sys




### GLOBAL VARIABLE ###
logger = getlogger('InstanceXmldbParser')


class InstanceXmldbParser():
    

    ### ATRIBUTES ###
    
    ### METHODS ###
    
    def parse_text_field(self, parser):
        value = parser.findtext('value')
        instance_field_model = TextField(value=value)
        # Need to add the id value after saving the instance_field_model
        
        return instance_field_model
        ### IS FIELD MODEL PASSED BY VALUE OR BY VARIABLE?
        ###return field_model


    def parse_textarea_field(self, parser):
        value = parser.findtext('value')
        instance_field_model = TextAreaField(value=value)
        # Need to add the id value after saving the instance_field_model
        
        return instance_field_model
        ### IS FIELD MODEL PASSED BY VALUE OR BY VARIABLE?
        ###return field_model


    def parse_numeric_field(self, parser):
        value = parser.findtext('value')
        instance_field_model = NumericField(value=value)
        # Need to add the id value after saving the instance_field_model
        
        return instance_field_model


    def parse_date_field(self, parser):
        day_value = int(parser.findtext('value/day'))
        month_value = int(parser.findtext('value/month'))
        year_value = int(parser.findtext('value/year'))
        date = datetime.date(year_value,month_value,day_value)
        instance_field_model = DateField(value=date)
        
        return instance_field_model
    
    

    def parse_radio_field(self, parser):
        value = parser.findtext('value')
        instance_field_model = RadioField(value=value)
        # Need to add the id value after saving the instance_field_model
        
        return instance_field_model
    

    def parse_check_field(self, parser):
        value = parser.findtext('value')
        instance_field_model = CheckField(value=value)
        # Need to add the id value after saving the instance_field_model
        
        return instance_field_model


    def parse_combo_field(self, parser):
        value = parser.findtext('value')
        instance_field_model = ComboField(value=value)
        # Need to add the id value after saving the instance_field_model
        
        return instance_field_model


    
    def parse_generic_field(self, instance_fields, instance_model, j, field_group_model=None):
        """
        :attention The loop iterators are:
                   "i" for sections; "j" for fields in sections; "k" for fields in groups
        """
        ## Data types ##
        actionSwitch = {
            'TEXT': self.parse_text_field,
            'TEXTAREA': self.parse_textarea_field,
            'NUMERIC': self.parse_numeric_field,
            'DATE': self.parse_date_field,
            'RADIO': self.parse_radio_field,
            'COMBO': self.parse_combo_field,
            'CHECKBOX': self.parse_check_field
            }
        # Parsing
        k = 0
        for field in instance_fields:
            id = field.find('id')
            formfield_id = field.findtext('formfieldid')

            # If there is a an error we report it to the logger
            ok_field = False
            try:
                field_model = FormField.objects.get(pk=formfield_id)
                ok_field = True
            except FormField.DoesNotExist:
                logger.error('FormField: does not exist:'+ str(sys.exc_info()[0]))
            except FormField.MultipleObjectsReturned:
                logger.error('FormField: expected one, received many:'+ str(sys.exc_info()[0]))
            # If the field is in the DB
            if ok_field:
                # If there is a an error we report it to the logger
                ok_type = False
                try:
                    # We fill the value of this concrete field in the instance
                    instance_field_model = actionSwitch[field_model.type](field)
                    ok_type = True
                except:
                    logger.error('Indexing actionSwitch error:'+ str(sys.exc_info()[0]))
                if ok_type:
                    # We fill the fields of the instance field
                    instance_field_model.instance_order = k
                    instance_field_model.form_field = field_model
                    instance_field_model.instance = instance_model
                    ### We save the value in the DDBB ###
                    instance_field_model.save()
                    k +=1
            # Next iteration
            j += 1
            
        return j
    
    
    def generateModels(self, xml):
        if xml is None:
            return False
        else:
            parser = XML(xml)
            #Starting the parsing
            # Getting the form meta info
            form_id = parser.findtext('meta/formid')
            #Getting the instance meta info
            user = parser.findtext('meta/author/user')
            creation_date = parser.findtext('meta/creationdate')
            modification_date = parser.findtext('meta/modificationdate')
            editable = parser.findtext('meta/editable')
            # We get the empty Id of the instance
            id = parser.find('meta/id')
            id_text = parser.findtext('meta/id')
            if id_text == '': id_text = 0
            # We create the instance or we modify it
            # TO-DO: Check if the instance already exist in the dataBase
                # If it already exists we shold do an update instead of an insert
            try:
                instance_model = Instance.objects.get(pk=id_text)
                # We update the necessary fields
                instance_model.modification_date = modification_date
                instance_model.editable = editable
                instance_model.save()
            except Instance.DoesNotExist:
                logger.error('Instance: does not exist. we should insert it. Details:'+ str(sys.exc_info()[0]))
                # TO-DO: What if we go into the except. No instance created?
                try:
                    form_model = Form.objects.get(pk=form_id)
                    instance_model = Instance(creation_date=creation_date, modification_date=modification_date,
                                              form=form_model, editable=editable)
                    instance_model.save()
                    # we set the id value once the instance is saved
                    id.text = str(instance_model.id)
                except Form.DoesNotExist:
                    logger.error('Instance: does not exist. we should insert it. Details:'+ str(sys.exc_info()[0]))

            
            #Section
            sections = parser.findall("sections/section")
            for section in sections:
                ##### The ID was introduced by the other parser
                id = section.findtext('id')
                #### We obtain the section that should have been saved ####
                ok = False
                try:
                    section_model = Section.objects.get(pk=id)
                    ok = True
                except Section.DoesNotExist:
                    logger.error('Section: does not exist:'+ str(sys.exc_info()[0]))
                except Section.MultipleObjectsReturned:
                    logger.error('Section: expected one, received many:'+ str(sys.exc_info()[0]))
                # Valid section
                if ok:
                    #Section fields
                    instance_fields = section.findall("fields/field")
                    # Parsing the fields
                    j = 0 # Integer in python are inmutable. So they are passed by value to the function
                    j = self.parse_generic_field(instance_fields, instance_model, j)
                    # Parsing the groups
                    groups = section.findall("fields/group")
                #####j = self.parse_generic_group(groups, instance_model, j)
            
            return True
            