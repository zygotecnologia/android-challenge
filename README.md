# Zygo Programming Challenge - Android Developer

Um pequeno aplicativo que lista séries de TV, criado usando Kotlin.

## Especificações

O aplicativo consiste em duas telas, uma página inicial que exibe a série mais popular do momento
junto com uma lista de séries categorizadas por gênero, e uma página de detalhes que exibe as
temporadas e episódios da série selecionada.

Os dados usados pelo app são consumidos da API pública [TMDB](https://developers.themoviedb.org/3/tv).

## Especificações técnicas

* O projeto foi desenvolvido em Kotlin.
* Para trabalhos assíncronos foi utilizado Coroutines e Flows (camada de dados/domínio) e LiveData (camada de apresentação).
* Retrofit e Moshi foram utilizados para chamadas HTTP. Glide foi utilizado para carregamento de imagens.
* Foi utilizado o Navigation component, parte do Android Jetpack, para lidar com a navegação no app.
* Para injeção de dependências foi utilizado o Koin.

## Arquitetura

* O projeto segue os princípios da Clean Architecture, que fundamentalmente separa as classes em 3
camadas: dados (data), domínio (domain) e apresentação (presentation).

    * Domain: contém as classes fundamentais para as regras de negócio do app, não contendo nenhuma
    dependência em outros frameworks ou na infraestrutura do app. Define as entidades usadas no app
    e contratos como a interface dos repositórios.

    * Data: camada responsável por obter os dados usados no restante do app. Define DTOs usados para
    receber dados de APIs externas ou de bancos locais. Implementa o contrato dos repositórios para
    poder fornecer dados às demais camadas ao mesmo tempo que abstrai a origem desses dados.

    * Presentation: camada responsável por receber as interações do usuário, demonstrar informações
    e se comunicar com a camada de domínio. Não possui regras complexas, apenas segue as regras já
    definidas na camada de domínio.

* A estrutura do projeto está dividida entre múltiplos packages (main/tmdb/home/details/di). Cada um
desses está então dividido em packages seguindo a nomenclatura apresentada a cima, com exceção do di,
cuja responsabilidade é unir as dependências do app em um módulo Koin.

* A camada de apresentação segue a arquitetura MVVM, onde cada tela (activity/fragment) possui um
view model responsável por gerenciar seu estado. O view model acessa a camada de domínio, movendo maior
parte da lógica para fora da view.
