module Set (Set, empty, single, union, member) 
	where
		import Data.List (nub) -- Remover repetidos
		
		data Set a = S [a]
			deriving Show
		
		empty :: Set a
		empty = S []
		
		single :: a->Set a
		single x = S [x]
		
		member :: Eq a=>a->Set a->Bool
		member x (S xs) = x`elem`xs
		
		union :: Eq a=> Set a->Set a->Set a
		union (S xs) (S ys) = S (nub (xs ++ ys))
		