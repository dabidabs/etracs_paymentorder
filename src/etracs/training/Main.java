package etracs.training;

import com.rameses.osiris2.test.OsirisTestPlatform;
import java.util.HashMap;
import java.util.Map;
import javax.swing.UIManager;

public class Main {
    public static void main(String[] args) {
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            
            Map env = new HashMap();
            env.put("app.host", "localhost:8072");
            env.put("app.context", "etracs25");
            env.put("app.cluster", "osiris3");
        
            Map roles = new HashMap();
            roles.put("TREASURY.SPONEW", null);
            roles.put("TREASURY.SPOLIST", null);
            roles.put("TREASURY.SPOADMIN", null);
            roles.put("TREASURY.SPOLISTREPORT", null);
            roles.put("TREASURY.SPOADMINLISTREPORT", null);
            roles.put("TREASURY.SPOSGASSESSOR", null);
            roles.put("TREASURY.SPOSGMASTER", null);
            roles.put("TREASURY.SPOPERMITTEE", null);
            roles.put("TREASURY.SPOPERMITTEELIST", null);
            roles.put("TREASURY.ORESPONEW", null);
            
            Map profile = new HashMap();
            profile.put("CLIENTTYPE", "desktop");
            profile.put("USERID", "ADMIN");
            profile.put("USERNAME", "ADMIN");
            profile.put("FULLNAME", "ADMINISTRATOR");
            profile.put("ORGID", "059");
            profile.put("ORGCODE", "059");
            profile.put("ORGNAME", "BUKIDNON");
            profile.put("ORGCLASS", "PROVINCE");
            OsirisTestPlatform.runTest(env, roles, profile);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
       
        
    }
}
