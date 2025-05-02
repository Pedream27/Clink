# 🔗 Clink - Encurtador de URLs

Clink é uma aplicação simples e eficiente para encurtar URLs. Desenvolvido em **Java 21**, utiliza **PostgreSQL** como banco de dados e é totalmente conteinerizado com **Docker** e **Docker Compose**.

---

## 🚀 Tecnologias Utilizadas

- Java 21
- Spring Boot
- PostgreSQL
- Docker
- Docker Compose

---

## 🧰 Como Iniciar o Projeto

Certifique-se de ter o **Docker** e **Docker Compose** instalados em sua máquina.

1. Clone o repositório:
```bash
git clone https://github.com/Pedream27/Clink.git
cd Clink
```

2. Inicie os containers:
```bash
docker-compose up --build
```

A aplicação estará disponível em: [http://localhost:8080](http://localhost:8080)

---

## 📌 Como Usar

### 🔸 Encurtar uma URL

**POST** `http://localhost:8080/url/shorten`

**Corpo da requisição (JSON):**
```json
{
  "url": "https://exemplo.com/alguma-pagina"
}
```

**Resposta:**
```json
{
  "shortenedUrl": "http://localhost:8080/codigoEncurtado"
}
```

---

### 🔸 Obter detalhes da URL encurtada

**GET** `http://localhost:8080/url/{codigoEncurtado}`

**Resposta:**
```json
{
  "originalUrl": "https://exemplo.com/alguma-pagina",
  "shortenedUrl": "http://localhost:8080/codigoEncurtado",
  "expirationTime": "2025-05-02T15:00:00",
  "clickCount": 3
}
```

---

### 🔸 Redirecionar para o site original

**GET** `http://localhost:8080/codigoEncurtado`

Redireciona automaticamente para a **URL original**.

---

## 🗃️ Estrutura do Projeto

- `/src` - Código fonte
- `/docker-compose.yml` - Configuração dos containers (App + PostgreSQL)
- `/Dockerfile` - Imagem da aplicação
- `/README.md` - Este arquivo

---

## ✍️ Autor

**Pedro Henrique Saraiva De Oliveira**
