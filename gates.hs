type Gate = (Int,Int) -> (Int,Int)


printSig [[x0,x1,x2],[y0,y1,y2],[z0,z1,z2]]=
 (show $ fst x0) ++ " " ++ (show $ fst x1) ++ " " ++ (show $ fst x2) ++ "\n" ++
 (show $ fst y0) ++ " " ++ (show $ fst y1) ++ " " ++ (show $ fst y2) ++ "\n" ++
 (show $ fst z0) ++ " " ++ (show $ fst z1) ++ " " ++ (show $ fst z2) ++ "\n" ++
 "---\n" ++
 (show $ snd x0) ++ " " ++ (show $ snd x1) ++ " " ++ (show $ snd x2) ++ "\n" ++
 (show $ snd y0) ++ " " ++ (show $ snd y1) ++ " " ++ (show $ snd y2) ++ "\n" ++
 (show $ snd z0) ++ " " ++ (show $ snd z1) ++ " " ++ (show $ snd z2) ++ "\n"

gateSig :: Gate -> [[(Int,Int)]]
gateSig g = [[g (l,r) | r <- [0,1,2]] | l <- [0,1,2]]

gate :: Gate
gate (l,r) = ((l-r+3) `mod` 3, (l*r+2) `mod` 3)

{-
(0,2) -> (0,0)
(2,2) -> (0,1) (2,0)
(1,2) -> (0,2) (1,0)
(0,0) -> (1,1) (2,2)
(1,1) -> (2,1)
(2,1) -> (1,2)

Find gate set such that left still gives us the digit we want

-}

par :: Gate -> Gate -> Gate
par g1 g2 = g2 . g1

twist :: Gate -> Gate -> Gate
twist g1 g2 = g2 . cross . g1

cross (a,b) = (b,a)

interp :: [Bool] -> Gate
interp [] = gate
interp (True:xs) = gate `twist` (interp xs)
interp (False:xs) = gate `par` (interp xs)

gates = [interp [], interp [False], interp [True]]


--[g1 `par` g0 |g0 <- gates, g1 <- gates]

{-

      0/2
True  2/2
False 1/2


-}


{-
0 2 1
1 0 2
2 1 0
---
2 2 2
2 0 1
2 1 0

False
1 0 2
2 0 1
0 0 0
---
2 0 1
1 2 1
0 0 2

True
2 0 1
1 0 2
0 0 0
---
2 0 1
1 2 1
0 0 2

-}

