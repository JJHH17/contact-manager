# contact-manager
A command line tool used for managing contact details.
The tool contains email addresses, names and telephone numbers of users.
The tool integrates with Postgresql, and was built/tested using a locally hosted server.

```How to use the application```
- The application is all command line based.
- A full list of commands that can be executed are presented when the program is executed.

```Why this project was built```

This was used as a learning ground for understanding the integration between Java and SQL.

```Requirements and recommendations```

- Postgresql is required for this project.
- Install postgresql: https://www.enterprisedb.com/downloads/postgres-postgresql-downloads
- Install the JDBC driver for Postgresql: https://jdbc.postgresql.org/download/
- Optional: Install pgAdmin4: https://www.pgadmin.org/ - This may help with visualising the data input SQL side.

```Installation steps```

- Create your postgresql server.

- CD to the projects root file, create a db.properties file (you can see the expected layout using the db.properties.example file included)

- Add the postgresql JDBC driver to the projects "External Libraries" directory.
You can do the above in Intelij IDEA by heading to "File > Project Structure > Libraries and adding the relevant JDBC driver you installed earlier.

- Once you've done this, enter the Database credentials to the "db.properties" file.