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
* `utils`: pacote com classes genéricas, como *extensions functions* e Dialogs *factory*.

## Notas de design

*

### Disclaimer

Completar a challenge não implica em nenhum vínculo nem obrigação da Zygo
com você. Todo o código criado será descartado. Este challenge usa elementos
reais de necessidades da Zygo apenas como uma maneira de avaliarmos sua
aptidão para o cargo.

### Final notes

Valorizamos **muito** a capacidade de nos surpreender!

Boa sorte :)
