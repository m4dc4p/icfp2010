-- ghc -o samplegates -O samplegates.hs

import qualified System
import Data.List
import Data.Array.IArray

concatWith :: [a] -> [[a]] -> [a]
concatWith _ [] = []
concatWith _ [l] = l
concatWith d (h:t) = h ++ d ++ concatWith d t

type Dst = Int
-- A Circuit is a list of where outputs go together with the index of the
-- half-gate that outputs to the world. The distinguished world input is
-- uniquely determined by this.
type Circ = ([Dst], Int)
data InOut = In | Out deriving (Ix, Enum, Bounded, Show, Eq, Ord)
type Bidi = (Array (Int,InOut) Int, Int)

ofLength :: Int -> [Circ]
ofLength n =
  [ (perm, ext) | perm <- permutations [0 .. 2*n-1], ext <- [0 .. 2*n-1] ]

-- TODO: rule out disconnected solutions

bidirectional :: Circ -> Bidi
bidirectional (perm, ext) =
  let arr = array ((0,In), (length perm - 1,Out)) $
        [ if io == Out then ((i,Out),dst) else ((dst,In),i)
          | io <- [In,Out], (i,dst) <- zip [0..] perm ] in
  (arr, ext)

pretty :: Bidi -> String
pretty (arr, ext) =
  let coord i =
        show (i `div` 2) ++ if i `mod` 2 == 0 then "L" else "R" in
  let label Out i = if i == ext then "X" else coord (arr ! (i,Out))
      label In i = if arr ! (i,In) == ext then "X" else coord (arr ! (i,In)) in
  let (_, (bound,_)) = bounds arr in
  coord (arr ! (ext,Out)) ++ ": " ++ concatWith ", "
    [ label In i ++ label In (i+1) ++ "0#" ++ label Out i ++ label Out (i+1)
      | i <- [0,2 .. bound-1] ] ++
    ": " ++ coord ext

main = do
  prog <- System.getProgName
  args <- System.getArgs
  case args of
    [ len ] ->
      mapM_ (\i ->
          mapM_ (\circ ->
              putStr (pretty (bidirectional circ) ++ "\n")
            ) (ofLength i)
        ) [1 .. read len]
    _ ->
      putStr ("Usage: "++ prog ++" MAX_NUMBER_OF_GATES\n")

-- vim: sw=2 sts=2 et
