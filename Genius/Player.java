import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Player {
    private String name;
    private int maxScore = 0;
    



    public Player(String name){
        this.name = name;
        

    }
   
    public Player(BufferedReader buffer){
        try {
            
            this.name = buffer.readLine();
            this.maxScore = Integer.parseInt(buffer.readLine());
            
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
   
    public void updateRecord(int scores){
        if(scores>this.maxScore) setScores(scores);      
    }
    
    public void saveFile(BufferedWriter buffer) throws IOException{
        try {
            buffer.write(this.name +"\n");
            buffer.write(this.maxScore+"\n");
        }finally{
            
        }
       
    }

    public void printarPlayers(){
        System.out.println("\nNome: " +this.name+ "\nScore: "+this.maxScore);
    }
    
    public String getName(){
        return this.name;
    }
    
    private void setScores(int scores){
        this.maxScore = scores;
    }
    
    public int getMaxScores(){
        return maxScore;
    }
}