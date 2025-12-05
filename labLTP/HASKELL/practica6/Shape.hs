module Shape where
    type Side = Float
    type Apothem = Float
    type Radius = Float

    data Pentagon = Pentagon Side Apothem
    data Circle = Circle Radius
    
    type Height = Float
    type Volume = Float  
    type Surface = Float

    class (Eq a, Show a) => Shape a where
        
        perimeter :: a -> Float
        area :: a -> Float
        
        volumePrism :: (Shape a) => a -> Height -> Volume
        volumePrism base height = (area base) * height
        
        surfacePrism :: (Shape a) => a -> Height -> Surface
        surfacePrism base height = 2 * (area base) + height * (perimeter base)

    instance Shape Pentagon where    
        perimeter (Pentagon s a) = 5 * s
        area (Pentagon s a) = 5 * ((s * a) / 2)

    instance Shape Circle where
        perimeter (Circle r) = 2 * pi * r
        area (Circle r) = pi * r * r
    
    instance Eq Pentagon where
        (Pentagon s1 a1) == (Pentagon s2 a2) = s1 == s2 && a1 == a2

    instance Show Pentagon where
    
        show (Pentagon s a) = "Pentagon's side = " ++ show s ++ " and apothem = " ++
            show a

    instance Eq Circle where
        (Circle r1) == (Circle r2) = r1 == r2
    
    instance Show Circle where
        show (Circle r) = "Circle's radius = " ++ show r

--module Shape where
--type Side = Float
--type Apothem = Float
--type Radius = Float
--data Shape = Pentagon Side Apothem |
--Circle Radius
--deriving (Eq, Show)
--perimeter :: Shape -> Float
--perimeter (Pentagon s a) = 5 * s
--perimeter (Circle r) = 2 * pi * r
