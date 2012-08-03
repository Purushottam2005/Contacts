Contacts
========

Tutos Java EE (Ejb3, Jax-RS, JPA) et Sencha ExtJs

Objectif
========

L'objectif de ce tutoriel est de faire fonctionner une partie serveur en Java EE 6 avec une partie IHM en Sencha Ext JS


Modules
========

Ce projet a �t� d�coup� en plusieurs modules :

+ un ear de business : Contacts-business-EAR

	- un projet common : Contacts-common qui contient les DTO et les interfaces des services
	- un projet EJB : Contacts-business-EJB qui contient les services EJB 3 qui accedent � la partie JPA
	- un projet Derby : Contacts-data-Derby qui contient la partie base de donn�es (jar, persistence, datasource)
	

+ un ear de pr�sentation : Contacts-presentation-EAR

	- un projet common : Contacts-common qui contient les DTO et les interfaces des services
	- un projet Rest : Contacts-presentation-JAXRS qui contient les web services REST qui accedent au EJB business
	- un projet ExtJS : Contacts-ihm-EXTJS qui contient les �crans IHM en sencha Ext JS


Le mod�le
========

Le mod�le de donn�es utilis� dans ce sample est double :

+ un cas simple : 
		une classe user (String login, String password, String lastName, String firstName) qui permet de travailler sur un cas proche du Hello world et voir la dynamique de l'application

+ un cas plus complexe : 
		un contact h�ritant de Person contenant des attributs de diff�rents types avec des associations oneTomany et manyTomany vers des listes d'Address, de Phone ou d'Email. 
		Ce cas permet de se confronter � plusieurs probl�matiques plus proche de la vie r�elle.
	
	
Evolution
========

L'id�e de ce d�coupage en module est de faire �voluer ce projet � l'avenir en ajoutant diff�rentes briques interchangeables. 
Par exemple fournir une stack Spring pour remplacer les projet EJB et REST, ou encore fournir d'autre module base de donn�es que Derby ou remplacer ExtJs par du Jquery.

Explication
========

Sencha ExtJS est un framework IHM javaxcript qui permet de faire du MVC cot� client. 

Les composants view ([view/List.js]( https://github.com/sebrevel/Contacts/blob/master/Contacts-ihm-EXTJS/src/main/webapp/contactViewer/contactViewer/view/contact/List.js)) sont associ�s � des stores ([store/Contacts.js](https://github.com/sebrevel/Contacts/blob/master/Contacts-ihm-EXTJS\src\main\webapp\contactViewer\common\store\Contacts.js)) eux m�me associ�s � des mod�les ([model/Contacts.js](https://github.com/sebrevel/Contacts/blob/master/Contacts-ihm-EXTJS/src/main/webapp/contactViewer/common/model/Contact.js)).

Ces store contiennent des proxy qui sont des proxy Rest qui sont directement branch�s sur les web services REST du projet JAX-RS ([ContactRestService.java](https://github.com/sebrevel/Contacts/blob/master/Contacts-presentation-JAXRS\src\main\java\org\sebrevel\contacts\rest\contact\ContactRestService.java)).

Ces web service utilisent des service Remote ([ContactServiceRemote.java](https://github.com/sebrevel/Contacts/blob/master/Contacts-common\src\main\java\org\sebrevel\contacts\service\contact\ContactServiceRemote.java))  pour acc�der aux EJB de la partie business ([ContactService.java](https://github.com/sebrevel/Contacts/blob/master/Contacts-business-EJB\src\main\java\org\sebrevel\contacts\service\contact\ContactService.java)).

Les EJB utilisent les bean annot�s en JPA ([Contact.java](https://github.com/sebrevel/Contacts/blob/master/Contacts-business-EJB\src\main\java\org\sebrevel\contacts\beans\Contact.java)) pour interroger la base de donn�es m�moire Derby.


Installation
========

Une vid�o de 8min montrant comment r�cup�rer le code sur GitHub, cr�er son workspace sur IntelliJ et d�ployer l'application sur Glassfish est disponible [ici](http://www.youtube.com/watch?v=NJpBY6ys-mE&feature=youtu.be)


TODO
========
+ Faire tourner l'application sur du JBOSS et du Tomcat EE
+ Faire �voluer l'IHM pour inclure un �cran de login, des �crans de recherche et un layout Card
+ Ajouter un module d'authentification JAAS pour compl�ter la stack Java EE
+ Ajouter diff�rents modules (Spring, HSQL, MySql, Jquery, Sencha Touch, etc.)





 


