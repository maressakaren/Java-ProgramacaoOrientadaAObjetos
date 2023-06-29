import java.io.BufferedWriter;
import java.io.IOException;



public class AlunoNota implements Comparable<AlunoNota>, Salvavel{
    private double nota;
    private Estudante aluno;

    AlunoNota(Estudante aluno, double nota){
        this.aluno = aluno;
        this.nota = nota;

    }
    public double get_nota(){ return this.nota;}

    public int compareTo(AlunoNota alunoNota){
        if(this.nota==alunoNota.get_nota()){
            return this.aluno.compareTo(alunoNota.aluno);
        }
        if(this.nota>alunoNota.get_nota()) return -1;
        return 1;
    }
    public void salva_arq(BufferedWriter buffer)throws IOException{
        String line  = "- "+this.aluno.get_nome() + " ("+this.aluno.get_mat() + "): " + this.nota +"\n";
        buffer.write(line);
    }

    
}