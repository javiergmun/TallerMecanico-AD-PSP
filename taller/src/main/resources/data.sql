
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
       ('admin@admin.com', '787856P', 'Jose', '1234', 'imagen1', '58568668', 'Paseo de la Ermita,9,28914,Leganes'),
       ('user1@user.com', '234532U', 'Jorge', '5685tygku', 'imagen2', '65464654', 'Calle Miraflores,10,28914,Leganes'),
       ('user2@user.com', '675432Y', 'Juanma', '5685tygku', 'imagen3', '90998888', 'Avenida Maria Guerrero,2,28914,Leganes'),
       ('javier@user.com', '764432M', 'Javier', '5685tygku', 'imagen4', '32214355', 'Avenida Maria Guerrero,3,28914,Leganes'),
       ('andrea@user.com', '098789R', 'Andrea', '5685tygku', 'imagen5', '82675547', 'Avenida Maria Guerrero,4,28914,Leganes'),
       ('daniel@user.com', '432435K', 'Daniel', '5685tygku', 'imagen6', '96776765', 'Avenida Maria Guerrero,5,28914,Leganes'),
       ('mario@user.com', '356567I', 'Mario', '5685tygku', 'imagen7', '14352455', 'Avenida Maria Guerrero,6,28914,Leganes'),
       ('adrian@user.com', '654574Y', 'Adrian', '5685tygku', 'imagen8', '12354254', 'Avenida Maria Guerrero,7,28914,Leganes'),
       ('teresa@user.com', '385932V', 'Teresa', '5685tygku', 'imagen9', '97686780', 'Avenida Maria Guerrero,8,28914,Leganes'),
       ('test@test.com', '547532N', 'Para test', '5685tygku', 'imagen10', '23132344', 'Calle Monegros,9,28914,Leganes');

insert into usuario_roles (usuario_id, roles) values (1,'USER');
insert into usuario_roles (usuario_id, roles) values (1,'ADMIN');
insert into usuario_roles (usuario_id, roles) values (2,'USER');
insert into usuario_roles (usuario_id, roles) values (3,'USER');
insert into usuario_roles (usuario_id, roles) values (4,'USER');

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
       (100.0, 50.0, 'Chapa y pintura', 'https://www.motormontesinos.com/wp-content/uploads/2014/08/profesionales-2.jpg', 'Reparación en la carrocería a y pintada del automovil con nuestras mejores pinturas.'),
       (60.0, 30.0, 'Cambio aceite', 'https://media.istockphoto.com/photos/draining-down-old-engine-oil-picture-id453646561?k=20&m=453646561&s=612x612&w=0&h=q8Ja5WnitcXheX5Kb3iBDp667175faPbajSwNgvjUYY=', 'Cambio de aceite de la manera más rapida y limpia. Además de sostenible.'),
       (60.0, 30.0, 'Cambio neumáticos', 'https://www.neumaticosamoreno.es/wp-content/uploads/2020/08/Cambio-de-neumaticos-Taller-A-Moreno-Villalba-Servicio-Cambia-tus-Neumaticos.jpg', 'Cambio de neumaticos completos por unos nuevos o semiuevos de las mejores marcas.'),
       (80.0, 30.0, 'Pulir faros', 'https://talleresorchill.com/wp-content/uploads/2020/05/pulido-faros.jpg', 'Deja tus faros brillantes para tener una vision más clara de la carretera');

INSERT INTO MECANICO(username, salario)
VALUES
       ('Paco', 1200.0),
       ('Juan', 1200.0);

INSERT INTO CITA(fecha, mecanico, servicio_contratado, cliente, vehiculo_del_usuario)
VALUES
       ('2022-10-10T11:00', 1, 1, 1,1),
       ('2022-10-10T12:00', 2, 2, 2,2),
       ('2022-10-11T09:00', 2, 3, 3,3),
       ('2022-10-11T11:00', 1, 2, 4,4),
       ('2022-10-11T16:00', 1, 2, 5,5),
       ('2022-10-12T11:00', 2, 3, 3,3),
       ('2022-10-12T18:00', 1, 3, 4,4),
       ('2022-10-13T10:00', 2, 2, 2,5);