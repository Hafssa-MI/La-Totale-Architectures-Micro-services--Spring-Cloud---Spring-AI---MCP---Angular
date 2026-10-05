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


---

#### Création du discovery Service

- Création du service gateway : un module springBoot avec les dépendances suivantes : Eureka pour discovery, actuator pour le monitoring des micros services
- Ajouter l'annotation @EnableEurekaServer
- Dans le fichier application.properties, configurer le regist-with-eureka et fetch-registory false pour dire à discovery server de ne pas s'enregistrer sur lui meme
- Tester la connexion
  ![img_7.png](img_7.png)


---

#### Utilisation du OpenFeign pour communication entre services

- Configurer le routage dynamique : en créeant une méthode annotée @Bean de type DiscoveryClientRouteDefinitionLocator dans ebank-serviceApplication
- Ajouter la dépendance du OpenFeign
- Ajouter le package Feign dans le micro service contenant l'interface CustomerRestClient avec l'annotation @FeignClient contenat le nom du customer-service pour le trouver à l'aide du discovery service
- Dans la couche service on injecte le CustomerRestClient et on l'utilise pour accéder aux informations du customer
- Dans ebank-serviceApplication on ajoute l'annotation @EnableFeignClients
- Tester l'accès au ebank service d'après le discovery et accéder aux customers
  ![img_8.png](img_8.png)


