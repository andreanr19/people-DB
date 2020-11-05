package thread;

import javafx.application.Platform;
import model.Generator;
import ui.PeopleController;

import java.io.FileWriter;
import java.io.PrintWriter;

public class ProgressBarThread extends Thread{

    private PeopleController pc;
    private Generator g;
    private boolean active;
    private int q;

    public ProgressBarThread (PeopleController pc, Generator g, int q){
        this.pc = pc;
        this.g = g;
        this.q = q;
        active = true;
    }

    @Override
    public void run(){
            try{
            int count = 0;
            g.setQ(q);
            g.loadDataToGenerate(q);
            PrintWriter pw = new PrintWriter(new FileWriter(g.PATHTOWRITE));
            pw.write("id,name,lastname,gender,age\n");

            for (int i = 0; i < q && count < g.MAX_CAPACITY; i++) {
                pw.write(g.getRandomPerson());
                count++;
                g.setCount(count);
                pc.progressBar();
            }
            pw.close();
            try{
                sleep(1);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        } catch (Exception e) {
                e.printStackTrace();
            }

    }

    public void desactivate(){
        active = false;
    }
}
