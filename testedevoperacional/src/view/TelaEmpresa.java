package view;

import model.Cliente;
import model.Empresa;
import model.Produto;
import model.Usuario;
import model.Venda;

import java.util.List;
import java.util.Scanner;

import controller.VendasController;

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
                                        VendasController.listarVendas(usuarioLogado.getEmpresa(), vendas);
                                        break;
                                }

                                case 2: {
                                        System.out.println();
                                        VendasController.listarProdutos(usuarioLogado.getEmpresa(), produtos);
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
                                                        "Escolha uma opção para a empresa " + empresaEscolhida.getNome()
                                                                        + ":");
                                        System.out.println("1 - Listar vendas");
                                        System.out.println("2 - Ver produtos");
                                        System.out.println("3 - Selecionar outra empresa");
                                        System.out.println("4 - Deslogar");
                                        Integer escolhaOpcao = sc.nextInt();
                                        switch (escolhaOpcao) {
                                                case 1: {
                                                        System.out.println();
                                                        System.out.println("EMPRESA "
                                                                        + empresaEscolhida.getNome());
                                                        VendasController.listarVendas(empresaEscolhida, vendas);
                                                        System.out.println();
                                                        break;
                                                }

                                                case 2: {
                                                        System.out.println("EMPRESA "
                                                                        + empresaEscolhida.getNome());
                                                        VendasController.listarProdutos(empresaEscolhida, produtos);
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
