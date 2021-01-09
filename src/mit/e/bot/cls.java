
package mit.e.bot;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


public class cls {
    
    //-----------------------------------------------------------transmit signal
    public void transmit(String ip,String s,boolean lan){
        if(lan){
            try {
                URL a = new URL("http://"+ip+"/"+s);
                URLConnection b = a.openConnection();
                b.getContentLength();  
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(">>\t"+"http://"+ip+"/"+s);
        }
        else{//-------------------------------------------------------------IOT
            
        }
    }
    
    
    //----------------------------------------------------------reveived signal
    public String[] received(String ip, boolean lan){
        int x=0,l=0;
        String rcv="";
        String[] sa=new String[3];
        
        if(lan){            
            try {
                String line;
                URL url = new URL("http://"+ip+"/");
                BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
                BufferedWriter writer = new BufferedWriter(new FileWriter("data.html"));
                while ((line = reader.readLine()) != null) {
                    rcv=line;
                    writer.write(line);
                    writer.newLine();
                }
                reader.close();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{//-------------------------------------------------------------IOT
            
        }
        
        for (int y = 0; y < rcv.length(); y++) {
            if(rcv.charAt(y)=='#'){
                sa[l]=rcv.substring(x, y);
                x=y+1;
                l++;
            }
        }
        
        if(sa[0].matches("")){
            sa[0]="- - -";
        }
        if(sa[1].matches("")){
            sa[1]="- -";
        }
        if(sa[2].matches("")){
            sa[2]="- -";
        }
        
        return sa;
    }    
    
}
