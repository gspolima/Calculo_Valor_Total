package com.estacio.programacaoII;

import java.text.MessageFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Produto smartphone = new Produto("Xiaomi Redmi Note 8", 1500);
        exibirDetalhesProduto(smartphone);
        boolean entradaCorreta = false;

        while (entradaCorreta == false) {
            exibirFormasDePagamento();

            try {
                Scanner scanner = new Scanner(System.in);
                int numeroDaForma = scanner.nextInt();
                FormasDePagamento formaEscolhida = FormasDePagamento.values()[numeroDaForma];

                if (FormasDePagamento.values()[numeroDaForma].ordinal() != -1) {

                    while (entradaCorreta == false) {
                        exibirCondicoesDePagamento();

                        try {
                            Scanner scanner2 = new Scanner(System.in);
                            int numeroDaCondicao = scanner2.nextInt();
                            CondicoesDePagamento condicaoEscolhida = CondicoesDePagamento.values()[numeroDaCondicao];
                            if (CondicoesDePagamento.values()[numeroDaCondicao].ordinal() != -1) {
                                if (
                                        (formaEscolhida == FormasDePagamento.DINHEIRO
                                            || formaEscolhida == FormasDePagamento.PIX)
                                        &&
                                        (condicaoEscolhida == CondicoesDePagamento._2x
                                            || condicaoEscolhida == CondicoesDePagamento._3x)) {
                                    System.out.println("A VISTA somente para DINHEIRO e PIX");
                                    continue;
                                }

                                double valorTotal = 0;

                                if (formaEscolhida == FormasDePagamento.DINHEIRO
                                        || formaEscolhida == FormasDePagamento.PIX) {
                                    valorTotal = smartphone.getPrecoDeEtiqueta() * 0.9;
                                    System.out.print("Total a pagar, com 10% de desconto: ");
                                }
                                if (formaEscolhida == FormasDePagamento.CARTAO_CREDITO
                                        && condicaoEscolhida == CondicoesDePagamento.A_VISTA) {
                                    valorTotal = smartphone.getPrecoDeEtiqueta() * 0.95;
                                    System.out.print("Total a pagar, com 5% de desconto: ");
                                }
                                if (formaEscolhida == FormasDePagamento.CARTAO_CREDITO
                                        && condicaoEscolhida == CondicoesDePagamento._2x) {
                                    valorTotal = smartphone.getPrecoDeEtiqueta();
                                    System.out.print("Total a pagar, sem juros ou desconto: ");
                                }
                                if (formaEscolhida == FormasDePagamento.CARTAO_CREDITO
                                        && condicaoEscolhida == CondicoesDePagamento._3x) {
                                    valorTotal = smartphone.getPrecoDeEtiqueta() * 1.10;
                                    System.out.print("Total a pagar, com 10% de juros: ");
                                }
                                String valorFormatado = String.format("%.2f", valorTotal);
                                System.out.print("R$" + valorFormatado);
                                return;
                            }
                        }
                        catch (InputMismatchException exception) {
                            System.out.println("*** Entre apenas com um número. Digite novamente! ***");
                            entradaCorreta = false;
                            continue;
                        }
                        catch (IndexOutOfBoundsException exception) {
                            System.out.println("*** A opção selecionada não existe. Escolha novamente! ***");
                            entradaCorreta = false;
                            continue;
                        }
                    }
                }
            }
            catch (InputMismatchException exception) {
                System.out.println("*** Entre apenas com um número. Digite novamente! ***");
                entradaCorreta = false;
                continue;
            }
            catch (IndexOutOfBoundsException exception) {
                System.out.println("*** A opção selecionada não existe. Escolha novamente! ***");
                entradaCorreta = false;
                continue;
            }
        }
    }

    private static void exibirDetalhesProduto(Produto produto) {
        System.out.printf("Produto: %s", produto.getNome());
        System.out.println();
        System.out.printf("Preço: %f", produto.getPrecoDeEtiqueta());
        System.out.println();
        System.out.println("----------");
    }

    private static void exibirCondicoesDePagamento() {
        for (CondicoesDePagamento condicao : CondicoesDePagamento.values()) {
            System.out.println(MessageFormat.format("{0} - {1}", condicao.ordinal(), condicao));
        }
        System.out.println("Escolha uma condição de pagamento");
    }

    private static void exibirFormasDePagamento() {
        for (FormasDePagamento forma : FormasDePagamento.values()) {
            System.out.println(MessageFormat.format("{0} - {1}", forma.ordinal(), forma));
        }
        System.out.println("Escolha uma forma de pagamento:");
    }
}

