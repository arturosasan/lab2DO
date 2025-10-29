module ExamplesP4 where

  signum' x = if x < 0 then -1 else
              if x == 0 then 0 else 1

  signum'' x 
           | x < 0     = -1 
           | x == 0    = 0 
           | otherwise = 1 

  
  -- right hello func:
  hello n = concat (replicate n "hello ")
  hello' n = putStrLn (concat (replicate n "hello \n"))
  

  convert :: (Char, Int) -> String
  convert (c,i) = [c] ++ show i

  -- main = convert (2,'c')
  main = convert ('c',2)


  length' [] = 0
  length' (x:t) = 1 + length' t


  add :: (Int , Int) -> Int
  add (x,y) = x + y

  cAdd :: Int -> Int -> Int
  cAdd x y = x + y


  power1 :: Int -> Int -> Int
  power1 _ 0 = 1
  power1 n t = n * power1 n (t - 1)

  power2 :: Int -> Int -> Int
  power2 _ 0 = 1
  power2 n t
         | even t = power2 (n * n) (div t 2)
         | otherwise = n * power2 (n * n) (div t 2)


  squarepow :: Int -> Int
  squarepow x = x * x

  doubleHO :: (Int -> Int) -> Int -> Int
  doubleHO f x = f (f x)

  fourthpow :: Int -> Int
  fourthpow = doubleHO squarepow

