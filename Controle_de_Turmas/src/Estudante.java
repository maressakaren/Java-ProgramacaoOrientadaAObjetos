import java.io.BufferedReader;
import java.io.IOException;


public class Estudante extends Pessoa implements Comparable<Estudante>{

    private String matricula;
    Estudante(){}

    Estudante(BufferedReader buffer)throws IOException{

        super(buffer);
        try{
            this.matricula = buffer.readLine();

        }catch(IOException errException){
            //System.out.println();
        }    
    }
    /*public void mostraDados(){
        super.mostraDados();
        System.out.println("Matricula: " + this.matricula);
    }*/
    public String get_mat(){return this.matricula;}

    public int compareTo(Estudante aluno){
        if(this.get_nome().compareTo(aluno.get_nome())==0){ return this.get_mat().compareTo(aluno.get_mat());}
        return this.get_nome().compareTo(aluno.get_nome());
    }
      
}