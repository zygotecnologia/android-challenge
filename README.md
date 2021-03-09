# Zygo Programming Challenge - Android Developer - Leonardo Pontes Santana

Arquitetura: MVVM

Bônus realizados:
Remover a necessidade de passar os parâmetros api_key e region para toda a chamada da API; - Adicionado intercpetor no OKHttp
Armazenar a chave de API em um local seguro; - Armazenado no gradle.properties e recuperado do BuildConfig

Features adicionadas para melhorar a qualidade do projeto:

- Data Binding para popular os dados em tela
- Koin para injeção de dependências e organização das camadas do MVVM
- Binding adapters para enxugar a View
- Estrutura do room para possível uso de salvamento local de informações
- Estrutura de navigation convertendo os activies em fragments
- Estrutura de teste unitário. Usei a camada do Room para exemplificar
