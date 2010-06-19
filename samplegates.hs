import Data.Array.IArray

-- Borrowed from Haskell-Cafe
selections :: [a] -> [(a,[a])]
selections []     = []
selections (x:xs) = (x,xs) : [ (y,x:ys) | (y,ys) <- selections xs ]

-- Borrowed from Haskell-Cafe
permutations :: [a] -> [[a]]
permutations xs =
  [ y : zs
  | (y,ys) <- selections xs
  , zs     <- permutations ys
  ]


--data Side = L | R deriving (Eq, Ord, Show, Read, Enum, Bounded)
--type Dst = (Int, Side)
--type Gate = (Dst, Dst)
--type Circ = (Dst, [Gate])
--
--ofLength :: Int -> [Circ]
--ofLength n =
--  let toDst n = (n `div` 2, if n `mod` 2 == 0 then L else R) in
--  let toGates [] = []
--      toGates (a:b:tail) = (toDst a, toDst b) : toGates tail in
--  let perms = permutations [0 .. 2*n] in
--  map (\(ext : int) -> (toDst ext, toGates int)) perms

-- TODO: rule out disconnected solutions

--samples = [ ((0, side), []) | side <- [L,R] ]

--type SGate = (Dst, Dst, Dst, Dst)

type Dst = Int
type Circ = [Dst]
data InOut = In | Out deriving (Ix, Enum, Bounded, Show, Eq, Ord)
type FullCirc = [ ((Int,InOut), Int) ]

ofLength :: Int -> [Circ]
ofLength n = permutations [0 .. 2*n]

bidirectional :: Circ -> FullCirc
bidirectional circ =
  let arr :: Array (Int,InOut) Int
      arr = array ((0,In), (length circ - 1,Out)) $
        [ if io == Out then ((i,Out),dst) else ((dst,In),i)
          | io <- [In,Out], (i,dst) <- zip [0..] circ ] in
  assocs arr

-- pretty :: FullCirc -> String TODO

main = do
  mapM_ (\circ ->
      putStr (show circ ++ "\n")
    ) (ofLength 0)

-- vim: sw=2 sts=2 et
