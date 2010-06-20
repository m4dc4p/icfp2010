module Main where
import Text.ParserCombinators.Parsec
import qualified System.IO.Strict as S
import System.Environment (getArgs)

-- | A tank is a list of ingredient coeffecients
data Tank = Tank [Ingredients]
            deriving (Eq, Read, Show)

-- | Ingredients are coeffecients for a number of
-- different ingredients.
type Ingredients = [Ingredient]
-- | An ingredient coeffecient is an integer
type Ingredient = Integer

fuel input = do
  let parseFuel = many1 (try (blankLines >> tank) <|> tank)
  return $ parse parseFuel "unknown" input
  
tank = do
  ingrs <- many1 coeffs
  blankLine
  return (Tank ingrs)

coeffs :: GenParser Char st [Integer]
coeffs = do
  r <- many1 coeff
  eol
  return r

coeff :: GenParser Char st Integer
coeff = do
  digits <- many1 digit 
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

blankLines :: GenParse Char st ()
blankLines = 
blankLines = eol <|> eof
blankLine = eol <|> eof
eol = newline >> return ()

blanks :: GenParser Char st ()
blanks = skipMany (satisfy (`elem` [' ', '\t']))

main = do
  args <- getArgs
  desc <- case args of
            (i : _) -> S.readFile i
            _ -> error $ "Must provide input file name in args: " ++ show args
  f <- fuel desc
  putStrLn (show f)