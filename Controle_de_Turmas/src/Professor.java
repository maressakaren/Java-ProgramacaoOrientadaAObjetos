import java.io.BufferedReader;
import java.io.IOException;
import java.util.Comparator;

public class Professor extends Pessoa implements Comparable<Professor> {

    Professor(BufferedReader buffer)throws IOException{
        super(buffer);
    }

    public int compareTo(Professor b){
        if(this.get_nome().compareTo(b.get_nome())==0) return this.get_cpf().compareTo(b.get_cpf());
        return this.get_nome().compareTo(b.get_nome());
    }
}