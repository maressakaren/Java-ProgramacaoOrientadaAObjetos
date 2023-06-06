import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Controle {

    private ArrayList<Player> RecordPlayers;

    private Player atual;
    private String right = "";
    private Random random = new Random();

    public Controle(){

    }
    
    public int draw(){ // método para sortear
        int number = random.nextInt(9)+1;
        this.right +=number;
        return number;
    }
    
    public Player locatePlayer(String name){
        for(Player player: RecordPlayers){
            if(player.getName().equals(name)) return player;
        }
        this.atual = new Player(name);
        
        RecordPlayers.add(this.atual);
        return this.atual;
    }
    

    public void saveFile(){
        FileWriter file = null;
        BufferedWriter buffer = null;
        try {
            file = new FileWriter("Arquivo.txt", false);
            buffer = new BufferedWriter(file);
            buffer.write(String.valueOf(RecordPlayers.size()));
            buffer.write("\n");
            for (Player player : RecordPlayers) {
                player.saveFile(buffer);
            }
        }catch( IOException error){
            MyJOptionPane.showMessageDialog("Erro ao escrever no arquivo ", "ERRO");
        }finally {
            if (buffer != null) {
                try {
                    buffer.close();
                } catch (IOException e) {
                    MyJOptionPane.showMessageDialog("Erro fechar Buffer ", "ERRO");
                } 
            }
            if (file != null) {
                try{
                    file.close();
                }catch(IOException error){
                    MyJOptionPane.showMessageDialog("Erro fechar Arquivo ", "ERRO");
                }
                
            }
        }
    }
    
    public void uploadFile(){   //ESSE MÉTODO FAZ ISSO MESMO????
        RecordPlayers = new ArrayList<Player>();
        FileReader file = null;
        BufferedReader buffer = null;
        try {
            file = new FileReader("Arquivo.txt");
            buffer = new BufferedReader(file);
            String linha = (buffer.readLine());
            if(linha!=null &&!linha.isEmpty()){
               int quantEntradas = Integer.parseInt(linha.trim());
               for (int i = 0; i < quantEntradas; i++) {
                   RecordPlayers.add(new Player(buffer));
               }  
            }
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            MyJOptionPane.showMessageDialog("Arquivo de Recordes não encontrado.", "ERRO");
        } catch (IOException e) {
            e.printStackTrace();
            MyJOptionPane.showMessageDialog("Erro de entrada e saída.", "ERRO");
        } catch (SecurityException e) {
            e.printStackTrace();
            MyJOptionPane.showMessageDialog("Permissão para abrir o arquivo negada.", "ERRO");  
        } finally {
            if (buffer != null) {
                try {
                    buffer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Erro ao fechar o buffer.");
                }
            }
            if (file != null) {
                try {
                    file.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Erro ao fechar o arquivo.");
                }
            }
        }
    }     

    public Player welcome(){
        String name = MyJOptionPane.showInputDialog("Qual é o seu Nome?", "BEM VINDO!!!");
        while(name==null || name.isEmpty()){
            name = MyJOptionPane.showInputDialog("Qual é o seu Nome?", "NOME INVALIDO!");
        }

        return locatePlayer(name);
    }

    public boolean missed(){
        String phrase1  = "Você acertou " + (this.right.length()-1) + " números. Deseja começar novo jogo??";
        String phrase2 = "Fim de Jogo";
        int choice = MyJOptionPane.showConfirmDialog(phrase1, phrase2);
        if(choice==0) return true;
        return false;
    }

    public Player recordist(){
        int maxRecord = 0;
        Player recordPlayer = null;
        for (Player player : RecordPlayers) {
            if (player.getMaxScores() >= maxRecord) {
                maxRecord = player.getMaxScores();
                recordPlayer = player;
            }
        }
        
        return recordPlayer;   
}


    public void bye(String name, int score){
        Player player = recordist();
        String recordists = "Recorde da sessão: " + name + " - " + score +" ponto(s). "+"Geral: "+ player.getName() +" - "+ player.getMaxScores() +" ponto(s).";
        MyJOptionPane.showMessageDialog(recordists, "RECORDES");

    }
   
    public void jogo(){
        int max = -1;
        String name = null;
        uploadFile();
        this.atual = welcome();

        while(this.atual!=null){
            String answer = MyJOptionPane.showInputDialog("O novo número é: "+ draw(), "Digite a sequencia completa");
            while(answer.equals(this.right)){
                answer = MyJOptionPane.showInputDialog("O novo número é: "+ draw(), "Digite a sequencia completa");
            }
            this.atual.updateRecord((this.right.length())-1);
            if(this.atual.getMaxScores()>max){
                name = this.atual.getName();
                max = (this.right.length())-1;
            }
            if(!missed()){
                bye(name,max);
                saveFile();
                this.atual = null;
            }else{
                this.atual= welcome();
                this.right = "";
            }
        }
    }
 
}

// FECHAR BUFFERS
// TEM QUE TER OUTRA LISTA COM JOGADORES ATUAIS??