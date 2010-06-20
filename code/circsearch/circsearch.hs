import System
import Data.List
import Data.Ord
import Data.Maybe
import Data.Array.IArray

data Trit = T0 | T1 | T2 deriving (Eq, Ord, Enum, Bounded, Ix, Show)
data Ori = Straight | Cross deriving (Eq, Ord, Enum, Bounded, Show)
type Stage = [Ori] -- must be nonempty
type Circ = [Stage] -- reads from left to right as one would expect
type TT = (Trit,Trit)
type Table = Array TT TT -- truth table

tritString :: String -> [Trit]
tritString s =
  map (\c -> case c of
               '0' -> T0
               'O' -> T0 -- tolerate typos
               '1' -> T1
               '2' -> T2
               _ -> error "Bad character in trit string"
    ) s

allTT :: [TT]
allTT = [ (t1,t2) | t1 <- [minBound..maxBound], t2 <- [minBound..maxBound] ]

ttGate :: Table
ttGate = array (minBound,maxBound) [ ((T0,T0), (T0,T2)),
                                     ((T0,T1), (T2,T2)),
                                     ((T0,T2), (T1,T2)),
                                     ((T1,T0), (T1,T2)),
                                     ((T1,T1), (T0,T0)),
                                     ((T1,T2), (T2,T1)),
                                     ((T2,T0), (T2,T2)),
                                     ((T2,T1), (T1,T1)),
                                     ((T2,T2), (T0,T2)) ]

-- The outer list is sorted by increasing number of gates
candStages :: [[Stage]]
candStages = [ ofLen ngates | ngates <- [1 .. 4] ]
  where ofLen 0 = [[]]
        ofLen n = [ ori : tail | ori <- [Straight,Cross], tail <- ofLen (n-1) ]

evalOri :: Ori -> TT -> TT
evalOri ori tt =
  let (l,r) = ttGate ! tt in
  case ori of
    Straight -> (l,r)
    Cross    -> (r,l)

evalStage :: Stage -> TT -> TT
evalStage stage tt =
  foldl (flip evalOri) tt stage

evalCirc :: Circ -> TT -> TT
evalCirc circ tt =
  foldl (flip evalStage) tt circ

makeTable :: (TT -> TT) -> Table
makeTable f =
  array (minBound,maxBound) [ (tt, f tt) | tt <- allTT ]

{-
  Heuristic: in left truth table of all stages assembled so far, maximize the
  number of occurrences of the least represented trit (between 1 and 3).
  Example: in
        1 2 1
        0 1 2
        1 1 1
  there's only one way to get 0, so its heuristic value is 1.
-}
heuristic :: Circ -> Int
heuristic circ =
  let (n0,n1,n2) = foldr (\tt (n0,n1,n2) ->
          case evalCirc circ tt of
            (T0, _) -> (n0+1, n1, n2)
            (T1, _) -> (n0, n1+1, n2)
            (T2, _) -> (n0, n1, n2+1)
        ) (0,0,0) allTT in
  min n0 (min n1 n2)

{-
  Takes the circuit produced so far and the remaining desired output sequence.
  Returns the circuit that satisfies the whole thing.
-}
search :: Circ -> [Trit] -> Maybe Circ
search circ [] = Just circ
search circ (trit:tail) =
  -- Find the possible trit pairs that produce the desired output trit at the
  -- end of what we have so far.
  let tts = filter (\tt -> fst (evalCirc circ tt) == trit) allTT in
  -- Find candidates for trit pairs that produce such a trit pair from 0
  let candidates = map (\stages -> filter (\stage ->
          evalStage stage (T0,T0) `elem` tts
        ) stages) candStages in
  -- Sort candidates by number of gates, then by heuristic value of composing
  -- it with the rest of the circuit.
  let sortedCandidates = map (sortBy (comparing (\stage -> 
          - heuristic (stage:circ)
        ))) candidates in
  let flatCandidates = concat sortedCandidates in
  -- For each candidate, Tack it onto the circuit and try calling ourselves
  -- recursively with new circuit and desired input.
  let choices = catMaybes $ map (\stage ->
          search (stage:circ) tail
        ) flatCandidates in
  listToMaybe choices

dumpSegment :: Int -> Stage -> String
dumpSegment i stage =
  let (last_ori, oris) = (last stage, init stage) in
  let (i', str) = foldl (\(i',str') ori ->
          (i' + 1, str'++"F"++show i')
        ) (i,"") oris in
  str ++ "B"++show i'

toOutput :: Circ -> String
toOutput circ =
  let circ' = reverse circ in
  output 0 circ
    where
      output i [] = " EOF "
      output i (stage:tail) =
        let (last_ori, oris) = (last stage, init stage) in
        let (i', str) = foldl (\(i',str') ori ->
                (i' + 1, str'++"F"++show i')
              ) (i,"") oris in
         output (i'+1) tail ++ str ++ "B"++show i'++" "

main = do
  prog <- System.getProgName
  args <- System.getArgs
  case args of
    [ str ] ->
      case search [] (tritString str) of
        Just circ -> do
          putStrLn (show circ)
          putStrLn (toOutput circ)
        Nothing -> putStrLn "Unsolvable"
    _ ->
      putStr ("Usage: "++ prog ++" TRIT_STRING\n")

-- vim: sw=2 sts=2 et
