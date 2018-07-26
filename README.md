# crudWeb

Crud приложение 

сборка mvn clean install

запуск через tomcat

localhost:8080

если будет ошибка 

cannot resolve:

 <provider>org.hibernate.jpa.HibernatePersistenceProvider</provider>
 
 тогда нужно добавить entityManager в classpath
 
 project structure -> library -> new Project Library from Maven
 
 самой последней версии
 
 в приложение заходить localhost:8080/home
 
