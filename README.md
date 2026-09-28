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

## 🚀 Como Executar o Projeto Localmente

### 1. Clonar o Repositório
```bash
git clone [https://github.com/Sistematizacao-Ceub/sistema-gerenciamento-fila-pacientes-upa.git](https://github.com/Sistematizacao-Ceub/sistema-gerenciamento-fila-pacientes-upa.git)
cd sistema-gerenciamento-fila-pacientes-upa
