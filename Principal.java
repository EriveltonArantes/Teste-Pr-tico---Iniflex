public class Principal {
    public static void main(String[] args) {
        // 3.1 - Inserir todos os funcionários
        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 15), new BigDecimal("2000.00"), "Analista"));
        funcionarios.add(new Funcionario("Maria", LocalDate.of(1985, 8, 20), new BigDecimal("2500.00"), "Gerente"));
        funcionarios.add(new Funcionario("Pedro", LocalDate.of(1987, 10, 25), new BigDecimal("1800.00"), "Analista"));

        // 3.2 - Remover o funcionário "João" da lista
        funcionarios.removeIf(funcionario -> funcionario.getNome().equals("João"));

        // 3.3 - Imprimir todos os funcionários com todas suas informações
        System.out.println("Funcionários:");
        for (Funcionario funcionario : funcionarios) {
            System.out.println("Nome: " + funcionario.getNome());
            System.out.println("Data de Nascimento: " + funcionario.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            System.out.println("Salário: " + funcionario.getSalario().toString().replace(".", ","));
            System.out.println("Função: " + funcionario.getFuncao());
            System.out.println();
        }

        // 3.4 - Aumentar salários em 10%
        for (Funcionario funcionario : funcionarios) {
            BigDecimal novoSalario = funcionario.getSalario().multiply(BigDecimal.valueOf(1.1));
            funcionario.salario = novoSalario.setScale(2, BigDecimal.ROUND_HALF_UP);
        }

        // 3.5 - Agrupar funcionários por função
        Map<String, List<Funcionario>> funcionariosPorFuncao = new HashMap<>();
        for (Funcionario funcionario : funcionarios) {
            funcionariosPorFuncao.computeIfAbsent(funcionario.getFuncao(), k -> new ArrayList<>()).add(funcionario);
        }

        // 3.6 - Imprimir os funcionários agrupados por função
        System.out.println("\nFuncionários por função:");
        for (Map.Entry<String, List<Funcionario>> entry : funcionariosPorFuncao.entrySet()) {
            System.out.println(entry.getKey() + ":");
            for (Funcionario funcionario : entry.getValue()) {
                System.out.println("- " + funcionario.getNome());
            }
            System.out.println();
        }

        // 3.8 - Imprimir funcionários que fazem aniversário em outubro (mês 10) e dezembro (mês 12)
        System.out.println("\nFuncionários que fazem aniversário em outubro e dezembro:");
        for (Funcionario funcionario : funcionarios) {
            int mesAniversario = funcionario.getDataNascimento().getMonthValue();
            if (mesAniversario == 10 || mesAniversario == 12) {
                System.out.println("- " + funcionario.getNome() + " (Aniversário em " + mesAniversario + ")");
            }
        }

        // 3.9 - Imprimir o funcionário com a maior idade
        System.out.println("\nFuncionário mais velho:");
        Funcionario maisVelho = Collections.max(funcionarios, Comparator.comparing(Funcionario::getDataNascimento));
        int idadeMaisVelho = LocalDate.now().getYear() - maisVelho.getDataNascimento().getYear();
        System.out.println("- Nome: " + maisVelho.getNome());
        System.out.println("- Idade: " + idadeMaisVelho);

        // 3.10 - Imprimir a lista de funcionários por ordem alfabética
        funcionarios.sort(Comparator.comparing(Funcionario::getNome));
        System.out.println("\nFuncionários em ordem alfabética:");
        for (Funcionario funcionario : funcionarios) {
            System.out.println("- " + funcionario.getNome());
        }

        // 3.11 - Imprimir o total dos salários dos funcionários
        BigDecimal totalSalarios = BigDecimal.ZERO;
        for (Funcionario funcionario : funcionarios) {
            totalSalarios = totalSalarios.add(funcionario.getSalario());
        }
        System.out.println("\nTotal dos salários dos funcionários: " + totalSalarios.toString().replace(".", ","));

        // 3.12 - Imprimir quantos salários mínimos ganha cada funcionário
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        System.out.println("\nSalários mínimos ganhos por cada funcionário:");
        for (Funcionario funcionario : funcionarios) {
            BigDecimal salariosMinimos = funcionario.getSalario().divide(salarioMinimo, 2, BigDecimal.ROUND_DOWN);
            System.out.println("- " + funcionario.getNome() + ": " + salariosMinimos);
        }
    }
}



