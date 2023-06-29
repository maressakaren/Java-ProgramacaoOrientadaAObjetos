import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Sistema{
    private List<Estudante> estudantes;
    private  List<Professor> professores;
    private  List<Turma> turmas;
    Sistema(){
        
        this.estudantes = Leitura.ler_arq_estudantes();
    
        this.professores = Leitura.ler_arq_Professores();
        
        this.turmas = Leitura.ler_arq_turmas(this.estudantes,this.professores);
        exibirNotas();
    }
    
    public static Estudante buscaAluno(String matricula, List<Estudante> alunos)throws AlunoNotFoundException{
        for(Estudante estudante: alunos){if(estudante.get_mat().equals(matricula)){return estudante;}}
        throw new AlunoNotFoundException("Aviso: Aluno não encontrado.");
    }
    
    public static Professor buscaProfessor(String cpf,List<Professor> professores)throws ProfessorNotFoundException{
        for(Professor professor: professores){if(cpf.equals(professor.get_cpf())) return professor;}
        throw new ProfessorNotFoundException("Aviso: Professor não encontrado.");
    }
    
    public void exibirNotas(){
        
        //for(Estudante estudante: this.estudantes){ estudante.mostraDados();}
        Collections.sort(this.turmas);
        try(BufferedWriter buffer = new BufferedWriter(new FileWriter("saida.txt",false))){
            for(Turma turma:this.turmas){turma.salva_arq(buffer);}
        
            System.out.println("Arquivo salvo com sucesso!");

        }catch(IOException e){
            System.err.println("Erro ao escrever no arquivo de saída: " + e.getMessage());
            e.printStackTrace();
        }
        
    }
}