# Flight Finder

A full-stack application to determine how many times the word `"flight"` can be formed from a given string of lowercase characters.

---

## Tech Stack

### Frontend

- [React 19](https://react.dev/)
- [TypeScript](https://www.typescriptlang.org/)
- [Vite](https://vitejs.dev/)
- [Tailwind CSS 4](https://tailwindcss.com/)
- [React Testing Library](https://testing-library.com/)
- [Vitest](https://vitest.dev/)

### Backend

- [Java 17](https://adoptium.net/)
- [Spring Boot 3.4](https://spring.io/projects/spring-boot)
- [Gradle](https://gradle.org/)
- [Springdoc OpenAPI (Swagger)](https://springdoc.org/)
- [Lombok](https://projectlombok.org/)

### Dev Tools

- Docker + Docker Compose
- ESLint
- JSDOM

---

## Getting Started

### Prerequisites

- Node.js 20+
- Java 17
- Docker + Docker Compose

---

## Run the Full Stack

From the project root, run:

```bash
docker-compose up --build
```

Then access:

- **Frontend:** [http://localhost:5173](http://localhost:5173)
- **Backend API:** [http://localhost:8080/api/count](http://localhost:8080/api/count)
- **Swagger UI:** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---

## Running Tests

### Frontend (Vitest)

```bash
cd frontend
npm install
npm run test
```

### Backend (JUnit)

```bash
cd backend
./gradlew test
```

---

## Swagger Documentation

Once the backend is running, open:
[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

### Includes:

- `POST /api/count`
- Example requests/responses
- Error schema documentation

---

## Project Structure

```bash

/frontend
├── src/
│   ├── hooks/
│   ├── pages/
│   └── index.css
└── vite.config.ts

/backend
├── src/main/
│   ├── controller/
│   ├── service/
│   ├── dto/
│   └── exception/
└── src/test/

/docker-compose.yml

```
