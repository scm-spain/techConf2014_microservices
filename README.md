# TechConf 2014: Microservices

## Objectivos

Mostrar el uso y funcionamiento de la arquitectura de microservicios y como se relaciona con la capa web.

## Guión
* Introducción a microservicios microservicios basándonos en la arquitectura de Netflix.
* Creación de un microservicio A.
* Creación de otro microservicio B que se conectará al microservicio A.
* Integración de los microservicios con Turbine y hystrix dashboard.
* Introducción a Play Framework y BigPipe.
* Integración de Play Framework con los microservicios.

## Prerequisitos
* [IntelliJ idea](https://www.jetbrains.com/idea/download/)
* [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* [Cliente git](http://git-scm.com/book/en/v2/Getting-Started-Installing-Git)
* Play framework de Typesafe: [ Activator Typesafe](https://typesafe.com/platform/getstarted)

## Que haremos
Dividiremos el workshop en 2 partes:

### Parte 1
Basándonos en la arquitectura de Netflix cada uno desarrollará una pequeña implementación de un microservicio que se conectará con un service discovery(eureka). Después conectaremos los microservicios entre ellos para ver las métricas con turbine y hystrix dashboard. También aprenderemos el patrón Circuit Breaker con el cual enseñaremos el funcionamiento de hystrix cuando una de las instancias del microservicio deja de funcionar.

### Parte 2
Usando un framework reactivo como PlayFramework de Typesafe haremos una pequeña implementación de una aplicación web contra el microservicio creado. Usando el paradigma de BigPipe.
