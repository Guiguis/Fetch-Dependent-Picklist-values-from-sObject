/*******************************************************************************
*                               Guilherme Miranda - 2017
*-------------------------------------------------------------------------------
*
*
* Classe responsável por mapear Objeto (pObjName) e resgatar os valores dos PickLists
*
* AUTHOR: Unknown                                             DATE: 2017
*******************************************************************************/

/**
* Pega os valores do PickList 
* 
* @param  objObject sObjectType
* @param  fld       String     Campo  Linha atual da peça a ser movida
* @return allOpts   List       Lista com as oções da lista do Picklist de Nível 1
*/    
@AuraEnabled
public static List <String> getselectOptions(sObject objObject, string fld) {
    List < String > allOpts = new list < String > ();
        // Get the object type of the SObject.
    Schema.sObjectType objType = objObject.getSObjectType();

        // Describe the SObject using its object type.
    Schema.DescribeSObjectResult objDescribe = objType.getDescribe();

        // Get a map of fields for the SObject
    map < String, Schema.SObjectField > fieldMap = objDescribe.fields.getMap();

        // Get the list of picklist values for this field.
    list < Schema.PicklistEntry > values =
    fieldMap.get(fld).getDescribe().getPickListValues();

        // Add these values to the selectoption list.
    for (Schema.PicklistEntry a: values) {
        allOpts.add(a.getValue());
    }
    allOpts.sort();
    return allOpts;
}
