# Day 2 notes
## Part 1
### Solution idea 1
1. Read data into a set of lists. We do not know how many rows or columns there are (I mean we do, but thats assuming input is always 1000 lines still)
2. Lists are analyzed through the following process:
    a. Start by processing the head and tail. If sign is 0 return false immediately, since diff needs to be between 1 and 3
    b. Pass these results recursively until tail is reached. If the mathematical sign remains the same throughout and the difference never leaves the range then we have true. If it at any point becomes false we break
3. Map the processing function over the lists and count the amount of safe verdicts (true)

## Part 2
This should be as simple as just having some boolean ``dampened`` passed through the recursive part, that turns false on the first failure.

By allowing the dampened to be set as false, we can still let the program behave as prior and thus won't need to create a new class or method, but I will be creating a new file simply to make it easier to see which version is for which part.