# 🏥 Sistema de Gerenciamento de Fila de Pacientes em uma UPA

![Java](https://img.shields.io/badge/Java-17+-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-3.8+-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)
![JUnit5](https://img.shields.io/badge/JUnit5-Testing-25A162?style=for-the-badge&logo=junit5&logoColor=white)

**Disciplina:** Programação Orientada a Objetos — UniCEUB
**Professor:** Prof. Romes Heriberto
**Turma:** [turma]  |  **Data de entrega:** [dd/mm/aaaa]

---

## 👥 Integrantes da Equipe

| Nome Completo | Matrícula | Principais Contribuições |
| :--- | :--- | :--- |
| Rafael [@Rafael](https://github.com/RafaelRLeite) | 72501342 | Estruturas de dados (`FilaPrioridade`, `Pilha`), Algoritmos (`Ordenacao.mergeSort`), Testes unitários |
| Matheus [@Matheus](https://github.com/matheusbrito090108) | 72650414 | Modelagem de domínio (`Paciente`, `Atendimento`), Algoritmo de Busca Binária recursiva, Regras de negócio |
| Fernando [@Fernando](https://github.com/fernandoluca015) | 72650534 | Triagem Manchester (`ClassificadorManchesterSimplificado`), Validação matemática de CPF, Exceções personalizadas |
| Italo [@Italo](https://github.com/Italo-917) | 72650588 | Interface CLI (`MenuCli`), Camada de Serviço (`UpaService`), Padrão Observer (`PainelChamada`), Documentação e empacotamento |

---

## 📖 Descrição da Aplicação

Este projeto é um **Sistema de Controle de Fluxo de Atendimento para Unidades de Pronto Atendimento (UPAs)** executado via Interface de Linha de Comando (CLI). O sistema resolve o problema das filas baseadas estritamente em ordem de chegada, implementando o **Protocolo de Manchester Simplificado** para classificar pacientes de acordo com a gravidade dos sinais vitais.

O software foi desenvolvido com forte foco em boas práticas de Engenharia de Software, aplicando os pilares da Programação Orientada a Objetos (POO), princípios SOLID, Design Patterns e a implementação manual (from scratch) de Estruturas de Dados e Algoritmos de ordenação e busca.

### ⚙️ Funcionalidades Implementadas

- [X] RF01 — Cadastro de paciente com validação de CPF e geração de senha
- [X] RF02 — Triagem com classificação de risco (5 cores)
- [X] RF03 — Chamada do próximo paciente por prioridade, com painel de chamada
- [X] RF04 — Visualização das filas de triagem e de atendimento
- [X] RF05 — Busca de paciente por CPF e por senha (busca binária)
- [X] RF06 — Relatórios: últimos atendimentos (pilha) e ordenado por espera (merge sort)
- [X] RF07 — Estatísticas do plantão
- [X] Bônus: 

---

## 🧩 Conceitos de POO Aplicados

A tabela abaixo detalha onde cada conceito fundamental de POO, estruturas de dados e boas práticas foi aplicado no código-fonte:

| Conceito | Classe(s) Principal(is) | Método / Trecho Relevante | Explicação / Justificativa |
| :--- | :--- | :--- | :--- |
| **Encapsulamento** | `Paciente`, `Pessoa`, `SinaisVitais` | Getters/Setters e regras em `registrarTriagem()`, validações no construtor de `SinaisVitais` | Atributos privados e métodos públicos protegendo o estado dos objetos contra modificações inconsistentes. |
| **Herança** | `Pessoa` $\rightarrow$ `Profissional` $\rightarrow$ `Medico`, `Enfermeiro`; `Pessoa` $\rightarrow$ `Paciente` | `extends Pessoa`, `extends Profissional` | Reutilização de atributos base (`nome`, `cpf`, `dataNascimento`) e especialização de comportamentos específicos. |
| **Classe Abstrata** | `Pessoa`, `Profissional` | `public abstract String getIdentificacao();` | Classes que não podem ser instanciadas diretamente e definem contratos comuns para subclasses concretas. |
| **Interface** | `Colecao<T>`, `ClassificadorRisco`, `ObservadorChamada`, `PacienteRepository` | `enfileirar()`, `desenfileirar()`, `classificar()`, `notificarChamada()`, `salvar()` | Desacoplamento entre contratos e implementações concretas, permitindo extensibilidade e inversão de dependência. |
| **Polimorfismo** | `Paciente`, `Medico`, `Enfermeiro`, `PainelChamada` | `@Override public String getIdentificacao()`, `@Override public void notificarChamada(...)` | Sobrescrita de métodos com comportamentos específicos conforme a instância em tempo de execução. |
| **Composição** | `Paciente`, `Triagem`, `Atendimento` | `Paciente` contém `Triagem`; `Triagem` contém `SinaisVitais`, `Enfermeiro`, `Prioridade`; `Atendimento` contém `Paciente`, `Medico` | Modelagem "tem-um" estruturando os relacionamentos clínicos essenciais do sistema. |
| **Exceções Personalizadas** | `UpaException`, `CpfInvalidoException`, `FilaVaziaException`, `PacienteNaoEncontradoException`, `PacienteDuplicadoException`, `SinaisVitaisInvalidosException`, `MedicoNaoInformadoException` | `throw new CpfInvalidoException(...)`, `throw new MedicoNaoInformadoException()` | Hierarquia de exceções de domínio checadas/não checadas derivadas de `UpaException`, garantindo clareza e robustez. |
| **Recursão** | `Busca`, `Ordenacao` | `Busca.buscaBinariaRecursiva(...)`, `Ordenacao.mergeSort(...)` | Algoritmos de divisão e conquista implementados de forma recursiva sem laços iterativos diretos. |
| **Estruturas de Dados** | `No<T>`, `Fila<T>`, `FilaPrioridade<T>`, `Pilha<T>` | `enfileirar()`, `desenfileirar()`, `empilhar()`, `desempilhar()` | Estruturas de dados dinâmicas encadeadas implementadas do zero (*from scratch*) sem usar `java.util.Queue` ou `Stack`. |
| **Padrões de Projeto** | `ClassificadorManchesterSimplificado`, `PainelChamada`, `PacienteRepositoryMemoria` | `ClassificadorRisco` (Strategy), `ObservadorChamada` (Observer), `PacienteRepository` (Repository) | Padrões de projeto clássicos aplicados para desacoplamento de algoritmos clínicos, notificações de chamada e persistência. |
| **Princípios SOLID** | `ValidadorCpf`, `UpaService`, `Colecao<T>`, `ClassificadorRisco` | Métodos focados em SRP, OCP, LSP, ISP e DIP em toda a camada de serviço e utilitários | **SRP:** `ValidadorCpf` e `UpaService` com responsabilidades únicas; **OCP/DIP:** `UpaService` depende apenas de interfaces. |

---

## 🛠️ Tecnologias e Requisitos

* **Linguagem:** Java 17 ou superior (verifique com `java -version` e `javac -version`).
* **Gerenciador de Dependências:** Maven 3.8+ (opcional — só necessário para rodar os testes e gerar o `.jar`).
* **Testes:** JUnit 5.
* **Interface:** Terminal (CLI) — Sem dependências de frameworks gráficos.

---

## 🎯 Etapas de Desenvolvimento

O desenvolvimento deste sistema foi estruturado de forma sequencial, conforme o escopo do projeto. As tarefas podem ser divididas e acompanhadas pela equipe marcando as caixas abaixo:

- [x] **ETAPA 0 — Preparar o ambiente e conhecer o projeto:** Instalação do JDK 17+, configuração da IDE, execução do esqueleto inicial com Maven e divisão das tarefas entre o grupo.
- [x] **ETAPA 1 — Validar CPF (util/ValidadorCpf.isValido()):** Implementação do algoritmo matemático de validação dos dígitos verificadores do CPF.
- [x] **ETAPA 2 — Estudar as classes prontas:** Leitura e compreensão do código fornecido, focando em herança, composição e na classe genérica `Fila<T>` (FIFO).
- [x] **ETAPA 3 — Completar o modelo (herança, encapsulamento e polimorfismo):** Codificação do interior das classes `Enfermeiro`, `Medico`, `Paciente` e `Atendimento`, implementando métodos como `getIdentificacao()` e formatação de senhas.
- [x] **ETAPA 4 — Classificação de risco, ordem da fila e estruturas de dados:** Implementação do `ClassificadorManchesterSimplificado` com a cadeia de regras clínicas, ordenação `compareTo()` do Paciente, e construção das estruturas `FilaPrioridade<T>` e `Pilha<T>`.
- [x] **ETAPA 5 — Exceções, validações e algoritmos recursivos:** Codificação de exceções customizadas (`PacienteNaoEncontradoException`, `MedicoNaoInformadoException`), regras de `SinaisVitais`, e implementação dos algoritmos recursivos `Busca.buscaBinariaRecursiva()` e `Ordenacao.mergeSort()`.
- [x] **ETAPA 6 — Regras de negócio (servico/UpaService):** Orquestração do sistema integrando o cadastro, triagem, chamadas médicas e geração de relatórios estatísticos, respeitando o Princípio de Responsabilidade Única (SRP) sem uso de saídas no console.
- [x] **ETAPA 7 — Interface de linha de comando (cli/MenuCli):** Desenvolvimento das interações e telas do terminal (opções 2, 3, 5, 6 e 7), garantindo a robustez do programa contra entradas inválidas de dados.
- [x] **ETAPA 8 — Escrever os testes do grupo (mínimo 8 novos):** Criação de novos testes unitários com JUnit 5 (padrão Arrange-Act-Assert) para atingir os requisitos de qualidade e complementar os testes fornecidos.
- [x] **ETAPA 9 — Documentar e empacotar:** Preenchimento completo do `README.md`, exclusão de pastas de compilação (`target/`, `.class`), validação final em pasta limpa e empacotamento do projeto em `.zip`.

---

## 🖼️ Arquitetura e Representações Visuais

### Estrutura de Diretórios do Projeto

```text
upa-fila/
├── pom.xml                                   # Dependências (JUnit 5) e configuração de build Maven
├── README.md                                 # Documentação completa da sistematização
├── executar.bat / executar.sh                # Scripts para compilar e rodar via terminal
└── src/
    ├── main/java/br/ceub/poo/upa/
    │   ├── App.java                          # Ponto de entrada (main): monta injeções e inicia o menu
    │   ├── cli/
    │   │   ├── Console.java                  # Utilitário de leitura segura do teclado
    │   │   └── MenuCli.java                  # Interface textual de interação com o usuário
    │   ├── modelo/
    │   │   ├── Pessoa.java                   # Classe abstrata base
    │   │   ├── Profissional.java             # Especialização abstrata de Pessoa
    │   │   ├── Medico.java                   # Profissional com consultório
    │   │   ├── Enfermeiro.java               # Profissional responsável pela triagem
    │   │   ├── Paciente.java                 # Entidade do paciente (Comparable por prioridade e chegada)
    │   │   ├── Triagem.java                  # Registro clínico e classificação de risco
    │   │   ├── SinaisVitais.java             # Dados vitais aferidos e suas validações
    │   │   ├── Prioridade.java               # Enum com as cores e tempos do Protocolo de Manchester
    │   │   ├── StatusPaciente.java           # Enum de estados do paciente no fluxo
    │   │   └── Atendimento.java              # Registro do atendimento médico concluído
    │   ├── triagem/
    │   │   ├── ClassificadorRisco.java       # Interface (Strategy)
    │   │   └── ClassificadorManchesterSimplificado.java # Implementação das regras clínicas
    │   ├── estrutura/
    │   │   ├── No.java                       # Elemento nó das estruturas encadeadas
    │   │   ├── Colecao.java                  # Interface genérica de coleção
    │   │   ├── Fila.java                     # Fila FIFO encadeada
    │   │   ├── FilaPrioridade.java           # Fila de prioridade com inserção ordenada
    │   │   └── Pilha.java                    # Pilha LIFO encadeada
    │   ├── algoritmo/
    │   │   ├── Busca.java                    # Busca linear e Busca Binária recursiva
    │   │   └── Ordenacao.java                # Insertion sort e Merge Sort recursivo
    │   ├── servico/
    │   │   └── UpaService.java               # Camada de negócio e orquestração do fluxo da UPA
    │   ├── repositorio/
    │   │   ├── PacienteRepository.java       # Interface de repositório
    │   │   └── PacienteRepositoryMemoria.java# Implementação em memória com Array dinâmico
    │   ├── observador/
    │   │   ├── ObservadorChamada.java        # Interface do padrão Observer
    │   │   └── PainelChamada.java            # Painel observador que exibe chamadas
    │   ├── excecao/
    │   │   ├── UpaException.java             # Exceção base do sistema
    │   │   ├── CpfInvalidoException.java
    │   │   ├── FilaVaziaException.java
    │   │   ├── MedicoNaoInformadoException.java # Criada pelo grupo para validação de chamada
    │   │   ├── PacienteDuplicadoException.java
    │   │   ├── PacienteNaoEncontradoException.java
    │   │   └── SinaisVitaisInvalidosException.java
    │   └── util/
    │       ├── GeradorSenha.java             # Gerador de senhas sequenciais
    │       └── ValidadorCpf.java             # Validação matemática dos dígitos do CPF
    └── test/java/br/ceub/poo/upa/            # Suíte com 47 testes unitários (JUnit 5)
```

> **Observação sobre classes criadas pelo grupo além do esqueleto:**
> Além de implementar todos os métodos e classes marcados como `TODO` no esqueleto (`Paciente`, `Medico`, `Enfermeiro`, `Atendimento`, `ValidadorCpf`, `ClassificadorManchesterSimplificado`, `FilaPrioridade`, `Pilha`, `Busca` binária, `Ordenacao` merge sort, `UpaService` e `MenuCli`), o grupo criou a classe de exceção personalizada **`MedicoNaoInformadoException`** (`br.ceub.poo.upa.excecao.MedicoNaoInformadoException`) para garantir consistência no disparo de chamadas médicas sem médico associado, além de testes adicionais para cobertura de casos de borda.

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

---

## 📦 Instalação e Configuração Local

### 1. Obtenção do Código-Fonte

**Via Git:**
```bash
git clone https://github.com/Sistematizacao-Ceub/sistema-gerenciamento-fila-pacientes-upa.git
cd sistema-gerenciamento-fila-pacientes-upa
```

**Via Arquivo Compactado (.zip):**
1. Descompacte o arquivo `.zip` em uma pasta de sua preferência.
2. Abra o terminal nessa pasta:
```bash
cd sistema-gerenciamento-fila-pacientes-upa
```

### 2. Importação na IDE (IntelliJ IDEA)

1. Abra o **IntelliJ IDEA**.
2. Clique em **File** $\rightarrow$ **Open...** (ou *Open* na tela de boas-vindas).
3. Navegue até a pasta do projeto e selecione o arquivo **`pom.xml`** (ou a pasta raiz do projeto) e clique em **OK**.
4. Selecione a opção **Open as Project**.
5. Verifique a versão do JDK configurada:
   * Vá em **File** $\rightarrow$ **Project Structure** $\rightarrow$ **Project**.
   * Em **SDK**, selecione **Java 17** (ou superior).
   * Em **Language level**, selecione **17 - Sealed types, always-strict floating-point, etc.**.
6. Aguarde o IntelliJ sincronizar as dependências do Maven (o painel lateral do Maven mostrará as tarefas e o JUnit 5 disponível).

---

## ▶️ Como Executar

**Opção A — Sem Maven (Apenas JDK instalado):**

```bash
# No Windows (prompt de comando ou PowerShell):
executar.bat

# No Linux ou macOS:
chmod +x executar.sh
./executar.sh
```

**Opção B — Diretamente com Maven:**

```bash
mvn compile exec:java
```

**Opção C — Gerando e executando o arquivo .jar:**

```bash
mvn package
java -jar target/upa-fila-1.0.jar
```

---

## 🧪 Como Rodar os Testes

Para executar toda a suíte de testes unitários automatizados com o Maven:

```bash
mvn test
```

### Resultado Obtido pela Equipe

```text
[INFO] Results:
[INFO] 
[INFO] Tests run: 47, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] BUILD SUCCESS
```

* **Total de testes:** 47 testes
* **Passando:** 47
* **Falhando:** 0
* **Ignorados/Pulados:** 0

*(31 testes originais fornecidos pela disciplina + 16 testes adicionais implementados pelo grupo para validação de casos limites e robustez).*

---

## 🎬 Roteiro de Demonstração

Para validar rapidamente as principais regras de negócio do sistema:

1. **Opção 1:** Cadastre a paciente `"Ana"`, CPF `529.982.247-25`.
2. **Opção 1:** Cadastre o paciente `"Bruno"`, CPF `111.444.777-35`.
3. **Opção 2:** Realize a triagem da **Ana** com sinais vitais normais $\rightarrow$ Classificação **AZUL**.
4. **Opção 2:** Realize a triagem do **Bruno** com saturação $\text{SpO}_2 = 85\%$ $\rightarrow$ Classificação **VERMELHO** (Emergência).
5. **Opção 4:** Visualize a fila de atendimento. Note que **Bruno aparece na frente de Ana**, mesmo tendo chegado depois, devido à gravidade clínica.
6. **Opção 3:** Chame o próximo paciente. O painel exibirá a chamada prioritária de Bruno com indicação do médico e consultório.
7. **Opção 5 e 6:** Realize a busca por CPF/Senha e consulte os relatórios de atendimento e tempos de espera.

---

## ⚠️ Limitações Conhecidas

1. **Persistência Volátil em Memória:** Todos os cadastros, históricos e atendimentos são mantidos exclusivamente em memória durante a execução. Ao finalizar o programa, os dados são reiniciados (ausência de banco de dados relacional persistente).
2. **Interface Exclusivamente em Linha de Comando (CLI):** O sistema opera via terminal texto, não possuindo interface gráfica para os pacientes ou painel eletrônico web/televisivo.
3. **Reclassificação Dinâmica de Risco:** A prioridade do paciente é estabelecida no momento da triagem clínica; o sistema não possui mecanismo de decaimento ou aumento dinâmico automático de prioridade por tempo excessivo de espera além dos relatórios gerados.
4. **Concorrência:** O software foi projetado para execução em ambiente monothread no terminal, sem suporte a múltiplos operadores simultâneos concorrentes via rede.

---

## 🤖 Declaração de Uso de Ferramentas de IA

Durante o desenvolvimento deste trabalho, o grupo utilizou ferramentas de Inteligência Artificial Generativa (Google Gemini e ChatGPT) com propósitos estritamente auxiliares e pedagógicos, atuando nas seguintes frentes:

* **Tira-dúvidas conceituais:** Consulta sobre particularidades de sintaxe da linguagem Java 17, padrões de projeto (*Strategy* e *Observer*) e funcionamento de algoritmos recursivos (*Merge Sort* e Busca Binária).
* **Geração de cenários de teste:** Apoio na ideação de casos de borda para testes unitários com JUnit 5 (como sinais vitais limítrofes e listas de pacientes com prioridades idênticas para validação do critério de desempate).
* **Revisão e formatação da documentação:** Estruturação visual e diagramação deste arquivo `README.md` no padrão Markdown e Mermaid.

> **Garantia de Autoria:** Todo o código-fonte gerado, arquitetura das classes, resolução dos algoritmos e testes passaram por análise crítica, validação e adaptação manual por todos os integrantes do grupo, assegurando total domínio sobre a solução entregue.
