# Zygo Programming Challenge - Android Developer
Android challange implementado por José Diogo Castro

## Tecnologias adicionadas
* Coroutines
* Lifecycle
* ViewModel
* Koin
* Chuck
* Gson
* ExpandableLayout (skydoves)
* NDK

## Arquitetura
* Mantive os 4 pacotes raiz `main`, `model`, `network` e `utils`. Cada um foi subdividos em outros pacotes como mostrado abaixo.
* `main`: pacote principal da aplicação
    * `adapters`: coletânea dos `RecyclerView.Adapter` e `RecyclerView.ViewHolder` da aplicação;
    * `di.koin`: declaração dos módulos de Koin para DI;
    * `ui.activity`: coletânea de `Activity` da aplicação;
    * `viewModel`: coletânea de `ViewModel` da aplicação;
* `model`: modelos da aplicação. Dividio em 4 subpacotes cada um referente a um tipo de entidade da aplicação: `episode`, `genre`, `season` e `show`.
* `network`:  pacote responsável pela comunicação do app com a API e configurações do Retrofit. Dividido em 3 subpacotes:
    * `api`: dividida em 2 subpacotes: `repository` e `service`;
    * `model`: modelos da API e Retrofit (respostas e recursos);
    * `retrofit`: configuração do cliente Retrofit para a API.
* `utils`: pacote com classes genéricas, como *extensions functions, buiders* e *factories*.

## Notas de design
* Chamadas da API removidas das MainActivity. Agora ocorrem dentro das `ViewModel`. Cliente do Retrofit injetado em nível de aplicação com Koin.
* Parâmetos `api_key` e `region` setados diretamente na ApiService, evitando de passá-los em todas as chamadas.
* Chave da API salva em arquivo .cpp utilizando a NDK.
* Tela principal remodealada e tela de detalhes desenvolvida de acordo com a especificação encontrada no Invision.
* Crash sobre erro de conexão resolvido.
* Utilizei uma biblioteca de terceiros para layout expansível.
* Utilizei RecyclerView para todas as listagens.
* Adicionei o Chuck para fazer o log das *requests* e *responses* por notificações do app.
* Adicionado *loading* durante espera das requisições.

### Disclaimer
Parabéns a todos os envolvidos na elaboração desse desafio técnico. Achei a ideia de construir uma aplicação e pedir melhorias muito interessante, direto ao ponto :)

Um abraço pro time da Zygo!

José Diogo Castro
