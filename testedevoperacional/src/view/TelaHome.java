package view;

import java.util.Scanner;

import controller.LoginController;

public class TelaHome {
    private static Scanner sc = new Scanner(System.in);
    public static void exibirTelaHome(LoginController loginController) {
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
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }}
