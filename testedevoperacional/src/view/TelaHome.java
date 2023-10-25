package view;

import java.util.List;
import java.util.Scanner;

import controller.LoginController;
import model.Cliente;

public class TelaHome {
    private static Scanner sc = new Scanner(System.in);

    public static void exibirTelaHome(LoginController loginController) {
        Scanner sc = new Scanner(System.in);
        boolean sairDoSistema = false;

        while (!sairDoSistema) {
            System.out.println("Bem-vindo ao sistema de compras.");
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Fazer login");
            System.out.println("2 - Sair do sistema");

            int opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    TelaLogin.exibirTela(loginController);
                    break;
                case 2:
                    loginController.sairDoSistema();
                    sairDoSistema = true;
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }

}
