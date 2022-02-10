package com.taller2dam.database;


import lombok.Getter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Controlador Hibernate JPA
 */
@Getter
public class HibernateController {
    private static HibernateController controller;

    //Se crean las Entity para poder manejar las entidades
    private EntityManagerFactory entityManagerFactory;
    private EntityManager manager;
    private EntityTransaction transaction;

    private HibernateController() {
    }

    public static HibernateController getInstance() {
        if (controller == null)
            controller = new HibernateController();
        return controller;
    }

    public void open() {
        if(entityManagerFactory == null || !entityManagerFactory.isOpen())
            entityManagerFactory = Persistence.createEntityManagerFactory("default");
        if(manager == null || !manager.isOpen())
            manager = entityManagerFactory.createEntityManager();
        transaction = manager.getTransaction();
    }

    public void close() {
        manager.close();
        entityManagerFactory.close();
    }

    public EntityManager getManager() {
        return manager;
    }

    public EntityTransaction getTransaction() {
        return transaction;
    }
/*
    public void removeDataBase(){

        ApplicationProperties properties = ApplicationProperties.getInstance();
        String serverUrl = properties.readProperty("database.server.url");
        String serverPort = properties.readProperty("database.server.port");
        String dataBaseName = properties.readProperty("database.name");

        Dotenv dotenv = Dotenv.load();
        String user = dotenv.get("DATABASE_USER");
        String password = dotenv.get("DATABASE_PASSWORD");
        ConnectionString connectionString = new ConnectionString("mongodb://" + user + ":" + password + "@" + serverUrl + ":" +
                serverPort + "/" + dataBaseName + "?authSource=admin");
        MongoClient mongoClient = MongoClients.create(connectionString);

        MongoDatabase blogDB = mongoClient.getDatabase(dataBaseName);
        blogDB.drop();
    }

 */

}
