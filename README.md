<div align="center">

# 🏪 StoreCore

## Gerenciamento de Pessoas para Lojas (MVP)

Sistema simples para **gerenciar colaboradores e clientes**, com **controle de permissões por cargo**.  
Interface em **Java Swing** e dados persistidos em **PostgreSQL**.

</div>

---

## ✨ Funcionalidades

### 👥 Colaboradores

- Adicionar, editar e remover funcionários
- 🔒 **Apenas CEO pode alterar**

### 👤 Clientes

- Adicionar, editar e remover clientes
- 🔓 **CEO e Manager podem alterar**

### 🛡️ Controle de

- **CEO:** acesso total
- **Manager:** gerencia clientes
- **Worker:** apenas visualização

---

## 🧱 Arquitetura (simples e organizada)

```dir
src/
 ├─ ui/              → Telas (Swing)
 ├─ service/         → Regras e permissões
 ├─ database/        → Conexão, DAOs e inicialização
 ├─ models/          → Entidades (Customer, CEO, Manager, Worker)
 ├─ abstract_classes/
 └─ App.java         → Ponto de entrada

sql/
 └─ schema.sql       → Criação das tabelas

lib/
 └─ postgresql-*.jar → Driver JDBC
```

**Fluxo:**  
Usuário → Interface → Service (regras) → DAO (SQL) → PostgreSQL → Interface → Usuário

---

## 🧩 Tecnologias

- **Java Swing (`javax.swing`)** — Interface gráfica
- **JDBC (`java.sql`)** — Conexão com banco
- **PostgreSQL** — Persistência de dados
- **Collections (`java.util`)** — Listas e estruturas de dados

---

## 📥 Instalação do PostgreSQL

### 🪟 Windows (PowerShell)

```powershell
winget install -e --id PostgreSQL.PostgreSQL
```

### 🐧 Linux / WSL (Ubuntu)

```bash
sudo apt update
sudo apt install postgresql postgresql-contrib
sudo service postgresql start
```

---

## 🗄️ Criar banco e usuário (uma única vez)

Acesse o PostgreSQL como administrador:

### Linux / WSL

```bash
sudo -u postgres psql
```

### Windows

```powershell
psql -U postgres
```

Execute:

```sql
CREATE USER storecore_user WITH PASSWORD '123';
CREATE DATABASE storecore_db OWNER storecore_user;
GRANT ALL PRIVILEGES ON DATABASE storecore_db TO storecore_user;
\q
```

---

## 🔌 Driver JDBC

Baixe o driver do PostgreSQL e coloque em:

```dir
lib/postgresql-<versão>.jar
```

---

## ▶️ Como executar pelo terminal

Na raiz do projeto:

### Linux / wsl

```bash
mkdir -p bin
javac -cp "lib/*" -d bin $(find src -name "*.java")
java -cp "bin:lib/*" App
```

### Windows (PowerShell)

```powershell
mkdir bin -ErrorAction SilentlyContinue
javac -cp "lib/*" -d bin (Get-ChildItem -Recurse -Filter *.java src | % FullName)
java -cp "bin;lib/*" App
```

---

## 🔑 Login Inicial

Na primeira execução, o sistema cria um usuário padrão:

- **CPF:** `111`
- **Senha:** `admin`

---

## 🧑‍💻 Como Usar

1. Execute o programa
2. Faça login
3. No menu:
   - **CEO:** gerencia colaboradores e clientes
   - **Manager:** gerencia clientes
   - **Worker:** apenas visualiza
4. Para **editar**:
   - selecione uma linha
   - altere os campos
   - clique em **Editar**

---

## ⚡ Exemplo Rápido

##**Login**

```
CPF: 111
Senha: admin
```

1. Vá em **Gerenciar Clientes**
2. Clique em **Adicionar** e preencha:
   - Nome: Maria
   - CPF: 123
   - Email: maria@email.com
   - Telefone: 9999-0000
3. Selecione Maria, altere o telefone e clique **Editar**

✔ Dados salvos no banco.

---

## 🏁 Conclusão

O **StoreCore** é um MVP enxuto, funcional e organizado para:

- ✔ Gerenciar clientes
- ✔ Controlar colaboradores
- ✔ Aplicar permissões por cargo

**Simples. Direto. Profissional.** 😄
