
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
       ('test@test.com', 'g7878giu', 'Para test', '5685tygku', 'imagen4', '58568668', 'Calle Monegros,9,28914,Leganes');

INSERT INTO VEHICULO(color, marca, matricula, modelo, imagen, propietario)
VALUES
       ('rojo','BMV','6758','gGJ','Esto es una Imagen', 1),
       ('negro','Citroen','78699b','C7','Esto es una Imagen', 1),
       ('blanco','Test','78699b','Prueba', 'Esto es una Imagen', 2);

INSERT INTO SERVICIO(precio, tiempo, tipo, imagen, descripcion)
VALUES
       (100.0, 50.0, 'chapa y pintura', 'https://www.motormontesinos.com/wp-content/uploads/2014/08/profesionales-2.jpg', 'descripcion1'),
       (60.0, 30.0, 'cambio neumáticos', 'https://www.neumaticosamoreno.es/wp-content/uploads/2020/08/Cambio-de-neumaticos-Taller-A-Moreno-Villalba-Servicio-Cambia-tus-Neumaticos.jpg', 'descripcion2'),
       (80.0, 30.0, 'pulir faros', 'https://talleresorchill.com/wp-content/uploads/2020/05/pulido-faros.jpg', 'descripcion3');

INSERT INTO MECANICO(username, salario)
VALUES
       ('Paco', 1200.0),
       ('Juan', 1200.0);

INSERT INTO CITA(fecha, mecanico, servicio_contratado, cliente)
VALUES
       (CURRENT_TIMESTAMP, 1, 1, 1),
       (CURRENT_TIMESTAMP, 2, 2, 2);