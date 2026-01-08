🏪 StoreCore (MVP – Gerenciamento de Pessoas)

Sistema simples para gerenciar clientes e colaboradores de uma loja, com permissões por cargo (CEO / Manager / Worker).
Interface gráfica feita em Java Swing e dados salvos em PostgreSQL.

---

🧱 Arquitetura (bem direta)
Pastas principais
src/
├─ ui/ (telas Swing: LoginFrame, MenuFrame, CustomerFrame, CollaboratorFrame)
├─ service/ (regras e permissões: UserService)
├─ database/ (conexão + DAOs + inicialização do banco)
├─ models/ (classes: Customer, CEO, Manager, Worker…)
├─ abstract_classes/
└─ App.java (ponto de entrada)
sql/
└─ schema.sql (criação das tabelas)
lib/
└─ postgresql-\*.jar (driver JDBC do Postgres)

Fluxo de uso (como o sistema roda)

Usuário → UI (Swing) → UserService (permissões) → DAO (SQL) → PostgreSQL → volta pra UI → Usuário

---

📚 Bibliotecas usadas

Java Swing (javax.swing): interface gráfica

JDBC (java.sql): conexão e comandos SQL

PostgreSQL: banco de dados

Collections (java.util): principalmente List e ArrayList para listar clientes/colaboradores

---

✅ Instalação do PostgreSQL
🪟 Windows (PowerShell)

Opção mais simples (recomendada): instalar via winget:

winget install -e --id PostgreSQL.PostgreSQL

Depois, abra o pgAdmin (vem junto) ou o SQL Shell (psql) para criar o banco.

Se o winget não funcionar, instale pelo site oficial do PostgreSQL (installer) e siga o assistente.

🐧 Linux (Ubuntu / WSL)
sudo apt update
sudo apt install postgresql postgresql-contrib
sudo service postgresql start

---

🗄️ Criar banco e usuário (uma vez só)

Abra o terminal e entre no PostgreSQL como admin.

Linux/WSL:
sudo -u postgres psql

Windows (se tiver psql no PATH):
psql -U postgres

Agora rode estes comandos SQL:

CREATE USER storecore_user WITH PASSWORD '123';
CREATE DATABASE storecore_db OWNER storecore_user;
GRANT ALL PRIVILEGES ON DATABASE storecore_db TO storecore_user;
\q

---

🔌 Driver JDBC do Postgres (obrigatório)

Coloque o arquivo do driver JDBC aqui:

lib/postgresql-<versão>.jar

(Esse .jar é o que permite o Java conversar com o PostgreSQL.)

---

▶️ Como iniciar o programa pelo terminal

Na raiz do projeto (onde tem src/, sql/, lib/):

Linux / WSL
mkdir -p bin
javac -cp "lib/_" -d bin $(find src -name "_.java")
java -cp "bin:lib/\*" App

Windows (PowerShell)
mkdir bin -ErrorAction SilentlyContinue
javac -cp "lib/_" -d bin (Get-ChildItem -Recurse -Filter _.java src | % FullName)
java -cp "bin;lib/\*" App

---

🔑 Login inicial

Na primeira execução, se não existir nenhum colaborador no banco, o sistema cria um usuário padrão:

CPF: 111

Senha: admin

---

🧑‍💻 Como usar (passo a passo)

Abra o programa (App.java)

Faça login

No menu:

CEO pode gerenciar Clientes e Colaboradores

Manager pode gerenciar Clientes

Worker apenas visualiza

Para editar:

selecione uma linha na tabela

os campos serão preenchidos

edite e clique Editar

---

✅ Exemplo de uso (rapidinho)

Login com:

CPF: 111

Senha: admin

Vá em Gerenciar Clientes

Adicione:

Nome: Maria

CPF: 123

Email: maria@email.com

Telefone: 9999-0000

Clique Adicionar

Selecione “Maria” na tabela, altere o telefone e clique Editar

Pronto: dados persistidos no banco.
