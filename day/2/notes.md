# Day 2 notes
## Part 1
### Solution idea 1
1. Read data into an array of lists. We can read the number of lines, but cannot assume the length of each list
2. Lists are analyzed through the following process:
    a. Remove the head and the tail, compare them to get the "trend"
    b. Recursively pass this list, along with the identified trend and prior head and tail
    c. If the result ever returns an opposing trend, return unsafe, otherwise safe (represented as a bool)
3. Map the processing function over the lists and count the amount of safe verdicts (true)

#### Psuedocode
```C
procedure SafetyCheck:
    begin
        input = read file
        n = read amount of lines in input
        lists = new list array of size n
        
        for each line i in input do:
            begin
                lists[i] = line.split(" ").map(parse integer)
            end
        

function AnalyzeList(list):
    begin
        head = list.first
        tail = list.last

        trend = head >= tail // true = down, false = up

        if (list.size == 1)
            begin
                return true
            end
        else 
            begin
                return AnalyzeListHelper(list, 0, list.size - 1, trend)
            end
    end

function AnalyzeListHelper(list, currHead, currTail, trend):
    begin
        if (currTail != round(list.size / 2))
            begin
                head = list.get(currHead)
                tail = list.get(currTail)

                currTrend = head >= tail

                if (currTrend != trend)
                    begin
                        return false
                    end
                else
                    begin
                        return AnalyzeListHelper(list, currHead+1, currTail-1);
                    end
            end
        else
            begin
                return true
            end
        
    end
```