module Main where
import Text.ParserCombinators.Parsec
import System.Environment (getArgs)

-- | A tank is a list of ingredient coeffecients
data Tank = Tank [Ingredients]
            deriving (Eq, Read, Show)

-- | Ingredients are coeffecients for a number of
-- different ingredients.
type Ingredients = [Ingredient]
-- | An ingredient coeffecient is an integer
type Ingredient = Integer

main = do
  args <- getArgs
  let file = case args of
               (f : _) -> f
               _ -> error $ "Must provide input file name in args: " ++ show args
  f <- parseFromFile fuel file
  case f of 
    Left err -> putStrLn (show err)
    Right tanks -> mapM_ (putStrLn . show ) tanks

fuel = (try blankLines <|> empty) >> many1 tank
  
tank = do
  ingrs <- many1 coeffs
  blankLine
  try blankLines <|> empty
  return (Tank ingrs)

coeffs :: GenParser Char st [Integer]
coeffs = do
  r <- many1 coeff
  comment <|> eol
  comments <|> empty -- in-matrix comment.
  return r

coeff :: GenParser Char st Integer
coeff = do
  digits <- blanks >> many1 digit 
  blanks
  case reads digits of
    [] -> fail  $ "Can't parse (1): " ++ show digits
    [(v, _)] -> return v
    _ -> fail $ "Can't parse (2): " ++ show digits 

ignoreLines :: GenParser Char st ()
ignoreLines = skipMany whiteSpace

-- Eat all blank lines, if there are any
whiteSpace :: GenParser Char st ()
whiteSpace = (char '#' >> manyTill anyChar newline >> return ()) <|> eol

blankLines :: GenParser Char st ()
blankLines = skipMany (comment <|> (blanks >> eol) <|> eol)

comments = skipMany comment
comment = blanks >> char '#' >> skipMany (noneOf ['\n']) >> eol

blankLine = (skipMany (char ' ') >> eol) <|> eol <|> eof
eol = newline >> return ()

blanks :: GenParser Char st ()
blanks = skipMany (satisfy (`elem` [' ', '\t']))

empty :: GenParser Char st ()
empty = return ()

