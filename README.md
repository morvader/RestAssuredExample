[![Build Status](https://travis-ci.org/morvader/RestAssuredExample.svg?branch=master)](https://travis-ci.org/morvader/RestAssuredExample)

[![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?logo=gitpod)](https://gitpod.io/#https://github.com/morvader/RestAssuredExample) 

# EJEMPLO DE USO DE REST-ASSURED

## CONTENIDO

En la el archivo `src/test/java/ReqResExamplesTests.java` se encuentran los ejemplos básicos de uso de esta librería.

La peticiones se realizan sobre el API online: "<https://reqres.in/".>

En las peticiones se muestran los casos de uso más habituales:

- Comprobar códigos de respuesta de la peticiones

- Comprobaciones de valores sobre arrays de datos

- Comprobar cookies y cabeceras en las respuestas

- Realizar peticiones parametrizadas en query y body

- Reutilziar comprobaciones (asserts) en varias peticiones

- Serializar objetos a JSON para incluir en el cuerpo de las peticiones

## EJECUCIÓN

Para lanzar las pruebas, ejecutar en comando:

`$gradlew clean test`

El informe de resultados quedará ubicado en: `/build/reports/`
