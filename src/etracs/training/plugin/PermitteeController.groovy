package etracs.training.plugin;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.osiris2.common.*;
import com.rameses.osiris2.client.*;

public class PermitteeController extends CRUDController
{
    @Binding
    def binding;
    
    String serviceName = 'BUKPermitteeCRUDService';
    String entityName = 'permittee';
    boolean allowApprove = false;
    def aftersave;
    
    //def genders = ["Male", "Female"];
    //def nationalities = ["FILIPINO", "AMERICAN", "ESPANOL"];
    //def civilservice = ["passed", "failed", "nottaken"]
    
    
    //override create entity to initialize trainings field   
    public Map createEntity(){
        return [ commoditys:[], ];
    }
    
    //override afterOpen to load trainings
    public void afterOpen(Object entity){
        entity.putAll(service.open(entity));
    }
    
    void entityChanged(){
        commodityHandler.reload();
    }
    
    void afterCreate(permittee){
        entityChanged();
    }
    
    void afterSave(permittee){
        
    }
    
    /*================== Training Support ===========*/
    
    //def commoditytypes = ['Sand & Gravel', 'Limestone', 'Mt. Quarry', 'Boulders', 'Phyllites'];
    def status = ['New', 'Renewal', 'For Approval', 'Suspended', 'Gov Approval Only'];
    def selectedCommodity;
   
    def commodityHandler = [
       getRows : {entity.commoditys.size() + 1 },
       fetchList: { entity.commoditys },
       
       createItem : {
           return[
               objid : 'COM' + new java.rmi.server.UID(),
               permobjid : entity.objid,
               isnew : true
           ]
       },
       
        onRemoveItem : {
            if (MsgBox.confirm('Delete item?')){                
                service.deleteCommodity(it)               
                entity.commoditys.remove(it)
                entityChanged()
                return true;
            }
            return false;
        },
        onColumnUpdate:{item,colName ->
            entity.commoditys.each{ y ->
                if(item.objid == y.objid){
                    y.status = item.status;
                }
            }
        },
        onAddItem : {
            entity.commoditys << it; /* add to list syntax */
        },
             
    ] as EditorListModel;
    
   // def onAdd = {
     //   entity.commoditys << it;
       // commodityHandler.reload();
    //}
    
   // def addCommodity(){
     //   return Inv.lookupOpener('commodity:create', [permittee:entity, onAdd:onAdd]);
    //}
    
   // def onUpdate = {
     //   selectedCommodity.putAll(it);
       // commodityHandler.refresh();
    //}
    
   // def editCommodity(){
     //   def params = [
       //     commodity : entity,
         //   entity    : selectedCommodity,
           // onUpdate  : onUpdate,
        //]
       // return Inv.lookupOpener('commodity:edit', params);
   // }
   
   /* ========== Lookup Payer ========= */
    def getLookupPayer(){
        return Inv.lookupOpener('entity:lookup',[
                onselect :{
                    entity.entobjid = it.objid;
                    entity.payer = it;
                    entity.permitteename = it.name;
                    //entity.paidbyaddress = it.address.text;
                    binding.refresh('entity.permitteename.*')
                },
                
                onempty: {
                    //
                }
        ])
    }
    
    def getLookupCommodity(){       
        return Inv.lookupOpener('commodity:lookup',[
                onselect :{
                    selectedCommodity.commodityid = it.objid;
                    selectedCommodity.commodityname = it.commodityname;
                    selectedCommodity.commoditycode = it.commoditycode;
                    //selectedCommodity.name = it.name;
                    //selectedCommodity.code = it.code;  
                    //selectedCommodity.qty = it.qty;  
                  
                    binding.refresh('selectedCommodity.commodityname.*')
                    //binding.refresh('entity.paidby.*')
                },
                
                onempty: {
                    //
                }
        ])
    }
    
}