package etracs.training.plugin;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.osiris2.common.*;
import com.rameses.osiris2.client.*;

public class PermitteeListController{
    @Service('PermitteeService')
    def svc;
    def searchtext;
    
    
    String title = 'List of Permittee'
    def selectedPermitteeitem;
    def permitteeHandler = [
        fetchList : { 
            it.searchtext = searchtext;
            svc.getList(it) }
    ] as BasicListModel; //other type: EditorListModel BasicListModel
    
    public def create(){
        return Inv.lookupOpener('permittee:create');
    }
    
    public def open(){
        return Inv.lookupOpener('permittee:open', [entity:selectedPermitteeitem]);
    }
   // public def edit(){
    //    return Inv.lookupOpener('permittee:edit', [entity:selectedPermitteeitem]);
    //}
    
    public void delete(){
        if (MsgBox.confirm('Delete Item?')){
            if(MsgBox.confirm('Are you sure?')){
                if(MsgBox.confirm('Are you really sure?')){
                    svc.delete(selectedPermitteeitem);
                    permitteeHandler.reload();
                }
                
            }
            
        }
        
    }
    
    public void search(){
        permitteeHandler.reload();
    }
}
