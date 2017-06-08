import Prelude hiding (elem)

type Item = (Int, String, Float)

itemBanca :: Int->Item
itemBanca x
	|x==1 = (123, "Epoca", 10.90)
	|x==2 = (322, "Veja", 4.90)
	|x==3 = (452, "Info", 8.60)
	|x==4 = (113, "Exame", 7.20)
	|otherwise = undefined

preco (a,b,c) = c

maiorRevista :: Int->Item
maiorRevista x
	|x==1 = itemBanca 1
	|otherwise = if (preco (itemBanca x)) > preco (maiorRevista (x-1)) then
															itemBanca x
														else
															maiorRevista (x-1)
										
calcSeq :: Float->Int->Float
calcSeq x n
	|n==0 = 0
	|otherwise = (sqrt (x + (calcSeq x (n-1))))
	
serie :: (Float,Int)->Float
serie (x,n) = serieAux (x,n) 0

serieAux :: (Float,Int)->Float->Float
serieAux (x,n) result
	|n==0 = result
	|even n = serieAux (x,n-1) (result + (x/n1))
	|otherwise = serieAux (x,(n-1)) (result + (n1/x))
	where
		n1 = fromIntegral n
		
--tipo Relogio (hora, minuto, segundo)
type Relogio = (Int, Int, Float)

hora :: Relogio->Int
hora (h,m,s) = h

segundo :: Relogio->Float
segundo (h,m,s) = h1*3600 + m1*60 + s
	where
		h1 = fromIntegral h
		m1 = fromIntegral m

valido :: Relogio->Bool
valido (h,m,s) = h>=0 && h<=23 && m>=0 && m<=59 && s>=0.0 && s<=59.9

remove :: Int->[Int]->[Int]
remove x (h:b)
	|x==h = b
	|otherwise = [h] ++ remove x b

minAux :: [Int]->Int->Int
minAux [] x = x
minAux (h:b) x
	|h<x = (minAux b h)
	|otherwise = (minAux b x)
	
minimo :: [Int]->Int
minimo (h:b) = minAux b h

ordena :: [Int]->[Int]
ordena [] = []
ordena (h:b) = minimo (h:b) : (ordena (remove (minimo (h:b)) (h:b)))

misterio :: Int->Int
misterio (x+1) = 2 + (misterio x)
misterio _ = 0

g x y = if x then (head y) else "Hello"

elem _ [] = False
elem x (y:ys) = x == y || elem x ys