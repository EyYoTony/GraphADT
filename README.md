#GraphADT Project

##1)

I ran into a few different challenges when creating the way of holding the information of the graph.

I initially tried to compute the formula for how many edges each graph should genererate for each size, but when you scale the equation of nC2 up to 100000 it takes a long time to compute without dynamic programming. I decided to hard code the nC2 amount of edges to each graph like so:

[<img src="https://imgur.com/a/cQUxkv4">](https://i.imgur.com/)

As you can see I had to use the 'long' variable type because 100000C2 = 4999950000 which excedes the size of an int.


The next problem I ran into was how to was how to store the data of edges generated

My initial idea was to use an adjacency matrix to store which nodes were connected to each other by directed edges.
I used a 2d array, but I found this would take up too much data even if I increased the maximum heap size as it would take up n^2 size of the graph. I could have tried to use a smaller data type than int as all that was reaquired to indicate if there is an edge would be 1 bit.

I decided to use an adjacency list instead of a matrix so that you only store the nC2 amount of edges.

This is my implimentation of adding the edges randomly to the adacency list:
[<img src="https://imgur.com/a/RjEATl7">](https://i.imgur.com/)

I make sure that every node has an outgoing edge as instructed, and have a test for this to verify that it works every run.
I also make the assumption that there shouldn't be any edges for one node to itself.

##2)
