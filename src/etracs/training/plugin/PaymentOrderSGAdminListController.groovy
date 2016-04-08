package etracs.training.plugin;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.osiris2.common.*;
import com.rameses.osiris2.client.*;

public class PaymentOrderSGAdminListController{
    @Service('BUKPayOrderService')
    def svc;   
    def searchtext;
    
    
    String title = 'List of Payment Orders';
    def selectedSGAdminPayorderitem;
    def payorderSGAdminHandler = [
        fetchList : { 
            it.searchtext = searchtext;
            svc.getSGAdminList(it) },
        
        onCommitItem : {
                    if(! MsgBox.confirm("Please make sure the coupon numbers are correct. Continue? ")) return;                       
                        //selectedSGAdminPayorderitem.couponno << entity.couponno;
                        //isnew : true
                    if(! svc.addCouponValidation(it)) {
                        MsgBox.alert("Duplicate coupon numbers!")
                        it.couponno = "";
                    }
                                 
                }
                
    ] as EditorListModel; //other type: BasicListModel
    
    public void search(){
        payorderSGAdminHandler.reload();
        
    }
    
  //  public def create(){
  //      return Inv.lookupOpener('payorder:create');
  //  }
    
   // public def open(){
     //   return Inv.lookupOpener('payorder:open', [entity:selectedSGAdminPayorderitem]);
   // }
    
  //  public void delete(){
  //      if (MsgBox.confirm('Delete Item?')){
  //          if(MsgBox.confirm('Are you sure?')){
  //              if(MsgBox.confirm('Are you really sure?')){
  //                  svc.delete(selectedItem);
 //                   listHandler.reload();
  //              }
                
  //          }
            
   //     }
        
  //  }
    
    
    
}
