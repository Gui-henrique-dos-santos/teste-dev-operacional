package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import controller.VendasController;
import model.Cliente;
import model.Empresa;
import model.Produto;
import model.Usuario;
import model.Venda;

public class TelaVenda {
    public static void telaVenda(Usuario usuarioLogado, List<Produto> produtos, List<Venda> vendas,
            List<Empresa> empresas, List<Cliente> clientes) {
        Scanner sc = new Scanner(System.in);
        boolean deslogar = false;
        List<Produto> carrinho = new ArrayList<>();

        while (!deslogar) {
            System.out.println("1 - Realizar Compras");
            System.out.println("2 - Ver Compras");
            System.out.println("0 - Deslogar");
            Integer escolha = sc.nextInt();

            switch (escolha) {
                case 1: {
                    System.out.println("Para realizar uma compra, escolha a empresa onde deseja comprar: ");
                    empresas.forEach(e -> {
                        System.out.println(e.getId() + " - " + e.getNome());
                    });
                    Integer escolhaEmpresa = sc.nextInt();
                    Integer escolhaProduto;

                    do {
                        System.out.println("Escolha os seus produtos: ");
                        produtos.forEach(p -> {
                            if (p.getEmpresa().getId().equals(escolhaEmpresa)) {
                                System.out.println(p.getId() + " - " + p.getNome());
                            }
                        });
                        System.out.println("0 - Finalizar compra");
                        escolhaProduto = sc.nextInt();

                        if (escolhaProduto != 0) {
                            final Integer escolhaProdutoFinal = escolhaProduto;
                            Optional<Produto> produtoOptional = produtos.stream()
                                    .filter(p -> p.getId().equals(escolhaProdutoFinal)
                                            && p.getEmpresa().getId().equals(escolhaEmpresa))
                                    .findFirst();

                            if (produtoOptional.isPresent()) {
                                carrinho.add(produtoOptional.get());
                            } else {
                                System.out.println("Produto não encontrado.");
                            }
                        }
                    } while (escolhaProduto != 0);

                    System.out.println("************************************************************");
                    System.out.println("Resumo da compra: ");
                    carrinho.forEach(x -> {
                        System.out.println(x.getId() + " - " + x.getNome() + "    R$" + x.getPreco());
                    });
                    Optional<Empresa> empresaOptional = empresas.stream()
                            .filter(e -> e.getId().equals(escolhaEmpresa))
                            .findFirst();
                    Optional<Cliente> clienteOptional = clientes.stream()
                            .filter(c -> c.getUsername().equals(usuarioLogado.getUsername()))
                            .findFirst();

                    if (empresaOptional.isPresent() && clienteOptional.isPresent()) {
                        VendasController.realizarCompra(carrinho, empresaOptional.get(), clienteOptional.get(), vendas);
                        System.out.println("************************************************************");
                    } else {
                        System.out.println("Empresa ou cliente não encontrados.");
                    }

                    carrinho.clear();
                    break;
                }

                case 2: {
                    VendasController.listarComprasCliente(usuarioLogado, vendas);
                    break;
                }
                case 0: {
                    deslogar = true;
                    break;
                }
                default: {
                    System.out.println("Opção inválida.");
                }
            }
        }
    }
}
