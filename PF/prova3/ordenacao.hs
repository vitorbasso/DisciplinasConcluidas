selecao :: (Ord a) => [a]->[a]
selecao [] = []
selecao xs = [x] ++ selecao(remove x xs)
				where
					x = minimo xs
					
remove :: (Ord a)=>a->[a]->[a]
remove _ [] = []
remove a (x : xs)
	|a == x = xs
	|otherwise = x:(remove a xs)
	
minimo :: (Ord a)=>[a]->a
minimo [] = undefined
minimo [x] = x
minimo (x:xs)
	|x<=(minimo xs) = x
	|otherwise = minimo xs
	
remove_igual [] = []
remove_igual (x : xs) = x:(filter (/=x) (remove_igual xs))

insercao :: (Ord a)=>[a]->[a]
insercao [] = []
insercao (x : xs) = insereOrd x (insercao xs)

insereOrd :: (Ord a)=>a->[a]->[a]
insereOrd x [] = [x]
insereOrd x (y : ys)
	|x <= y = (x:y:ys)
	|otherwise = y:(insereOrd x ys)

insercao2 :: (Ord a)=>[a]->[a]
insercao2 = foldr insereOrd []

quickSort :: (Ord a)=>[a]->[a]
quickSort [] = []
quickSort (s : xs) = (quickSort [x | x<-xs , x<s]) ++ [s] ++ (quickSort [x | x<-xs, x>s])

bolha [] = []
bolha lista = bolhaOrd lista (length lista)

bolhaOrd lista 0 = lista
bolhaOrd lista n = bolhaOrd (troca lista) (n-1)

troca [x] = [x]
troca (x:y:zs)
	|x > y = y : troca(x:zs)
	|otherwise = x : troca (y:zs)