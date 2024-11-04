# 👗 Lookbook LeadTech 

**Lookbook LeadTech** é um aplicativo de moda desenvolvido para ajudar usuários a gerenciar seus "lookbooks" (conjuntos de peças de roupa) e obter sugestões automáticas de looks. O objetivo principal é criar uma experiência prática e intuitiva para os usuários armazenarem, organizarem e visualizarem seus looks, bem como obterem sugestões com base em suas preferências.

## 🚀 Objetivo do Projeto

O objetivo principal do LeadTech Mobile é facilitar a organização dos looks e guardar a criatividade dos usuários no seu dia-a-dia, permitindo que eles:

- **Criem e organizem lookbooks**: Coleções de peças de roupas que podem ser combinadas.
- **Visualizem detalhes de cada lookbook**: Permite que o usuário visualize cada item em um lookbook específico.
- **Recebam sugestões automáticas de looks**: Baseadas em suas peças salvas, o sistema oferece sugestões para facilitar a escolha do visual do dia.
- **Cadastro e Autenticação de Usuários**: Os usuários podem criar contas e gerenciar seus dados com segurança.

## 📂 Estrutura de Pastas
```tree
  app
├── manifests
│   └── AndroidManifest.xml
├── kotlin+java
│   └── com.example.leadtech_mobile
│       ├── activity
│       │   └── [Atividades/Telas do Aplicativo]
│       ├── adapter
│       │   └── [Adaptadores para RecyclerView]
│       ├── model
│       │   └── [Modelos de Dados]
│       ├── repository
│       │   └── [Classes de Repositório]
│       ├── viewModel
│           └── [ViewModels para lógica de negócio]
└── res
    ├── drawable
    ├── layout
    |     └── [Layout (estilização) das Páginas]
    ├── menu
    ├── mipmap
    ├── values
    └── xml
```

## Funcionalidades

### 1. **Autenticação de Usuário**
   - **Cadastro de novos usuários**: Permite que o usuário insira seu nome, e-mail e senha para criar uma conta no aplicativo.
   - **Login de usuários registrados**: Usuários registrados podem fazer login com suas credenciais.
   - **Armazenamento de Sessão de Usuário**: Após o login, os dados do usuário são salvos localmente, permitindo que o usuário permaneça conectado na próxima abertura do aplicativo.

### 2. **Dashboard de Lookbooks**
   - **Exibição de lookbooks**: Mostra uma lista de lookbooks criados pelo usuário, com informações breves como o nome.
   - **Adicionar novo lookbook**: Usuários podem criar novos lookbooks fornecendo o nome do lookbook.
   - **Sugestões de looks automáticas**: O sistema fornece sugestões de looks baseados nos lookbooks existentes.

### 3. **Detalhes do Lookbook**
   - **Exibição das peças do lookbook**: Ao selecionar um lookbook, o usuário pode visualizar todas as peças incluídas nele.
   - **Gerenciamento do lookbook**: O usuário pode adicionar ou remover peças do lookbook conforme necessário.

### 4. **Sugestões Automáticas**
   - **Sugestões de looks**: Com base nas roupas já cadastradas, o sistema fornece sugestões automáticas de combinações.

### 5. **Cadastro de Novo Lookbook**
   - **Adicionar lookbooks com peças**: Usuários podem adicionar novos lookbooks ao sistema, fornecendo um nome e adicionando as peças.
   - **Editar lookbooks existentes**: Ao clicar no ícone de edição, um formulário é aberto para que o usuário atualize informações do lookbook. Após salvar, uma mensagem de sucesso ou erro é exibida.
   - **Feedback Visual para Operações CRUD**: O sistema exibe mensagens de sucesso ou erro ao adicionar, atualizar ou excluir lookbooks, e limpa os campos de entrada ao final das   operações bem-sucedidas.

### 6. **Operção CRUD de Peças de Roupa**
  - **Criar nova peça**: Os usuários podem cadastrar novas peças de roupa fornecendo informações como nome, categoria, cor e tamanho.
  - **Ler peças existentes**: O sistema permite que os usuários visualizem todas as peças de roupa cadastradas.
  - **Atualizar peças**: Os usuários podem editar as informações de uma peça de roupa existente.
  - **Remover peças**: Os usuários podem excluir peças de roupa que não desejam mais manter.



## Estrutura do Projeto

### Atividades (Activities)

#### 1. **HomeActivity**
   - **Descrição**: Página inicial que leva o usuário ao login.

#### 2. **LoginActivity**
   - **Descrição**: Tela de login onde os usuários podem inserir suas credenciais.

#### 3. **CadastroActivity**
   - **Descrição**: Tela de cadastro onde novos usuários podem se registrar.

#### 4. **DashboardActivity**
   - **Descrição**: Tela principal do aplicativo após o login.
  
#### 5. **AddLookbookActivity**
   - **Descrição**: Tela para adicionar novos lookbooks ao sistema.

#### 6. **LookbookDetailsActivity**
   - **Descrição**: Tela que exibe os detalhes de um lookbook selecionado.

#### 7. **SuggestionsActivity**
   - **Descrição**: Tela que sugere looks automaticamente para o usuário com base nos lookbooks cadastrados.
     
#### 8. AddPecaRoupaActivity
  - **Descrição**: Tela para adicionar novas peças de roupa ao sistema, permitindo que o usuário insira informações como nome, categoria, cor e tamanho.

#### 9. EditPecaRoupaActivity
  - **Descrição**: Tela para editar informações de uma peça de roupa existente. O usuário pode atualizar os detalhes e salvar as alterações.

#### 10. PecaRoupaDetailsActivity
  - **Descrição**: Tela que exibe os detalhes de uma peça de roupa específica, com opções para editar ou remover a peça.

     

## API e Requisições

O sistema interage principalmente com os dados de lookbooks e usuários por meio de `ViewModels` e a arquitetura `MVVM` (Model-View-ViewModel). Abaixo estão as interações principais:

### **Requisições de Cadastro e Login**
- **Cadastro de Usuário**: O cadastro de usuários é feito através do `UsuarioViewModel`, que envia os dados de usuário para serem armazenados (futuramente no Firebase).
  - **Endpoint (Futuro)**: `/register`
  - **Parâmetros**:
    - `nome`: String (Nome do usuário)
    - `email`: String (E-mail do usuário)
    - `senha`: String (Senha do usuário)
  - **Resposta**: 
    - `200 OK` em caso de sucesso.
    - `400 Bad Request` se os dados forem inválidos.

- **Login de Usuário**: A autenticação de usuários se dá pelo `UsuarioViewModel`, que valida os dados inseridos.
  - **Endpoint (Futuro)**: `/login`
  - **Parâmetros**:
    - `email`: String
    - `senha`: String
  - **Resposta**:
    - `200 OK` em caso de sucesso.
    - `401 Unauthorized` se as credenciais forem inválidas.

### **Requisições de Lookbooks**
- **Listar Lookbooks**: O sistema utiliza o `LookbookViewModel` para carregar todos os lookbooks de um usuário.
  - **Endpoint (Futuro)**: `/lookbooks`
  - **Parâmetros**: 
    - `userId`: String (ID do usuário autenticado)
  - **Resposta**:
    - Lista de lookbooks.

- **Criar Lookbook**: Ao adicionar um lookbook, o `LookbookViewModel` faz uma requisição para armazenar um novo lookbook.
  - **Endpoint (Futuro)**: `/lookbooks/create`
  - **Parâmetros**:
    - `nome`: String (Nome do lookbook)
    - `pecas`: Lista (Lista de peças no lookbook)
  - **Resposta**:
    - `201 Created` em caso de sucesso.

- **Obter Detalhes do Lookbook**: Quando o usuário acessa um lookbook, o sistema carrega as peças associadas.
  - **Endpoint (Futuro)**: `/lookbooks/{lookbookId}`
  - **Parâmetros**:
    - `lookbookId`: String (ID do lookbook)
  - **Resposta**:
    - Detalhes do lookbook, incluindo as peças.
   
### **Requisições de Peças de Roupa**
- **Listar Peças de Roupa**: O sistema utiliza o PecaRoupaViewModel para carregar todas as peças de roupa de um usuário.
  - **Endpoint (Futuro)**: /pecas
  - **Parâmetros**:
    - `userId`: String (ID do usuário autenticado)
  - **Resposta**:
    - Lista de peças de roupa.
      
- **Criar Peça de Roupa**: Ao adicionar uma nova peça, o PecaRoupaViewModel faz uma requisição para armazenar uma nova peça de roupa.
  - **Endpoint (Futuro)**: /pecas/create
  - **Parâmetros**:
    - `nome`: String (Nome da peça)
    - `categoria`: String (Categoria da peça, ex: CAMISA, CALCA)
    - `cor`: String (Cor da peça)
    - `tamanho`: String (Tamanho da peça)
    - `urlImagem`: String (URL da imagem da peça)
  - **Resposta**:
    - 201 Created em caso de sucesso.
  
- **Obter Detalhes da Peça de Roupa**: Quando o usuário acessa uma peça, o sistema carrega os detalhes dessa peça.
  - **Endpoint (Futuro)**: /pecas/{pecaId}
  - **Parâmetros**:
    - `pecaId`: String (ID da peça)
  - **Resposta**:
    - Detalhes da peça, incluindo nome, categoria, cor, tamanho e imagem.
      
- **Atualizar Peça de Roupa**: O PecaRoupaViewModel permite que o usuário atualize os detalhes de uma peça existente.
  - **Endpoint (Futuro)**: /pecas/update/{pecaId}
  - **Parâmetros**:
    - `pecaId`: String (ID da peça)
    - `nome`: String (Novo nome da peça)
    - `categoria`: String (Nova categoria da peça)
    - `cor`: String (Nova cor da peça)
    - `tamanho`: String (Novo tamanho da peça)
    - `urlImagem`: String (Nova URL da imagem da peça)
  - **Resposta**:
    - 200 OK em caso de sucesso.
      
- **Remover Peça de Roupa**: O sistema permite que o usuário remova uma peça de roupa.
  - **Endpoint (Futuro)**: /pecas/delete/{pecaId}
  - **Parâmetros**:
    - `pecaId`: String (ID da peça)
  - **Resposta**:
    - 204 No Content em caso de sucesso.

---

## Requisitos

- **Android Studio** 4.0 ou superior.
- **Gradle** para gerenciamento de dependências.
- **SDK Android** 21 ou superior.

---

## Dependências

Inclua as seguintes dependências no arquivo `build.gradle`:

```gradle
implementation 'androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0'
implementation 'androidx.recyclerview:recyclerview:1.2.0'
implementation 'com.google.android.material:material:1.5.0'

```

---

## Como Executar o Projeto

1. Clone o repositório:

```bash
https://github.com/biancaroman/LeadTech-MobileKotlin.git
```

2. Abra o projeto no Android Studio.

3. Conecte seu dispositivo ou inicie um emulador.

4. Execute o projeto:

  - Clique em Run > Run 'app' ou utilize o atalho Shift + F10.


## 🫂 Integrantes

Aqui estão os membros do grupo que participaram durante desenvolvimento desta SPRINT.

* **RM 552267 - Bianca Leticia Román Caldeira**
  - Turma: 2TDSPH
    
* **RM 552252 – Charlene Aparecida Estevam Mendes Fialho**
  - Turma: 2TDSPH

* **RM 552258 - Laís Alves da Silva Cruz**
  - Turma: 2TDSPH

* **RM 97916 – Fabricio Torres Antonio**
  - Turma: 2TDSPH

* **RM 99675 – Lucca Raphael Pereira dos Santos**
  - Turma: 2TDSPZ

<table>
  <tr>
        <td align="center">
      <a href="https://github.com/biancaroman">
        <img src="https://avatars.githubusercontent.com/u/128830935?v=4" width="100px;" border-radius='50%' alt="Bianca Román's photo on GitHub"/><br>
        <sub>
          <b>Bianca Román</b>
        </sub>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/charlenefialho">
        <img src="https://avatars.githubusercontent.com/u/94643076?v=4" width="100px;" border-radius='50%' alt="Charlene Aparecida's photo on GitHub"/><br>
        <sub>
          <b>Charlene Aparecida</b>
        </sub>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/laiscrz">
        <img src="https://avatars.githubusercontent.com/u/133046134?v=4" width="100px;" alt="Lais Alves's photo on GitHub"/><br>
        <sub>
          <b>Lais Alves</b>
        </sub>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/LuccaRaphael">
        <img src="https://avatars.githubusercontent.com/u/127765063?v=4" width="100px;" border-radius='50%' alt="Lucca Raphael's photo on GitHub"/><br>
        <sub>
          <b>Lucca Raphael</b>
        </sub>
      </a>
    </td>
     <td align="center">
      <a href="https://github.com/Fabs0602">
        <img src="https://avatars.githubusercontent.com/u/111320639?v=4" width="100px;" border-radius='50%' alt="Fabricio Torres's photo on GitHub"/><br>
        <sub>
          <b>Fabricio Torres</b>
        </sub>
      </a>
    </td>
  </tr>
</table>


