module ExercisesP4 where
    import Data.Char

    nextchar :: Char -> Char
    nextchar c = chr ((ord c) + 1)

    fact :: Int -> Int
    fact 0 = 1
    fact n = n * fact (n - 1)
    
    numCbetw2 :: Char -> Char -> Int
    numCbetw2 c1 c2
        | c1 == c2 = 0
        | otherwise = abs (ord c1 - ord c2) - 1

    addRange :: Int -> Int -> Int
    addRange x y
        | x > y = addRange y x
        | x == y = x
        | otherwise = x + addRange (x+1) y

    max' :: Int -> Int -> Int
    max' x y 
        | x > y = x
        | otherwise = y

    leapyear :: Int -> Bool
    leapyear x
        | mod x 400 == 0 = True 
        | mod x 100 == 0 = False
        | mod x 4 == 0 = True
        | otherwise = False

    daysAMonth :: Int -> Int -> Int
    daysAMonth m y
        | m == 2 && leapyear y = 29
        | m == 2 = 28
        | m == 4 || m == 6 || m == 9 || m == 11 = 30
        | otherwise = 31
 
    reminder :: Int -> Int -> Int
    reminder a b
        | a < b = a
        | a == b = 0
        | otherwise = reminder (a-b) b

    sumFact :: Int -> Int
    sumFact n
        | n == 1 = 1
        | otherwise = fact(n) + sumFact(n-1)
