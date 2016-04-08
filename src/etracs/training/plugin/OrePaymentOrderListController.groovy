package etracs.training.plugin;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.osiris2.common.*;
import com.rameses.osiris2.client.*;

public class PaymentOrderListController{
    @Service('BUKPayOrderService')
    def svc;
    def searchtext;
    
    
    String title = 'List of Payment Orders';
    def selectedPayorderitem;
    def payorderHandler = [
        fetchList : { 
            it.searchtext = searchtext;
            svc.getList(it) }
    ] as BasicListModel; //other type: EditorListModel
    
  //  public def create(){
  //      return Inv.lookupOpener('payorder:create');
  //  }
    
    public def open(){
        return Inv.lookupOpener('payorder:open', [entity:selectedPayorderitem]);
    }
    
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
    
    public void search(){
        listHandler.reload();
    }
    
}
