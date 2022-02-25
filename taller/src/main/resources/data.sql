
INSERT INTO DIRECCION(id, calle, codigo, localidad, numero)
VALUES(1, 'avenida','8567','Leganes','7');
VALUES(2, 'avenida','8567','Leganes','8');
VALUES(3, 'avenida','8567','Leganes','9');
VALUES(4, 'avenida','8567','Leganes','10');
VALUES(5, 'avenida','8567','Leganes','11');

INSERT INTO LOGIN(id, esta_activo,token)
VALUES(1,true,'ytf');
INSERT INTO LOGIN(id, esta_activo,token)
VALUES(2,true,'ytf');
INSERT INTO LOGIN(id, esta_activo,token)
VALUES(3,false,'ytf');


INSERT INTO USUARIO(id, correo, dni, username, password, telefono, direccion, login)
VALUES(10,  'admin@admin.com', 'g7878giu', 'Jose', '5685tygku', '58568668', 1, 1);
INSERT INTO USUARIO(id, correo, dni, username, password, telefono, direccion, login)
VALUES(20, 'user1@user.com', 'g7878giu', 'George', '5685tygku', '58568668', 2, 2);
INSERT INTO USUARIO(id, correo, dni, username, password, telefono, direccion, login)
VALUES(30,  'user2@user.com', 'g7878giu', 'Javier', '5685tygku', '58568668', 3, 3);


INSERT INTO VEHICULO(id, color, marca, matricula, modelo, imagen)
VALUES(1,'rojo','BMV','6758','gGJ','Esto es una Imagen');
INSERT INTO VEHICULO(id, color, marca, matricula, modelo, imagen)
VALUES(2,'negro','Citroen','78699b','C7','Esto es una Imagen');
INSERT INTO VEHICULO(id, color, marca, matricula, modelo, imagen)
VALUES(10,'blanco','Test','78699b','Prueba', 'Esto es una Imagen');


INSERT INTO SERVICIO(id, precio, tiempo, tipo)
VALUES(1, 100.0, 50.0, 'chapa y pintura');
INSERT INTO SERVICIO(id, precio, tiempo, tipo)
VALUES(2, 60.0, 30.0, 'cambio neumáticos');

INSERT INTO MECANICO(id, username, salario)
VALUES(1, 'Paco', 1200.0);
INSERT INTO MECANICO(id, username, salario)
VALUES(2, 'Juan', 1200.0);

INSERT INTO CITA(id, fecha, mecanico, servicio_contratado, cliente)
VALUES(1, CURRENT_TIMESTAMP, 1, 1, 20);
INSERT INTO CITA(id, fecha, mecanico, servicio_contratado, cliente)
VALUES(2, CURRENT_TIMESTAMP, 2, 2, 30);

