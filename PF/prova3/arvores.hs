data ArvoreBinInt = Nulo | No Int ArvoreBinInt ArvoreBinInt
								deriving Show
								
data ArvBinGen a = Nulo1 | No1 a (ArvBinGen a)
							   (ArvBinGen a)
								deriving Eq
							   
instance Show a => Show (ArvBinGen a) where
	show Nulo1 = "_"
	show (No1 x esq dir) = "{" ++ show x ++ ":" ++ show esq ++ "|" ++ show dir ++ "}"
	
arvBin = (No1 2 (No1 7 (No1 2 Nulo1 Nulo1) 
				     (No1 6 (No1 5 Nulo1 Nulo1) 
					       (No1 11 Nulo1 Nulo1)))
			   (No1 5 Nulo1 (No1 9 (No1 4 Nulo1 Nulo1) Nulo1)))
	
arvBB = (No 7 (No 3 (No 1 Nulo Nulo)
					(No 6 (No 4 Nulo Nulo)
						  (No 7 Nulo Nulo)))
			  (No 10 Nulo (No 14 (No 13 Nulo Nulo) Nulo)))
			  

emOrdem::ArvoreBinInt -> [Int]
emOrdem Nulo = []
emOrdem (No x esq dir) = (emOrdem esq) ++ [x] ++ (emOrdem dir)


insereGen::Ord a=> a->ArvBinGen a->ArvBinGen a
insereGen x Nulo1 = (No1 x Nulo1 Nulo1)
insereGen x (No1 y esq dir)
	| x == y = No1 y esq dir
	| x < y = No1 y (insereGen x esq) dir
	| otherwise = No1 y esq (insereGen x dir)

removeGen :: Ord a => a->ArvBinGen a->ArvBinGen a
removeGen val Nulo1 = Nulo1
removeGen val (No1 v esq dir)
	|val < v = No1 v (removeGen val esq) dir
	|val > v = No1 v esq (removeGen val dir)
	|esq == Nulo1 = dir
	|dir == Nulo1 = esq
	|otherwise = juntaGen esq dir
	

juntaGen::Ord a=> ArvBinGen a->ArvBinGen a->ArvBinGen a
juntaGen esq dir = No1 menor esq novadir
	where
		menor = menorGen dir
		novadir = removeGen menor dir
		
menorGen Nulo1 = undefined
menorGen (No1 x esq dir)
	|esq == Nulo1 = x
	|otherwise = menorGen esq
	