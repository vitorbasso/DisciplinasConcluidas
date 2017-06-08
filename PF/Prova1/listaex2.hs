retornaSup :: Int->[Int]->Int
retornaSup x (a:b) = auxSup x (a:b) 0

auxSup :: Int->[Int]->Int->Int
auxSup x ls y
	|ls==[] = y
	|(head ls)>x = auxSup x (tail ls) (y+1)
	|otherwise = auxSup x (tail ls) y
	
retornaListaSup :: Int->[Int]->[Int]
retornaListaSup x ls = [y | y<-ls, x<y]

mult_listas :: [Int]->[Int]->[[Int]]
mult_listas ls ls2 = [[x*y | y<-ls2] | x<-ls]

distintos :: Eq a => [a] -> Bool
distintos [] = False
distintos (h:t) = dist_aux (h:t) t
	
dist_aux :: Eq a => [a] -> [a] -> Bool
dist_aux ls ls2
	|ls==[] = True
	|ls2==[] = dist_aux (tail ls) (tail (tail ls))
	|head ls == head ls2 = False
	|otherwise = dist_aux ls (tail ls2)

