package com.TunkDesign.McFix.packets;

import com.TunkDesign.McFix.Main;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;

/**
 *
 * @author Karl
 */
public class Fix {
    
    //Runtime run=Runtime.getRuntime();
    //Process process=run.exec("java -jar setvalue.jar");
    
    Main m;
    
    public Fix (Main m) {
        this.m = m;
    }
    
    public void Now() {
        //Stage 1
        SwingWorker<Void, Void> loadWorker = new SwingWorker<Void, Void>()
        {
            protected Void doInBackground() throws Exception
            {
                //Delete level.dat
                Main.print("Deleting level.dat..");
                return null;
            }
            
            protected void done()
            {
                        Main.print("leve.dat deleted.");
                //Stage 2
                SwingWorker<Void, Void> loadWorker = new SwingWorker<Void, Void>()
                {
                    protected Void doInBackground() throws Exception
                    {
                        //Move folder to temp folder for simulation
                        Main.print("Moving save folder for simulation..");
                        return null;
                    }
                    
                    protected void done()
                    {
                        Main.print("Save folder moved.");
                        //Stage 3
                        SwingWorker<Void, Void> loadWorker = new SwingWorker<Void, Void>()
                        {
                            protected Void doInBackground() throws Exception
                            {
                                //Start simulation
                                Main.print("Starting world simulation..");
                                
                                
                                try {
                                    Process[] proc = new Process[2];
                                    proc[0] = new ProcessBuilder("java", "-jar", "sim/sim.jar", "nogui").start();
                                    Main.print("Fixing world..");
                                    try {
                                        Thread.sleep(10000);
                                    } catch (InterruptedException ex) {
                                    }
                                    
                                    proc[0].destroy();
                                    
                                } catch (IOException ioe) {
                                    ioe.printStackTrace();
                                }
                                return null;
                            }
                            
                            protected void done()
                            {
                                //Stage 4
                                SwingWorker<Void, Void> loadWorker = new SwingWorker<Void, Void>()
                                {
                                    protected Void doInBackground() throws Exception
                                    {
                        Main.print("World fixed. Stopping simulation..");
                                        //Stop simulations if no errors
                                        return null;
                                    }
                                    
                                    protected void done()
                                    {
                        Main.print("Simulation stopped.");
                                        //Stage 5
                                        SwingWorker<Void, Void> loadWorker = new SwingWorker<Void, Void>()
                                        {
                                            protected Void doInBackground() throws Exception
                                            {
                        Main.print("Moving save folder back to the origin folder..");
                                                //Move save back to saves folder
                                                return null;
                                            }
                                            
                                            protected void done()
                                            {
                        Main.print("Folder moved. Displaying success message.");
                                                //Done fixing selected save!
                        Main.message("World successfully fixed.");
                                            }
                                        };
                                        
                                        loadWorker.execute();
                                    }
                                };
                                
                                loadWorker.execute();
                            }
                        };
                        
                        loadWorker.execute();
                    }
                };
                
                loadWorker.execute();
            }
        };
        
        loadWorker.execute();
    }
}
