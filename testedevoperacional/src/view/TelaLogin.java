package view;

import java.util.Scanner;
import controller.LoginController;

public class TelaLogin {
    private static Scanner sc = new Scanner(System.in);

    public static void exibirTela(LoginController loginController) {
        System.out.println("faça o login.");
        System.out.print("Usuário: ");
        String username = sc.next();
        System.out.print("Senha: ");
        String senha = sc.next();

        loginController.realizarLogin(username, senha);
    }
}
