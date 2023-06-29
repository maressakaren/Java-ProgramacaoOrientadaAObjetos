import java.io.BufferedReader;
import java.io.IOException;

public abstract class Pessoa {
    String nome;
    String cpf;

    Pessoa(){}
    Pessoa(BufferedReader buffer)throws IOException{
        this.nome = buffer.readLine();
        this.cpf = buffer.readLine();
    }
    public void mostraDados(){
        System.out.println("Nome: " + this.nome);
        System.out.println("Cpf: " + this.cpf);

    }
    public String get_cpf(){ return this.cpf;}

    public String get_nome(){return this.nome;}
    
    
}