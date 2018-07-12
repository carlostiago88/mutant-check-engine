# Mutant Engine

Magneto needs find mutants to help him with Brotherhood of Mutants.

For such, he must know if a human is also a mutant based in his DNA code sample only.

## Getting Started

Do you remember from Nucleotide? So, you must send a JSON array 6x6(7x7,8x8, etc.) named dna with nucleotide: ATCG. 

The condition rule to a normal human be mutant is: 
The DNA chain sent must be at least 2 idential nucleotide both horizontally or vertically or oblique.

> DNA CHAIN SENT
```
{
"dna":["ACTATG","TGCACG","CATGTG","GTATCG","AGGTTG","TAGCGA"]
}
```

A C T A T G

**T** G C A C **G**

C A **T** G T **G**

G T A **T** C **G**

A G G T **T** **G**

T A G C G A

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


### Installation

```
./gradlew build
./gradlew run
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
