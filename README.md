# 🏥 Sistema de Gerenciamento de Fila de Pacientes em uma UPA

![Java](https://img.shields.io/badge/Java-17+-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-3.8+-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)
![JUnit5](https://img.shields.io/badge/JUnit5-Testing-25A162?style=for-the-badge&logo=junit5&logoColor=white)

## 👥 Integrantes da Equipe
| Nome Completo | Matrícula |
| :--- | :--- |
| Rafael | [@Rafael](https://github.com/RafaelRLeite) |
| Matheus | [@Matheus](https://github.com/matheusbrito090108) |
| Fernando | [@Fernando](https://github.com/fernandoluca015) |
| Italo | [@Italo](https://github.com/Italo-917) |

---

## 📖 Descrição da Aplicação
Este projeto é um **Sistema de Controle de Fluxo de Atendimento para Unidades de Pronto Atendimento (UPAs)** executado via Interface de Linha de Comando (CLI). O sistema resolve o problema das filas baseadas estritamente em ordem de chegada, implementando o **Protocolo de Manchester Simplificado** para classificar pacientes de acordo com a gravidade dos sinais vitais. 

O software foi desenvolvido com forte foco em boas práticas de Engenharia de Software, aplicando os pilares da Programação Orientada a Objetos (POO), princípios SOLID, Design Patterns e a implementação manual (from scratch) de Estruturas de Dados e Algoritmos de ordenação e busca.

### ⚙️ Funcionalidades Implementadas
* **[RF01] Cadastro de Paciente:** Validação real de CPF (dígitos verificadores) e geração sequencial de senhas (Ex: P001).
* **[RF02] Triagem Clínica:** Leitura de sinais vitais, validação de regras de negócio e classificação de risco (Vermelho, Laranja, Amarelo, Verde, Azul).
* **[RF03] Painel de Chamada:** Atendimento baseado em prioridade clínica e ordem de chegada.
* **[RF04] Visualização de Filas:** Exibição da fila de triagem (FIFO) e da fila de atendimento (Priority Queue).
* **[RF05] Busca de Pacientes:** Localização O(log n) por senha utilizando Busca Binária.
* **[RF06] Relatórios Históricos:** Pilha de últimos atendimentos e listagem por tempo de espera via Merge Sort.
* **[RF07] Estatísticas do Plantão:** Dashboard CLI com contagem por cores e tempo médio de espera.

---

## 🛠️ Tecnologias e Requisitos
* **Linguagem:** Java 17 ou superior.
* **Gerenciador de Dependências:** Maven.
* **Testes:** JUnit 5.
* **Interface:** Terminal (CLI) — Sem dependências de frameworks gráficos.

---

## 🎯 Etapas de Desenvolvimento

O desenvolvimento deste sistema foi estruturado de forma sequencial, conforme o escopo do projeto[cite: 3]. As tarefas podem ser divididas e acompanhadas pela equipe marcando as caixas abaixo:

- [ ] **ETAPA 0 — Preparar o ambiente e conhecer o projeto:** Instalação do JDK 17+, configuração da IDE, execução do esqueleto inicial com Maven e divisão das tarefas entre o grupo.
- [ ] **ETAPA 1 — Validar CPF (util/ValidadorCpf.isValido()):** Implementação do algoritmo matemático de validação dos dígitos verificadores do CPF.
- [ ] **ETAPA 2 — Estudar as classes prontas:** Leitura e compreensão do código fornecido, focando em herança, composição e na classe genérica `Fila<T>` (FIFO).
- [ ] **ETAPA 3 — Completar o modelo (herança, encapsulamento e polimorfismo):** Codificação do interior das classes `Enfermeiro`, `Medico`, `Paciente` e `Atendimento`, implementando métodos como `getIdentificacao()` e formatação de senhas.
- [ ] **ETAPA 4 — Classificação de risco, ordem da fila e estruturas de dados:** Implementação do `ClassificadorManchesterSimplificado` com a cadeia de regras clínicas, ordenação `compareTo()` do Paciente, e construção das estruturas `FilaPrioridade<T>` e `Pilha<T>`.
- [ ] **ETAPA 5 — Exceções, validações e algoritmos recursivos:** Codificação de exceções customizadas (`PacienteNaoEncontradoException`), regras de `SinaisVitais`, e implementação dos algoritmos recursivos `Busca.buscaBinariaRecursiva()` e `Ordenacao.mergeSort()`.
- [ ] **ETAPA 6 — Regras de negócio (servico/UpaService):** Orquestração do sistema integrando o cadastro, triagem, chamadas médicas e geração de relatórios estatísticos, respeitando o Princípio de Responsabilidade Única (SRP) sem uso de saídas no console.
- [ ] **ETAPA 7 — Interface de linha de comando (cli/MenuCli):** Desenvolvimento das interações e telas do terminal (opções 2, 3, 5, 6 e 7), garantindo a robustez do programa contra entradas inválidas de dados.
- [ ] **ETAPA 8 — Escrever os testes do grupo (mínimo 8 novos):** Criação de novos testes unitários com JUnit 5 (padrão Arrange-Act-Assert) para atingir os requisitos de qualidade e complementar os 33 testes já fornecidos.
- [ ] **ETAPA 9 — Documentar e empacotar:** Preenchimento do `README.md`, exclusão de pastas de compilação (`target/`, `.class`), validação final em pasta limpa e empacotamento do projeto em `.zip`.

---

## 🖼️ Arquitetura e Representações Visuais

### Fluxo do Paciente
```text
  upa-fila/
  ├── pom.xml ← dependências (JUnit 5) e build Maven
  ├── README.md ← MODELO: preencham (obrigatório)
  ├── executar.bat / executar.sh ← compila e roda SEM Maven
  └── src/
    ├── main/java/br/ceub/poo/upa/
    │ ├── App.java PRONTO monta as dependências e inicia o menu
    │ ├── cli/ Console PRONTO leitura segura do teclado
    │ │ MenuCli PARCIAL opções 1 e 4 prontas; 2,3,5,6,7 TODO
    │ ├── modelo/ Pessoa, Profissional PRONTO (modelos de referência)
    │ │ Prioridade, StatusPaciente, Triagem PRONTO
    │ │ Paciente, Medico, Enfermeiro, Atendimento TODO
    │ │ SinaisVitais PARCIAL (validações)
    │ ├── triagem/ ClassificadorRisco PRONTO (interface)
    │ │ ClassificadorManchesterSimplificado TODO
    │ ├── estrutura/ No, Colecao, Fila PRONTO (Fila é o MODELO)
    │ │ FilaPrioridade, Pilha TODO
    │ ├── algoritmo/ Busca, Ordenacao PARCIAL (sequencial e insertion prontos)
    │ ├── servico/ UpaService PARCIAL
    │ ├── repositorio/ PacienteRepository(+Memoria) PRONTO
    │ ├── observador/ ObservadorChamada, PainelChamada PRONTO
    │ ├── excecao/ 6 exceções 5 PRONTAS, 1 TODO
    │ └── util/ GeradorSenha PRONTO; ValidadorCpf TODO
    └── test/java/br/ceub/poo/upa/ 33 testes (31 do professor + 2 exemplos)
```

### Diagrama de Classes (Simplificado)
```text
                    «abstract» Pessoa
                    - nome, cpf, dataNascimento
                    + getIdentificacao() «abstract»
                    + getIdade()
                        △                      △
          ┌─────────────┘                      └───────────────┐
      Paciente  ──implements──▷ Comparable<Paciente>   «abstract» Profissional
      - senha, horarioChegada, status                  - registroConselho
      ◆ triagem : Triagem                                △             △
      + compareTo(), registrarTriagem()              Enfermeiro      Medico
                                                                   - consultorio
      Triagem      ◆── SinaisVitais, Enfermeiro, «enum» Prioridade
      Atendimento  ◆── Paciente, Medico

  «interface» ClassificadorRisco ◁┈┈ ClassificadorManchesterSimplificado   (Strategy)
  «interface» PacienteRepository ◁┈┈ PacienteRepositoryMemoria             (Repository)
  «interface» ObservadorChamada  ◁┈┈ PainelChamada                         (Observer)
  «interface» Colecao<T>         ◁┈┈ Fila<T>, FilaPrioridade<T>, Pilha<T>

  MenuCli ──usa──► UpaService ──usa──► ClassificadorRisco, PacienteRepository,
                                       Fila, FilaPrioridade, Pilha, ObservadorChamada
```

## 🚀 Como Executar o Projeto Localmente

### 1. Clonar o Repositório
```bash
git clone [https://github.com/Sistematizacao-Ceub/sistema-gerenciamento-fila-pacientes-upa.git](https://github.com/Sistematizacao-Ceub/sistema-gerenciamento-fila-pacientes-upa.git)
cd sistema-gerenciamento-fila-pacientes-upa
