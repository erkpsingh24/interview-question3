
#Build the project:
* run `mvn clean install` - This should also run the tests

# Running the project:
* run `mvn spring-boot:run`
* application uses file-based local h2 database as the persistence layer which can be accessed at `http://localhost:5000/h2`
* for now the database is recreated when the application is started, this can be changed in application.yml `jpa.hibernate.ddl-auto : update`

# Swagger url:
* `http://localhost:5000/swagger-ui/#/`

# Java docs:
* run `mvn javadoc:javadoc`
* open `target/site/apidocs/index.html`
 
# Test coverage stats:
* run `mvn test`
* run `mvn jacoco:report`
* open `~/target/site/jacoco/index.html`









## Guidelines
* Fork this repository and push your commits
* Use the spring-boot template given
* Write unit-tests, integration-tests 
  * Write in javadocs what scenarios are in test
  * Higher coverage is better
* Write code documentation
* All classes given are meant to used as reference - once they are not needed, they can be removed.
* This project uses [lombok](https://projectlombok.org/) - use it when possible
* Properly organize your project with `.gitignore` file, `readme` file explaining how to run the project, etc.

## Deliverables
* Send us a link to a repository fulfilling the requirements.
* Your code will be tested using different tests.
* Successful implementation will move to interview.
