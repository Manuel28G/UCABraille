# UCABraille Manual de Técnico
UCABraille es un software capaz de leer y transcribir documentos digitalizados (PDF, Word, documentos de texto) a su representación en caracteres braille, los teclados braille actualmente son muy costosos, en esta nueva era las personas con discapacidad visual podrán acceder a la tecnología de una mejor manera a un costo mucho menor.

### JDK
Es una implementación de una de las platformas de Java creada por Oracle, esta implementación incluye una JVM privada y otros recursos para delimitar el desarrollo de un aplicación realiada en JAVA, hay muchas versiones de esta harramienta para el caso del desarrollo de UCABraille se utilizó la version 1.8 por motivos de compatibilidad con la librería PANAMAHITEK_ARDUINO que se comunicaba con el arduino, las version del jdk esta disponible [aquí](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) .

### Eclipse
Eclipse es un IDE utilizado para el desarrollo de aplicaciones en JAVA (en su mayoria), este contiene un ambiente de desarrollo con un gran cantidad de plug-ins para parametrizar dicho ambiente, por lo que tambien se puede desarrollar aplicaciones en PHP, C, C++, C#, Python, Ruby, etc, este ambiente es ideal para el desarrollo de UCABraille por las múltiples ventajas anteriormente mencionadas además permite la integración con multiples librerías de terceros, la última versión del eclipse para Windows x64 se puede descargar [aquí](https://www.eclipse.org/downloads/download.php?file=/oomph/epp/oxygen/R/eclipse-inst-win64.exe).

### Manejador de Versiones
GitHub es una plataforma de desarrollo que se utiliza para almacenar proyectos utilizando un manejador de versiones, la principal ventaja que ofrece GitHub es eliminar la necesidad de un servidor físico, adicionalmente opera bajo opensource, lo que nos ofrece una operatividad pública gratis, la única condición que hay que cumplir es que puede ser integrada y vista por terceras personas, en el caso de UCABraille era necesario tener este control ya que habían dos ambientes de desarrollo (trabajo, hogar), el desarrollo se realizó en 3 ramas definidas a continuación:

- **Master** En esta rama reside el proyecto final realizando integraciones del backend con el frontend
- **Backend** En dicha rama, reside la lógica de la aplicación Windows y las estructuras de los objetos que serán manipulados a través de ella
- **Frontend** La rama del frontend, recopila toda la parte estética de la aplicación como lo son las imágenes e interfaces gráficas

Se puede conseguir la última versión del manejador de versiones [aquí](https://desktop.github.com/)

### Arquitectura MVC
La arquitectura MVC es un patrón de arquitectura que se utiliza para implementar interfaces de usuarios, de esta manera separa los datos,  la lógica de negocio y la interfaz de usuario, una de las ideas princiaples de este pratón de arquitectura se basa en la reutilización de código junto con la separación de conceptos mencionada anteriormente, se facilita la tarea del desarrollo de la aplicación

- **Modelo**
- **Vista**
- **Controlador**
