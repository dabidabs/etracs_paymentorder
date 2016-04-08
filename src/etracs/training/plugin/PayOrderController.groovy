package etracs.training.plugin;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.osiris2.common.*;
import com.rameses.osiris2.client.*;

public class PayOrderController extends CRUDController
{
    @Binding
    def binding;
    
    
    String serviceName = 'BUKPayOrderService';
    String entityName = 'payorder';
    boolean allowApprove = false;
    boolean allowEdit = false;
    boolean allowDelete = false;
    
    def officeOrigin = ['Housing', 'Admin', 'BENRO'];
    
    
    public Map createEntity(){
     return [items:[]];
    }
    
    //override afterOpen to load trainings
    public void afterOpen(Object payorder){
        payorder.putAll(service.open(payorder));
    }
    
    void entityChanged(){
        payorderHandler.reload();
    }
    
    void afterCreate(payorder){
        entityChanged();
    }
    
    
    
    /*================== Items Support ===========*/
   
    def selectedPayorderitem;
   
    def payorderHandler = [
       getRows : {entity.items.size() + 1 },
       fetchList: { entity.items },
       
       createItem : {
           return[
               objid : 'PI' + new java.rmi.server.UID(),
               payorderid : entity.objid,
               isnew : true
           ]
       },
       
        onRemoveItem : {
            if (MsgBox.confirm('Delete item?')){                
                service.deleteItem(it)               
                entity.items.remove(it)
                entityChanged()
                return true;
            }
            return false;
        },
        
        onAddItem : {
            entity.items << it; /* add to list syntax */
            calculatetotal()
     },
     
        validate:{li->
            def item=li.item;
            calculatetotal()
            
        }
        
  ] as EditorListModel; 
    
    void calculatetotal(){
        entity.amountdue = 0.0
        if (entity.items){
            entity.amountdue = entity.items.amount.sum();
        }
        binding.refresh('entity.amountdue')
    }
    
    /* ========== Lookup Payer ========= */
    def getLookupPayer(){
        return Inv.lookupOpener('entity:lookup',[
                onselect :{
                    entity.payer = it;
                    entity.paidby = it.name;
                    entity.paidbyaddress = it.address.text;
                    binding.refresh('entity.paidby.*')
                },
                
                onempty: {
                    //
                }
        ])
    }
     /* ========== Lookup Itemaccounts ========= */
    def getLookupItemaccount(){       
        return Inv.lookupOpener('revenueitem:lookup',[
                onselect :{   
                    selectedPayorderitem.item_objid = it.objid;
                    selectedPayorderitem.item_title = it.title;                                        
                    selectedPayorderitem.item_code = it.code;
                    selectedPayorderitem.item_fund_code = it.fund.code;
                    selectedPayorderitem.item_fund_objid = it.fund.objid;
                    selectedPayorderitem.item_fund_title = it.fund.title;
                    selectedPayorderitem.type = it.type;
                    selectedPayorderitem.valuetype = it.valuetype;
                    selectedPayorderitem.defaultvalue = it.defaultvalue;
                    
                    if(it.valuetype == "FIXEDUNIT") {
                            def m = MsgBox.prompt( "Enter qty" );
                            if( !m || m == "null" ) throw new Exception("Please provide qty"); 
                            if( !m.isInteger() ) throw new Exception("Qty must be numeric"); 
                            selectedPayorderitem.amount = Integer.parseInt( m )*it.defaultvalue; 
                            selectedPayorderitem.remarks = "qty@"+Integer.parseInt( m );
                            selectedPayorderitem.sgquantity = Integer.parseInt( m );
                    } 
                    binding.refresh('selectedPayorderitem.item_code.*')
                    //binding.refresh('entity.paidby.*')
                },
                
                onempty: {
                    //
                }
        ])
    }
   
}