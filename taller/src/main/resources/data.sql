INSERT INTO DIRECCION(id, calle, codigo, localidad, numero)
VALUES(1, 'avenida','8567','Leganes','7');

INSERT INTO LOGIN(id,correo,contraseña,token)
VALUES(1,'admin@admin.com','5685tygku','ytf');
INSERT INTO LOGIN(id,correo,contraseña,token)
VALUES(2,'prueba1@prueba.com','5685tygku','ytf');
INSERT INTO LOGIN(id,correo,contraseña,token)
VALUES(3,'prueba2@prueba.com','5685tygku','ytf');

INSERT INTO USUARIO(id, es_administrador, dni, nombre, telefono, direccion, login)
VALUES(1, true, 'g7878giu', 'Jose', 58568668, 1, 1);
INSERT INTO USUARIO(id, es_administrador, dni, nombre, telefono, direccion, login)
VALUES(2, false, 'g7878giu', 'Jorge', 58568668, 1, 2);
INSERT INTO USUARIO(id, es_administrador, dni, nombre, telefono, direccion, login)
VALUES(3, false, 'g7878giu', 'Paco', 58568668, 1, 3);

INSERT INTO VEHICULO(id, color, fallos, marca, matricula, modelo, propietario)
VALUES(1,'rojo','coche rayado y falta pintura','BMV','6758','gGJ',2);
INSERT INTO VEHICULO(id, color, fallos, marca, matricula, modelo, propietario)
VALUES(2,'negro','rueda pinchada','Citroen','78699b','C7',3);

INSERT INTO SERVICIO(id, fecha_fin, fecha_inicio, precio, tiempo, tipo)
VALUES(1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 100.0, 50.0, 'chapa y pintura');
INSERT INTO SERVICIO(id, fecha_fin, fecha_inicio, precio, tiempo, tipo)
VALUES(2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 60.0, 30.0, 'cambio neumáticos');

INSERT INTO MECANICO(id, nombre, salario, servicio_asignado)
VALUES(1, 'Paco', 1200.0, 1);
INSERT INTO MECANICO(id, nombre, salario, servicio_asignado)
VALUES(2, 'Juan', 1200.0, 2);

INSERT INTO CITA(id, fecha, precio, mecanico, servicio, cliente)
VALUES(1, CURRENT_TIMESTAMP, 70.0, 1, 1, 2);
INSERT INTO CITA(id, fecha, precio, mecanico, servicio, cliente)
VALUES(2, CURRENT_TIMESTAMP, 60.0, 2, 2, 3);