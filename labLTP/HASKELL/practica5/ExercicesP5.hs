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


filter' :: (a -> Bool) -> [a] -> [a]
filter' p [] = []
filter' p (x:xs) = if p x then x:filter p xs else filter p xs

-- EJERCICIO 11

type Person = String
type Book = String
type Database = [(Person, Book)]


exampleBase :: Database
exampleBase = [("Alicia", "El nombre de la rosa"), 
               ("Juan", "La hija del canibal"), 
               ("Pepe", "Odesa"), 
               ("Alicia", "La ciudad de las bestias")]

obtain :: Database -> Person -> [Book]
obtain dBase thisPerson = [book | (person,book) <- dBase, person == thisPerson]

borrow :: Database -> Book -> Person -> Database
borrow dBase book person = (person, book) : dBase

return' :: Database -> (Person,Book) -> Database
return' ((p, b):dBase) (person, book)
    | p == person && b == book = dBase
    | otherwise = (p, b):return' dBase (person, book)

-- EJERCICIO 12

data Tree a = Leaf a | Branch (Tree a) (Tree a) deriving Show


symmetric :: Tree a -> Tree a
symmetric (Leaf x) = Leaf x
symmetric (Branch left right) = Branch (symmetric right) (symmetric left)

-- EJERCICIO 13

listToTree :: [a] -> Tree a
listToTree [x] = Leaf x
listToTree (x:tail) = Branch (Leaf x) (listToTree tail)

treeToList :: Tree a -> [a]
treeToList (Leaf a) = [a]
treeToList (Branch x y) = (treeToList x) ++ (treeToList y)

-- EJERCICIO 14

data BinTreeInt = Void | Node Int BinTreeInt BinTreeInt deriving Show

insTree :: Int -> BinTreeInt -> BinTreeInt
insTree x (Void) = (Node x Void Void)
insTree x (Node n left right)
    | x > n = (Node n left (insTree x right))
    | otherwise = (Node n (insTree x left) right)

-- EJERCICIO 15

creaTree :: [Int] -> BinTreeInt
creaTree [] = Void
creaTree (head:tail) = insTree head (creaTree tail)

-- EJERCICIO 16

treeElem :: Int -> BinTreeInt -> Bool
treeElem x (Node n Void Void)
    |x == n = True
    |otherwise = False
treeElem x (Node n left right)
    |x < n = treeElem x left
    |x > n = treeElem x right
    |otherwise = True