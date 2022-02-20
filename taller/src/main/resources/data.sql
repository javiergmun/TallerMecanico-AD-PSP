INSERT INTO DIRECCION(id, calle, codigo, localidad, numero)
VALUES(1, 'avenida','8567','Leganes','7');

INSERT INTO LOGIN(id, esta_activo,token)
VALUES(1,true,'ytf');
INSERT INTO LOGIN(id, esta_activo,token)
VALUES(2,true,'ytf');
INSERT INTO LOGIN(id, esta_activo,token)
VALUES(3,false,'ytf');


INSERT INTO USUARIO(id, es_administrador, correo, dni, nombre, contraseña, telefono, direccion, login)
VALUES(1, true, 'admin@admin.com', 'g7878giu', 'Jose', '5685tygku', 58568668, 1, 1);
INSERT INTO USUARIO(id, es_administrador, correo, dni, nombre, contraseña, telefono, direccion, login)
VALUES(2, false, 'user1@user.com', 'g7878giu', 'Gorge', '5685tygku', 58568668, 1, 2);
INSERT INTO USUARIO(id, es_administrador, correo, dni, nombre, contraseña, telefono, direccion, login)
VALUES(3, false, 'user2@user.com', 'g7878giu', 'Javier', '5685tygku', 58568668, 1, 3);
INSERT INTO USUARIO(id, es_administrador, correo, dni, nombre, contraseña, telefono, direccion)
VALUES(10, true, 'test@test.com', 'g7878giu', 'Para test', '5685tygku', 58568668, 1);


INSERT INTO VEHICULO(id, color, marca, matricula, modelo, propietario, imagen)
VALUES(1,'rojo','BMV','6758','gGJ',2, 'Esto es una Imagen');
INSERT INTO VEHICULO(id, color, marca, matricula, modelo, propietario, imagen)
VALUES(2,'negro','Citroen','78699b','C7',3, 'Esto es una Imagen');
INSERT INTO VEHICULO(id, color, marca, matricula, modelo, propietario, imagen)
VALUES(10,'blanco','Test','78699b','Prueba',10, 'Esto es una Imagen');


INSERT INTO SERVICIO(id, fecha_fin, fecha_inicio, precio, tiempo, tipo)
VALUES(1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 100.0, 50.0, 'chapa y pintura');
INSERT INTO SERVICIO(id, fecha_fin, fecha_inicio, precio, tiempo, tipo)
VALUES(2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 60.0, 30.0, 'cambio neumáticos');

INSERT INTO MECANICO(id, nombre, salario)
VALUES(1, 'Paco', 1200.0);
INSERT INTO MECANICO(id, nombre, salario)
VALUES(2, 'Juan', 1200.0);

INSERT INTO CITA(id, fecha, precio, mecanico, servicio_contratado, cliente)
VALUES(1, CURRENT_TIMESTAMP, 70.0, 1, 1, 2);
INSERT INTO CITA(id, fecha, precio, mecanico, servicio_contratado, cliente)
VALUES(2, CURRENT_TIMESTAMP, 60.0, 2, 2, 3);