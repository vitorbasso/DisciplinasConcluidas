dobro :: Int->Int
dobro x = x*2

quadruplica :: Int->Int
quadruplica x = dobro (dobro x)

distPontos (x1 , y1) (x2 , y2) = sqrt ( x'*x' + y'*y' )
	where
		x' = x2 - x1
		y' = y2 - y1

hipotenusa x y = sqrt ( x*x + y*y )

type Data = (Int, Int, Int)

bissexto::Int->Bool
bissexto x
	| (mod x 400 == 0) = True
	| (mod x 4 == 0) && (mod x 100 /=0) = True
	| otherwise = False
	
valida::Data->Bool
valida (d,m,a)
	| d >= 1 && d <= 31 && (m==1 || m==3 || m==5 || 
	m==7 || m==8 || m==10 || m==12) = True
	| d >= 1 && d <= 30 && (m==4 || m ==6 || m==9 || m==11) = True
	| d >= 1 && d <= 28 && m==2 && not (bissexto a) = True
	| d >= 1 && d <= 29 && m==2 && (bissexto a) = True
	| otherwise = False