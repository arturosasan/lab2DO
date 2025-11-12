module ExercicesP5 where

-- EJERCICIO 1

decBin :: Int -> [Int]
decBin x
    | x < 2     = [x]
    | otherwise = x `mod` 2 : decBin (x `div` 2)

-- EJERCICIO 2

binDec :: [Int] -> Int
binDec []       = 0
binDec (x:xs)   = x + 2 * binDec xs

-- EJERCICIO 3

divisors :: Int -> [Int]
divisors n = [x | x <- [1..n], n `mod` x == 0]

-- EJERCICIO 4

member :: Int -> [Int] -> Bool
member _ [] = False
member x (y:ys) = (x == y) || member x ys

-- EJERCICIO 5



-- EJERCICIO 6

selectEven :: [Int] -> [Int]
selectEven = filter even

-- EJERCICIO 7 

selectEvenPos :: [Int] -> [Int]
selectEvenPos [] = []
selectEvenPos (x:xs) = x : selectEvenPos (drop 1 xs)

-- EJERCICIO 8

ins :: Int -> [Int] -> [Int]
ins x [] = [x]
ins x (y:ys)
    | x <= y = x : y : ys
    | otherwise = y : ins x ys

iSort :: [Int] -> [Int]
iSort [] = []
iSort (x:xs) = ins x (iSort xs)

-- EJERCICIO 9

doubleAll :: [Int] -> [Int]
doubleAll = map (*2)

-- EJERCICIO 10

map' :: (a -> b) -> [a] -> [b]
map' f [] = []
map' f (x:xs) = f x : map f xs

-- EJERCICIO 11

filter' :: (a -> Bool) -> [a] -> [a]
filter' p [] = []
filter' p (x:xs) = if p x then x:filter p xs else filter p xs