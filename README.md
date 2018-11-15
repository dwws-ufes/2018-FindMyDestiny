# FindMyDestiny
Trabalho da Disciplina de Desenvolvimento Web e Web Semântica.

Descrição: FindMyDestiny é um serviço que busca reunir viajantes e empresas que fornecem pacotes turísticos. Isso é alcançado através de uma procura, dentro do serviço, por localidades, e adicionando-se estas localidades a uma roteiro turístico, que então é enviado no sistema.
As empresas interessadas nesse pacote podem fornecer um orçamento para o usuário que atenda aquele roteiro turístico.

* Para esta aplicação, foram utilizados:
	- Eclipse Photon
	- Java EE
	- Javascript
	- Frameworks: CDI, JPA, JSF, Primefaces.
	- WildFly 13
	- Gerenciamento de dependências com Maven
	- JDBC para conexão com banco de dados MySQL

* Para a implantação desta aplicação, deve-se:
	1) Seguir o tutorial do JButler(tutorial escrito por Vítor E. Silva Souza), em especial o preâmbulo e a parte 1, para a integração do WildFly com o projeto.
	2) Dentro do Eclipse IDE, Criar um projeto Maven.
	3) Botão direito sobre o projeto -> Propriedades
		3.1) Em Server, utilizar o servidor WildFly 13.0
		3.2) Em Targerted Runtime, marcar WildFly 13.0 Runtime
		3.3) Em Java Build Path, devem estar marcados os arquivos de classe Maven Dependencies e wildFly 13 Runtime.
		3.4) Em Project Faces, marcar Dynamic Web Module, Java, Javascript, JavaServer Faces e JPA
	4) Iniciar o WildFly
