package etracs.training.plugin;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.osiris2.common.*;
import com.rameses.osiris2.client.*;

public class PermitteeCommodityController
{
    @Binding
    def binding;
    
    def permittee;
    def entity; //employment
    def mode;
   
    String title = 'Commodity Details';
    def status = ['New', 'Renewal', 'For Approval', 'Suspended', 'Gov Approval Only'];
    
    void create(){
        entity = [
            objid : 'COM' + new java.rmi.server.UID(),
            permobjid : entity.objid,
        ]
        mode = 'create';
    }
    
    void edit(){
        mode = 'edit';
    }
    
    
    
    /* PermitteeController callbacks */
    
    def onAdd;
    def onUpdate;
    def onDelete;
   
    def add(){
        onAdd(entity);
        return '_close';
    }
    
    def update(){
        onUpdate(entity);
        return '_close';
    }
    
    
   // def selectedCommodity ;
    def commodityid;
    def commodityname;
    def commoditycode;
    
    def getLookupCommodity(){  
        return Inv.lookupOpener('commodity:lookup',[
                
                onselect :{                   
                    //selectedCommodity.commodityid = it.objid;
                    //selectedCommodity.commodityname = it.commodityname;
                    //selectedCommodity.commoditycode = it.commoditycode;
                    commodityid = it.objid;
                    commodityname = it.commodityname;
                    commoditycode = it.commoditycode;
                                     
                    //binding.refresh('selectedCommodity.commodityname.*')
                    binding.refresh('commodityname.*')
                    
                },
                
                onempty: {
                    //
                }
        ])
    }
  
}
