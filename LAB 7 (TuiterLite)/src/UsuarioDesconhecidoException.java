/**
 * Exceção para o caso em que um usuário não cadastrado tentou tuitar algo
 */
public class UsuarioDesconhecidoException extends Exception {
    public UsuarioDesconhecidoException(String message) {
        super(message);
    }
}
