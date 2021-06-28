## ZygoTV - Séries de ZygoTV

ZygoTV é um aplicativo de catálogo de séries de TV. Nesta versão do aplicativo é possível:

* Ver na tela principal a série de TV mais popular no momento, além da lista dos seriados mais populares por gênero
* Ver detalhes de uma série de TV, as suas temporadas e uma sinopse dos episódios de cada temporada
* Pesquisar séries de TV por nome

### Exemplos das telas
<img src="screenshots/home.png?raw=true" width="250"> <img src="screenshots/details.png?raw=true" width="250"> <img src="screenshots/search.png?raw=true" width="250">

### Ferramentas utilizadas
* Linguagem de programação: Kotlin
* Android Architecture Components: ViewModel, LiveData
* Navegação entre telas: Android Navigation Component
* Injeção de dependências: Koin
* Execução de tarefas assíncronas: Coroutines
* Integração com API REST: Retrofit
* Parser de payload json: Moshi
* Download e gerenciamento de imagens: Glide
* Biblioteca nativa e c++: armazenamento de chave de API
* Mock para testes: mockk
* Testes unitários: JUnit
* Testes de integração: Espresso

### Arquitetura
O código está organizado segundo os princípios da Clean Architecture, com as seguintes camadas:
* Camada de apresentação (view): componentes responsáveis por apresentar a interface do usuário (UI).
Estes componentes estão organizados por features e é onde estão localizados as Activities, Fragments,
ViewModels, Adapters, etc
* Camada de domínio (domain): regras de negócio e objetos de negócio
* Camada de dados (data): Implementação do padrão de repository, que por sua vez consulta o data
source remoto (REST API) para obtenção dos dados