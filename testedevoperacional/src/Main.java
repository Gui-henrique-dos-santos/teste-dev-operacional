import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import controller.LoginController;
import controller.VendasController;
import model.Cliente;
import model.Empresa;
import model.Produto;
import model.Usuario;
import model.Venda;
import view.TelaHome;

public class Main {

    public static void main(String[] args) {
        // SIMULANDO BANCO DE DADOS

        List<Produto> carrinho = new ArrayList<Produto>();
        List<Venda> vendas = new ArrayList<Venda>();
        List<Usuario> usuarios = new ArrayList<Usuario>();
        VendasController vendasController = new VendasController();

        Empresa empresa = new Empresa(2, "SafeWay Padaria", "30021423000159", 0.15, 0.0);
        Empresa empresa2 = new Empresa(1, "Level Varejo", "53239160000154", 0.05, 0.0);
        Empresa empresa3 = new Empresa(3, "SafeWay Restaurante", "41361511000116", 0.20, 0.0);

        Produto produto = new Produto(1, "Pão Frances", 5, 3.50, empresa);
        Produto produto2 = new Produto(2, "Coturno", 10, 400.0, empresa2);
        Produto produto3 = new Produto(3, "Jaqueta Jeans", 15, 150.0, empresa2);
        Produto produto4 = new Produto(4, "Calça Sarja", 15, 150.0, empresa2);
        Produto produto5 = new Produto(5, "Prato feito - Frango", 10, 25.0, empresa3);
        Produto produto6 = new Produto(6, "Prato feito - Carne", 10, 25.0, empresa3);
        Produto produto7 = new Produto(7, "Suco Natural", 30, 10.0, empresa3);
        Produto produto8 = new Produto(8, "Sonho", 5, 8.50, empresa);
        Produto produto9 = new Produto(9, "Croissant", 7, 6.50, empresa);
        Produto produto10 = new Produto(10, "Ché Gelado", 4, 5.50, empresa);

        Cliente cliente = new Cliente("07221134049", "Allan da Silva", "cliente", 20);
        Cliente cliente2 = new Cliente("72840700050", "Samuel da Silva", "cliente2", 24);

        Usuario usuario1 = new Usuario("admin", "1234", null, null);
        Usuario usuario2 = new Usuario("empresa", "1234", null, empresa);
        Usuario usuario3 = new Usuario("cliente", "1234", cliente, null);
        Usuario usuario4 = new Usuario("cliente2", "1234", cliente2, null);
        Usuario usuario5 = new Usuario("empresa2", "1234", null, empresa2);
        Usuario usuario6 = new Usuario("empresa3", "1234", null, empresa3);

        usuarios.add(usuario1);
        usuarios.add(usuario2);
        usuarios.add(usuario3);
        usuarios.add(usuario4);
        usuarios.add(usuario5);
        usuarios.add(usuario6);
        List<Cliente> clientes = Arrays.asList(cliente, cliente2);
        List<Empresa> empresas = Arrays.asList(empresa, empresa2, empresa3);
        List<Produto> produtos = Arrays.asList(produto, produto2, produto3, produto4, produto5, produto6, produto7,
                produto8, produto9, produto10);

        executarTelas(usuarios, clientes, empresas, produtos, carrinho, vendas, vendasController);
    }

    public static void executarTelas(List<Usuario> usuarios, List<Cliente> clientes, List<Empresa> empresas,
            List<Produto> produtos, List<Produto> carrinho, List<Venda> vendas, VendasController vendasController) {
        
        TelaHome telaHome = new TelaHome();
        telaHome.exibirTelaHome(new LoginController(usuarios, empresas, produtos, vendas, vendasController, clientes));
    }
}
