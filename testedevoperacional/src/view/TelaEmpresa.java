package view;

import model.Cliente;
import model.Empresa;
import model.Produto;
import model.Usuario;
import model.Venda;

import java.util.List;
import java.util.Scanner;

public class TelaEmpresa {

    public static void exibirTela(Usuario usuarioLogado, List<Produto> produtos, List<Venda> vendas,
            List<Empresa> empresas, List<Cliente> clientes) {
        Scanner sc = new Scanner(System.in);
        boolean deslogar = false;
        while (!deslogar) {
            System.out.println("1 - Listar vendas");
            System.out.println("2 - Ver produtos");
            System.out.println("0 - Deslogar");
            Integer escolha = sc.nextInt();

            switch (escolha) {
                case 1: {
                    System.out.println();
                    System.out.println(
                            "************************************************************");
                    System.out.println("VENDAS EFETUADAS");
                    vendas.stream().forEach(venda -> {
                        if (venda.getEmpresa().getId().equals(usuarioLogado.getEmpresa().getId())) {
                            System.out.println(
                                    "************************************************************");
                            System.out.println("Venda de código: " + venda.getCódigo() + " no CPF "
                                    + venda.getCliente().getCpf() + ": ");
                            venda.getItens().stream().forEach(x -> {
                                System.out.println(
                                        x.getId() + " - " + x.getNome() + "    R$" + x.getPreco());
                            });
                            System.out.println("Total Venda: R$" + venda.getValor());
                            System.out
                                    .println("Total Taxa a ser paga: R$"
                                            + venda.getComissaoSistema());
                            System.out.println("Total Líquido  para empresa"
                                    + (venda.getValor() - venda.getComissaoSistema()));
                            System.out.println(
                                    "************************************************************");
                        }

                    });
                    System.out.println("Saldo Empresa: " + usuarioLogado.getEmpresa().getSaldo());
                    System.out.println(
                            "************************************************************");
                    break;
                }
                case 2: {
                    System.out.println();
                    System.out.println(
                            "************************************************************");
                    System.out.println("MEUS PRODUTOS");
                    produtos.stream().forEach(produto -> {
                        if (produto.getEmpresa().getId()
                                .equals(usuarioLogado.getEmpresa().getId())) {
                            System.out.println(
                                    "************************************************************");
                            System.out.println("Código: " + produto.getId());
                            System.out.println("Produto: " + produto.getNome());
                            System.out.println("Quantidade em estoque: " + produto.getQuantidade());
                            System.out.println("Valor: R$" + produto.getPreco());
                            System.out.println(
                                    "************************************************************");
                        }

                    });
                    System.out.println("Saldo Empresa: " + usuarioLogado.getEmpresa().getSaldo());
                    System.out.println(
                            "************************************************************");
                    break;
                }
                case 0: {
                    deslogar = true;

                }
            }
        }

    }

    public static void exibirTelaAdmin(Usuario usuarioLogado, List<Produto> produtos, List<Venda> vendas,
            List<Empresa> empresas, List<Cliente> clientes) {
        boolean deslogar = false;
        Scanner sc = new Scanner(System.in);
        while (!deslogar) {
            System.out.println("Qual empresa você quer ver as informações?");
            empresas.forEach(e -> System.out.println(e.getId() + " - " + e.getNome()));
            Integer escolhaEmpresa = sc.nextInt();
            Empresa empresaEscolhida = empresas.stream()
                    .filter(e -> e.getId().equals(escolhaEmpresa))
                    .findFirst().orElse(null);
            if (empresaEscolhida == null) {
                System.out.println("Empresa não encontrada.");
            } else {
                boolean sairEmpresa = false;
                while (!sairEmpresa) {
                    System.out.println(
                            "Escolha uma opção para a empresa " + empresaEscolhida.getNome() + ":");
                    System.out.println("1 - Listar vendas");
                    System.out.println("2 - Ver produtos");
                    System.out.println("3 - Selecionar outra empresa");
                    System.out.println("4 - Deslogar");
                    Integer escolhaOpcao = sc.nextInt();
                    switch (escolhaOpcao) {
                        case 1: {
                            System.out.println(
                                    "");
                            System.out.println(
                                    "VENDAS EFETUADAS PELA EMPRESA " + empresaEscolhida.getNome());
                            vendas.stream()
                                    .filter(v -> v.getEmpresa().getId()
                                            .equals(empresaEscolhida.getId()))
                                    .forEach(venda -> {
                                        System.out.println(
                                                "");
                                        System.out.println(
                                                "Venda de código: " + venda.getCódigo() + " no CPF "
                                                        + venda.getCliente().getCpf() + ": ");
                                        venda.getItens().stream().forEach(x -> {
                                            System.out.println(
                                                    x.getId() + " - " + x.getNome() + " R$"
                                                            + x.getPreco());
                                        });
                                        System.out.println("Total Venda: R$" + venda.getValor());
                                        System.out.println(
                                                "Total Taxa a ser paga: R$"
                                                        + venda.getComissaoSistema());
                                        System.out.println("Total Líquido para empresa: "
                                                + (venda.getValor() - venda.getComissaoSistema()));
                                        System.out.println(
                                                "");
                                    });
                            System.out.println("Saldo Empresa: " + empresaEscolhida.getSaldo());
                            System.out.println(
                                    "");
                            break;
                        }
                        case 2: {
                            System.out.println(
                                    "");
                            System.out.println("PRODUTOS DA EMPRESA " + empresaEscolhida.getNome());
                            produtos.stream()
                                    .filter(p -> p.getEmpresa().getId()
                                            .equals(empresaEscolhida.getId()))
                                    .forEach(p -> {
                                        System.out.println(
                                                p.getId() + " - " + p.getNome() + " R$"
                                                        + p.getPreco());
                                    });
                            System.out.println(
                                    "");
                            break;
                        }
                        case 3:
                            sairEmpresa = true;
                            break;
                        case 4:
                            deslogar = true;
                            sairEmpresa = true;
                            break;
                        default:
                            System.out.println("Opção inválida.");
                    }
                }
            }
        }

    }

}
