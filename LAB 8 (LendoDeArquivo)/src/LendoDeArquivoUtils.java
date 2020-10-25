import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LendoDeArquivoUtils {

    public static float calcularMedia(String nomeDoArquivo) throws FileNotFoundException, ArquivoCorrompidoException {

        int quantNotasInvalidas = 0, quantNotasValidas = 0;
        float somaTotalDasNotas = 0;

        File arquivo = new File(nomeDoArquivo);
        Scanner scanner = new Scanner(arquivo);

        while (scanner.hasNext()) {
            try {

                float nota = Float.parseFloat(scanner.nextLine());

                if (nota > 10 || nota < 0) {
                    throw new NumberFormatException();
                }

                somaTotalDasNotas += nota;
                quantNotasValidas++;

            } catch (NumberFormatException e) {
                quantNotasInvalidas++;
            }
        }

        if (quantNotasInvalidas > quantNotasValidas) {
            throw new ArquivoCorrompidoException(quantNotasInvalidas);
        } else return (quantNotasValidas != 0) ? (somaTotalDasNotas / quantNotasValidas) : 0;

    }

}