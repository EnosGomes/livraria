# livraria

Sistema de Livraria Web com validação usando thymeleaf e Heroku.

### O que é usado:
* STS Eclipse
* Maven
* MySQL
* Apache
* Git
* Bootstrap 4
* Heroku

### Como rodar:
Para que o sistema execute no seu computador é preciso que você configure as informações do application.properties ou até crie um novo arquivo com esse nome.Você pode achá-lo em src/main/resources/application.properties

Após isso rode usando uma IDE com um servidor de aplicação embarcado já (STS ou Intellij IDEA) ou usando a linha de comando desse modo:
Baixe o Maven em (http://maven.apache.org).

Depois de instalado e o PATH adicionado digite na linha de comando:
```
$ mvn clean
$ mvn compile 
$ mvn tomcat7:run
```


Você pode ter acesso ao sistema usando o link (http://localhost:8080/).
