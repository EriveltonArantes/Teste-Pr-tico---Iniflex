/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.desafio2.novoDesafio;

/**
 *
 * @author Erivelton
 */
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Principal {
    public static void main(String[] args) {
        List<Funcionario> funcionarios = new ArrayList<>();

        // 3.1 - Inserir todos os funcionários
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 1, 1), new BigDecimal("5000.00"), "Desenvolvedor"));
        funcionarios.add(new Funcionario("Maria", LocalDate.of(1992, 2, 2), new BigDecimal("6000.00"), "Gerente"));
        funcionarios.add(new Funcionario("José", LocalDate.of(1995, 3, 3), new BigDecimal("7000.00"), "Desenvolvedor"));
        funcionarios.add(new Funcionario("Ana", LocalDate.of(1998, 4, 4), new BigDecimal("8000.00"), "Gerente"));

        // 3.2 - Remover o funcionário "João" da lista
        funcionarios.removeIf(f -> f.getNome().equals("João"));

        // 3.3 - Imprimir todos os funcionários com todas suas informações
        for (Funcionario f : funcionarios) {
            System.out.println("Nome: " + f.getNome());
            System.out.println("Data Nascimento: " + f.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            System.out.println("Salário: " + f.getSalario().toString().replace('.', ','));
            System.out.println("Função: " + f.getFuncao());
            System.out.println();
        }

        // 3.4 - Os funcionários receberam 10% de aumento de salário
        for (Funcionario f : funcionarios) {
            f.setSalario(f.getSalario().multiply(new BigDecimal("1.10")));
        }

        // 3.5 - Agrupar os funcionários por função em um MAP
        Map<String, List<Funcionario>> funcionariosPorFuncao = new HashMap<>();
        for (Funcionario f : funcionarios) {
            if (!funcionariosPorFuncao.containsKey(f.getFuncao())) {
                funcionariosPorFuncao.put(f.getFuncao(), new ArrayList<>());
            }
            funcionariosPorFuncao.get(f.getFuncao()).add(f);
        }

        // 3.6 - Imprimir os funcionários, agrupados por função
        for (Map.Entry<String, List<Funcionario>> entry : funcionariosPorFuncao.entrySet()) {
            System.out.println("Função: " + entry.getKey());
            for (Funcionario f : entry.getValue()) {
                System.out.println("  Nome: " + f.getNome());
                System.out.println("  Data Nascimento: " + f.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                System.out.println("  Salário: " + f.getSalario().toString().replace('.', ','));
                System.out.println();
            }
        }

        // 3.8 - Imprimir os funcionários que fazem aniversário no mês 10 e 12
        for (Funcionario f : funcionarios) {
            if (f.getDataNascimento().getMonthValue() == 10 || f.getDataNascimento().getMonthValue() == 12) {
                System.out.println("Nome: " + f.getNome());
                System.out.println("Data Nascimento: " + f.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                System.out.println();
            }
        }

        // 3.9 - Imprimir o funcionário com a maior idade
        Funcionario funcionarioMaisVelho = funcionarios.stream()
                .max((f1, f2) -> f1.getDataNascimento().compareTo(f2.getDataNascimento()))
                .orElseThrow();
        System.out.println("Funcionário mais velho: " + funcionarioMaisVelho.getNome());
        System.out.println("Idade: " + funcionarioMaisVelho.getDataNascimento().until(LocalDate.now()).getYears());
        System.out.println();

        // 3.10 - Imprimir a lista de funcionários por ordem alfabética
        funcionarios.sort((f1, f2) -> f1.getNome().compareTo(f2.getNome()));
        for (Funcionario f : funcionarios) {
            System.out.println("Nome: " + f.getNome());
            System.out.println("Data Nascimento: " + f.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            System.out.println("Salário: " + f.getSalario().toString().replace('.', ','));
            System.out.println("Função: " + f.getFuncao());
            System.out.println();
        }

        // 3.11 - Imprimir o total dos salários dos funcionários
        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("Total dos salários: " + totalSalarios.toString().replace('.', ','));

        // 3.12 - Imprimir quantos salários mínimos ganha cada funcionário
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        for (Funcionario f : funcionarios) {
            BigDecimal quantosSalariosMinimos = f.getSalario().divide(salarioMinimo, 2, BigDecimal.ROUND_HALF_UP);
            System.out.println("Funcionário: " + f.getNome());
            System.out.println("Ganha " + quantosSalariosMinimos + " salários mínimos");
            System.out.println();
        }
    }
}
