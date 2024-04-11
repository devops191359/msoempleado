# msoempleado
Microservicio de Empleados


#git clone https://github.com/devops191359/msoempleado.git


#git clone https://github.com/devops191359/mso-empleado-postman.git


#liga swagger: http://localhost:10410/msoempleado/v1/swagger-ui/index.html#/


#liga h2 DB : http://localhost:10410/msoempleado/v1/h2-console/login.jsp?jsessionid=02c829e77d7d87cc93c58bfc98b81469


Cadena de conexion H2 DB 

url: jdbc:h2:mem:empleados

username: sa

pass: password


#--//--Tablas

#SELECT * FROM TA_EMPLEADO;


#SELECT * FROM TA_BITACORA;



#INSTRUCCIONES 


Para poder ejecutar este código primero deberá clonar en su pc el repositorio con el siguiente comando:

#git clone https://github.com/devops191359/msoempleado.git

Para compilar y ejecutar dicho web service deberá instalar el java jdk 11

Una vez clonado o descargado el código en su PC deberá importar el proyecto o servuicio web en un entorno de desarrollo Eclipse o Spring Tool Siute versión 4 en adelante o puede ejecutar el web service usando el siguiente comando desde el cmd estando sobre la carpeta del proyecto:

#mvn spring-boot:run

Se debe de agregar esta variable de entorno a la PC

![image](https://github.com/devops191359/msoempleado/assets/16689291/966f954c-7408-4d93-9d75-e99eebbd9fb4)


##VERIFICAR INFORMACIÓN EN BASE DE DATOS H2

Para verificar la trazabilidad o la info de los empleados desde la BD de H2 deberás acceder a la liga http://localhost:8080/h2-console/login.jsp?jsessionid=d392b4350f826e765a87d9c801bf0ba8 en la cual deberás ingresar las siguientes cedenciales y cadena de conexión coimo se muestra a continuación:

##Datasource

url: jdbc:h2:mem:empleados

username: sa

pass: password


![image](https://github.com/devops191359/msoempleado/assets/16689291/e73544ef-c50d-4c7f-a672-467034005b51)

Una vez que realices la conexion te aparecerá una pantalla en donde podrás realizar las instrucciones sql de coonsulta para que valides que se muestre la información de la bitácora y de los empleados como se muestran en las figuras :

![image](https://github.com/devops191359/msoempleado/assets/16689291/c845f93c-2285-4c53-9394-85179dd862fa)

![image](https://github.com/devops191359/msoempleado/assets/16689291/9d4e7a69-c284-4baa-8760-2e6d641e2931)

Y una vez realizado lo anterior verás que todo esta correcto y funcional






