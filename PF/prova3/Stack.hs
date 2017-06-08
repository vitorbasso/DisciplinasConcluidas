module Stack (Stack,
				push, pop, top, empty, isEmpty) where
				data Stack a =Stk [a]
					deriving Show
				push :: a->Stack a->Stack a
				push x (Stk xs) = Stk (x:xs)
				
				pop :: Stack a->Stack a
				pop (Stk (_:xs)) = Stk xs
				pop _ = error "Stack.pop: empty stack"
				
				top :: Stack a->a
				top (Stk (x:_)) = x
				top _ = error "Stack.top: empty stack"
				
				empty :: Stack a
				empty = Stk []
				
				isEmpty :: Stack a->Bool
				isEmpty (Stk []) = True
				isEmprty (Stk _) = False
				
				size :: Stack a->Int
				size s
					|isEmpty s = 0
					|otherwise = 1 + size (pop s)