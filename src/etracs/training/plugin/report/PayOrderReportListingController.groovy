package etracs.training.plugin;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.osiris2.common.*;
import com.rameses.osiris2.client.*;
import com.rameses.osiris2.reports.*;

public class PayOrderReportListingController extends DBReportModel
{
   String title = 'Payment Order Listing'
   String reportName = 'etracs/training/plugin/report/payment_order_list.jasper';
   
    List formControls = [
        [type:'text', name:'query.office', caption:'Office']
    ]
}
