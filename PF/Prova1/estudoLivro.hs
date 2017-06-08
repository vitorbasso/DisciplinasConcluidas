import Char

nor :: Bool->Bool->Bool
nor x y = (x || y) && not (x && y)

nand :: Bool->Bool->Bool
nand x y = not (x && y)

threeEqual :: Int->Int->Int->Bool
threeEqual x y z = (x==y) && (y==z)

mystery :: Int->Int->Int->Bool
mystery m n p = not((m==n) && (n==p))

threeDifferent :: Int->Int->Int->Bool
threeDifferent x y z = x/=y && x/=z && z/=y

fourEqual :: Int->Int->Int->Int->Bool
fourEqual x y z w = (threeEqual x y z) && x==w

max1 :: Int->Int->Int
max1 x y
	|x>=y = x
	|otherwise = y

maxThree :: Int->Int->Int->Int
maxThree x y z
	|x>=y && x>=z = x
	|y>=z = y
	|otherwise = z
	
min1 :: Int->Int->Int
min1 x y
	|x<=y = x
	|otherwise = y
	
minThree :: Int->Int->Int->Int
minThree x y z
	|x<=y && x<=z = x
	|y<=z = y
	|otherwise = z
	
upperLetter :: Char->Char
upperLetter x = chr ((ord x) - (ord 'a' - ord 'A'))

charInt :: Char->Int
charInt x 
	|ord x>=ord '0' && ord x<=ord '9' = ord x - 48
	|otherwise = 0
	
averageThree :: Integer->Integer->Integer->Float
averageThree x y z = fromInteger ((x+y+z)) / 3.0

howMany :: Integer->Integer->Integer->Integer
howMany x y z
	|x1>avg && y1>avg && z1>avg = 3
	|(x1>avg && y1>avg) || (x1>avg && z1>avg) || (y1>avg && z1>avg) = 2
	|x1<=avg && y1<=avg && z1<=avg = 0
	|otherwise = 1
	where
		x1 = fromInteger x
		y1 = fromInteger y
		z1 = fromInteger z
		avg = averageThree x y z

root :: Integer->Integer->Integer->[Float]
root a b c
	|(b2-delta)>0 = [(b1 - square)/(a2) , (b1 + square)/(a2)]
	|(b2-delta)==0 = [(b1)/(a2),(b1)/(a2)]
	|otherwise = [0.0 , 0.0]
	where
		b1 = fromInteger (-b)
		a2 = fromInteger (2*a)
		b2 = fromInteger (b^2)
		delta = (4.0*(fromInteger a)*(fromInteger c))
		square = sqrt (b2 - delta)
		
numberNDroots :: Integer->Integer->Integer->Int
numberNDroots a b c
	|(b2-delta)>0 = 2
	|(b2-delta)==0 = 1
	|otherwise = 0
	where
		b2 = fromInteger (b^2)
		delta = (4.0 * (fromInteger a) * (fromInteger c))
		
middleNumber :: Int->Int->Int->Int
middleNumber x y z
	|between y x z = x
	|between x y z = y
	|otherwise = z

between :: Int->Int->Int->Bool
between x y z = x<=y && z>=y

maxFour :: Int->Int->Int->Int->Int
maxFour x y z w
	|(maxThree x y z) >= w = maxThree x y z
	|otherwise = w
	
maxFour2 :: Int->Int->Int->Int->Int
maxFour2 x y z w
	|(max1 x y)>=(max1 z w) = max1 x y
	|otherwise = max1 z w

maxFour3 :: Int->Int->Int->Int->Int
maxFour3 x y z w = max (maxThree x y z) w

weekAscendingOrder :: Int->Int->Int->Bool
weekAscendingOrder x y z = x<=y && y<=z

howManyEqual :: Int->Int->Int->Int
howManyEqual x y z
	|threeEqual x y z = 3
	|threeDifferent x y z = 0
	|otherwise = 2
	
equal :: Int->Int->Bool
equal x y = x==y

fourNotEqual :: Int->Int->Int->Int->Bool
fourNotEqual x y z w = (x/=y && x/=z && x/=w && y/=z && y/=w && z/=w)

wNotEqual :: Int->Int->Int->Int->Bool
wNotEqual x y z w = (w/=x && w/=y && w/=z)

howManyOfFourEqual :: Int->Int->Int->Int->Int
howManyOfFourEqual x y z w
	|(equal x y) && (equal y z) = 4
	|wNotEqual x y z w = howManyEqual x y z
	|wNotEqual x y w z = howManyEqual x y w
	|wNotEqual x z w y = howManyEqual x z w
	|otherwise = howManyEqual y z w
	
recursion :: Int->Int
recursion x
	|x==0 = 1
	|x>0 = x*recursion (x-1) 
	|otherwise = error "recursion only defined on natural numbers"
	
range :: Integer->Integer->Integer
range x y
	|x<y = x * (range (x+1) y)
	|x==y = y
	|otherwise = 1

fac :: Integer->Integer
fac x
	|x>=0 = range 1 x
	|otherwise = 0
	
power2 :: Int->Int
power2 n
	|n==0 = 1
	|otherwise = 2 * (power2 (n-1))
	
sumFacs :: Integer->Integer
sumFacs x
	|x==0 = 1
	|x>0 = (fac x) + (sumFacs (x-1))
	|otherwise = error "Input not a natural Integer."
	
sumFun :: (Integer->Integer)->Integer->Integer
sumFun f n
	|n>0 = (f n) + sumFun f (n-1)
	|n==0 = 1
	|otherwise = error "SumWrong"
	
piecesCut :: Integer->Integer
piecesCut x
	|x==0 = 1
	|x<0 = error "Natural Int required."
	|otherwise = piecesCut (x-1) + x

	
piecesCut2Aux :: Integer->Integer
piecesCut2Aux x = x

piecesCut2 :: Integer->Integer
piecesCut2 x
	|x>0 = sumFun piecesCut2Aux x
	|x==0 = 1
	|otherwise = error "Input must be a natural Integer."
	
mult :: Int->Int->Int
mult x y
	|y==0 = 0
	|y>0 = x + (mult x (y-1))
	|otherwise = (-x) + (mult x (y+1))
	
squareAux :: Int->Int->Int
squareAux x y
	|y*y<=x = y
	|otherwise = squareAux x (y-1)
	
squareRoot :: Int->Int
squareRoot x
	|x==0 = 0
	|x<0 = error "Input must be a natural Int"
	|otherwise = squareAux x x
	
f :: Int->Int
f x
	|even x = 0
	|otherwise = 1
	
testeF :: Int->Bool
testeF x
	|x==0 = True
	|x>0 && (f x)==1 = testeF (x-1)
	|otherwise = False

fib :: Int->Int
fib x
	|x==0 = 0
	|x==1 = 1
	|otherwise = fib (x-2) + fib (x-1)

remainder :: Int->Int->Int
remainder x y
	|x>=y = remainder (x-y) y
	|otherwise = x
	
divide :: Int->Int->Int
divide x y
	|(abs x)>=y && x>0 = 1 + (divide (x-y) y)
	|x*y < 0 = error "que"
	|otherwise = 0
	
mfcAux :: Int->Int->Int->Int
mfcAux x y z
	|remainder x z == 0 && remainder y z == 0 = z
	|otherwise = mfcAux x y (z-1)
	
mfc :: Int->Int->Int
mfc x y
	|(remainder x y)==0 || (remainder y x)==0 = mfcAux x y (min x y)
	|otherwise = mfcAux x y ((min x y)`div`2)