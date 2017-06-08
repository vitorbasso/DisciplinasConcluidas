import Char hiding (isDigit )
import Prelude hiding (elem , snd)

type ShopItem = (String, Int)

minAndMax :: Int->Int->(Int,Int)
minAndMax x y
	|x<=y = (x,y)
	|otherwise = (y,x)

fibStep :: (Int,Int)->(Int,Int)
fibStep (x,y) = (y , x+y)

fibPair :: Int->(Int,Int)
fibPair x
	|x==0 = (0,1)
	|otherwise = fibStep (fibPair (x-1))
	
fastFib :: Int->Int
fastFib = snd . fibPair

maxOccurs :: Int->Int->(Int,Int)
maxOccurs x y
	|x==y = (x,2)
	|otherwise = ((max x y), 1)

repeat1 :: Int->Int->Int->Int->Int
repeat1 x y z w
	|x==w && y==w && z==w = 3
	|(x==w && z==w) || (x==w && y==w) || (y==w && z==w) = 2
	|otherwise = 1

maxThreeOccurs :: Int->Int->Int->(Int,Int)
maxThreeOccurs x y z = (m , repeat1 x y z m)
	where
		m = max x (max y z)
		
middle :: Int->Int->Int->Int
middle x y z
	|(x>=y && x<=z) || (x>=z && x<=y) = x
	|(y>=x && y<=z) || (y>=z && y<=x) = y
	|otherwise = z

orderTriple :: (Int, Int, Int) -> (Int, Int, Int)
orderTriple (x, y, z) = (min x (min y z), middle x y z,max x (max y z))

xAxis :: (Integer,Integer)->(Float,Bool)
xAxis (x,y)
	|x==0 = (0,False)
	|y==0 = (0,True)
	|otherwise = (fromInteger (-y) / fromInteger(x) , True)
	
addPairs :: [(Int,Int)]->[Int]
addPairs pairList = [m+n | (m,n)<-pairList , m<n]

isDigit :: Char->Bool
isDigit c = (c<='9' && c>='0')

digits :: String->String
digits st = [ch | ch<-st, isDigit ch]

allEven :: [Int]->Bool
allEven ls = ls==[x | x<-ls, even x]

allOdd :: [Int]->Bool
allOdd ls = ls==[x | x<-ls , odd x]

doubleAll :: [Int]->[Int]
doubleAll ls = [x*2 | x<-ls]

capitalize :: String->String
capitalize st = [toUpper c | c<-st]

capitalizeLetters :: String->String
capitalizeLetters st = [toUpper c | c<-st, not (isDigit c)]

divisors :: Int->[Int]
divisors x = [y | y<-[1..x] , mod x y == 0]

prime :: Int->Bool
prime x
	|x>0 && divisors x == [1,x] = True
	|otherwise = False
	
matches :: Int->[Int]->[Int]
matches x ls = [n | n<-ls , n==x]

elem :: Int->[Int]->Bool
elem x ls = (matches x ls) /= []


--	Library Database Exemple

type Person = String
type Book = String

type Database = [(Person, Book)]

exampleBase :: Database
exampleBase = [("Alice" , "Tintin") , ("Anna", "Little Woman"),
					("Alice" , "Asterix") , ("Rory", "Tintin")]
				
books :: Database->Person->[Book]
books db p = [book | (person,book)<-db , person==p]

borrowers :: Database->Book->[Person]
borrowers db b = [person | (person,book)<-db , b==book]

borrowed :: Person->Book->Bool
borrowed p b = [database | database<-exampleBase , database==(p,b)]/=[]

numBorrowed :: Database->Person->Int
numBorrowed db p = length ([book | (person,book)<-db, person==p])

makeLoan :: Database->Person->Book->Database
makeLoan db p b = db++[(p,b)]

returnLoan :: Database->Person->Book->Database
returnLoan db p b = [database | database<-db , database/=(p,b)]

snd :: (a,b)->b
snd (a,b) = b

sing :: a->[a]
sing a = [a]

id :: a->a
id a = a

shift :: ((a,b),c)->(a,(b,c))
shift ((a,b),c) = (a,(b,c))

capitalize1 :: String->String
capitalize1 ls = [toUpper c | c<-ls]

romanDigit :: Char->String
romanDigit c
	|(read [c] :: Int) == 5 = "V"
	|(read [c] :: Int) <=3 = replicate (read [c] :: Int) 'I'
	|(read [c] :: Int) == 4 = "IV"
	|otherwise = "V" ++ (replicate ((read [c] :: Int) - 5)) 'I'
	
onThreeLines :: String->String->String->String
onThreeLines ls1 ls2 ls3 = ls1 ++ "\n" ++ ls2 ++ "\n" ++ ls3

onSeparateLines :: [String]->String
onSeparateLines ls = concat [s++"\n" | s<-ls]

duplicate :: String->Int->String
duplicate str x
	|x<0 = []
	|otherwise = concat (replicate x str)
	
pushRight :: String->String
pushRight ls = lineLenght ++ ls
	where
		lineLenght = "            "

fib :: Int->Int
fib x
	|x<0 = 0
	|x==0 = 0
	|x==1 = 1
	|otherwise = (fib (x-2) + fib (x-1))

fibTable :: Int->String
fibTable x
	|x<0 = error "Cant fibonacci less then zero."
	|x==0 = "n\t\tfib n\n0\t\t    0\n"
	|otherwise = ((fibTable (x-1)) ++ (show x) ++ "\t\t    " ++ (show (fib x)) ++ "\n")