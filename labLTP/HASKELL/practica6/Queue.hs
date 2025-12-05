module Queue (Queue, empty, enqueue, dequeue, first, isEmpty, size) where
    
    data Queue a = EmptyQueue | Item a (Queue a)
    
    empty = EmptyQueue
    
    enqueue x EmptyQueue = Item x EmptyQueue
    enqueue x (Item a q) = Item a (enqueue x q)
    
    dequeue (Item _ q) = q
    
    first (Item a _) = a
    
    isEmpty EmptyQueue = True
    isEmpty _ = False
    
    size EmptyQueue = 0
    size (Item _ q) = 1 + size q
    
    instance (Show a) => Show (Queue a) where
        show EmptyQueue = " <- "
        show (Item x y) = " <- " ++ (show x) ++ (show y)
    
    instance (Eq a) => Eq (Queue a) where
        EmptyQueue == EmptyQueue = True
        (Item x q1) == (Item y q2) = x == y && q1 == q2
        _ == _ = False

    fromList :: [a] -> Queue a
    fromList [] = empty
    fromList (x:xs) = enqueue x (fromList xs)
    
    toList :: Queue a -> [a]
    toList q
        |isEmpty q == True = []
        |otherwise = (first q):(toList (dequeue q))
--module Queue where
--data Queue a = Queue [a] [a]
--empty = Queue [] []
--enqueue y (Queue xs ys) = Queue xs (y:ys)
--dequeue (Queue (x:xs) ys) = Queue xs ys
--dequeue (Queue [] ys) = dequeue (Queue (reverse ys) [])
--first (Queue (x:xs) ys) = x
--first (Queue [] ys) = head (reverse ys)
--isEmpty (Queue [] []) = True
--isEmpty _ = False
--size (Queue a b) = length a + length b
--instance (Show a) => Show (Queue a) where
--show (Queue [] []) = " <- "
--show (Queue [] ys) = show (Queue (reverse (ys)) [])
--show (Queue (x:xs) ys) = " <- " ++ show (x) ++ show (Queue xs ys)
