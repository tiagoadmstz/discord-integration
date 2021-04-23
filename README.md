# Discord Integration Example Project
Example project to integrate the discord log

## Configuration file
Create the folder config with "application.yml" file in same place with jar file of the application. e.g.:

```
discor-api:
    token: ODKhdkjlajdkjf.dfhajsdhflahdsf.123da5da1dfah
```

## Database configuration
```
spring:
  datasource:
    driver-class: org.h2.Driver
    url: jdbc:h2:~/test
    username: sa
    password:

  host: analytics.pbl.tec.br
  porta: 8082
  usuario postgres: micros-discord
  senha: YGUYjhbgYTF77654E44tfhfgg
  banco de dados: db-micros-discord
```