import java.util.Collections;

public class ProgressBar {
    
    private String item;
    private int completedTasks;
    private int totalTasks;
    
    public ProgressBar(String item, int taskCount) {
        this.item = item;
        this.totalTasks = taskCount;
        this.completedTasks = 0;
        
        this.draw();
    }
    
    public int getCompletedTasks() {
        return this.completedTasks;
    }
    public int getTotalTasks() {
        return this.totalTasks;
    }
    
    public void advance() {
        this.advance(1);
    }
    public void advance(int increment) {
        if((this.completedTasks + increment) <= this.totalTasks)
            this.completedTasks += increment;
        this.draw();
    }
    
    public void reset() {
        this.completedTasks = 0;
        this.draw();
    }
    
    private void draw() {
        int percent = (this.completedTasks * 100 / this.totalTasks);
        
        StringBuilder string = new StringBuilder(140);
        string
                .append('\r')
                .append(String.join("", Collections.nCopies(percent == 0 ? 2 : 2 - (int) (Math.log10(percent)), " ")))
                .append(String.format(" %d%% [", percent))
                .append(String.join("", Collections.nCopies(percent, "#")))
                .append(String.join("", Collections.nCopies(100 - percent, " ")))
                .append(']')
                .append(String.format(" %d/%d %s", this.completedTasks, this.totalTasks, this.item));

        System.out.print(string);
    }
}
