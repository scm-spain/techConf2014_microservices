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
* [VirtualBox](https://www.virtualbox.org/wiki/Downloads)
* Imagen virtualVox[vdi](https://s3-eu-west-1.amazonaws.com/techconf-vm/techconf2014.vdi) [vbox](https://s3-eu-west-1.amazonaws.com/techconf-vm/techconf2014.vbox)

## Docs
* [step 1](https://docs.google.com/document/d/14rd2kOWr0W-n17QHS8KhcWpzas9W_fh-X4KoNBExkwI/edit)
* [step 2](https://docs.google.com/document/d/1b8pKwWH1o-dMIIEthrUhDzfjrn6s6g5zfC7bFT3wkFw/edit)
* [step 3](https://docs.google.com/document/d/1QE8yfbNd79uNeqIKFLfdLLJCOKxvzi66B2PB9HW4kYc/edit)
* [step 4](https://docs.google.com/document/d/1wiHJ3vC2rrBvy2tuyWBL_Z1MKRBwmU5ywX8PaSi0P0U/edit)
* [step 5](https://docs.google.com/document/d/17dqqL_PATNBd12T1ldppU6G_U9wgvgvl1-X8DwEu80s/edit)

## Que haremos
Dividiremos el workshop en 2 partes:

### Parte 1
Basándonos en la arquitectura de Netflix cada uno desarrollará una pequeña implementación de un microservicio que se conectará con un service discovery(eureka). Después conectaremos los microservicios entre ellos para ver las métricas con turbine y hystrix dashboard. También aprenderemos el patrón Circuit Breaker con el cual enseñaremos el funcionamiento de hystrix cuando una de las instancias del microservicio deja de funcionar.

### Parte 2
Usando un framework reactivo como PlayFramework de Typesafe haremos una pequeña implementación de una aplicación web contra el microservicio creado. Usando el paradigma de BigPipe.
