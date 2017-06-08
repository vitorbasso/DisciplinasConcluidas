import Char
type Pessoa = (String,Int,Float,Char)

pess :: Int->Pessoa
pess x
	|x==1 = ("Rosa", 27, 1.66,'F')
	|x==2 = ("João", 26, 1.85,'M')
	|x==3 = ("Maria", 67, 1.55,'F')
	|x==4 = ("Jose", 48, 1.78, 'M')
	|x==5 = ("Paulo", 24, 1.93, 'M')
	|x==6 = ("Clara", 38, 1.70,'F')
	|x==7 = ("Bob", 12, 1.45, 'M')
	|x==8 = ("Rosana", 31, 1.58,'F')
	|x==9 = ("Daniel", 75, 1.74, 'M')
	|x==10 = ("Jocileide", 21, 1.69,'F')
	|otherwise = ("Acabou!",0, 0.0, 'x')

maior :: Int->Int->Bool
maior x y
	|x>=y = True
	|otherwise = False
	
idade :: Pessoa->Int
idade (_,i,_,_) = i
	
maiorIdade :: Int->Int
maiorIdade x
	|x==1 = 1
	|otherwise = if (maior (idade (pess x)) (idade (pess (x-1)))) then x
									else maiorIdade (x-1)
									
mediaIdade :: Int->Float
mediaIdade x
	|x==1 = fromIntegral (idade (pess 1))
	|otherwise = media [(idade (pess y)) | y<-[1..x]]
									
media :: [Int]->Float
media [] = 0
media ls = (fromIntegral (sum ls))/(fromIntegral (length ls))

sexo :: Pessoa->Char
sexo (_,_,_,s) = s

numeroMasc :: Int->Int
numeroMasc x = sum [1 | y<-[1..x],sexo (pess y) == 'M', idade (pess y) >=25]

soma :: Int->Int->Int
soma x y = somaAux x y x
	
somaAux :: Int->Int->Int->Int
somaAux x y z
	|z>=y = x
	|otherwise = somaAux (x+y) (y-1) z
	
fatAux :: Int->Int->Int
fatAux x y
	|x==1 = y
	|otherwise = (fatAux (x-1) (y*x))
	
fat :: Int->Int
fat x = fatAux x 1

seq1 :: Float->Int->Float
seq1 x n
	|n==1 = sqrt x
	|otherwise = sqrt (x + (seq1 x (n-1)))
	
pot :: Int->Int->Int
pot x y = potAux x y 1
	
potAux :: Int->Int->Int->Int
potAux x y z
	|y==0 = z
	|otherwise = potAux x (y-1) (z*x)

somaImparAux :: Int->Int->Int
somaImparAux x y
	|x==0 = y
	|odd x = (somaImparAux (x-1) (y+x))
	|otherwise = (somaImparAux (x-1) y)

somaImpar :: Int->Int
somaImpar x = somaImparAux x 0

serieAux :: (Float,Int)->Float->Float
serieAux (x, n) resultado
	|n==0 = resultado
	|odd n = (serieAux (x, (n-1)) ((n1/x) + resultado))
	|otherwise = (serieAux (x, (n-1)) ((x/n1) + resultado))
	where
		n1 = fromIntegral n

serie :: (Float, Int)->Float
serie ls = serieAux ls 0.0