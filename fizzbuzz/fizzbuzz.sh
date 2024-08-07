#!/bin/bash

for i in {1..100}; do
    if ((i % 3 == 0 && i % 5 == 0))
    then
        echo -n "FizzBuzz"
    elif ((i % 3 == 0))
    then
        echo -n "Fizz"
    elif ((i % 5 == 0))
    then
        echo -n "Buzz"
    else
        echo -n "$i"
    fi
done
