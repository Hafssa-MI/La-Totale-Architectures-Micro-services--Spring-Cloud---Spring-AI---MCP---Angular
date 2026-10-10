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
  ![img_9.png](img_9.png)


---

#### Utilisation du Resillience4j pour résoudre les pannes

- Ajouter la dépendance Resilience4j
- Dans package Reign , dans les interface Rest on ajoute l'annotation @CircuitBreaker avec method Fallback qui affiche un client par défaut
  ![img_10.png](img_10.png)


---

#### Configurer le service chatbot

- Créer un service chatbot avec les dépendences : SpringWeb, OpenAI, EurekaDiscoveryClient, ConfigClient,SpringBoot  actuator, Swagger
- Configurer l api key et le port 8058
- Créer un package Controller avec une classe @RestController contenant un ChatClient
- Tester avec un query=Bonjour
  ![img_11.png](img_11.png)
  ![img_12.png](img_12.png)
  ![img_13.png](img_13.png) <br>
- Utiliser le MCP pour la connexion : ajouter la dependance Mcp Server dans les microservices en exposants les méthodes dans le service avec @MCPTool en décrivant les tools pour le LLM et configurer le mcp avec la méthode streamable aui permet la connexion bidirectionelle
- Ajouter la dépendence MCP client dans le micro service du chatbot et configurer en spécifiant les liens pour que le service se connecte aux serveurs mcp
- Ajouter le ToolCallbackProvider et tester
  ![img_14.png](img_14.png)
  ![img_15.png](img_15.png)
  ![img_16.png](img_16.png)
  ![img_17.png](img_17.png)

- Ajouter un System message pour limiter le contexte de réponse pour l'agent
  ![img_18.png](img_18.png)
  ![img_19.png](img_19.png)
  ![img_20.png](img_20.png)
  ![img_21.png](img_21.png)
  ![img_22.png](img_22.png)
  ![img_23.png](img_23.png)


---

#### Configurer la connexion avec Discord

- Ajouter la dépendance du discord dans le microservice ai agent
- Créer un package discord dont on ajoutera une classe DiscordBot annoté @DiscordController, Injecter le EbankAIAgent et ajouter une méthode de connexion avec @DiscordMapping
- Tester
  ![img_24.png](img_24.png)


---

#### Configurer la connexion avec Telegram

- Ajouter la dépendance du telegram dans le microservice ai agent
- Créer un package telegram dont on ajoutera une classe DiscordBot annoté @Component, Injecter le EbankAIAgent et hériter de TelegramLongPoolingBot et utiliser le TelegramBotsApi
- disable eureka de créer le Bean de Jersey
- Tester
  ![img_25.png](img_25.png)



---

#### Ajouter un Front Angular

- Création du projet Angular avec bootstrap et intégrer les dépendances dans style.css
- Ajout d'un navbar daprès bootstrap avec <router-outlet> à la fin
- Création du component accounts et configurer sa route dans app.routes
- Vérification du fonctionnement du backend :
  ![img_26.png](img_26.png)
  ![img_27.png](img_27.png) <br>
- Injecteion de l'HttpClient dans le fichier .ts et récupérer la liste des comptes via la gateway 9999, en faisant le subscribe dans le fichier html
- Résolution du problème du CrossOrigin dans la gateway dans le fichier properties.yml
- Création du folder model pour l'ajout des interfaces Accounts et AccountListState dans le fichier accounts.ts
- Test
  ![img_28.png](img_28.png)
