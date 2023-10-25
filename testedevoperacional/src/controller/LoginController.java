package controller;

import java.util.List;
import model.Cliente;
import model.Empresa;
import model.Produto;
import model.Usuario;
import model.Venda;
import view.TelaEmpresa;
import view.TelaLogin;
import view.TelaVenda;

public class LoginController {
    private List<Usuario> usuarios;
    private List<Empresa> empresas;
    private List<Produto> produtos;
    private List<Venda> vendas;
    private VendasController controladorPrincipal;
    private List<Cliente> clientes;

    public LoginController(List<Usuario> usuarios, List<Empresa> empresas, List<Produto> produtos, List<Venda> vendas,
            VendasController vendasController, List<Cliente> clientes) {
        this.usuarios = usuarios;
        this.empresas = empresas;
        this.produtos = produtos;
        this.vendas = vendas;
        this.controladorPrincipal = vendasController;
        this.clientes = clientes;
    }

    public void iniciar() {
        TelaLogin.exibirTela(this);
    }

    public void setControladorPrincipal(VendasController controladorPrincipal) {
        this.controladorPrincipal = controladorPrincipal;
    }

    public void realizarLogin(String username, String senha) {
        Usuario usuarioLogado = validarUsuario(username, senha);

        if (usuarioLogado != null) {
            if (usuarioLogado.IsAdmin()) {
                // Redirecionar para a tela de admin da empresa
                TelaEmpresa.exibirTelaAdmin(usuarioLogado, produtos, vendas, empresas, null);
            } else if (usuarioLogado.IsEmpresa()) {
                // Redirecionar para a tela de empresa comum
                TelaEmpresa.exibirTela(usuarioLogado, produtos, vendas, empresas, null);
            } else if (usuarioLogado.IsCliente()) {
                // Lógica para cliente
                Cliente clienteLogado = usuarioLogado.getCliente();
                if (clienteLogado != null) {
                    controladorPrincipal.setClienteLogado(clienteLogado);
                    TelaVenda.telaVenda(usuarioLogado, produtos, vendas, empresas, clientes);
                } else {
                    System.out.println("Erro: Cliente não encontrado.");
                }
            }
        } else {
            System.out.println("Erro: Senha incorreta ou usuário não encontrado.");
        }
    }

    private Usuario validarUsuario(String username, String senha) {
        for (Usuario usuario : usuarios) {
            if (usuario.getUsername() != null && usuario.getUsername().equals(username) && usuario.getSenha() != null
                    && usuario.getSenha().equals(senha)) {
                return usuario;
            }
        }
        return null;
    }

    public void sairDoSistema() {
        System.exit(0);
    }
}
