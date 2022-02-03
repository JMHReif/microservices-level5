#! /bin/bash
auth="--authenticationDatabase admin --username mongoadmin --password Testing123"
db="books"
options="--type=csv --headerline --ignoreBlanks"

mongoimport $auth --db=$db $options --collection=book --file=books_quoted.csv