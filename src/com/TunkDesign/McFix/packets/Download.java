package com.TunkDesign.McFix.packets;

import com.TunkDesign.McFix.Main;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import javax.swing.SwingWorker;

/**
 *
 * @author Karl
 */
public class Download {
    
    Main m;
    
    
    private URL url;
    private URLConnection con;
    private BufferedOutputStream out;
    private FileOutputStream fos;
    private byte[] fileData;
    
    public Download(Main m) {
        this.m = m;
    }
    
    public void LatestServer(){
        SwingWorker<Void, Void> loadWorker = new SwingWorker<Void, Void>()
        {
            private boolean success;
            protected Void doInBackground() throws Exception
            {
                // Create a directory; all non-existent ancestor directories are
// automatically created
success = (new File(m.appPath + "/sim")).mkdirs();
if (!success) {
    // Directory creation failed
}
        m.jProgressbar.setValue(0);
        m.DownloadSize = 0;
                
                try {
                    
                    url = new URL("https://s3.amazonaws.com/MinecraftDownload/launcher/minecraft_server.jar");   
                    con = url.openConnection();     
                    m.DownloadSize = con.getContentLength();
                    float totalDataRead=0;
                    
                    
                    java.io.BufferedInputStream in = new java.io.BufferedInputStream(con.getInputStream());
                    
                    out = new BufferedOutputStream(new FileOutputStream(m.appPath + "/sim/sim.jar"));
                    fos = new FileOutputStream(new File(m.appPath + "/sim/sim.jar"));
                    
                    byte[] data = new byte[1024];
                    int i=0;
                    while((i=in.read(data,0,1024))>=0)
                    {
                        totalDataRead=totalDataRead+i;
                        out.write(data,0,i); 
                        float Percent=(totalDataRead*100)/m.DownloadSize;
                        m.jProgressbar.setValue((int)Percent);
                        
                    }   
                    
                    out.flush();
                    out.close();
                    in.close();
                    
                } catch (MalformedURLException e) {
                    m.message("Oops! Something went wrong!   -   "
                            + "Please contact TunkDesign.");
                } catch (IOException e) {
                    m.message("");
                }
                return null;
                
            }
            
            // the done() method gets called when the background process above is complete.
            // it's called on the EDT.
            protected void done()
            {
                m.message("DONE!");
            }
        };
        
        loadWorker.execute();
    }    
}