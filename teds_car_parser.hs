
import IO

{-------- MONAD SHIT oh god how do i parsec ----------}
newtype Parser a = Parser (String -> [(a,String)])

parse (Parser p) = p

class Monad m => MonadZero m where
   zero :: m a
	
class MonadZero m => MonadPlus m where
   (<++>) :: m a -> m a -> m a


instance Monad Parser where
  return a = Parser (\cs -> [(a,cs)])
  p >>= f = Parser (\cs -> concat [parse (f a) cs' | (a,cs') <- parse p cs])

instance MonadZero Parser where
   zero   = Parser (\cs -> [])

instance MonadPlus Parser where
   p <++> q = Parser (\cs -> parse p cs ++ parse q cs)


item :: Parser Char
item = Parser (\cs -> case cs of
                      ""      -> []
                      (c:cs) -> [(c,cs)])

sat :: (Char -> Bool) -> Parser Char
sat p = do {c <- item; if p c then return c else zero}

char :: Char -> Parser Char
char c = sat (c ==)

(+++) :: Parser a -> Parser a -> Parser a
p +++ q = Parser (\cs -> case parse (p <++> q) cs of
                            []     -> []
                            (x:xs) -> [x])




chainl1 :: Parser a -> Parser (a -> a -> a) -> Parser a
p `chainl1` op = do {a <- p; rest a}
                 where
                    rest a = (do f <- op
                                 b <- p
                                 rest (f a b))
                             +++ return a

apply :: Parser a -> String -> [(a,String)]
apply p = parse (do { p})


{--------- END MONAD SHIT -----------}

--  +++ is "orelse"
--  char 't' is "exactly the character t"

-- parses 'digits' trinary digits  (start with with accumulator = 0)
parsetri :: Int -> Int -> Parser Int
parsetri accum 0 = return accum
parsetri accum digits = do char '0'
                           parsetri (accum*3+0) (digits-1)
                      +++
                        do char '1'
                           parsetri (accum*3+1) (digits-1)
                      +++
                        do char '2'
                           parsetri (accum*3+2) (digits-1)

-- natural number format
parsenat :: Parser Int
parsenat = do char '0'
              return 0
         +++
           do char '1'
              char '0'
              return 1
         +++
           do char '1'
              char '1'
              return 2
         +++
           do char '1'
              char '2'
              return 3
           +++
           do char '2'
              char '2' 
              n <- parsenat
              t <- parsetri 0 (n+2)
              return (t + (calc n))
 where
  calc :: Int -> Int
  calc 0 = 4
  calc n = (calc (n-1)) + (round (3**(fromIntegral (n+1))))

-- length number format
parselen :: Parser Int
parselen = do char '0'
              return 0
         +++
           do char '1'
              return 1
         +++
           do char '2'
              char '2' 
              n <- parselen
              t <- parsetri 0 n
              return (t + (calc n))
 where
  calc :: Int -> Int
  calc 0 = 2
  calc n = (calc (n-1)) + (round (3**(fromIntegral (n-1))))

-- parse n natural numbers
parsennats :: Int -> Parser [Int]
parsennats 0 = return []
parsennats n = do
                k <- parsenat
                rest <- parsennats (n-1)
                return (k:rest)

-- (upper pipe, class 0=main 1=aux, lower pipe)
parsechamber :: Parser ([Int], Int, [Int])
parsechamber = do n1 <- parselen
                  upper <- parsennats n1
                  clas <- parsenat
                  n2 <- parselen
                  lower <- parsennats n2
                  return (upper, clas, lower)


parsecar :: [Char] -> IO ()
parsecar initialstr = do let [(total_chambers, rest)] = apply parselen initialstr
                         print ((show total_chambers) ++ " chambers in this car.")
                         parsechambers total_chambers rest
                         return ()
   where
      parsechambers :: Int -> String -> IO ()
      parsechambers 0 l = print ("done parsing chambers. remaining string is: " ++ l)
      parsechambers n l = do let [(chmbr, r1)] = apply parsechamber l
                             print ("chamber " ++ (show n) ++ ": " ++ (show chmbr) ++ "")
                             parsechambers (n-1) r1

-- so you can just  echo "122000010" | thisprog
main :: IO ()
main = do
        dat <- hGetContents stdin
        parsecar dat


