package etracs.training.plugin;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.osiris2.common.*;
import com.rameses.osiris2.client.*;

public class SGKindPageController extends CRUDController
{
   // @Service('BUKSGKindService')
   // def svc;
    
    String serviceName = 'BUKSGKindService';
    String entityName = 'sgkind';
    boolean allowApprove = false;
    
    @Binding
    def binding;
  
    
 //override create entity to initialize trainings field   
    public Map createEntity(){
       return [sgkindaccounts:[]];
   }
    
     //override afterOpen to load trainings
    public void afterOpen(Object sgkind){
        sgkind.putAll(service.open(sgkind));
    }
    
    void entityChanged(){
        sgkindHandler.reload();
    }
    
    void afterCreate(sgkind){
        entityChanged();
    }
 
    //def init(){ entity = [sgkindaccounts:[]] }
     
    def selectedSGKinditem;
    
    def sgkindHandler = [
       getRows : {entity.sgkindaccounts.size() + 1 },
       fetchList: { entity.sgkindaccounts },
       
       createItem : {
           return[ 
               objid : 'SGIT' + new java.rmi.server.UID(),
               sgobjid : entity.objid,               
               isnew : true
           ]
       },
       
        onRemoveItem : {
            if (MsgBox.confirm('Delete item?')){                
                service.deleteItem(it)               
                entity.sgkindaccounts.remove(it)
                entityChanged()
                return true;
            }
            return false;
        },
        
        onAddItem : {
            entity.sgkindaccounts << it; /* add to list syntax */
           // calculatetotal()
     }
     
       // validate:{li->
        //    def item=li.item;
       //     calculatetotal()
            
      //  }
        
  ] as EditorListModel; 
      
      /* ========== Lookup Itemaccounts ========= */
    
    def getLookupItemaccount(){       
        return Inv.lookupOpener('revenueitem:lookup',[
                onselect :{   
                    
                    selectedSGKinditem.item_title = it.title;                                        
                    selectedSGKinditem.item_code = it.code;
                    selectedSGKinditem.item_fund_code = it.fund.code;
                    selectedSGKinditem.item_objid = it.objid;
                    selectedSGKinditem.orseq = it.orseq;
                    
                 //   selectedSGitem.item_fund_objid = it.fund.objid;
                 //   selectedSGitem.item_fund_title = it.fund.title;
                //    selectedSGitem.type = it.type;
                //    selectedSGitem.valuetype = it.valuetype;
                //    selectedSGitem.defaultvalue = it.defaultvalue;
                    
                
                     
                    binding.refresh('selectedSGKinditem.item_code.*')
                    //binding.refresh('entity.paidby.*')
                },
                
                onempty: {
                    //
                }
        ])
    }    

}