package etracs.training.plugin;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import sun.security.pkcs11.TemplateManager.Template.*;

import com.rameses.osiris2.common.*;
import com.rameses.osiris2.client.*;
import com.rameses.enterprise.treasury.cashreceipt.*
import com.rameses.osiris2.common.BasicListModel;


public class PayorderReceiptController extends AbstractCashReceipt
{
    @Service('BUKPayOrderService')
    def svc;
    
    @Service('BUKPayOrderLookupListService')
    def listSvc;
    
    @Binding
    def binding;

    
    void init(){
        super.init();
    }
    
    
    def payorderHandler = [
        fetchList : { 
            return entity.items
        }
    ] as BasicListModel; //other type: EditorListModel
    
    def selectedPayorderitem;
        
    def getLookupPayorder(){       
        return Inv.lookupOpener('payorder:lookup',[
                onselect :{
                    entity.payorder = it;
                    entity.items = svc.getItems(entity.payorder)
                    payorderHandler.reload();
                    entity.amount = entity.payorder.amountdue;
                    entity.paidby = it.paidby;
                    entity.paidbyaddress = it.paidbyaddress;
                    updateBalances();
                    binding.refresh('entity.paidby.*')
                },
                
                onempty: {
                    //
                }
            ])
    }
}
