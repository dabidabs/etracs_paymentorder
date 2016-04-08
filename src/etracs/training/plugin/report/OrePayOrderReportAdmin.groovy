package etracs.training.plugin.report;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.osiris2.common.*;
import com.rameses.osiris2.client.*;
import com.rameses.osiris2.reports.*;

public class OrePayOrderReportAdmin extends ReportController
{
   @Service('BUKPayOrderService')
   def svc;
   
   def query = [:];
   
   String title = 'Ore Assessment';
   String reportPath = 'etracs/training/plugin/report/';
   String reportName = reportPath + 'OrePayOrderReportAdmin.jasper';
   
   public def getReportData(){
       //entity is personnel info akin to the platform
//       MsgBox.alert(svc.getReportData(entity))
       return svc.getOreReportData(entity);
       
   }
   
   SubReport[] getSubReports(){
       return[
           new SubReport('SGPAYORDERITEMS', reportPath+'OrePayOrderReportAdminItems.jasper'), 
       ]
   }
}

