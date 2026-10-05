# La Totale Architectures Micro services Spring Cloud Spring AI MCP Angular
### Auteur : Hafssa Miftah Idrissi
### Master : SDIA2
### Module : Systèmes distribués
### Année universitaire : 2026/2027

---


#### Création du customer Service

- Création du service Customer : un module springBoot avec les dépendances suivantes : SpringWeb, SpringDataJpa, H2 Database, Lombok, Eureka pour discovery, ConfigClient pour configuration, actuator pour le monitoring des micros services, MCP pour le chatbot
- Création des packages : Entites, Repository, Service, Controller
- Initialisation d'un client via CommandLineRunner annoté @Bean et exposer l'application via le port 8056 avec l'ajout de la documentation swagger.
![img.png](img.png)
![img_1.png](img_1.png)
![img_2.png](img_2.png)

---

#### Création du ebank Service

- Création du service Ebank : un module springBoot avec les dépendances suivantes : SpringWeb, SpringDataJpa, H2 Database, Lombok, Eureka pour discovery, ConfigClient pour configuration, actuator pour le monitoring des micros services, MCP pour le chatbot
- Création des packages : Entites, Repository, Service, Controller, Model pour contenir les information du customer qui sera ajouter dans l'entité BankAccount annoté @Transient
- Initialisation des comptes via CommandLineRunner annoté @Bean et exposer l'application via le port 8057 avec l'ajout de la documentation swagger.
  ![img_3.png](img_3.png)
  ![img_4.png](img_4.png)
  ![img_5.png](img_5.png)


---

#### Création du gateway Service

- Création du service gateway : un module springBoot avec les dépendances suivantes : Eureka pour discovery, actuator pour le monitoring des micros services, ReactiveGateway
- Création du ficier application.yml avec un routage statique des routes du customer service et ebank service
  ![img_6.png](img_6.png)


