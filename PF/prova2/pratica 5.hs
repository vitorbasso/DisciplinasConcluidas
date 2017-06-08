import Array

mat::Array (Int, Int) Int
mat = array ((1,1),(2,3)) [((1,1),4),((1,2),0),((1,3),8),
									((2,1),7),((2,2),1),((2,3),7)]
									
vet1::Array Int Char
vet1 = array (1,7) [(1,'h'),(2,'a'),(3,'s'),(4,'k'),(5,'e'),(6,'l'),(7,'l')]

vet2::Array Int Float
vet2 = array (1,7) [(1,1.0),(2,2.0),(3,3.0),(4,3.0),(5,4.0),(6,4.0),(7,3.0)]



conta [] = 0
conta (a:as) = 1 + conta as

vet l = array (1,conta l) [(i, l!!(i-1))| i<-[1.. conta l]]

b::Array Int Float
b= array (1,3) [(1,4.0),(2,6.0),(3,9.0)]

serie::Array Int Float -> Float
serie b = foldr1 (+) [ ((b!a)^2) / (fromIntegral a) |
									a <- range (bounds b)]

fat 0 = 1
fat n = n * fat (n-1)

preencheVet :: Array Int Float -> Array Int Float
preencheVet b = array (1, conta (elems b)) [(i,((b!i) ** (fromIntegral i)) / (fromIntegral (fat i))) | i <-range (bounds b)]				

somar :: Array Int Float -> Float
somar b = 1.0 + (foldr1 (+) [(b!i) | i<-range (bounds b)])

transposta :: Array (Int,Int) Int -> Array (Int,Int) Int
transposta a = array ((li, ui), (lj,uj)) [((i,j) , a!(j,i)) | i<-[li..lj],
							j<-[ui..uj]]
						where ((li, ui) , (lj, uj)) = bounds a