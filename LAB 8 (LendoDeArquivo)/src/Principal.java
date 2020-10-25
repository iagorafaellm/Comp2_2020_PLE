import java.io.FileNotFoundException;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);   //para ler do standard in (teclado)

        boolean existeArquivo;                      //se o arquivo existe, true e, caso contrário, false

            do {
                System.out.println("Bem vindx! Por favor, comecemos digitando o nome do arquivo desejado: ");
                String nomeArquivo = scanner.nextLine();

                existeArquivo = false;

                try {

                    System.out.printf("Média da turma: %.2f%n", LendoDeArquivoUtils.calcularMedia(nomeArquivo));
                    existeArquivo = true;

                } catch (ArquivoCorrompidoException e) {

                    System.out.println("Esse arquivo está corrompido!");

                    System.out.println("Esse arquivo tem exatas " + e.getLinhasCorrompidas() +
                            " linhas inválidas.");
                    existeArquivo = true;

                } catch (FileNotFoundException e) {
                    System.out.println("Esse arquivo não existe!");
                }

            } while (!existeArquivo);
    }
}
