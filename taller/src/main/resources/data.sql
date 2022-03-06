
/*
INSERT INTO LOGIN(id, esta_activo,token)
VALUES(1,true,'ytf');
INSERT INTO LOGIN(id, esta_activo,token)
VALUES(2,true,'ytf');
INSERT INTO LOGIN(id, esta_activo,token)
VALUES(3,false,'ytf');
*/

INSERT INTO USUARIO(correo, dni, nombre, contraseña, imagen, telefono, direccion)
VALUES
       ('admin@admin.com', 'g7878giu', 'Jose', '5685tygku', 'imagen1', '58568668', 'Paseo de la Ermita,9,28914,Leganes'),
       ('user1@user.com', 'g7878giu', 'Gorge', '5685tygku', 'imagen2', '58568668', 'Calle Miraflores,10,28914,Leganes'),
       ('user2@user.com', 'g7878giu', 'Javier', '5685tygku', 'imagen3', '58568668', 'Avenida Maria Guerrero,2,28914,Leganes'),
       ('javier@user.com', 'g7878giu', 'Javier', '5685tygku', 'imagen4', '58568668', 'Avenida Maria Guerrero,3,28914,Leganes'),
       ('andrea@user.com', 'g7878giu', 'Andrea', '5685tygku', 'imagen5', '58568668', 'Avenida Maria Guerrero,4,28914,Leganes'),
       ('daniel@user.com', 'g7878giu', 'Daniel', '5685tygku', 'imagen6', '58568668', 'Avenida Maria Guerrero,5,28914,Leganes'),
       ('mario@user.com', 'g7878giu', 'Mario', '5685tygku', 'imagen7', '58568668', 'Avenida Maria Guerrero,6,28914,Leganes'),
       ('adrian@user.com', 'g7878giu', 'Adrian', '5685tygku', 'imagen8', '58568668', 'Avenida Maria Guerrero,7,28914,Leganes'),
       ('teresa@user.com', 'g7878giu', 'Teresa', '5685tygku', 'imagen9', '58568668', 'Avenida Maria Guerrero,8,28914,Leganes'),
       ('test@test.com', 'g7878giu', 'Para test', '5685tygku', 'imagen10', '58568668', 'Calle Monegros,9,28914,Leganes');

INSERT INTO VEHICULO(color, marca, matricula, modelo, imagen, propietario)
VALUES
       ('rojo','BMV','6758','gGJ','Esto es una Imagen', 1),
       ('negro','Citroen','78699b','C7','Esto es una Imagen', 1),
       ('amarillo','Audi','78699b','A1', 'Esto es una Imagen', 2),
       ('rojo','Audi','78699b','A2', 'Esto es una Imagen', 3),
       ('gris','Audi','78699b','A3', 'Esto es una Imagen', 4),
       ('marron','Mercedes','78699b','CLS', 'Esto es una Imagen', 5),
       ('azul','Mercedes','78699b','CLA', 'Esto es una Imagen', 6),
       ('verde','Test','78699b','Prueba', 'Esto es una Imagen', 7);

INSERT INTO SERVICIO(precio, tiempo, tipo, imagen, descripcion)
VALUES
       (100.0, 50.0, 'chapa y pintura', 'https://www.motormontesinos.com/wp-content/uploads/2014/08/profesionales-2.jpg', 'Reparación en la carrocería a y pintada del automovil con nuestras mejores pinturas.'),
       (60.0, 30.0, 'cambio aceite', 'https://media.istockphoto.com/photos/draining-down-old-engine-oil-picture-id453646561?k=20&m=453646561&s=612x612&w=0&h=q8Ja5WnitcXheX5Kb3iBDp667175faPbajSwNgvjUYY=', 'Cambio de aceite de la manera más rapida y limpia. Además de sostenible.'),
       (60.0, 30.0, 'cambio neumáticos', 'https://www.neumaticosamoreno.es/wp-content/uploads/2020/08/Cambio-de-neumaticos-Taller-A-Moreno-Villalba-Servicio-Cambia-tus-Neumaticos.jpg', 'Cambio de neumaticos completos por unos nuevos o semiuevos de las mejores marcas.'),
       (80.0, 30.0, 'pulir faros', 'https://talleresorchill.com/wp-content/uploads/2020/05/pulido-faros.jpg', 'Deja tus faros brillantes para tener una vision más clara de la carretera');

INSERT INTO MECANICO(username, salario)
VALUES
       ('Paco', 1200.0),
       ('Juan', 1200.0);

INSERT INTO CITA(fecha, mecanico, servicio_contratado, cliente)
VALUES
       (CURRENT_TIMESTAMP, 1, 1, 1),
       (CURRENT_TIMESTAMP, 2, 2, 2),
       (CURRENT_TIMESTAMP, 2, 3, 3),
       (CURRENT_TIMESTAMP, 1, 2, 4),
       (CURRENT_TIMESTAMP, 1, 2, 5),
       (CURRENT_TIMESTAMP, 2, 3, 3),
       (CURRENT_TIMESTAMP, 1, 3, 4),
       (CURRENT_TIMESTAMP, 2, 2, 2);