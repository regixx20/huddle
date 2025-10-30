# Projet prise de redez-vous

## Membres du Projet
- Régix

## Tâches Accomplies
- 

## Tâches à Compléter
- 

## Statut du Projet
En cours de développement

## A retenir


## DERNIER PUSH

## Build

1. `./mvnw clean package`

### Désactiver temporairement l'envoi de mails

Si vous n'avez pas encore de serveur SMTP prêt, désactivez le module mail avec la propriété
`app.mail.enabled=false`. Vous pouvez la définir dans `application.properties`, `application-docker.properties`
ou via la variable d'environnement `MAIL_ENABLED=false` (utile pour Docker Compose). Lorsque l'option est
désactivée, l'application ignore simplement les tentatives d'envoi sans planter au démarrage.

### Dépannage Maven (erreur 403 sur Spring Boot parent)

Dans certains environnements verrouillés (par exemple les bacs à sable ou réseaux d’entreprise), Maven peut
échouer avec `HTTP 403` lors du téléchargement du parent `spring-boot-starter-parent`. Cette erreur provient
d’un blocage réseau et non du projet. Pour contourner le problème :

* Vérifiez que votre proxy/pare-feu autorise l’accès à `https://repo.maven.apache.org/maven2`.
* Configurez `~/.m2/settings.xml` pour utiliser un miroir Maven interne ou un proxy d’entreprise si nécessaire :

  ```xml
  <settings>
    <proxies>
      <proxy>
        <id>corp-proxy</id>
        <active>true</active>
        <protocol>https</protocol>
        <host>proxy.example.com</host>
        <port>8080</port>
      </proxy>
    </proxies>
    <mirrors>
      <mirror>
        <id>internal-repo</id>
        <mirrorOf>central</mirrorOf>
        <url>https://maven.example.com/repository/maven-public/</url>
      </mirror>
    </mirrors>
  </settings>
  ```

* Si vous disposez déjà des dépendances en local, lancez la compilation en mode hors-ligne :

  ```bash
  ./mvnw -o -DskipTests package
  ```

Une fois l’accès au dépôt Maven rétabli, la commande de build standard fonctionne normalement.
