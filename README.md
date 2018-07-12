# Mutant Engine

Magneto needs find mutants to help of Brotherhood of Mutants.

For such, he must know if a human is also a mutant based in his DNA code sample only.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Installation

```
./gradlew build
./gradlew run
```

### Prerequisites

Send a json array 6x6 with nucleotide: ATCG.
 

```
{
"dna":["ACTATG","TGCACA","CATGTA","GTATCG","AGGTAG","TAGCGA"]
}
```

### Endpoints
```
http://localhost:8080/api/v1/mutant (matrix sequential processor)
http://localhost:8080/api/v2/mutant (matrix parallel processor)
```
```
http://localhost:8080/api/v2/stats (statistics)
```
> working in progress

### Response Expected

If the json array is right, then the answer can be:

``` status:200```
```
{
  "result" : "Do you want to join us, Brotherhood of Mutants?"
}
```

Or

``` status:403```
```
{
  "result": "Sorry for the inconvenience. Bye weak human!"
}
```
