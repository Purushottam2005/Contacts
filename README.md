Contacts
========

Tutos Java EE (Ejb3, Jax-RS, JPA) et Sencha ExtJs

Objectif :

L'objectif de ce tutoriel est de faire fonctionner une partie serveur en Java EE 6 avec une partie IHM en Sencha Ext JS


Modules :

Ce projet a été découpé en plusieurs modules :

_ un ear de business : Contacts-business-EAR

	_ un projet common : Contacts-common qui contient les DTO et les interfaces des services
	_ un projet EJB : Contacts-business-EJB qui contient les services EJB 3 qui accedent à la partie JPA
	_ un projet Derby : Contacts-data-Derby qui contient la partie base de données (jar, persistence, datasource)
	

_ un ear de présentation : Contacts-presentation-EAR

	_ un projet common : Contacts-common qui contient les DTO et les interfaces des services
	_ un projet Rest : Contacts-presentation-JAXRS qui contient les web services REST qui accedent au EJB business
	_ un projet ExtJS : Contacts-ihm-EXTJS qui contient les écrans IHM en sencha Ext JS


Le modèle :

Le modèle de données utilisé dans ce sample est double :
	_ un cas simple : 
		une classe user (String login, String password, String lastName, String firstName) qui permet de travailler sur un cas proche du Hello world et voir la dynamique de l'application
	_ un cas plus complexe : 
		un contact héritant de Person contenant des attributs de différents types avec des associations oneTomany et manyTomany vers des listes d'Address, de Phone ou d'Email. 
		Ce cas permet de se confronter à plusieurs problématique plus proche de la vie réelle.
	
	
Evolution :

L'idée de ce découpage en module est de faire évoluer ce projet à l'avenir en ajoutant différentes briques interchangeables. 
Par exemple fournir une stack Spring pour remplacer les projet EJB et REST, ou encore fournir d'autre module base de données que Derby ou remplacer ExtJs par du Jquery.

Explication :

Sencha ExtJS est un framework IHM javaxcript qui permet de faire du MVC coté client. 
Les composants view (ex : Contacts-ihm-EXTJS\src\main\webapp\contactViewer\contactViewer\view\contact\list.js) sont associés à des stores (ex : Contacts-ihm-EXTJS\src\main\webapp\contactViewer\common\store\Contacts.js) eux même associés à des modèles (ex : Contacts-ihm-EXTJS\src\main\webapp\contactViewer\common\model\Contacts.js).
Ces store contiennent des proxy qui sont des proxy Rest qui sont directement branchés sur les web services REST du projet JAX-RS (ex : Contacts-presentation-JAXRS\src\main\java\org\sebrevel\contacts\rest\contact\ContactRestService.java). 
Ces web service utilisent des service Remote (Contacts-common\src\main\java\org\sebrevel\contacts\service\contact\ContactServiceRemote.java)  pour accéder aux EJB de la partie business (ex : Contacts-business-EJB\src\main\java\org\sebrevel\contacts\service\contact\ContactService.java).
Les EJB utilisent les bean annotés en JPA (ex : Contacts-business-EJB\src\main\java\org\sebrevel\contacts\beans\Contact.java) pour interroger la base de données mémoire Derby.

TODO :
_ Faire tourner l'application sur du JBOSS et du Tomcat EE
_ Faire évoluer l'IHM pour inclure un écran de login, des écrans de recherche et un layout Card
_ Ajouter un module d'authentification JAAS pour compléter la stack Java EE
_ Ajouter différents modules (Spring, HSQL, MySql, Jquery, Sencha Touch, etc.)





 



