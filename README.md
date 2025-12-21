# Heimdall Gateway

Gateway que atua como o **guardião das APIs**, controlando quem pode acessar cada aplicação, validando credenciais e protegendo os serviços internos — assim como **Heimdall** vigia e protege os reinos conectados a **Yggdrasil**.

---

## Roadmap

### MVP 1 – Painel e Autenticação
- [ ] Autenticação para acesso ao painel
- [ ] Listar aplicações conectadas
- [ ] Visualizar dados das aplicações
- [ ] CRUD básico de aplicações
- [ ] Exibir URL da API
- [ ] Painel renderizado no backend com **Qute**

---

### MVP 2 – Gerenciamento de Ambiente
- [ ] Verificar manualmente o status da AWS
- [ ] Reiniciar manualmente instâncias AWS
- [ ] Verificação automática de status
- [ ] Reinício automático em caso de falha

---

### MVP 3 – Logs
- [ ] Coleta de logs da aplicação
- [ ] Persistência dos logs
- [ ] Visualização dos logs no painel
- [ ] Acompanhamento em tempo real

---

### MVP 4 – Build e Atualização
- [ ] Acesso ao repositório de imagens
- [ ] Atualização de versão da aplicação
- [ ] Substituição da versão em execução

---

## Infraestrutura
- [ ] Banco de dados **Oracle**
- [ ] **Gradle** como gerenciador de dependências
- [ ] **Quarkus** como framework backend

> As tecnologias foram escolhidas com foco em **aprendizado e exploração**, não necessariamente como a solução ideal para o problema.
This project uses Quarkus, the Supersonic Subatomic Java Framework.

--- 


If you want to learn more about Quarkus, please visit its website: <https://quarkus.io/>.

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./gradlew quarkusDev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at <http://localhost:8080/q/dev/>.

## Packaging and running the application

The application can be packaged using:

```shell script
./gradlew build
```

It produces the `quarkus-run.jar` file in the `build/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `build/quarkus-app/lib/` directory.

The application is now runnable using `java -jar build/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./gradlew build -Dquarkus.package.jar.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar build/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./gradlew build -Dquarkus.native.enabled=true
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./gradlew build -Dquarkus.native.enabled=true -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./build/heimdall-api-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult <https://quarkus.io/guides/gradle-tooling>.

## Related Guides

- REST resources for Hibernate ORM with Panache ([guide](https://quarkus.io/guides/rest-data-panache)): Generate Jakarta REST resources for your Hibernate Panache entities and repositories
- REST ([guide](https://quarkus.io/guides/rest)): A Jakarta REST implementation utilizing build time processing and Vert.x. This extension is not compatible with the quarkus-resteasy extension, or any of the extensions that depend on it.
- Hibernate Validator ([guide](https://quarkus.io/guides/validation)): Validate object properties (field, getter) and method parameters for your beans (REST, CDI, Jakarta Persistence)
- JDBC Driver - Oracle ([guide](https://quarkus.io/guides/datasource)): Connect to the Oracle database via JDBC
- Reactive Routes ([guide](https://quarkus.io/guides/reactive-routes)): REST framework offering the route model to define non blocking endpoints

## Provided Code

### REST Data with Panache

Generating Jakarta REST resources with Panache

[Related guide section...](https://quarkus.io/guides/rest-data-panache)


### REST

Easily start your REST Web Services

[Related guide section...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)
