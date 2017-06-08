import Char
type Pessoa = (String, Int, Float, Char)

pess :: Int->Pessoa
pess x
	|x==1 = ("Rosa", 27, 1.66, 'F')
	|x==2 = ("João", 26, 1.85, 'M')
	|x==3 = ("Maria", 67, 1.55, 'F')
	|x==4 = ("Jose", 48, 1.78, 'M')
	|x==5 = ("Paulo", 24, 1.93, 'M')
	|x==6 = ("Clara", 38, 1.70, 'F')
	|x==7 = ("Bob", 12, 1.45, 'M')
	|x==8 = ("Rosana", 31, 1.58, 'F')
	|x==9 = ("Daniel", 75, 1.74, 'M')
	|x==10 = ("Jocileide", 21, 1.69, 'F')
	|otherwise = ("Acabou!", 0, 0.0, 'x')

idade :: Pessoa->Int
idade (_,x,_,_) = x

maior :: Pessoa->Pessoa->Pessoa
maior x y 
	|x1>=y1 = x
	|otherwise = y
		where
			x1 = idade x
			y1 = idade y

maiorIdade :: Int->Pessoa
maiorIdade x
	|x==1 = pess 1
	|otherwise = maior (pess x) (maiorIdade (x-1))

somaIdade :: Int->Int
somaIdade x
	|x==1 = idade (pess 1)
	|otherwise = idade (pess x) + somaIdade (x-1)

mediaIdade :: Int->Int
mediaIdade x
	|x <= 10 = (somaIdade x)`div`x
	|x > 10 = (somaIdade x)`div`10
	
sexo :: Pessoa->Char
sexo (_,_,_,s) = s
	
homemIdade :: Int->Int
homemIdade x
	|x==1 && (sexo (pess 1))=='F' =0
	|x==1 && (sexo (pess 1))=='M' && (idade (pess 1))>=25 = 1
	|x<=10 && (sexo (pess x))=='M' && (idade (pess x))>=25 = 1 + homemIdade (x-1)
	|otherwise = homemIdade(x-1)
	
condicaoExistencia :: Int->Int->Int->Bool
condicaoExistencia x y z
	|(x<y+z && y<z+x && z<y+x) &&
	(x>abs (y-z) && y>abs (x-z) && z>abs (x-y)) = True
	|otherwise = False

classificaTriangulo :: Int->Int->Int->(String,Int)
classificaTriangulo x y z 
	|(condicaoExistencia x y z) && x==y && y==z = ("Equilatero", x+y+z)
	|(condicaoExistencia x y z) && ((x==y && y/=z) || (x==z && z/=y) 
	|| (z==y && y/=x)) = ("Isoceles", x+y+z)
	|not (condicaoExistencia x y z) = ("Esse triangulo nao atinge a condicao de existencia", 0)
	|otherwise = ("Escaleno", x+y+z)

--Perguntar se essa função é de calda
somaImpar :: Int->Int
somaImpar x
	|x==1 = 1
	|x`mod`2==0 = somaImpar(x-1)
	|otherwise = x+somaImpar(x-1)
	
somaI :: Int->Int
somaI x = somaIAux x 0

somaIAux :: Int->Int->Int
somaIAux x y
	|x==1 = y+1
	|even x = somaIAux (x-1) y
	|otherwise = somaIAux (x-1) (y+x)

calc :: (Float,Int)->Float->Float
calc (x,n) y
	|n==1 = y + ((fromIntegral (n))/x)
	|odd n = calc (x,n-1) y+(fromIntegral(n))/x
	|even n = calc (x,n-1) y+(x/(fromIntegral(n)))
	
serie :: (Float,Int)->Float
serie (x,n) = calc (x,n) 0

calcSerie :: (Integer, Integer)->Float
calcSerie (x,y)
	|y<=0 = 0
	|y==1 = 1/fromInteger (x)
	|odd y = fromInteger (y)/fromInteger (x) + calcSerie (x,y1)
	|otherwise = fromInteger (x)/fromInteger (y) + calcSerie (x,y1)
		where 
			y1 = y-1