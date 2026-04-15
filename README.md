# TP-01-AED-s-3
Este, repositório serve para servir de suporte para nosso projeto 1 de Aeds 3 com o professor Marcos Kutova

Link do Video Da Plataforma no Youtube: [TP01-AEDS 3](https://youtu.be/K8iDMP5FKEc) 


# Relatório TP01 - Algoritmos e Estruturas de Dados III

## Participantes:
* **Davi Rafael de Oliveira Gurgel Martins**
* **Pedro Augusto Gomes de Araújo**

---

## 1. Descrição do Sistema
O projeto consiste em uma plataforma de gestão de cursos livres, onde alunos da PUC Minas podem atuar tanto como instrutores quanto como alunos. Nesta primeira etapa (TP1), o foco do sistema é:

* **Gerenciamento de Usuários:** Autenticação segura via e-mail e senha.
* **Gerenciamento de Cursos:** Vinculados a um proprietário.
* **Persistência:** Uso de arquivos binários com indexação avançada para garantir alta performance.

---

## 2. Estrutura de Dados e Persistência
Para garantir a eficiência das buscas, o sistema utiliza as seguintes estruturas:

* **Tabela Hash Extensível:** Utilizada para a indexação direta do e-mail do usuário, permitindo login rápido.
* **Árvore B+ (Relacionamento):** Implementada para gerenciar o relacionamento **1:N**, vinculando o ID de um usuário aos IDs de seus respectivos cursos.
* **Árvore B+ (Ordenação):** Utilizada como índice indireto para organizar os cursos por nome (ordem alfabética).

---

## 3. Implementação das Classes
Seguimos o padrão de camadas para manter a organização do código:

* **Visão (View):** `VisaoUsuario.java` e `VisaoCurso.java` (Interação via terminal).
* **Controle (Controller):** `ControleUsuario.java` e `ControleCurso.java` (Lógica de negócios e validações).
* **Modelo (Model):** Entidades que estendem a interface para CRUD genérico, utilizando `toByteArray()` e `fromByteArray()`.

---

## 4. Operações Especiais
* **Segurança:** As senhas são armazenadas utilizando o método `hashCode()` da classe String, evitando texto plano.
* **Identificadores:** Uso de **NanoID** (10 caracteres) para compartilhamento externo dos cursos.
* **Integridade:** Trava de segurança que impede a exclusão de usuários que possuam cursos ativos.

---

## 5. Checklist de Requisitos

- [x] **CRUD de usuários funcional?** Sim, integrado a Hash e Árvore B+.
- [x] **CRUD de cursos funcional?** Sim, com suporte a busca e ordenação.
- [x] **Vínculo entre entidades?** Sim, cursos utilizam `idUsuario` como chave estrangeira.
- [x] **Relacionamento 1:N?** Sim, implementado via Árvore B+.
- [x] **Herança de ArquivoIndexado?** Sim, segue a estrutura genérica solicitada.
- [x] **Compilação?** Sim, projeto compila sem erros.
- [x] **Execução?** Sim, testado e funcionando sem erros.
- [x] **Originalidade?** Sim, desenvolvido integralmente pelo grupo.

---

## 6. Capturas de Tela

### Tela de Cadastro
![Cadastro do Usuário](img/Cadastro.png)

### Tela de Login
![Login do Usuário](img/login.png)

### Tela de Dados
![Dados de Login](img/dadoslogin.png)

### Tela de Curso
![Interface de Cursos](img/login.png)

---

