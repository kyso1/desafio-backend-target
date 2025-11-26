# 🎯 Desafio Backend

> Neste projeto eu desenvolvi uma API REST para solucionar os desafios de lógica de negócios propostos envolvendo cálculo de comissões, controle de estoque e cálculo de juros compostos.

## 🚀 Tecnologias

| Tecnologia | Versão |
|-----------|--------|
| **Java** | 17+ |
| **Spring Boot** | 3.x (Web) |
| **Maven** | 3.x+ |
| **Lombok** | Latest |

## 📋 Funcionalidades

### 💰 Cálculo de Comissões de Vendas

Processa uma lista de vendas e calcula a comissão total por vendedor baseada nas seguintes regras:

- Vendas abaixo de R$ 100,00: **Sem comissão**
- Vendas entre R\$ 100,00 e R$ 499,99: **1% de comissão**
- Vendas a partir de R$ 500,00: **5% de comissão**

### 📦 Controle de Estoque (In-Memory)

Gerencia a entrada e saída de produtos, validando se há estoque suficiente para saídas.

- Mantém o estado dos produtos em memória
- Gera um identificador único para cada movimentação
- Possui funcionalidade de **Reset** para facilitar testes manuais

### 📊 Cálculo de Juros

Calcula o valor atualizado de uma dívida baseada em dias de atraso.

- Taxa de multa: **2,5% ao dia** (Juros simples sobre o valor original)

---

## 🛠️ Como Rodar o Projeto

### Pré-requisitos

- Java JDK 17+ instalado
- Maven instalado

### Passo a Passo

1. Clone o repositório:

```bash
git clone https://github.com/kyso1/desafio-backend-spring.git
```

1. Entre na pasta do projeto:

```bash
cd desafio-backend-spring
```

1. Execute a aplicação via Maven:

```bash
mvn spring-boot:run
```

> A aplicação iniciará na porta 8080 (ou 8081 se configurado no `application.properties`).

---

## 🔌 Documentação da API (Endpoints)

Abaixo estão os exemplos de requisição para testar cada exercício.

### 💰 Calcular Comissões (Exercício 1)

Recebe uma lista de vendas e retorna o total de comissão por vendedor.

**Endpoint:** `POST /api/desafio/comissoes`

**Exemplo de Body (JSON):**

```json
{
  "vendas": [
    { "vendedor": "João Silva", "valor": 1200.50 },
    { "vendedor": "João Silva", "valor": 950.75 },
    { "vendedor": "Maria Souza", "valor": 90.00 }
  ]
}
```

**Exemplo de Resposta:**

```json
{
  "João Silva": 107.56,
  "Maria Souza": 0.00
}
```

---

### 📦 Movimentar Estoque (Exercício 2)

Realiza a entrada ou saída de mercadorias.

**Endpoint:** `POST /api/desafio/estoque/movimentar`

**Exemplo de Body (JSON) - Entrada:**

```json
{
  "codigoProduto": 101,
  "descricaoMovimentacao": "Entrada NF 550",
  "tipo": "ENTRADA",
  "quantidade": 50
}
```

**Exemplo de Body (JSON) - Saída:**

```json
{
  "codigoProduto": 104,
  "descricaoMovimentacao": "Venda Balcão",
  "tipo": "SAIDA",
  "quantidade": 20
}
```

**Resetar Estoque (Apenas para Testes):**

Como o banco é em memória, use este endpoint para voltar aos valores originais do exercício.

**Endpoint:** `POST /api/desafio/estoque/reset`

---

### 📅 Calcular Juros (Exercício 3)

Calcula o valor final de uma conta com base na data de vencimento.

**Endpoint:** `GET /api/desafio/juros`

**Parâmetros (Query Params):**

- `valor`: Valor original da dívida (ex: 100.00)
- `vencimento`: Data de vencimento no formato YYYY-MM-DD

**Exemplo de URL:**

```bash
http://localhost:8080/api/desafio/juros?valor=100.00&vencimento=2023-01-01
```

---

## 🧪 Testes

Os testes podem ser realizados via **Postman** ou **Insomnia** importando as URLs e JSONs descritos acima.

### 📮 Guia Completo: Testando com Postman

#### 1️⃣ Instalar e Configurar Postman

1. Baixe o Postman em: [https://www.postman.com/downloads/](https://www.postman.com/downloads/)
2. Instale e abra a aplicação
3. Crie uma nova coleção clicando em `+ New` → `Collection`

#### 2️⃣ Testando o Endpoint de Comissões

1. Clique em `+ Add request`
2. Configure:
   - **Método:** `POST`
   - **URL:** `http://localhost:8080/api/desafio/comissoes`
   - **Body:** Selecione `raw` e escolha `JSON`
   - Copie e cole:

```json
{
  "vendas": [
    { "vendedor": "João Silva", "valor": 1200.50 },
    { "vendedor": "João Silva", "valor": 950.75 },
    { "vendedor": "Maria Souza", "valor": 90.00 }
  ]
}
```

1. Clique em `Send` para executar
2. Você deve receber uma resposta similar a:

```json
{
  "João Silva": 107.56,
  "Maria Souza": 0.00
}
```

#### 3️⃣ Testando o Endpoint de Estoque

**Movimentação de Entrada:**

1. Crie uma nova request `POST`
2. Configure:
   - **URL:** `http://localhost:8080/api/desafio/estoque/movimentar`
   - **Body (raw - JSON):**

```json
{
  "codigoProduto": 101,
  "descricaoMovimentacao": "Entrada NF 550",
  "tipo": "ENTRADA",
  "quantidade": 50
}
```

1. Clique em `Send`

**Movimentação de Saída:**

1. Crie uma nova request `POST`
2. Configure:
   - **URL:** `http://localhost:8080/api/desafio/estoque/movimentar`
   - **Body (raw - JSON):**

```json
{
  "codigoProduto": 101,
  "descricaoMovimentacao": "Venda Balcão",
  "tipo": "SAIDA",
  "quantidade": 20
}
```

1. Clique em `Send`

**Resetar Estoque:**

1. Crie uma nova request `POST`
2. Configure:
   - **URL:** `http://localhost:8080/api/desafio/estoque/reset`
3. Clique em `Send`

#### 4️⃣ Testando o Endpoint de Juros

1. Crie uma nova request `GET`
2. Configure:
   - **URL:** `http://localhost:8080/api/desafio/juros?valor=100.00&vencimento=2023-01-01`
3. Clique em `Send`
4. Você receberá o valor atualizado da dívida com os juros calculados

#### 💡 Dicas Úteis

- **Salvar requisições:** Todas as requests são salvas automaticamente na coleção
- **Ambiente:** Crie um ambiente no Postman com a variável `base_url = http://localhost:8080` para facilitar
- **Teste rápido:** Use `Ctrl + Enter` para enviar requisições rapidamente
- **Histórico:** Todas as requisições aparecem no histórico (aba History)

---
