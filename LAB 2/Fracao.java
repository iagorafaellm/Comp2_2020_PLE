import java.util.Objects;

public class Fracao {

    /**
     * Construtor.
     * O sinal da fração é passado no parâmetro específico.
     *
     * @param numerador O numerador (inteiro não-negativo)
     * @param denominador O denominador (inteiro positivo)
     * @param positiva se true, a fração será positiva; caso contrário, será negativa
     */

    private int numerador, denominador, sinal;
    private boolean positiva, fracaoNula;

    public Fracao(int numerador, int denominador, boolean positiva) {
        this.numerador = numerador;
        this.denominador = denominador;
        this.positiva = positiva;
        if (positiva) {
            this.sinal = 1;
        }
        else {
            this.sinal = -1;
        }
        fracaoNula = this.numerador == 0;
    }

    /**
     * @return um double com o valor numérico desta fração
     */
    public double getValorNumerico() {
        return (this.sinal * (double)this.numerador / (double)this.denominador);
    }

    /**
     * Retorna uma fração que é equivalente a esta fração (this),
     * e que é irredutível (numerador e denominador primos entre si).
     * Em outras palavras, retorna a fração geratriz desta fração.
     *
     * @return uma fração irredutível equivalente a esta;
     *         no caso desta fração JÁ SER ela própria irredutível, retorna this
     */

    private int MaiorDivisorComum (int numerador, int denominador) {
        if ((numerador % denominador) == 0) {
            return denominador;
        }
        else return MaiorDivisorComum(denominador, numerador % denominador);
    }

    public Fracao getFracaoGeratriz() {
        int MaiorDivisorComum = MaiorDivisorComum(this.numerador, this.denominador);
        if (MaiorDivisorComum == 1) {
            return this;
        }
        return new Fracao(this.numerador / MaiorDivisorComum,
                this.denominador / MaiorDivisorComum,
                this.isPositiva());
    }

    public int getNumerador() {
        return this.numerador;
    }

    public int getDenominador() {
        return this.denominador;
    }

    public int getSinal() {
        return sinal;
    }

    public boolean isPositiva() {
        return !this.fracaoNula && this.positiva;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Fracao fracao = (Fracao) o;                         //typecast transforma o num objeto tipo Fracao
        if (this.fracaoNula && fracao.fracaoNula) {         //verifica se as duas fracoes sao true e, se forem,
            return true;                                    //retorna true
        }
        return this.getFracaoGeratriz().getNumerador() == fracao.getFracaoGeratriz().getNumerador() &&
                this.getFracaoGeratriz().getDenominador() == fracao.getFracaoGeratriz().getDenominador() &&
                this.getFracaoGeratriz().getSinal() == fracao.getFracaoGeratriz().getSinal();
    }

}
