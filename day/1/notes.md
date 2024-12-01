# Day 1 Notes

## Part 1
### First solution idea
1. Read the input, putting every other item into list A, and the others in list B.
2. Sort the lists
3. Loop through the lists and add the distances into a variable ``distance``

The reading of the input is unavoidably ``O(n)``. Sorting is where time will be gained, so using a good sorting algorithm is key. I will just use a pre-existing one.

However, I'm sure there's a way to somehow calculate the distances whilst sorting. I will attempt to do so after I've made my solution. For now, I don't care.

Step 3 will also be ``O(n)``. The total complexity will be ``O(n+n+x)`` where ``x`` is the complexity of the sorting algorithm.

#### Psuedocode
> Note, I do not follow any psuedocode standard at the moment.
```C
procedure DistanceCalc:
    begin
        A = new list
        B = new list
        input = read file
        n = input size
        for every line in input:
            begin
                if line index % 2 == 0 then:
                    begin  
                        add line to B
                    end
                else 
                    begin
                        add line to A
                    end
            end
        
        sort A
        sort B

        totalDistance = 0

        for integer i from 0 to n:
            begin
                totalDistance += abs(A[i] - B[i])
            end
        
        return totalDistance
    end
```

## Part 2
### First solution idea
1. Read the input, putting every other item into set A, and the others in list B.
2. Sort only list B
3. Iterate through set A, and for each entry, check the index of the number in list B, then the index of the next number (n + 1) in list B. The difference between these is the count, which we add to the similarity score. To avoid extra complexity, this is easiest to do with a while-loop (in case the next number n + 1 doesn't exist in B)

Again, sorting methodology is the main part here. A is a set because we don't want to rerun checks.

#### Psuedocode
```C
procedure SimilarityScore:
    begin
        A = new set
        B = new list
        input = read file
        n = input size
        for every line in input:
            begin
                if line index % 2 == 0 then:
                    begin  
                        add line to B
                    end
                else 
                    begin
                        add line to A
                    end
            end
        
        sort B

        simScore = 0

        for integer a in A:
            begin
                beginIndex = index of a in B
                distance = 0
                
                if (beginIndex != -1)
                    begin
                    while (B[i + distance + 1] == a)
                        begin
                            distance++
                        end
                    end
                
                simScore += distance * a
            end
        
        return simScore
    end
```