import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import java.util.List;

public abstract class Leitura{
 
    public static List<Estudante> ler_arq_estudantes(){
        List<Estudante> estudantes = new ArrayList<Estudante>();

        try (BufferedReader buffer = new BufferedReader(new FileReader("alunos.txt"))){//FECHA O BUFFER AUTOMATICAMENTE EM CASO DE EXCEÇÃO
            String line = buffer.readLine();
            if(line!=null && !line.isEmpty())
            {   
                int tam = Integer.parseInt(line.trim());
                for(int index = 0; index <tam;index++){
                    estudantes.add(new Estudante(buffer));
                }
            }else{System.out.println("Arquivo Inválido");}
            
        } catch (IOException error) {
            System.err.println("Aviso:  Erro na leitura do Arquivo de Alunos");
        }
        return estudantes;
    }

    public static List<Professor> ler_arq_Professores(){
        List<Professor> professores = new ArrayList<Professor>();

        try (BufferedReader buffer = new BufferedReader(new FileReader("professores.txt"))){//FECHA O BUFFER AUTOMATICAMENTE EM CASO DE EXCEÇÃO
            String line = buffer.readLine();
            if(line!=null && !line.isEmpty()){
                professores = new ArrayList<Professor>();
                int tam = Integer.parseInt(line.trim());
                for(int index = 0; index <tam;index++){ professores.add(new Professor(buffer));}
            }   
       
       
        } catch (IOException e) {
            System.err.println("Aviso:  Erro na leitura do Arquivo de Professores");
        }
        return professores;
    }

    public static List<Turma> ler_arq_turmas(List<Estudante>estudantes, List<Professor> professores){
        List<Turma> turmas = new ArrayList<Turma>();

        try (BufferedReader buffer = new BufferedReader(new FileReader("turmas.txt"))){// FECHA O BUFFER AUTOMATICAMENTE EM CASO DE EXCEÇÃO
            String line = buffer.readLine();
            if(line!=null && !line.isEmpty()){
                turmas = new ArrayList<Turma>();
                int tam = Integer.parseInt(line.trim());
                for(int index = 0; index <tam;index++){
                    turmas.add(new Turma(buffer, estudantes,professores));
                }
               /*for(Turma turma: turmas){
                    System.out.println("\n***********************\n");
                    turma.mostrar_turma();
                }*/
            }

        } catch (IOException error) {
            System.err.println("Aviso:  Erro na leitura do Arquivo de Turmas");
        }
        return turmas;
    }
    
}