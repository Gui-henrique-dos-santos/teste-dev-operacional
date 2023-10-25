package controller;

import java.util.List;

import model.Cliente;
import model.Empresa;
import model.Produto;
import model.Usuario;
import model.Venda;

public class VendasController {

    private Cliente clienteLogado;
        public void setClienteLogado(Cliente cliente) {
        this.clienteLogado = cliente;
    }

    public static void listarVendas(Empresa empresa, List<Venda> vendas) {
        System.out.println("************************************************************");
        System.out.println("VENDAS EFETUADAS");
        vendas.stream().filter(venda -> venda.getEmpresa().getId().equals(empresa.getId())).forEach(venda -> {
            System.out.println("************************************************************");
            System.out
                    .println("Venda de código: " + venda.getCódigo() + " no CPF " + venda.getCliente().getCpf() + ": ");
            venda.getItens().forEach(item -> {
                System.out.println(item.getId() + " - " + item.getNome() + "    R$" + item.getPreco());
            });
            System.out.println("Total Venda: R$" + venda.getValor());
            System.out.println("Total Taxa a ser paga: R$" + venda.getComissaoSistema());
            System.out.println("Total Líquido para empresa: " + (venda.getValor() - venda.getComissaoSistema()));
            System.out.println("************************************************************");
        });
        System.out.println("Saldo Empresa: " + empresa.getSaldo());
        System.out.println("************************************************************");
    }

    public static void listarProdutos(Empresa empresa, List<Produto> produtos) {
        System.out.println("************************************************************");
        System.out.println("MEUS PRODUTOS");
        produtos.stream().filter(produto -> produto.getEmpresa().getId().equals(empresa.getId())).forEach(produto -> {
            System.out.println("************************************************************");
            System.out.println("Código: " + produto.getId());
            System.out.println("Produto: " + produto.getNome());
            System.out.println("Quantidade em estoque: " + produto.getQuantidade());
            System.out.println("Valor: R$" + produto.getPreco());
            System.out.println("************************************************************");
        });
        System.out.println("Saldo Empresa: " + empresa.getSaldo());
        System.out.println("************************************************************");
    }

    public static Venda criarVenda(List<Produto> carrinho, Empresa empresa, Cliente cliente, List<Venda> vendas) {
        Double total = carrinho.stream().mapToDouble(Produto::getPreco).sum();
        Double comissaoSistema = total * empresa.getTaxa();
        int idVenda = vendas.isEmpty() ? 1 : vendas.get(vendas.size() - 1).getCódigo() + 1;
        Venda venda = new Venda(idVenda, carrinho.stream().toList(), total, comissaoSistema, empresa, cliente);
        empresa.setSaldo(empresa.getSaldo() + total);
        vendas.add(venda);

        // Descontar a taxa do saldo da empresa
        empresa.setSaldo(empresa.getSaldo() - comissaoSistema);

        // Remover a quantidade de produtos vendidos do estoque
        for (Produto produto : carrinho) {
            int novaQuantidade = produto.getQuantidade() - 1;
            produto.setQuantidade(novaQuantidade);
        }

        return venda;
    }

    public static void listarComprasCliente(Usuario usuario, List<Venda> vendas) {
        System.out.println("************************************************************");
        System.out.println("COMPRAS EFETUADAS PELO CLIENTE " + usuario.getUsername());

        vendas.stream()
                .filter(venda -> venda.getCliente() != null
                        && venda.getCliente().getUsername().equals(usuario.getUsername()))
                .forEach(venda -> {
                    System.out.println("************************************************************");
                    System.out.println("Compra de código: " + venda.getCódigo() + " na empresa "
                            + venda.getEmpresa().getNome() + ": ");
                    venda.getItens().forEach(item -> {
                        System.out.println(item.getId() + " - " + item.getNome() + "    R$" + item.getPreco());
                    });
                    System.out.println("Total: R$" + venda.getValor());
                    System.out.println("************************************************************");
                });

        System.out.println("************************************************************");
    }
}
