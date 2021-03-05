# Zygo Programming Challenge - Android Developer

Seu objetivo neste challenge é refatorar e adicionar novas features um pequeno aplicativo que lista Séries de TV.

## Especificações

O aplicativo possui apenas uma tela de listagem das séries, seu desafio consiste em:

* Implementar a tela de Detalhes da Série;
* Remover as chamadas de API da Main;
* Implementar o novo layout seguindo as especificicações da nossa Designer;
* Resolver o Crash que ocorre ao iniciar o aplicativo sem conexão à Internet;

Acesse a [Documentação da API](https://developers.themoviedb.org/3/tv/get-tv-details) para qualquer dúvida!

**Serão avaliados:** apenas a organização do código, uso
das ferramentas disponíveis, conhecimento e domínio sobre as linguagens e a
capacidade de implementação das especificações

## Requisitos do Novo Layout
As novas telas podem ser encontradas no [Invision](https://isabellataques225701.invisionapp.com/console/share/6Z2ABNOYVB/549307395)

* A Série em destaque deve ser a top 1 mais popular retornada pela API.
* O restante dos resultados devem ser organizados por Gênero como mostrado no Layout

## Requisitos técnicos

* O projeto atual foi desenvolvido em Kotlin, mas fique a vontade para converter para Java caso sinta-se mais confortável.
* É permitido o uso de frameworks e bibliotecas externos, desde que dentro de um
  sistema de gerenciamento de pacotes.
* O código deve ser claro, preferencialmente documentado.
* A arquitetura e design do sistema devem ser documentadas em um arquivo README
  (brevemente, por favor).

## Bônus!

Testes são muito bem vindos, sobrando qualquer tempo, faça-os e ganhe uma
pontuação extra!

Se você tiver ainda mais tempo e quiser arriscar, temos uma listinha sobre o que pode ser melhorado no projeto:
* Remover a necessidade de passar os parâmetros `api_key` e `region` para toda a chamada da API;
* Armazenar a chave de API em um local seguro;
* Implementar a busca por nome das Séries;

## Envio

Faça um fork desse repositório para o seu GitHub, crie um branch de desenvolvimento e faça todos os seus Commits nesse branch. Ao terminar abra um Pull Request para a branch main e nos envie o link do mesmo. 

### Disclaimer

Completar a challenge não implica em nenhum vínculo nem obrigação da Zygo
com você. Todo o código criado será descartado. Este challenge usa elementos
reais de necessidades da Zygo apenas como uma maneira de avaliarmos sua
aptidão para o cargo.

### Final notes

Valorizamos **muito** a capacidade de nos surpreender!

Boa sorte :)
