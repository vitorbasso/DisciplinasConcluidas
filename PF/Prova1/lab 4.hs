insere :: Int->[Int]->[Int]
insere x [] = [x]
insere x (a:b)
	|x/=a && x>=a = ([a] ++ insere x b)
	|x/=a = ([x] ++ [a] ++ b)
	|otherwise = [a]++b

retornaSup :: Int->[Int]->[Int]
retornaSup x [] = []
retornaSup x (a:b)
	|x<a = [a]++retornaSup x b
	|otherwise = retornaSup x b
	
retornaSup1 :: Int->[Int]->[Int]
retornaSup1 x xs = [y | y<-[x..maximum xs] , y>x]

misterio :: String -> String
misterio p = [c | c<-p , (c>='a' && c<='z') || (c>='A' && c<='Z')]

paresCons :: [a] -> [(a,a)]
paresCons (a:b) = zip (a:b) b

divisores :: Int -> [Int]
divisores n = [x | x<-[1..n], n`mod`x==0]

primos :: [Int]->[Int]
primos [] = []
primos (a:b)
	|divisores a == [1,a] = [a] ++ primos b
	|otherwise = primos b
	
primos2 :: [Int]->[Int]
primos2 ls = [x | x<-ls, divisores x == [1,x]]
