import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;
import javax.management.RuntimeErrorException;
import java.util.ArrayList;
import java.util.Collections;
public class Turma implements Comparable<Turma>,Salvavel{
    
    private String nome_turma;
    private int year,semestre;
    private Professor responsavel;
    private int numAlunos;
    private List<AlunoNota> alunosNotas;

    Turma(){}

    Turma(BufferedReader buffer, List<Estudante> alunos, List<Professor> professores){
        Estudante atual = new Estudante();

        this.alunosNotas = new ArrayList<AlunoNota>();
       try{
           
            this.nome_turma = buffer.readLine();
            this.year = Integer.parseInt(buffer.readLine().trim());
            this.semestre = Integer.parseInt(buffer.readLine().trim());
            String cpfProfessor = buffer.readLine();

            try {
                this.responsavel = Sistema.buscaProfessor(cpfProfessor, professores);
                this.numAlunos = Integer.parseInt(buffer.readLine().trim());
                for(int index =0; index < numAlunos; index++){
                    String matricula = buffer.readLine();
                    int nota = Integer.parseInt(buffer.readLine().trim());
                    try {
                        atual = Sistema.buscaAluno(matricula, alunos);
                        this.alunosNotas.add(new AlunoNota(atual, nota));

                    } catch (AlunoNotFoundException error) {
                        System.out.println(error.getMessage());
                    }
                }
                
            }catch(ProfessorNotFoundException error){ 
                System.err.println(error.getMessage());
                System.exit(1);
            }
       }catch(IOException error){ System.out.println("Erro ao ler Arquivo");}
    }

    /*public void mostrar_turma(){
        System.out.println("\nTurma: " + this.nome_turma + " " + this.year+"/" + this.semestre + "\nResponsável: "+ this.responsavel.get_cpf());
        for(AlunoNota aluno: this.alunosNotas){
            System.out.println("\n"+aluno.aluno.get_nome()+ " - "+ aluno.aluno.get_cpf()+" - "+ aluno.aluno.get_mat());

        }

    }*/
    public String get_nome_mat(){ return this.nome_turma;}
    public Professor get_responsavel(){ return this.responsavel;}

    public int compareTo(Turma outra_turma){
        if(this.year>outra_turma.year) return -1;
        if(this.year<outra_turma.year) return 1;

        if(this.semestre>outra_turma.semestre) return -1;
        if(this.semestre<outra_turma.semestre) return 1;
        
        if(this.nome_turma.compareTo(outra_turma.nome_turma)==0) return this.responsavel.compareTo(outra_turma.get_responsavel());
        return this.nome_turma.compareTo(outra_turma.get_nome_mat());

    }
    public void salva_arq(BufferedWriter buffer)throws IOException{
        String line = this.nome_turma + " (" + this.year+"/"+this.semestre +") - " + this.responsavel.get_nome() + "\n";
        buffer.write(line);
        Collections.sort(this.alunosNotas);
        for(AlunoNota aluno: this.alunosNotas){aluno.salva_arq(buffer);}
    }
}