package etracs.training.plugin;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.osiris2.common.*;
import com.rameses.osiris2.client.*;
import com.rameses.osiris2.reports.*;

public class PayOrderReportAdminListingController extends DBReportModel
{
   String title = 'Delivery Receipts'
   String reportName = 'etracs/training/plugin/report/PayOrderReportAdminListing.jasper';
   
    List formControls = [
        [type:'text', name:'query.office', caption:'Office']
    ]
}
