module Main where

	main = do
				x <- leInt
				print(x*3)
				putStr "Obrigado"

	ecoLine = do
				putStr "Digite algo: "
				input <- getLine
				input2 <- getLine
				putStrLn input
				putStr input2
	
	leInt :: IO(Int)
	leInt = do
				putStr "Digite um valor inteiro: "
				readLn