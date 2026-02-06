#  API REST de Gestion de Comptes Bancaires

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.7-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)
[![Maven](https://img.shields.io/badge/Maven-3.8+-blue.svg)](https://maven.apache.org/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

Une application RESTful Spring Boot pour la gestion de comptes bancaires avec support JSON et XML, base de données H2 en mémoire et documentation Swagger/OpenAPI.

![Démonstration](./assets/demo.gif)

## 📋 Table des matières

- [Fonctionnalités](#-fonctionnalités)
- [Technologies utilisées](#-technologies-utilisées)
- [Prérequis](#-prérequis)
- [Installation](#-installation)
- [Configuration](#-configuration)
- [Utilisation](#-utilisation)
- [Endpoints API](#-endpoints-api)
- [Exemples de requêtes](#-exemples-de-requêtes)
- [Documentation API](#-documentation-api)
- [Structure du projet](#-structure-du-projet)
- [Tests](#-tests)

## ✨ Fonctionnalités

- ✅ **CRUD complet** pour la gestion des comptes bancaires
- ✅ **Support multi-format** (JSON et XML)
- ✅ **Base de données H2** en mémoire avec console web
- ✅ **Documentation Swagger/OpenAPI** interactive
- ✅ **Hot Reload** avec Spring DevTools
- ✅ **Architecture REST** respectant les bonnes pratiques
- ✅ **Lombok** pour réduire le code boilerplate
- ✅ **JPA/Hibernate** pour la persistance des données

## 🛠 Technologies utilisées

| Technologie | Version | Description |
|------------|---------|-------------|
| **Spring Boot** | 3.5.7 | Framework principal |
| **Java** | 21 | Langage de programmation |
| **Spring Data JPA** | - | Persistance des données |
| **H2 Database** | - | Base de données en mémoire |
| **Lombok** | - | Réduction du code boilerplate |
| **SpringDoc OpenAPI** | 2.8.0 | Documentation API |
| **Maven** | - | Gestion des dépendances |
| **Jackson XML** | - | Support du format XML |

## 📦 Prérequis

Avant de commencer, assurez-vous d'avoir installé :

- **Java JDK 21** ou supérieur
- **Maven 3.8+** (ou utiliser le wrapper Maven inclus)
- Un IDE (IntelliJ IDEA, Eclipse, VS Code)
- **Git** (optionnel)

## 🚀 Installation

### 1. Cloner le projet

```bash
git clone <url-du-repo>
cd spring
```

### 2. Compiler le projet

**Sous Windows :**
```cmd
mvnw.cmd clean install
```

**Sous Linux/Mac :**
```bash
./mvnw clean install
```

### 3. Lancer l'application

**Sous Windows :**
```cmd
mvnw.cmd spring-boot:run
```

**Sous Linux/Mac :**
```bash
./mvnw spring-boot:run
```

L'application sera accessible sur : **http://localhost:8082**

## ⚙ Configuration

### application.properties

```properties
# Port du serveur
server.port=8082

# Base de données H2
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.username=sa
spring.datasource.password=

# Console H2
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# JPA/Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Swagger/OpenAPI
springdoc.api-docs.path=/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
```

## 📖 Utilisation

### Accès à la console H2

- **URL** : http://localhost:8082/h2-console
- **JDBC URL** : `jdbc:h2:mem:testdb`
- **Username** : `sa`
- **Password** : *(laisser vide)*

### Accès à Swagger UI

- **URL** : http://localhost:8082/swagger-ui.html
- Interface interactive pour tester les endpoints

## 🌐 Endpoints API

### Base URL : `/banque`

| Méthode | Endpoint | Description | Format |
|---------|----------|-------------|--------|
| `GET` | `/banque/comptes` | Récupérer tous les comptes | JSON, XML |
| `GET` | `/banque/comptes/{id}` | Récupérer un compte par ID | JSON, XML |
| `POST` | `/banque/comptes` | Créer un nouveau compte | JSON, XML |
| `PUT` | `/banque/comptes/{id}` | Mettre à jour un compte | JSON, XML |
| `DELETE` | `/banque/comptes/{id}` | Supprimer un compte | - |

### Modèle de données : Compte

```json
{
  "id": 1,
  "solde": 1500.0,
  "dateCreation": "2025-10-26",
  "type": "COURANT"
}
```

### Types de compte

- `COURANT` : Compte courant
- `EPARGNE` : Compte épargne

## 📝 Exemples de requêtes

### 1. Récupérer tous les comptes (JSON)

```bash
curl -X GET http://localhost:8082/banque/comptes \
  -H "Accept: application/json"
```

**Réponse :**
```json
[
  {
    "id": 1,
    "solde": 1500.0,
    "dateCreation": "2025-10-26",
    "type": "COURANT"
  },
  {
    "id": 2,
    "solde": 3000.0,
    "dateCreation": "2025-10-26",
    "type": "EPARGNE"
  }
]
```

### 2. Récupérer tous les comptes (XML)

```bash
curl -X GET http://localhost:8082/banque/comptes \
  -H "Accept: application/xml"
```

**Réponse :**
```xml
<List>
  <item>
    <id>1</id>
    <solde>1500.0</solde>
    <dateCreation>2025-10-26</dateCreation>
    <type>COURANT</type>
  </item>
</List>
```

### 3. Récupérer un compte par ID

```bash
curl -X GET http://localhost:8082/banque/comptes/1 \
  -H "Accept: application/json"
```

### 4. Créer un nouveau compte (JSON)

```bash
curl -X POST http://localhost:8082/banque/comptes \
  -H "Content-Type: application/json" \
  -H "Accept: application/json" \
  -d "{\"solde\": 5000.0, \"dateCreation\": \"2025-10-26\", \"type\": \"EPARGNE\"}"
```

**Réponse :**
```json
{
  "id": 4,
  "solde": 5000.0,
  "dateCreation": "2025-10-26",
  "type": "EPARGNE"
}
```

### 5. Mettre à jour un compte

```bash
curl -X PUT http://localhost:8082/banque/comptes/1 \
  -H "Content-Type: application/json" \
  -H "Accept: application/json" \
  -d "{\"solde\": 2000.0, \"dateCreation\": \"2025-10-26\", \"type\": \"COURANT\"}"
```

### 6. Supprimer un compte

```bash
curl -X DELETE http://localhost:8082/banque/comptes/1
```

## 📚 Documentation API

### Swagger UI
Interface interactive pour explorer et tester l'API :
- **URL** : http://localhost:8082/swagger-ui.html

### OpenAPI JSON
Spécification OpenAPI au format JSON :
- **URL** : http://localhost:8082/api-docs

## 📁 Structure du projet

```
spring/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── TP8/
│   │   │       └── spring/
│   │   │           ├── controllers/
│   │   │           │   └── CompteController.java      # Contrôleur REST
│   │   │           ├── entities/
│   │   │           │   ├── Compte.java                # Entité JPA
│   │   │           │   └── TypeCompte.java            # Énumération
│   │   │           └── repositories/
│   │   │               ├── Application.java           # Classe principale
│   │   │               └── CompteRepository.java      # Repository JPA
│   │   └── resources/
│   │       └── application.properties                 # Configuration
│   └── test/
│       └── java/
│           └── TP8/
│               └── spring/
│                   └── ApplicationTests.java          # Tests unitaires
├── pom.xml                                            # Configuration Maven
└── README.md                                          # Documentation
```

## 🧪 Tests

### Exécuter les tests

```cmd
mvnw.cmd test
```

### Tests manuels avec Postman

1. Importer la collection d'endpoints
2. Tester chaque endpoint CRUD
3. Vérifier les réponses JSON et XML

## 🔍 Points techniques importants

### Architecture

- **Controller Layer** : Gestion des requêtes HTTP REST
- **Service Layer** : (Peut être ajouté pour la logique métier)
- **Repository Layer** : Accès aux données via JPA
- **Entity Layer** : Modèles de données JPA

### Annotations principales

- `@RestController` : Définit un contrôleur REST
- `@RequestMapping` : Mappage des routes
- `@GetMapping`, `@PostMapping`, etc. : Méthodes HTTP
- `@Entity` : Entité JPA
- `@Repository` : Repository Spring Data
- `@Data` : Lombok - génère getters/setters
- `@NoArgsConstructor`, `@AllArgsConstructor` : Lombok - constructeurs

### Négociation de contenu

L'API supporte automatiquement JSON et XML via les headers :
- `Accept: application/json` pour recevoir du JSON
- `Accept: application/xml` pour recevoir du XML
- `Content-Type: application/json` pour envoyer du JSON
- `Content-Type: application/xml` pour envoyer du XML


## 👤 Auteur

ACHRAF

