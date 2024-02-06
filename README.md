<h1>Introdução</h1>

API de Controle de Fármacos Hospitalares, uma aplicação RESTful stateless desenvolvida para gerenciamento de medicamentos e contingente de farmácias hospitalares. Esta API utiliza autenticação via JWT token com um tempo de duração de 2 horas, criptografia de senhas no banco de dados e foi construída utilizando Spring Boot e Java.

<h1>Tecnologias Utilizadas</h1>

<strong>Java

Spring Boot

Spring Security

JWT (JSON Web Token)

MySQL (banco de dados)

Swagger (documentação)</strong>


<h1>Estrutura do Projeto</h1>

<strong>src/main/java/com/example/pharmacyapi:</strong> Contém os arquivos da aplicação Java.

<strong>controller:</strong> Pacote para os controladores.
<strong>RemedioController.java:</strong> Controlador para manipulação de medicamentos.
<strong>UsuarioController.java:</strong> Controlador para manipulação de usuários.
<strong>infra:</strong> Pacote para classes de infraestrutura.
Configurações de segurança, como <strong>SecurityConfiguration</strong> e filtros de segurança.

<strong>Remedios:</strong> Pacote relacionado à entidade <strong>Remedio</strong>.
Classes para representar a entidade e serviços associados.

<strong>Usuarios:</strong> Pacote relacionado à entidade <strong>Usuario</strong>.
Classes para representar a entidade e serviços associados.

<h1>Funcionalidades Principais</h1>

<h3>Autenticação via JWT Token:</h3>
Token com tempo de duração de 2 horas.

Proteção dos endpoints, exceto para os de cadastro e login.
<h3>Operações CRUD para Medicamentos:</h3>

Adicionar, listar, excluir e atualizar medicamentos.

Exclusão lógica através do endpoint inativarRemedio.

Reativar medicamentos inativos.
<h3>Operações para Usuários:</h3>

Endpoint para cadastrar novos usuários.

Endpoint para realizar login.
<h3>Documentação Swagger:</h3>

Acesse a documentação Swagger em http://localhost:8080/swagger-ui.html

para explorar e testar os endpoints disponíveis.
