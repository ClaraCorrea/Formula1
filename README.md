
# Português

## Microsserviços ms-cars, ms-races e ms-history

Este projeto abrange três microsserviços projetados para gerenciar informações relacionadas a carros, corridas e histórico. O primeiro microsserviço lida com o cadastro de carros e inclui funcionalidades para simular uma corrida. O segundo microsserviço gerencia o registro de corridas e utiliza o método de simulação do primeiro serviço. O terceiro microsserviço é responsável por armazenar as corridas, bem como seus resultados, enviados pelo segundo. Para garantir o funcionamento adequado, siga as instruções abaixo:

## Inicialização dos Microsserviços

Certifique-se de que todos os três microsserviços estejam em execução simultaneamente. A ordem recomendada de inicialização e os principais arquivos de entrada são os seguintes:

1. **MsCarsApplication.java** (ms-cars)
2. **MsRacesApplication.java** (ms-races)
3. **MsHistoryApplication.java** (ms-history)

## Regras de Negócios Importantes

Para garantir uma operação eficaz do sistema, leve em consideração as seguintes regras de negócios:

- **Carros e Pilotos**: O sistema não permitirá o registro de carros ou pilotos com dados idênticos. Certifique-se de que os dados não sejam completamente iguais para evitar problemas.

- **Corridas**: Para cadastrar uma corrida, é necessário ter pelo menos 3 carros no banco de dados. O número máximo de participantes em uma corrida é de 10 carros. Além disso, o campo "date" deve ser preenchido seguindo o padrão ISO 8601 (AAAA-MM-DDTHH:MM:SS), onde AAAA representa o ano, MM o mês, DD o dia, T separa a data do horário, HH a hora, MM os minutos e SS os segundos.

## Configuração do RabbitMQ

Garanta que o RabbitMQ esteja em execução com o usuário padrão "guest" para que o sistema funcione corretamente, a senha padrão desse usuário é "guest", mas caso tenha sido alterada ou você deseje utilizar outro usuário é necessário alterar as configurações no arquivo application.properties nos microsserviços ms-races e ms-history.

## Endpoints

Acesse os seguintes endpoints para interagir com os microsserviços:

- **Microsserviço de Carros (ms-cars):**
    
    - http://localhost:8080/cars/postCar (Cadastro de um Carro)
    
    Exemplo de formato JSON adequado:
    
    ```json
    {
        "brand": "Ferrari",
        "model": "586",
        "pilot": {
            "name": "João",
            "age": 23
        }, 
        "year": "2023",
        "speed": 255
    }
    ```
- **Microsserviço de Corridas (ms-races):**
    
    - http://localhost:8081/races/postRace (Cadastro de uma Corrida)
    
    Exemplo de formato JSON adequado:
    
    ```json
    {
        "name": "Corrida",
        "country": "Brasil",
        "date": "2023-09-06T14:30:00.000"
    }
    ```
- **Microsserviço de Histórico (ms-history):**
    
    - http://localhost:8082/history/getAllRaces (Listagem de Todas as Corridas)
    - http://localhost:8082/history/getRace/{id} (Listagem de uma Corrida por ID)

Este guia rápido fornece as informações necessárias para executar e interagir com os microsserviços. Certifique-se de seguir as regras de negócios e padrões de data mencionados para obter os melhores resultados.

## Limitações e Considerações

O projeto possui algumas limitações, principalmente em relação ao tratamento de erros, que atualmente são limitados. Além disso, algumas melhorias planejadas para o futuro incluem:

- Utilização de contêineres Docker para facilitar a implantação.
- Implementação do Swagger para documentação de API.
- Aprimoramento da segurança do sistema.
- Correção dos IDs automáticos para torná-los mais amigáveis e compreensíveis.
- Flexibilização da inserção de datas e horários (para permitir o formato brasileiro).
- Desenvolvimento da funcionalidade de retorno do ID gerado após o cadastro de corridas.
- Melhorias no tratamento de erros para tornar o sistema mais robusto e confiável.

Por fim, é importante mencionar que houve alguns problemas com o uso do Git/GitHub durante o desenvolvimento, o que pode afetar o controle de versões do projeto.



# English

## Microservices ms-cars, ms-races and ms-history

This project encompasses three interdependent microservices designed to manage information related to cars, races, and history. The first microservice handles car registration and includes functionality to simulate a race. The second microservice manages race registration and utilizes the simulation method from the first service. The third microservice is responsible for storing races and their results sent by the second service. To ensure proper operation, please follow the instructions below:

## Initialization of Microservices

Ensure that all three microservices are running simultaneously. The recommended startup order and main entry files are as follows:

1. **MsCarsApplication.java** (ms-cars)
2. **MsRacesApplication.java** (ms-races)
3. **MsHistoryApplication.java** (ms-history)

## Important Business Rules

To ensure effective system operation, consider the following business rules:

- **Cars and Drivers**: The system will not allow the registration of cars or drivers with identical data. Ensure that the data is not completely identical to avoid issues.

- **Races**: To register a race, you must have a minimum of 3 cars in the database. The maximum number of participants in a race is 10 cars. Additionally, the "date" field must be filled following the ISO 8601 standard (YYYY-MM-DDTHH:MM:SS), where YYYY represents the year, MM the month, DD the day, T separates the date from the time, HH the hour, MM the minutes, and SS the seconds.

## RabbitMQ Configuration

Ensure that RabbitMQ is running with the default "guest" user for the system to function correctly. The default password for this user is "guest." However, if it has been changed or you wish to use another user, you need to modify the settings in the application.properties file in the ms-races and ms-history microservices.

## Endpoints

Access the following endpoints to interact with the microservices:

- **Cars Microservice (ms-cars):**
    
    - [Car Registration](http://localhost:8080/cars/postCar)
    
    Example of suitable JSON format:
    
    ```json
    {
        "brand": "Ferrari",
        "model": "586",
        "pilot": {
            "name": "João",
            "age": 23
        }, 
        "year": "2023",
        "speed": 255
    }
    ```
- **Races Microservice (ms-races):**
    
    - [Race Registration](http://localhost:8081/races/postRace)
    
    Example of suitable JSON format:
    
    ```json
    {
        "name": "Race",
        "country": "Brazil",
        "date": "2023-09-06T14:30:00.000"
    }
    ```
- **History Microservice (ms-history):**
    
    - [List of All Races](http://localhost:8082/history/getAllRaces)
    - [Race Listing by ID](http://localhost:8082/history/getRace/{id})

This quick guide provides the necessary information to run and interact with the microservices. Be sure to follow the mentioned business rules and date standards to achieve the best results.

## Limitations and Considerations

The project has some limitations, particularly regarding error handling, which is currently limited. Additionally, planned future improvements include:

- Using Docker containers to facilitate deployment.
- Implementing Swagger for API documentation.
- Enhancing system security.
- Correcting automatic IDs to make them more user-friendly and understandable.
- Flexibilizing the input of dates and times (to allow the Brazilian format).
- Developing the functionality to return the generated ID after race registration.
- Improving error handling to make the system more robust and reliable.

Finally, it's important to mention that there were some issues with Git/GitHub usage during development, which could affect version control for the project.
