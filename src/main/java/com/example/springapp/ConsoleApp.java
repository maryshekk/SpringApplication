package com.example.springapp;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class ConsoleApp {
    public static void main(String[] args)
    {
        UserInterface console = new UserInterface();
        console.printAllCommands();
        String cmd = "";
        Scanner in = new Scanner(System.in);
        try
        {
            while (!cmd.equals("exit"))
            {
                System.out.println("enter command number: ");
                    cmd = in.nextLine();
                    if (!cmd.equals("exit")) {
                        console.getCommand(Long.parseLong(cmd));
                }
            }
        } catch (ResponseStatusException | HttpClientErrorException | HttpServerErrorException ex) {
            String result = ex.getMessage();
            result = Objects.requireNonNull(result).substring(result.indexOf("\"message\":\""), result.indexOf(",\"path\""));
            System.err.println(result);
        } catch (InputMismatchException | NumberFormatException ex) {
            System.err.println("Wrong input format");
        } catch (BadCredentialsException ex){
            System.err.println("Authentication fail");
        }
    }
}
