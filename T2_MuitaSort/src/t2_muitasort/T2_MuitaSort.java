package t2_muitasort;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author julio
 */
public class T2_MuitaSort {

    public static void main(String[] args) throws IOException {
        String arquivo = "vetor.txt"; //Nome do arquivo .txt
        int[] vetor = leitura(arquivo);
        ordena(vetor);
    }

    //Função para a leitura do arquivo em .txt
    public static int[] leitura(String arquivo) {

        File file = new File(arquivo); //local do arquivo .txt
        Scanner teclado = null;
        String[] result = null;
        try {
            teclado = new Scanner(file); //leitura do arquivo .txt
            String line = null;

            while (teclado.hasNextInt()) {
                line = teclado.nextLine();
                result = line.split(" ");
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (teclado != null) {
                teclado.close();
            }
        }
        int vetor[] = new int[result.length];
        for (int i = 0; i < result.length; i++) {
            vetor[i] = Integer.parseInt(result[i]);
        }
        return vetor;
    }

    private static int[] ordena(int[] vetor) {
        Scanner teclado = new Scanner(System.in);
        System.out.print("Valor do módulo: ");
        int modulo = teclado.nextInt(); //Solicito o valor do módulo ao usuário
        while (modulo == 0) {
            System.out.println("Ops! Valor inválido.");
            System.out.print("Valor do módulo: ");
            modulo = teclado.nextInt();
        }
        
        int aux = 0; //Variável auxiliar para receber os valores durante as trocas
        
        //Laços que irão percorrer o vetor
        for (int i = 0; i < vetor.length; i++) {
            for (int j = 0; j < vetor.length - 1; j++) {

                //Ordeno os módulos em ordem crescente
                if (vetor[j] % modulo > vetor[j + 1] % modulo) {
                    ordenarModulos(vetor, aux, j);
                }

                //Regras de ordenação
                if (vetor[j] % modulo == vetor[j + 1] % modulo) { //Verifico se os módulos são iguais e aplico as regras

                    //Empate par: menor número par irá preceder o maior número par
                    if (vetor[i] % 2 == 0 && vetor[j] % 2 == 0) {  //Verifico se ambos são pares
                        if (vetor[j] > vetor[j + 1]) {
                            troca(vetor, j);
                        }
                    }

                    //Empate ímpar: maior número ímpar irá preceder o menor número ímpar.
                    if (vetor[j] % 2 != 0 && vetor[j + 1] % 2 != 0) { //Verifico se ambos são pares
                        if (vetor[j + 1] > vetor[j]) {
                            troca(vetor, j);
                        }

                        //Empate ímpar e par: então o número ímpar irá preceder o número par.
                    } else {
                        if (vetor[j + 1] % 2 != 0) {
                            troca(vetor, j);
                        }
                    }
                }
            }
        }
        for (int i = 0; i < vetor.length; i++) {
            System.out.print("[" + vetor[i] + "] ");
        }
        return vetor;
    }

    //Invertendo as posições de acordo com a regra
    private static void troca(int[] vetor, int j) {
        int aux = vetor[j + 1];
        vetor[j + 1] = vetor[j];
        vetor[j] = aux;
    }
    
    //Ordenando os módulo em ordem crescente (0,1,2,3...)
    private static void ordenarModulos(int[] vetor, int aux, int j) {
        aux = vetor[j];
        vetor[j] = vetor[j + 1];
        vetor[j + 1] = aux;
    }
}
